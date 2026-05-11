package classes;

import java.time.LocalDate;

/**
 * Tourist Visa application class, sub class of application.
 * 
 * @author Hassan Abdus Salam
 * @version 1.0
 */
public class TouristVisa extends Application {
	/**
	 * Duration of stay in days
	 */
	private int durationDays;
	/**
	 * Whether the applicant has a confirmed hotel reservation
	 */
	private boolean hotelReservation;
	/**
	 * Processing fee specific to tourist visas
	 */
	public static final double TOURIST_FEE = 500;

	/**
	 * Constructs a TouristVisa application
	 * 
	 * @param appNo            the application number
	 * @param applicant        the applicant
	 * @param status           the current status
	 * @param submissionDate   the submission date
	 * @param officer          the assigned officer
	 * @param durationDays     the intended stay duration in days
	 * @param hotelReservation true if the applicant has a hotel reservation
	 */
	public TouristVisa(int appliactionNO, LocalDate submissionDate, Applicant applicant, Status status, Officer officer,
			int durationDays, boolean hotelReservation) {
		super(appliactionNO, submissionDate, applicant, status, officer);
		this.durationDays = durationDays;
		this.hotelReservation = hotelReservation;
	}

	/**
	 * @return the duration of stay in days
	 */
	public int getDurationDays() {
		return durationDays;
	}

	/**
	 * Sets the duration of stay in days
	 * 
	 * @param durationDays
	 */
	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}

	/**
	 * @return true if hotel reservation is confirmed, otherwise false
	 */
	public boolean isHotelReservation() {
		return hotelReservation;
	}

	/**
	 * Sets whether the applicant has a hotel reservation
	 * 
	 * @param hotelReservation
	 */
	public void setHotelReservation(boolean hotelReservation) {
		this.hotelReservation = hotelReservation;
	}

	/**
	 * @return tourist visa fee
	 */
	public double getTOURIST_FEE() {
		return TOURIST_FEE;
	}

	/**
	 * Calculates the total processing fee (tourist fee + office fee)
	 *
	 * @return total processing fee
	 */
	@Override
	public double calculateProcessingFee() {
		return TOURIST_FEE + OFFICE_FEE;
	}

	/**
	 * @returns visa type
	 */
	@Override
	public String getVisaType() {
		return "TouristVisa";
	}

	/**
	 * @return string as per the project file.
	 */
	@Override
	public String toString() {
		return super.toString() + "\n" + "Visa Type: " + getVisaType() + "\n" + "Duration Days: " + durationDays + "\n"
				+ "Hotel Reservation: " + hotelReservation;
	}
}