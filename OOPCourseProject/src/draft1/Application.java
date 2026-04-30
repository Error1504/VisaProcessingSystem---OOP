package draft1;

import java.time.LocalDate;

public abstract class Application {
	private int applicationNO;
	private LocalDate submissionDate;
	private Applicant applicant;
	private Status status;
	private Officer officer;
	public final double OFFICE_FEE = 250;

	public Application(int applicationNO, LocalDate submissionDate, Applicant applicant, Status status, Officer officer) {
		super();
		this.applicationNO = applicationNO;
		this.submissionDate = submissionDate;
		this.applicant = applicant;
		this.status = status;
		this.officer = officer;

	}

	public abstract double calculateProcessingFee();

	public int getApplicationNO() {
		return application;
	}

	public void setApplicationNO(int applicationNO) {
		this.applicationNO = applicationNO;
	}

	public LocalDate getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public double getOFFICE_FEE() {
		return OFFICE_FEE;
	}

	public void approveApplication() {
		setStatus(Status.APPROVED);
	}

	public void rejectApplication() {
		setStatus(Status.REJECTED);
	}

	@Override
	public String toString() {
		return "Application No: " + applicationNO + "\n" + "Applicant info: " + applicant.toString() + "\n" + "Status: "
				+ status + "\n" + "SubmissionDate: " + submissionDate + "\n" + "Officer: " + officer.toString();
	}
}
