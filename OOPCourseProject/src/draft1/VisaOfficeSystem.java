package draft1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class VisaOfficeSystem {
	Scanner in = new Scanner(System.in);
	ArrayList<Application> applications;
	ArrayList<Officer> officers;
	ArrayList<Applicant> applicants;
	private static int application_counter = 1000;
	private static int officer_counter = 2000;
	private DataManager dm = new DataLoader();

	public VisaOfficeSystem() {
		applicants = dm.loadApplicants();
		officers = dm.loadOfficers();
		applications = dm.loadApplications();
	}

	public void addVisaOfficer(Officer officer) {
		officer_counter++;
		officer.setId(officer_counter);
		officers.add(officer);
		System.out.println("Officer " + officer.getName() + " is successfully added to the system.");

	}

	public void addApplication(Application application) {
		// application_counter++;
	//	application.setApplicationNO(application_counter);
		applications.add(application);
	}

	public void addApplicant(Applicant applicant) {
		applicants.add(applicant);

	}

	public void displayAllOfficers() {
		System.out.println("--- Available Officers ---");
		for (Officer o : officers) {
			System.out.println(o);
		}
	}

	public void assignOfficertoApplicaton(Application application, Officer officer) {
		application.setOfficer(officer);
		System.out.println("Application " + application.getApplicationNO() + " has been assigned to officer "
				+ officer.toString() + " successfully");
	}

	public Application findApplicationByApplicationNo(int no) {
		for (Application a : applications) {
			if (no == a.getApplicationNO())
				return a;
		}
		return null;
	}

	public Officer findOfficerbyID(int id) {
		for (Officer o : officers) {
			if (id == o.getId())
				return o;
		}
		return null;

	}

	public Applicant findApplicantByPassportNo(String passportNo) {
		for (Applicant a : applicants) {
			if (passportNo.equalsIgnoreCase(a.getPassportNo()))
				return a;
		}
		return null;
	}

	public void viewAllApplications() {
		displayApplicationsTabular(applications);
	}

	public void removeApplication(int applicationNo) {
		Application application = findApplicationByApplicationNo(applicationNo);
        if (application != null) {
            applications.remove(application);
            System.out.println("Application " + applicationNo + " removed successfully");
        } else {
            System.out.println("Application " + applicationNo + " not found.");
        }
    }

	

	public void processApplication(Application application, Status status) {

		if (status == Status.PENDING) {
			System.out.println("The application status cannot be updated");
			return;
		}
		if (application.getStatus() != Status.PENDING) {
			System.out.println("Application is already processed");
			return;

		}
		application.setStatus(status);
	}

	public void displayAssignedApplications(int officerID) {
		ArrayList<Application> result = new ArrayList<>();
		for (Application a : applications) {
			if (a.getOfficer()!= null && a.getOfficer().getId() == officerID)
				result.add(a);

			}
				displayApplicationsTabular(result);
		
	}

	public void displayApplicationTabular(Application a) {
		String applicantName = a.getApplicant().getName();
        String passportNo    =  a.getApplicant().getPassportNo();
        String nationality   =  a.getApplicant().getNationality();
        String officerName   =  a.getOfficer().getName();

        System.out.printf("%18d %15s %15s %15s %10s %15s %12s %12s%n",
                a.getApplicationNO(),
                applicantName,
                passportNo,
                nationality,
                a.getStatus(),
                a.getSubmissionDate(),
                a.getVisaType(),
                officerName);

	}
	public void displayApplicationsTabular(ArrayList<Application> app){
		System.out.printf("%-18s %-15s %-15s %-15s %-10s %-15s %-12s %-12s%n",
                "Application No", "Applicant Name", "Passport No.", "Nationality",
                "Status", "Submission Date", "Visa Type", "Officer");
        for (Application a : app) {
            displayApplicationTabular(a);
        }
	}

	public void displayApplicationsBeforeDate(LocalDate date) {
		ArrayList<Application> result = new ArrayList<>();
		for (Application a : applications) {
			if (a.getSubmissionDate().isBefore(date)) 
				result.add(a);
			}
			displayApplicationsTabular(result);

	}

	public void calculateTotalRevenue() {
		double totalRevenue = 0;
		for (Application a : applications) {
			totalRevenue += a.calculateProcessingFee();
		}
		System.out.printf("Total revenue is: QAR %.1f\n", totalRevenue);
	}

	public void displayPendingApplications() {
    ArrayList<Application> pending = new ArrayList<>();
    for (Application a : applications) {
        if (a.getStatus() == Status.PENDING) {
            pending.add(a);
        }
    }
    displayApplicationsTabular(pending);
}

	public void applicantWithMostApplications() {
		int mostApp = 0;
		Applicant highestApplications = null;
		for (Applicant a : applicants) {
			int count = 0;
			for (Application app : applications) {
				if (app.getApplicant().getPassportNo().equalsIgnoreCase(a.getPassportNo())) {
					count++;
				}
			}
			if (count > mostApp) {
				mostApp = count;
				highestApplications = a;
			}

		}

		if (highestApplications != null && mostApp > 0) {
			System.out.println("Applicant with most applications is " + highestApplications.getName() + "["
					+ highestApplications.getPassportNo() + "], " + "with " + mostApp + " application(s)");
		}
	}

	public void save() {
		dm.saveApplicants();
		dm.saveOfficers();
		dm.saveApplications();
		System.out.println("Data saved successfully.");

	}
}
