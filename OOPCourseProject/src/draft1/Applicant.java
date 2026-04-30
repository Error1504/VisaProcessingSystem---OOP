package draft1;

import java.time.LocalDate;

public class Applicant {
	private String name;
	private String passportNo;
	private String nationality;
	private LocalDate birthdate;

	public Applicant(String name, String passportNo, String nationality, LocalDate birthdate) {
		super();
		this.name = name;
		this.passportNo = passportNo;
		this.nationality = nationality;
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return name + ", " + passportNo + ", " + nationality + ", " + birthdate;
	}
}
