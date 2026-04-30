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
//		officer.setId(officer_counter);
		officers.add(officer);
		System.out.println("Officer " + officer.getName() + " is successfully added to the system.");

	}

	public void addApplication(Application application) {
		application_counter++;
//		application.setAppliactionNO(application_counter);
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
		for (Application a : applications) {
			System.out.println(a);
		}
	}

	public void removeApplication(int appNo) {
		for (Application a : applications) {
			if (appNo == a.getApplicationNO())
				applications.remove(a);
			break;
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
		for (Application a : applications) {
			if (a.getOfficer().getId() == officerID)
				System.out.println(a);
		}
	}

	public void displayApplicationTabular(Application a) {

	}

	public void displayApplicationBeforeDate(LocalDate date) {
		for (Application a : applications) {
			if (a.getSubmissionDate().isBefore(date)) {
				System.out.println(a);
			}
		}

	}

	public void calculateTotalRevenue() {
		double totalRevenue = 0;
		for (Application a : applications) {
			totalRevenue += a.calculateProcessingFee();
			System.out.printf("Total revenue is: QAR %.1f\n", totalRevenue);
		}
	}

	public void displayPendingApplications() {
		for (Application a : applications) {
			if (a.getStatus() == (Status.PENDING)) {
				System.out.println(a);
			}
		}
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