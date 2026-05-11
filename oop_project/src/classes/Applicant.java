package classes;

import java.time.LocalDate;

/**
 * Applicant class with Identification attributes
 * 
 * @author Hassan Abdus Salam
 * @version 1.0
 */
public class Applicant {
	/**
	 * Applicant's name
	 */
	private String name;
	/**
	 * Applicant's passport number
	 */
	private String passportNo;
	/**
	 * Applicant's nationality
	 */
	private String nationality;
	/**
	 * Applicant's date of birth
	 */
	private LocalDate birthdate;
	
	/**
	 * Constructs an empty applicant 
	 */
	public Applicant() {
	}

	/**
	 * Constructs an applicant with parameters below:
	 * 
	 * @param name        Applicant's name
	 * @param passportNo  Applicant's passport number
	 * @param nationality Applicant's nationality
	 * @param birthdate   Applicant's date of birth
	 */
	public Applicant(String name, String passportNo, String nationality, LocalDate birthdate) {
		this();
		this.name = name;
		this.passportNo = passportNo;
		this.nationality = nationality;
		this.birthdate = birthdate;
	}

	/**
	 * @return Applicant's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets Applicant's name
	 * 
	 * @param Applicant's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Applicant's Passport number
	 */
	public String getPassportNo() {
		return passportNo;
	}

	/**
	 * Set Applicant's passportNo
	 * 
	 * @param Applicant's passportNo
	 */
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	/**
	 * @return Applicant's nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Set Applicant's nationality
	 * 
	 * @param nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return Applicant's date of birth
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/**
	 * Set Applicant's date of birth
	 * 
	 * @param birthdate
	 */
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return String as per the project file.
	 */
	@Override
	public String toString() {
		return name + ", " + passportNo + ", " + nationality + ", " + birthdate;
	}
}
