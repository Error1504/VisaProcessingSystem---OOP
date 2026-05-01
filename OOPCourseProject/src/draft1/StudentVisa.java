package draft1;

import java.time.LocalDate;

public class StudentVisa extends Application{
	private String universityName;
	private int studyDurationYears;
	private boolean scholarship;
	public static final double STUDENT_FEE = 350;

	public StudentVisa(int appNO, Applicant applicant, Status status, LocalDate submissionDate, Officer officer,
			String universityName, int studyDurationYears, boolean scholarship) {
		super(appNO, submissionDate, applicant, status, officer);
		this.universityName = universityName;
		this.studyDurationYears = studyDurationYears;
		this.scholarship = scholarship;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public int getStudyDurationYears() {
		return studyDurationYears;
	}

	public void setStudyDurationYears(int studyDurationYears) {
		this.studyDurationYears = studyDurationYears;
	}

	public boolean isScholarship() {
		return scholarship;
	}

	public void setScholarship(boolean scholarship) {
		this.scholarship = scholarship;
	}

	public double getSTUDENT_FEE() {
		return STUDENT_FEE;
	}
	@Override
	public double calculateProcessingFee() {
		return STUDENT_FEE + OFFICE_FEE;

	}
	@Override
	public String getVisaType(){
		return "StudentVisa";
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + "Visa Type: "+ getVisaType +"\n" + "University Name: " + universityName + "\n"
				+ "Study Duration: " + studyDurationYears + " years\n" + "Scholarship: " + scholarship;
	}
}
