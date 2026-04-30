package draft1;

import java.time.LocalDate;

public class TouristVisa extends Application {
	private int durationDays;
	private boolean hotelReservation;
	public static final double TOURIST_FEE = 500;

	public TouristVisa(int appliactionNO, LocalDate submissionDate, Applicant applicant, Status status, Officer officer,
			int durationDays, boolean hotelReservation) {
		super(appliactionNO, submissionDate, applicant, status, officer);
		this.durationDays = durationDays;
		this.hotelReservation = hotelReservation;
	}

	public int getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}

	public boolean isHotelReservation() {
		return hotelReservation;
	}

	public void setHotelReservation(boolean hotelReservation) {
		this.hotelReservation = hotelReservation;
	}

	public static double getTouristFee() {
		return TOURIST_FEE;
	}

	@Override
	public double calculateProcessingFee() {
		return TOURIST_FEE + OFFICE_FEE;
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + "Visa Type: TouristVisa\n" + "Duration Days: " + durationDays + "\n"
				+ "Hotel Reservation: " + hotelReservation;
	}
}