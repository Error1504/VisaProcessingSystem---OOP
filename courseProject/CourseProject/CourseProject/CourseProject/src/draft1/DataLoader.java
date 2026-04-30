package draft1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DataLoader implements DataManager{
    private ArrayList<Applicant> applicants;
    private ArrayList<Application> applications;
    private ArrayList<Officer> officers;
	@Override
	public ArrayList<Applicant> loadApplicants(){
		ArrayList<Applicant> applicants = new ArrayList<>();
		try{
			BufferedReader in = new BufferedReader(new FileReader("applicants.csv"));
			String s = null;
			while((s=in.readLine()) != null) {
				String[] parts = s.split("\t");
				String name = parts[0];
				String passport_number = parts[1];
				String nationality = parts[2];
				LocalDate birthdate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("M/d/yyyy"));
				applicants.add(new Applicant(name, passport_number,nationality, birthdate));
			}
		}
		catch  (FileNotFoundException e){
			System.out.println("File is not found " + e);
		} catch (IOException e) {
			System.out.println("IO Error" + e);;
		}
		return applicants;
	}

	@Override
	public ArrayList<Application> loadApplications() {
		ArrayList<Application> applications = new ArrayList<>();
		try{
			BufferedReader in = new BufferedReader(new FileReader("applications.csv"));
			String s = null;
			while((s=in.readLine()) != null) {
				String[] parts = s.split("\t");
				String visa_type = parts[0];
				int visa_number = Integer.parseInt(parts[1]);
				String passport_number = parts[2];
				LocalDate birthdate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("M/d/yyyy"));
				Status status = Status.valueOf(parts[4]);
				Officer officer = null;
				if(!parts[5].equals("N/A")) {
					int officer_id = Integer.parseInt(parts[5]);
					for(Officer o: officers){
						if(o.getId() == officer_id) {
							officer = o;
							break;
						}
					}
				}
				Applicant applicant = null;
				for(Applicant a: applicants) {
					if(a.getPassportNo().equals(passport_number)) {
						applicant = a;
						break;
					}
				}
				if (visa_type.equals("TOURIST")) {
					int duration = Integer.parseInt(parts[6]);
					boolean hotel  = Boolean.parseBoolean(parts[7]);
					applications.add(new TouristVisa(visa_number, birthdate, applicant, status,officer, duration, hotel));
				}
				else if(visa_type.equals("STUDENT")) {
					String university = parts[6];
					int studyYears = Integer.parseInt(parts[7]);
					boolean scholarship = Boolean.parseBoolean(parts[8]);
					applications.add(new StudentVisa(visa_number, applicant, status, birthdate, officer, university, studyYears, scholarship));
				}
				else if(visa_type.equals("WORK")) {
					String company = parts[6];
					double salary = Double.parseDouble(parts[7]);
					int contractYears = Integer.parseInt(parts[8]);
					applications.add(new WorkVisa(visa_number, birthdate, applicant, status, officer, company, salary, contractYears));
				}
			}
		}
		catch  (FileNotFoundException e){
			System.out.println("File is not found " + e);
		} catch (IOException e) {
			System.out.println("IO Error" + e);;
		}
		return applications;
	}

	@Override
	public ArrayList<Officer> loadOfficers() {
		ArrayList<Officer> officers = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("officers.csv"));
			String s = null;
			while((s = in.readLine()) != null){
				String[] parts = s.split("\t");
				int id = Integer.parseInt(parts[0]);
				String name = parts[1];
				officers.add(new Officer(id,name));			
			}
		}catch (FileNotFoundException e){
			System.out.println("File not found " + e);
		}catch (IOException e) {
            System.out.println("IO Error " + e);
		}
		return officers;
		
	}

	@Override
	public void saveApplicants() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("applicants.csv"));
			for(Applicant a: applicants) {
				out.write(a.getName() + "," + a.getPassportNo() + "," + a.getNationality() + "," + a.getBirthdate());
				out.newLine();
			}
		out.close();
		}
		catch(IOException e) {
			System.out.println("IO Error " + e);
		}
	}

	@Override
	public void saveApplications() {
		try {
			 BufferedWriter out = new BufferedWriter(new FileWriter("applications.csv"));
			 for(Application a: applications) {
				String line = "";
				if(a instanceof TouristVisa) {
					TouristVisa t = (TouristVisa) a;  //downcasting karle
					line = "TOURIST, " + a.getAppliactionNO() + "," + a.getApplicant().getPassportNo() + ", " + a.getSubmissionDate() + "," + a.getStatus() + "," + (a.getOfficer() == null ? "N/A" : a.getOfficer().getId()) + "," + t.getDurationDays() + ", " + t.isHotelReservation();
				}
				else if(a instanceof StudentVisa) {
					StudentVisa s = (StudentVisa) a;
					line = "STUDENT, " + a.getAppliactionNO() + "," + a.getApplicant().getPassportNo() + ", " + a.getSubmissionDate() + "," + a.getStatus() + "," + (a.getOfficer() == null ? "N/A" : a.getOfficer().getId()) + "," + s.getUniversityName() + ", " + s.getStudyDurationYears() + "," + s.isScholarship();

				}
				else if(a instanceof WorkVisa) {
					WorkVisa w = (WorkVisa) a;
					line = "WORK, " + a.getAppliactionNO() + "," + a.getApplicant().getPassportNo() + ", " + a.getSubmissionDate() + "," + a.getStatus() + "," + (a.getOfficer() == null ? "N/A" : a.getOfficer().getId()) + "," + w.getCompanyName() + "," + w.getSalary() + "," + w.getContractYears();

				}
				out.write(line);
				out.newLine();
			 
			 
			 }
			 out.close();
		}
		catch(IOException e) {
			System.out.println("IOException " + e);
		}
	}

	@Override
	public void saveOfficers() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("officers.csv"));
			for(Officer o: officers) {
				out.write(o.getId() + "," + o.getName());
				out.newLine();
				
			}
			out.close();
		}catch (IOException e) {
			System.out.println("IOException " + e);
		}
	}

}
