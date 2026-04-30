package draft1;

import java.time.LocalDate;

public class StringTester {
	public static void main(String[] args) {
		Officer officer = new Officer(2001, "Omar Khaled");
		System.out.println(officer);
Applicant applicant = new Applicant("Hassan", "S121232","Indian",LocalDate.of(2003,04,15));
		
		StudentVisa stdvisa = new StudentVisa(2001, applicant, Status.PENDING, LocalDate.of(2003,04,15), officer, "QU", 2, false) ;
		System.out.println(stdvisa);
	}

}
