package classes;

import java.time.LocalDate;

/**
 * Work visa application class with employment information,and extends the
 * applications class.
 * 
 * @author Abdul Hadi
 * @version 1.0
 */
public class WorkVisa extends Application {

	/**
	 * Name of the employing company
	 */
	private String companyName;
	/**
	 * Offered salary
	 */
	private double salary;
	/**
	 * Work contract duration in years
	 */
	private int contractYears;
	/**
	 * Processing fee specific to work visas
	 */
	public static final double WORK_FEE = 650;

	/**
	 * Constructs a WorkVisa application.
	 *
	 * @param appNo          the application number
	 * @param applicant      the applicant
	 * @param status         the current status
	 * @param submissionDate the submission date
	 * @param officer        the assigned officer (may be null)
	 * @param companyName    the name of the employing company
	 * @param salary         the offered salary
	 * @param contractYears  the contract duration in years
	 */
	public WorkVisa(int appliactionNO, LocalDate submissionDate, Applicant applicant, Status status, Officer officer,
			String companyName, double salary, int contractYears) {
		super(appliactionNO, submissionDate, applicant, status, officer);
		this.companyName = companyName;
		this.salary = salary;
		this.contractYears = contractYears;
	}

	/**
	 * Returns the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Returns the offered salary.
	 *
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Sets the offered salary.
	 *
	 * @param salary the new salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * Returns the contract duration in years.
	 *
	 * @return the contract years
	 */
	public int getContractYears() {
		return contractYears;
	}

	/**
	 * Sets the contract duration in years.
	 *
	 * @param contractYears the new contract years
	 */
	public void setContractYears(int contractYears) {
		this.contractYears = contractYears;
	}

	/**
	 * @return work visa fee
	 */
	public static double getWorkFee() {
		return WORK_FEE;
	}

	/**
	 * Calculates the total processing fee (work fee + office fee).
	 *
	 * @return total processing fee
	 */
	@Override
	public double calculateProcessingFee() {
		return WORK_FEE + OFFICE_FEE;
	}

	/**
	 * @return visa type
	 */
	@Override
	public String getVisaType() {
		return "WorkVisa";
	}

	/**
	 * @returns a string as per the project file.
	 */
	@Override
	public String toString() {
		return super.toString() + "\n" + "Visa Type: " + getVisaType() + "\n" + "Company Name: " + companyName + "\n"
				+ "Salary: $" + String.format("%.2f", salary) + "\n" + "Contract Years: " + contractYears + " year(s)";
	}

}