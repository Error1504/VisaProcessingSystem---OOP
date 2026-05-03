package classes;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * DataLoader class responsible for loading and saving data 
 * related to Applicants, Applications, and Officers from CSV files.
 * 
 * This class implements the DataManager interface and provides
 * functionality to read from and write to external storage.
 * 
 * @author Hashir
 * @version 1.0
 */
public class DataLoader implements DataManager {

    /**
     * List of applicants in the system
     */
    private ArrayList<Applicant> applicants;

    /**
     * List of applications in the system
     */
    private ArrayList<Application> applications;

    /**
     * List of officers in the system
     */
    private ArrayList<Officer> officers;

    /**
     * Sets the applicants list
     * @param applicants list of applicants
     */
    public void setApplicants(ArrayList<Applicant> applicants) {
        this.applicants = applicants;
    }

    /**
     * Sets the applications list
     * @param applications list of applications
     */
    public void setApplications(ArrayList<Application> applications) {
        this.applications = applications;
    }

    /**
     * Sets the officers list
     * @param officers list of officers
     */
    public void setOfficers(ArrayList<Officer> officers) {
        this.officers = officers;
    }

    /**
     * Loads applicant data from "applicants.csv" file.
     * 
     * @return list of Applicant objects
     */
    @Override
    public ArrayList<Applicant> loadApplicants() {
        ArrayList<Applicant> applicants = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("applicants.csv"));
            String s;
            while ((s = in.readLine()) != null) {
                String[] parts = s.split(",");
                String name = parts[0];
                String passport_number = parts[1];
                String nationality = parts[2];
                LocalDate birthdate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                applicants.add(new Applicant(name, passport_number, nationality, birthdate));
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found " + e);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
        return applicants;
    }

    /**
     * Loads application data from "applications.csv" file
     * and maps them to their respective Applicant and Officer.
     * 
     * @return list of Application objects
     */
    @Override
    public ArrayList<Application> loadApplications() {
        ArrayList<Application> applications = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("applications.csv"));
            String s;
            while ((s = in.readLine()) != null) {
                String[] parts = s.split(",");

                String visa_type = parts[0];
                int visa_number = Integer.parseInt(parts[1].trim());
                String passport_number = parts[2];
                LocalDate birthdate = LocalDate.parse(parts[3].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Status status = Status.valueOf(parts[4]);

                Officer officer = null;
                if (!parts[5].equals("N/A")) {
                    int officer_id = Integer.parseInt(parts[5]);
                    for (Officer o : officers) {
                        if (o.getId() == officer_id) {
                            officer = o;
                            break;
                        }
                    }
                }

                Applicant applicant = null;
                for (Applicant a : applicants) {
                    if (a.getPassportNo().equals(passport_number)) {
                        applicant = a;
                        break;
                    }
                }

                if (visa_type.equals("TOURIST")) {
                    int duration = Integer.parseInt(parts[6]);
                    boolean hotel = Boolean.parseBoolean(parts[7]);
                    applications.add(new TouristVisa(visa_number, birthdate, applicant, status, officer, duration, hotel));

                } else if (visa_type.equals("STUDENT")) {
                    String university = parts[6];
                    int studyYears = Integer.parseInt(parts[7].trim());
                    boolean scholarship = Boolean.parseBoolean(parts[8].trim());
                    applications.add(new StudentVisa(visa_number, applicant, status, birthdate, officer, university, studyYears, scholarship));

                } else if (visa_type.equals("WORK")) {
                    String company = parts[6];
                    double salary = Double.parseDouble(parts[7]);
                    int contractYears = Integer.parseInt(parts[8]);
                    applications.add(new WorkVisa(visa_number, birthdate, applicant, status, officer, company, salary, contractYears));
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found " + e);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
        return applications;
    }

    /**
     * Loads officer data from "officers.csv" file.
     * 
     * @return list of Officer objects
     */
    @Override
    public ArrayList<Officer> loadOfficers() {
        ArrayList<Officer> officers = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("officers.csv"));
            String s;
            while ((s = in.readLine()) != null) {
                String[] parts = s.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                officers.add(new Officer(id, name));
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
        return officers;
    }

    /**
     * Saves applicant data into "applicants.csv".
     */
    @Override
    public void saveApplicants() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("applicants.csv"));
            for (Applicant a : applicants) {
                out.write(a.getName() + "," + a.getPassportNo() + "," + a.getNationality() + "," + a.getBirthdate());
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }

    /**
     * Saves application data into "applications.csv".
     */
    @Override
    public void saveApplications() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("applications.csv"));
            for (Application a : applications) {
                String line = "";

                if (a instanceof TouristVisa) {
                    TouristVisa t = (TouristVisa) a;
                    line = "TOURIST," + a.getApplicationNO() + "," + a.getApplicant().getPassportNo() + "," +
                            a.getSubmissionDate() + "," + a.getStatus() + "," +
                            (a.getOfficer() == null ? "N/A" : a.getOfficer().getId()) + "," +
                            t.getDurationDays() + "," + t.isHotelReservation();

                } else if (a instanceof StudentVisa) {
                    StudentVisa s = (StudentVisa) a;
                    line = "STUDENT," + a.getApplicationNO() + "," + a.getApplicant().getPassportNo() + "," +
                            a.getSubmissionDate() + "," + a.getStatus() + "," +
                            (a.getOfficer() == null ? "N/A" : a.getOfficer().getId()) + "," +
                            s.getUniversityName() + "," + s.getStudyDurationYears() + "," + s.isScholarship();

                } else if (a instanceof WorkVisa) {
                    WorkVisa w = (WorkVisa) a;
                    line = "WORK," + a.getApplicationNO() + "," + a.getApplicant().getPassportNo() + "," +
                            a.getSubmissionDate() + "," + a.getStatus() + "," +
                            (a.getOfficer() == null ? "N/A" : a.getOfficer().getId()) + "," +
                            w.getCompanyName() + "," + w.getSalary() + "," + w.getContractYears();
                }

                out.write(line);
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }

    /**
     * Saves officer data into "officers.csv".
     */
    @Override
    public void saveOfficers() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("officers.csv"));
            for (Officer o : officers) {
                out.write(o.getId() + "," + o.getName());
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }

    /**
     * @return list of applicants
     */
    public ArrayList<Applicant> getApplicants() {
        return applicants;
    }

    /**
     * @return list of applications
     */
    public ArrayList<Application> getApplications() {
        return applications;
    }

    /**
     * @return list of officers
     */
    public ArrayList<Officer> getOfficers() {
        return officers;
    }
}