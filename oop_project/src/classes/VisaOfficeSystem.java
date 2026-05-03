package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Core system class managing all visa processing operations.
 * 
 * @author Hassan Abdus Salam
 * @author Abdul Hadi
 */
public class VisaOfficeSystem {
	/**
	 * Scanner class object for user inputs
	 */
	Scanner in = new Scanner(System.in);
	/**
	 * In-memory list of all visa applications
	 */
	ArrayList<Application> applications;
	/**
	 * In-memory list of all officers
	 */
	ArrayList<Officer> officers;
	/**
	 * In-memory list of all applicants
	 */
	ArrayList<Applicant> applicants;
	/**
	 * a counter to generate application numbers
	 */
	private static int application_counter = 1000;
	/**
	 * a counter to generate officers' Id number
	 */
	private static int officer_counter = 2000;
	/**
	 * an instance of DataManager class to load and store and data.
	 */
	private DataManager dm = new DataLoader();

	/**
	 * Constructs the system upon calling it loads all the data in specified array
	 * lists
	 */
	public VisaOfficeSystem() {
		DataLoader dataLoader = (DataLoader) dm;

		applicants = dm.loadApplicants();
		dataLoader.setApplicants(applicants);

		officers = dm.loadOfficers();
		dataLoader.setOfficers(officers);

		applications = dm.loadApplications();

		for (Application app : applications) {
			if (app.getApplicationNO() > application_counter) {
				application_counter = app.getApplicationNO();
			}
		}
	}

	/**
	 * Adds new officer to the system
	 * 
	 * @param officer
	 */
	public void addVisaOfficer(Officer officer) {
		// officer_counter++;
		officer.setId((officer_counter + officers.size()) + 1);
		officers.add(officer);
		System.out.println("Officer " + officer.getName() + " is successfully added to the system.");

	}

	/**
	 * adds new visa application to the system
	 * 
	 * @param application
	 */
	public void addApplication(Application application) {
		application_counter++;
		application.setApplicationNO(application_counter);
		applications.add(application);
	}

	/**
	 * Adds new applicant to the system
	 * 
	 * @param applicant
	 */
	public void addApplicant(Applicant applicant) {
		applicants.add(applicant);

	}

	/**
	 * Prints all officers
	 */
	public void displayAllOfficers() {
		// System.out.println("--- Available Officers ---");
		for (Officer o : officers) {
			System.out.println(o);
		}
	}

	/**
	 * Assigns specific officer to the given application
	 * 
	 * @param application
	 * @param officer
	 */
	public void assignOfficertoApplicaton(Application application, Officer officer) {
		application.setOfficer(officer);
		/*
		 * System.out.println("Application " + application.getApplicationNO() +
		 * " has been assigned to officer " + officer.toString() + " successfully");
		 */
	}

	/**
	 * Finds application by application number
	 * 
	 * @param no
	 * @return the application of that application number
	 */
	public Application findApplicationByApplicationNo(int no) {
		for (Application a : applications) {
			if (no == a.getApplicationNO())
				return a;
		}
		return null;
	}

	/**
	 * finds the officer of a given ID number
	 * 
	 * @param id
	 * @return officer of that ID number
	 */
	public Officer findOfficerbyID(int id) {
		for (Officer o : officers) {
			if (id == o.getId())
				return o;
		}
		return null;

	}

	/**
	 * Finds application by the applicant's passport number
	 * 
	 * @param passportNo
	 * @return the application of that that applicant
	 */
	public Applicant findApplicantByPassportNo(String passportNo) {
		for (Applicant a : applicants) {
			if (passportNo.equalsIgnoreCase(a.getPassportNo()))
				return a;
		}
		return null;
	}

	/**
	 * displays all application in tabular form
	 */
	public void viewAllApplications() {
		displayApplicationsTabular(applications);
	}

	/**
	 * removes an application of a given application number
	 * 
	 * @param applicationNo
	 */
	public void removeApplication(int applicationNo) {
		Application application = findApplicationByApplicationNo(applicationNo);
		if (application != null) {
			applications.remove(application);
			System.out.println("Application " + applicationNo + " removed successfully");
		} else {
			System.out.println("Application " + applicationNo + " not found.");
		}
	}

	/**
	 * Updates the status of the given application. The status cannot be changed if
	 * the application is already APPROVED or REJECTED, and the new status cannot be
	 * PENDING.
	 * 
	 * @param application
	 * @param status
	 */
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

	/**
	 * Displays all applications assigned to the officer with the given ID
	 * 
	 * @param officerID
	 */
	public void displayAssignedApplications(int officerID) {
		ArrayList<Application> result = new ArrayList<>();
		for (Application a : applications) {
			if (a.getOfficer().getId() == officerID)
				result.add(a);

		}
		displayApplicationsTabular(result);

	}

	/**
	 * Formats and prints a single application as one table row. Intended to be
	 * called after a header line has been printed.
	 * 
	 * @param a
	 */
	public void displayApplicationTabular(Application a) {
		String applicantName = a.getApplicant().getName();
		String passportNo = a.getApplicant().getPassportNo();
		String nationality = a.getApplicant().getNationality();
		String officerName = (a.getOfficer() == null) ? "N/A" : a.getOfficer().getName();

		System.out.printf("%18d %15s %15s %15s %10s %15s %12s %12s%n", a.getApplicationNO(), applicantName, passportNo,
				nationality, a.getStatus(), a.getSubmissionDate(), a.getVisaType(), officerName);

	}

	/**
	 * displays Applications in tabular form, takes an arraylist as an argument.
	 * 
	 * @param app
	 */
	public void displayApplicationsTabular(ArrayList<Application> app) {
		System.out.printf("%-18s %-15s %-15s %-15s %-10s %-15s %-12s %-12s%n", "Application No", "Applicant Name",
				"Passport No.", "Nationality", "Status", "Submission Date", "Visa Type", "Officer");
		for (Application a : app) {
			displayApplicationTabular(a);
		}
	}

	/**
	 * Prints all applications submitted before the specified date
	 * 
	 * @param date
	 */
	public void displayApplicationsBeforeDate(LocalDate date) {
		ArrayList<Application> result = new ArrayList<>();
		for (Application a : applications) {
			if (a.getSubmissionDate().isBefore(date))
				result.add(a);
		}
		displayApplicationsTabular(result);

	}

	/**
	 * Calculates and prints the total revenue from all application fees
	 */
	public void calculateTotalRevenue() {
		double totalRevenue = 0;
		for (Application a : applications) {
			totalRevenue += a.calculateProcessingFee();
		}
		System.out.printf("Total revenue is: QAR %.1f\n", totalRevenue);
	}

	/**
	 * displays all the applications that are PENDING
	 */
	public void displayPendingApplications() {
		for (Application a : applications) {
			if (a.getStatus() == (Status.PENDING)) {
				System.out.println(a);
			}
		}
	}

	/**
	 * Finds and prints the applicant who has submitted the most applications
	 * 
	 */
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

	/**
	 * Saves all current data (applicants, applications, officers) to csv files
	 */
	public void save() {
		((DataLoader) dm).setApplicants(applicants);
		((DataLoader) dm).setApplications(applications);
		((DataLoader) dm).setOfficers(officers);
		dm.saveApplicants();
		dm.saveOfficers();
		dm.saveApplications();
		System.out.println("Data saved successfully.");

	}
}
