package classes;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Tester class serves as the entry point of the Visa Office System.
 * 
 * It provides a console-based menu interface for three types of users: Admin,
 * Officer, and Applicant.
 * 
 * The class allows users to interact with the system by performing operations
 * such as managing officers, processing applications, and creating visa
 * requests.
 * 
 * @author Ayham
 * @version 1.0
 */
public class Tester {

	/**
	 * Main method that starts the program and displays the main menu.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		VisaOfficeSystem system = new VisaOfficeSystem();

		int choice;
		do {
			printMainMenu();
			choice = in.nextInt();

			if (choice == 1)
				adminMenu(system, in);
			else if (choice == 2)
				officerMenu(system, in);
			else if (choice == 3)
				applicantMenu(system, in);
			else if (choice == 0)
				system.save();
			else
				System.out.println("Invalid option.");

		} while (choice != 0);

		in.close();
	}

	/**
	 * Loads sample data into the system (for testing purposes).
	 * 
	 * @param system the visa office system
	 */
	public static void loadData(VisaOfficeSystem system) {
		Applicant a1 = new Applicant("Ahmed Ali", "P0123456", "Sudan", LocalDate.of(2000, 1, 1));
		Applicant a2 = new Applicant("Baha Borei", "P0222333", "Egypt", LocalDate.of(2001, 2, 2));
		Applicant a3 = new Applicant("Cat Charles", "P0001234", "United Kingdom", LocalDate.of(2002, 3, 3));
		Applicant a4 = new Applicant("Dana Diab", "P9988770", "Syria", LocalDate.of(2004, 4, 4));

		system.addApplicant(a1);
		system.addApplicant(a2);
		system.addApplicant(a3);
		system.addApplicant(a4);

		system.addVisaOfficer(new Officer(2001, "Omar Khaled"));
		system.addVisaOfficer(new Officer(2002, "Othman Ali"));
		system.addVisaOfficer(new Officer(2003, "Osama Jamal"));

		system.addApplication(new TouristVisa(1001, LocalDate.of(2026, 2, 16), a1, Status.PENDING,
				system.findOfficerbyID(2001), 14, true));

		system.addApplication(new StudentVisa(1002, a2, Status.PENDING, LocalDate.of(2026, 3, 17),
				system.findOfficerbyID(2002), "Qatar University", 4, false));

		system.addApplication(new WorkVisa(1003, LocalDate.of(2026, 2, 1), a3, Status.PENDING,
				system.findOfficerbyID(2003), "QatarEnergy", 15000, 3));

		system.addApplication(
				new StudentVisa(1004, a4, Status.PENDING, LocalDate.of(2026, 3, 15), null, "QU", 2, true));
	}

	/**
	 * Displays the main menu options.
	 */
	public static void printMainMenu() {
		System.out.println("====== Welcome to Qatar Visa Processing System ======");
		System.out.println("1. Admin");
		System.out.println("2. Officer");
		System.out.println("3. Applicant");
		System.out.println("0. Save & Exit");
		System.out.println();
		System.out.println("Enter menu option:");
	}

	/**
	 * Displays the admin menu options.
	 */
	public static void printAdminMenu() {
		System.out.println("--------------------------------");
		System.out.println("          1. Add Visa Officer");
		System.out.println("          2. Display All Officers");
		System.out.println("          3. Assign Officer to Application");
		System.out.println("          4. View All Applications");
		System.out.println("          5. Remove Application");
		System.out.println("          6. Generate Reports");
		System.out.println("          0. Back to Main Menu");
		System.out.println();
		System.out.println("Enter menu option:");
	}

	/**
	 * Handles admin operations menu.
	 * 
	 * @param system the system instance
	 * @param in     scanner for input
	 */
	public static void adminMenu(VisaOfficeSystem system, Scanner in) {
		int choice;

		do {
			printAdminMenu();
			choice = in.nextInt();

			if (choice == 1) {
				in.nextLine();
				System.out.println("Enter Officer Name:");
				String name = in.nextLine();

				Officer officer = new Officer(name);
				system.addVisaOfficer(officer);
			}

			else if (choice == 2) {
				System.out.println("--- Available Officers ---");
				system.displayAllOfficers();
			}

			else if (choice == 3) {
				System.out.println("Enter Officer ID:");
				int officerID = in.nextInt();

				Officer officer = system.findOfficerbyID(officerID);

				System.out.println("Select one of the visa applications below");
				printApplications(system);

				System.out.println("Enter Visa Application No:");
				int appNo = in.nextInt();

				Application app = system.findApplicationByApplicationNo(appNo);

				if (officer != null && app != null) {
					system.assignOfficertoApplicaton(app, officer);
					System.out.println(
							"Application " + appNo + " has been assigned to officer " + officer + " successfully");
				} else {
					System.out.println("Invalid officer ID or application number.");
				}
			}

			else if (choice == 4) {
				printApplications(system);
			}

			else if (choice == 5) {
				System.out.println("Select one of the visa applications below");
				printApplications(system);

				System.out.println("Enter Visa Application No:");
				int appNo = in.nextInt();

				system.removeApplication(appNo);
				System.out.println("Application " + appNo + " removed successfully");
			}

			else if (choice == 6) {
				reportsMenu(system, in);
			}

			System.out.println();

		} while (choice != 0);
	}

	/**
	 * Displays and handles reports menu.
	 * 
	 * @param system system instance
	 * @param in     scanner
	 */
	public static void reportsMenu(VisaOfficeSystem system, Scanner in) {
		System.out.println("-- Reports --");
		System.out.println("1) Display pending applications");
		System.out.println("2) Calculate total revenue");
		System.out.println("3) Find applicant with most applications");
		System.out.println("4) Display applications before a specific date");
		System.out.println("5) Detailed Visa Perfomance Report");
		System.out.println("0) Back");
		System.out.println("Enter option number:");

		int choice = in.nextInt();

		if (choice == 1) {
			printPendingApplications(system);
		} else if (choice == 2) {
			system.calculateTotalRevenue();
		} else if (choice == 3) {
			system.applicantWithMostApplications();
		} else if (choice == 4) {
			System.out.println("Enter date [yyyy-mm-dd]:");
			LocalDate date = LocalDate.parse(in.next());
			printApplicationsBeforeDate(system, date);
		} else if (choice == 5) {
			System.out.print("Start date?	");
			LocalDate start = LocalDate.parse(in.next());
			System.out.print("End date?	");
			LocalDate end = LocalDate.parse(in.next());
			detailReport(system, start, end);
		}
	}

	public static void detailReport(VisaOfficeSystem system, LocalDate start, LocalDate end) {
		int officerCount = 0;
		int approved = 0;
		int pending = 0;
		int rejected = 0;
		int total = 0;
		double revenue = 0;
		System.out.printf("Visa report from %s to %s for all visas, as follows: %n", start, end);
		for (Officer o : system.officers) {
			officerCount += 1;
			int studentApproved = 0;
			int studentPending = 0;
			int studentRejected = 0;
			int touristApproved = 0;
			int touristPending = 0;
			int touristRejected = 0;
			int workApproved = 0;
			int workPending = 0;
			int workRejected = 0;
			int totalVisas = 0;
			System.out.printf("%d- Officer: %s%n", officerCount, o.getName());
			for (Application a : system.applications) {
				if (a.getOfficer().getId() == o.getId() && a.getSubmissionDate().isAfter(start) && a.getSubmissionDate().isBefore(end)) {
					if (a instanceof StudentVisa sv) {
						if (sv.getStatus() == Status.APPROVED) {
							studentApproved += 1;
							approved += 1;
						} else if (sv.getStatus() == Status.PENDING) {
							studentPending += 1;
							pending += 1;
						} else if (sv.getStatus() == Status.REJECTED) {
							studentRejected += 1;
							rejected += 1;
						}
						revenue += sv.calculateProcessingFee();
						totalVisas += 1;
					} else if (a instanceof TouristVisa tv) {
						if (tv.getStatus() == Status.APPROVED) {
							touristApproved += 1;
							approved += 1;
						} else if (tv.getStatus() == Status.PENDING) {
							touristPending += 1;
							pending += 1;
						} else if (tv.getStatus() == Status.REJECTED) {
							touristRejected += 1;
							rejected += 1;
						}
						revenue += tv.calculateProcessingFee();
						totalVisas += 1;
					} else if (a instanceof WorkVisa wv) {
						if (wv.getStatus() == Status.APPROVED) {
							workApproved += 1;
							approved += 1;
						} else if (wv.getStatus() == Status.PENDING) {
							workPending += 1;
							pending += 1;
						} else if (wv.getStatus() == Status.REJECTED) {
							workRejected += 1;
							rejected += 1;
						}
						revenue += wv.calculateProcessingFee();
						totalVisas += 1;
					}
				}
			}
			System.out.printf("	Student: Approved: %d visa(s), Pending: %d visa(s), Rejected: %d visa(s)%n",
					studentApproved, studentPending, studentRejected);
			System.out.printf("	Tourist: Approved: %d visa(s), Pending: %d visa(s), Rejected: %d visa(s)%n",
					touristApproved, touristPending, touristRejected);
			System.out.printf("	Work: Approved: %d visa(s), Pending: %d visa(s), Rejected: %d visa(s)%n", workApproved,
					workPending, workRejected);
			System.out.printf("Total assigned applications: %d visa(s)%n", totalVisas);
		}
		total += approved + pending + rejected;
		System.out.printf("Overall Approved visas: %d visa(s)%n", approved);
		System.out.printf("Overall Pending visas: %d visa(s)%n", pending);
		System.out.printf("Overall Rejected visas: %d visa(s)%n", rejected);
		System.out.printf("Overall applications in the selected period: %d visa(s)%n", total);
		System.out.printf("Overall revenue from selected applications: QAR %.2f%n", revenue);
	}

	/**
	 * Handles officer operations menu.
	 * 
	 * @param system system instance
	 * @param in     scanner
	 */
	public static void officerMenu(VisaOfficeSystem system, Scanner in) {
		System.out.println("----- Officer Login -----");
		system.displayAllOfficers();

		System.out.println("Enter Officer ID to login:");
		int id = in.nextInt();

		Officer officer = system.findOfficerbyID(id);

		if (officer == null) {
			System.out.println("Invalid officer ID.");
			return;
		}

		int choice;

		do {
			System.out.println("---- Welcome Officer " + officer.getName() + " ----");
			System.out.println("1. View Assigned Applications");
			System.out.println("2. Review Application Details");
			System.out.println("3. Process Application");
			System.out.println("4. Calculate Processing Fee");
			System.out.println("0. Back to Main Menu");
			System.out.println();
			System.out.println("Enter menu option:");

			choice = in.nextInt();

			if (choice == 1) {
				printAssignedApplications(system, officer);
			}

			else if (choice == 2) {
				System.out.println("Enter application number:");
				int appNo = in.nextInt();

				Application app = system.findApplicationByApplicationNo(appNo);

				if (app != null)
					printApplicationDetails(app);
				else
					System.out.println("Application not found.");
			}

			else if (choice == 3) {
				System.out.println("Enter application number to process:");
				int appNo = in.nextInt();

				Application app = system.findApplicationByApplicationNo(appNo);

				if (app == null) {
					System.out.println("Application not found.");
				} else if (app.getStatus() != Status.PENDING) {
					System.out.println("Application is already processed. Its status cannot be changed.");
				} else {
					System.out.println("Status of application [" + appNo + "] is currently PENDING");
					System.out.println("[1] Approved");
					System.out.println("[2] Rejected");
					System.out.println("Enter status:");

					int status = in.nextInt();

					if (status == 1) {
						system.processApplication(app, Status.APPROVED);
						System.out.println("Application is processed.");
					} else if (status == 2) {
						system.processApplication(app, Status.REJECTED);
						System.out.println("Application is processed.");
					}
				}
			}

			else if (choice == 4) {
				System.out.println("Enter application number:");
				int appNo = in.nextInt();

				Application app = system.findApplicationByApplicationNo(appNo);

				if (app != null) {
					printApplicationDetails(app);
					System.out.println("Application [" + appNo + "] fees: QAR " + app.calculateProcessingFee());
				} else {
					System.out.println("Application not found.");
				}
			}

			System.out.println();

		} while (choice != 0);
	}

	/**
	 * Handles applicant operations menu.
	 * 
	 * @param system system instance
	 * @param in     scanner
	 */
	public static void applicantMenu(VisaOfficeSystem system, Scanner in) {
		in.nextLine();

		System.out.println("----- Applicant Login -----");
		System.out.println("Enter passport number to login or 0 to create new credentials:");
		String passport = in.nextLine();

		Applicant applicant;

		if (passport.equals("0")) {
			System.out.println("Enter name:");
			String name = in.nextLine();

			System.out.println("Enter passport number:");
			String pass = in.nextLine();

			System.out.println("Enter nationality:");
			String nationality = in.nextLine();

			System.out.println("Enter date of birth in this format [yyyy-mm-dd]:");
			LocalDate dob = LocalDate.parse(in.nextLine());

			applicant = new Applicant(name, pass, nationality, dob);
			system.addApplicant(applicant);

			System.out.println("New applicant created successfully.");
		} else {
			applicant = system.findApplicantByPassportNo(passport);
		}

		if (applicant == null) {
			System.out.println("Applicant not found.");
			return;
		}

		int choice;

		do {
			System.out.println("---- Welcome Applicant " + applicant.getName() + " ----");
			System.out.println("1. Create New Visa Application");
			System.out.println("2. View My Applications");
			System.out.println("3. Check Application Status");
			System.out.println("4. Withdraw Application");
			System.out.println("0. Back to Main Menu");
			System.out.println();
			System.out.println("Enter menu option:");

			choice = in.nextInt();

			if (choice == 1) {
				createVisaApplication(system, in, applicant);
			}

			else if (choice == 2) {
				printMyApplications(system, applicant);
			}

			else if (choice == 3) {
				System.out.println("Enter application NO:");
				int appNo = in.nextInt();

				Application app = system.findApplicationByApplicationNo(appNo);

				if (app != null)
					System.out.println("Status of application [" + appNo + "]: " + app.getStatus());
				else
					System.out.println("Application not found.");
			}

			else if (choice == 4) {
				System.out.println("Enter application number to withdraw:");
				int appNo = in.nextInt();

				Application app = system.findApplicationByApplicationNo(appNo);

				if (app == null) {
					System.out.println("Application not found.");
				} else if (app.getStatus() == Status.PENDING) {
					system.removeApplication(appNo);
					System.out.println("Application " + appNo + " removed successfully");
				} else {
					System.out.println(
							"Application " + appNo + " is already been processed. It cannot be removed/withdrawn");
				}
			}

			System.out.println();

		} while (choice != 0);
	}

	/**
	 * Creates a new visa application.
	 * 
	 * @param system    system instance
	 * @param in        scanner
	 * @param applicant the applicant creating the application
	 */
	public static void createVisaApplication(VisaOfficeSystem system, Scanner in, Applicant applicant) {
		System.out.println("1) Tourist Visa");
		System.out.println("2) Student Visa");
		System.out.println("3) Work Visa");
		System.out.println("Select Visa Type:");

		int type = in.nextInt();

		Application app = null;

		if (type == 1) {
			System.out.println("Enter Duration Days:");
			int days = in.nextInt();

			System.out.println("Hotel Reservation true/false:");
			boolean hotel = in.nextBoolean();

			app = new TouristVisa(0, LocalDate.now(), applicant, Status.PENDING, null, days, hotel);
		}

		else if (type == 2) {
			in.nextLine();

			System.out.println("Enter University Name:");
			String university = in.nextLine();

			System.out.println("Enter Study Duration Years:");
			int years = in.nextInt();

			System.out.println("Scholarship true/false:");
			boolean scholarship = in.nextBoolean();

			app = new StudentVisa(0, applicant, Status.PENDING, LocalDate.now(), null, university, years, scholarship);
		}

		else if (type == 3) {
			in.nextLine();

			System.out.println("Enter Company Name:");
			String company = in.nextLine();

			System.out.println("Enter Salary:");
			double salary = in.nextDouble();

			System.out.println("Enter Contract Years:");
			int years = in.nextInt();

			app = new WorkVisa(0, LocalDate.now(), applicant, Status.PENDING, null, company, salary, years);
		}

		if (app != null) {
			system.addApplication(app);
			System.out.println("New application has been added.");
			printApplicationDetails(app);
		}
	}

	/**
	 * Prints all applications.
	 * 
	 * @param system system instance
	 */
	public static void printApplications(VisaOfficeSystem system) {
		printApplications(system, null, null, null);
	}

	/**
	 * Prints pending applications.
	 * 
	 * @param system system instance
	 */
	public static void printPendingApplications(VisaOfficeSystem system) {
		printApplications(system, Status.PENDING, null, null);
	}

	/**
	 * Prints applications before a specific date.
	 * 
	 * @param system system instance
	 * @param date   cutoff date
	 */
	public static void printApplicationsBeforeDate(VisaOfficeSystem system, LocalDate date) {
		printApplications(system, null, date, null);
	}

	/**
	 * Prints applications assigned to an officer.
	 * 
	 * @param system  system instance
	 * @param officer officer
	 */
	public static void printAssignedApplications(VisaOfficeSystem system, Officer officer) {
		System.out.println("Assigned Applications:");
		printApplications(system, null, null, officer);
	}

	/**
	 * Prints applications belonging to an applicant.
	 * 
	 * @param system    system instance
	 * @param applicant applicant
	 */
	public static void printMyApplications(VisaOfficeSystem system, Applicant applicant) {
		printApplications(system, null, null, applicant);
	}

	/**
	 * General method for printing filtered applications.
	 */
	public static void printApplications(VisaOfficeSystem system, Status status, LocalDate beforeDate, Object owner) {
		System.out.printf("%15s %18s %15s %20s %12s %16s %15s %15s%n", "Application No", "Applicant Name",
				"Passport No.", "Nationality", "Status", "Submission Date", "Visa Type", "Officer");

		for (Application a : system.applications) {
			if (status != null && a.getStatus() != status)
				continue;

			if (beforeDate != null && !a.getSubmissionDate().isBefore(beforeDate))
				continue;

			if (owner instanceof Officer) {
				Officer o = (Officer) owner;
				if (a.getOfficer() == null || a.getOfficer().getId() != o.getId())
					continue;
			}

			if (owner instanceof Applicant) {
				Applicant ap = (Applicant) owner;
				if (!a.getApplicant().getPassportNo().equalsIgnoreCase(ap.getPassportNo()))
					continue;
			}

			printApplicationRow(a);
		}
	}

	/**
	 * Prints a single application row in tabular format.
	 * 
	 * @param a application
	 */
	public static void printApplicationRow(Application a) {
		String visaType = "N/A";

		if (a instanceof TouristVisa)
			visaType = "TouristVisa";
		else if (a instanceof StudentVisa)
			visaType = "StudentVisa";
		else if (a instanceof WorkVisa)
			visaType = "WorkVisa";

		String officerName = "N/A";

		if (a.getOfficer() != null)
			officerName = a.getOfficer().getName();

		System.out.printf("%15d %18s %15s %20s %12s %16s %15s %15s%n", a.getApplicationNO(), a.getApplicant().getName(),
				a.getApplicant().getPassportNo(), a.getApplicant().getNationality(), a.getStatus(),
				a.getSubmissionDate(), visaType, officerName);
	}

	/**
	 * Prints detailed information about an application.
	 * 
	 * @param app application
	 */
	public static void printApplicationDetails(Application app) {
		System.out.println("---- Application Details ----");
		System.out.println("Application NO: " + app.getApplicationNO());
		System.out.println("Applicant Info: " + app.getApplicant().getName() + ", " + app.getApplicant().getPassportNo()
				+ ", " + app.getApplicant().getNationality() + ", " + app.getApplicant().getBirthdate());
		System.out.println("Status: " + app.getStatus());
		System.out.println("SubmissionDate: " + app.getSubmissionDate());
		System.out.println("Officer: " + (app.getOfficer() == null ? "N/A" : app.getOfficer()));

		if (app instanceof TouristVisa) {
			TouristVisa t = (TouristVisa) app;
			System.out.println("Visa Type: TouristVisa");
			System.out.println("Duration Days: " + t.getDurationDays());
			System.out.println("Hotel Reservation: " + t.isHotelReservation());
		}

		else if (app instanceof StudentVisa) {
			StudentVisa s = (StudentVisa) app;
			System.out.println("Visa Type: StudentVisa");
			System.out.println("University Name: " + s.getUniversityName());
			System.out.println("Study Duration: " + s.getStudyDurationYears() + " years");
			System.out.println("Scholarship: " + s.isScholarship());
		}

		else if (app instanceof WorkVisa) {
			WorkVisa w = (WorkVisa) app;
			System.out.println("Visa Type: WorkVisa");
			System.out.println("Company Name: " + w.getCompanyName());
			System.out.println("Salary: " + w.getSalary());
			System.out.println("Contract Years: " + w.getContractYears());
		}
	}
}