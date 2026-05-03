package classes;

import java.time.LocalDate;

/**
 *  Student Visa application class, sub class of application.
 *  @author Hassan Abdus Salam
 *  @version 1.0
 */

public class StudentVisa extends Application{
	/**
	 * Name of the university the applicant will attend
	 */
	private String universityName;
	/**
	 * Duration of the study in years
	 */
	private int studyDurationYears;
	/**
	 * Whether the applicant holds a scholarship
	 */
	private boolean scholarship;
	/**
	 * Processing fee specific to student visas
	 */
	public static final double STUDENT_FEE = 350;

	/**
	 * Constructs a StudentVisa application
	 * @param appNo              the application number
     * @param applicant          the applicant
     * @param status             the current status
     * @param submissionDate     the submission date
     * @param officer            the assigned officer 
     * @param universityName     the name of the university
     * @param studyDurationYears the study duration in years
     * @param scholarship        true if the applicant has a scholarship
	 */
	public StudentVisa(int appNO, Applicant applicant, Status status, LocalDate submissionDate, Officer officer,
			String universityName, int studyDurationYears, boolean scholarship) {
		super(appNO, submissionDate, applicant, status, officer);
		this.universityName = universityName;
		this.studyDurationYears = studyDurationYears;
		this.scholarship = scholarship;
	}

	/**
	 * @return the university name
	 */
	public String getUniversityName() {
		return universityName;
	}

	/**
	 * Sets the university name
	 * @param universityName
	 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	/**
	 * @return the study duration in years
	 */
	public int getStudyDurationYears() {
		return studyDurationYears;
	}

	/**
	 * Sets the study duration in years
	 * @param studyDurationYears
	 */
	public void setStudyDurationYears(int studyDurationYears) {
		this.studyDurationYears = studyDurationYears;
	}

	/**
	 * @return true if scholarship is held, false otherwise
	 */
	public boolean isScholarship() {
		return scholarship;
	}

	/**
	 * Sets whether the applicant has a scholarship
	 * @param scholarship
	 */
	public void setScholarship(boolean scholarship) {
		this.scholarship = scholarship;
	}

	/**
	 * @return student visa fee
	 */
	public double getSTUDENT_FEE() {
		return STUDENT_FEE;
	}
	/**
	 *Calculates the total processing fee (student fee + office fee)
     *
     * @return total processing fee
	 */
	@Override
	public double calculateProcessingFee() {
		return STUDENT_FEE + OFFICE_FEE;

	}
	/**
	 * @return the visa type
	 */
	@Override
	public String getVisaType(){
		return "StudentVisa";
	}

	/**
	 * @return string as per the project file.
	 */
	@Override
	public String toString() {
		return super.toString() + "\n" + "Visa Type: "+ getVisaType() +"\n" + "University Name: " + universityName + "\n"
				+ "Study Duration: " + studyDurationYears + " years\n" + "Scholarship: " + scholarship;
	}
}
