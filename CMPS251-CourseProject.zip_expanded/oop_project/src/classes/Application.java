package classes;

import java.time.LocalDate;

/**
 * Application class, abstract and father class to three other visa classes
 * @author Hassan Abdus Salam
 * @version 1.0 
 */
public abstract class Application {
	/**
	 * Application number
	 */
	private int applicationNO;
	/**
	 * Date the application got submitted
	 */
	private LocalDate submissionDate;
	/**
	 * The applicant who owns this application, an instance of applicant class.
	 */
	private Applicant applicant;
	/**
	 * Current Status of the application
	 */
	private Status status;
	/**
	 * The officer assigned to this application
	 */
	private Officer officer;
	/**
	 * office processing fee charged for every application
	 */
	public final double OFFICE_FEE = 250;

	/**
	 * Construct an application with parameters below:
	 * @param applicationNO 		the application number
	 * @param submissionDate		the date of submission
	 * @param applicant				the applicant who made this application	
	 * @param status				the current status 
	 * @param officer				the officer assigned
	 */
	public Application(int applicationNO, LocalDate submissionDate, Applicant applicant, Status status, Officer officer) {
		super();
		this.applicationNO = applicationNO;
		this.submissionDate = submissionDate;
		this.applicant = applicant;
		this.status = status;
		this.officer = officer;

	}

	/**
	 *  Calculates and returns the total processing fee for this application.
     * 	must be overridden by each visa subclass.
     *
     * @return the total processing fee
	 * 
	 */
	public abstract double calculateProcessingFee();

	/**
	 * 
     *
	 * @return the application number
	 */
	public int getApplicationNO() {
		return applicationNO;
	}

	/**
	 * Sets the application number
	 * @param applicationNO
	 */
	public void setApplicationNO(int applicationNO) {
		this.applicationNO = applicationNO;
	}

	/**
	 * 
	 * @return the date of submission
	 * 
	 */
	public LocalDate getSubmissionDate() {
		return submissionDate;
	}

	/**
	 * Sets the date of submission
	 * @param submissionDate
	 */
	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

	/**
	 * @return the applicant 
	 */
	public Applicant getApplicant() {
		return applicant;
	}

	/** Sets the applicant
	 * @param applicant
	 */
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	/**
	 * @return current status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the Status
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the officer assigned
	 */
	public Officer getOfficer() {
		return officer;
	}

	/**
	 * sets an officer to the application
	 * @param officer
	 */
	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	/**
	 * @return the office fee
	 */
	public double getOFFICE_FEE() {
		return OFFICE_FEE;
	}

	/**
	 * Sets the status to approved 
	 */
	public void approveApplication() {
		setStatus(Status.APPROVED);
	}

	/**
	 * Sets the status to rejected
	 */
	public void rejectApplication() {
		setStatus(Status.REJECTED);
	}

	/**
	 * @return the visa type 
	 * must be overridden by the sub classes
	 */
	public abstract String getVisaType();

	/**
	 * returns a string to match the project file output.
	 */
	@Override
	public String toString() {
		return "Application No: " + applicationNO + "\n" + "Applicant info: " + applicant.toString() + "\n" + "Status: "
				+ status + "\n" + "SubmissionDate: " + submissionDate + "\n" + "Officer: " + officer.toString();
	}
}
