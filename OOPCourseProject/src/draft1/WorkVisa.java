package draft1;

import java.time.LocalDate;

public class WorkVisa extends Application {
	private String companyName;
	private double salary;
	private int contractYears;
	public static final double WORK_FEE = 650;

	public WorkVisa(int appliactionNO, LocalDate submissionDate, Applicant applicant, Status status, Officer officer,
			String companyName, double salary, int contractYears) {
		super(appliactionNO, submissionDate, applicant, status, officer);
		this.companyName = companyName;
		this.salary = salary;
		this.contractYears = contractYears;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getContractYears() {
		return contractYears;
	}

	public void setContractYears(int contractYears) {
		this.contractYears = contractYears;
	}

	public static double getWorkFee() {
		return WORK_FEE;
	}

	@Override
	public double calculateProcessingFee() {
		return WORK_FEE + OFFICE_FEE;
	}
	public String getVisaType(){
		return "WorkVisa"
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + "Visa Type: WorkVisa\n" + "Company Name: " + companyName + "\n" + "Salary: $"
				+ String.format("%.2f", salary) + "\n" + "Contract Years: " + contractYears + " year(s)";
	}

}