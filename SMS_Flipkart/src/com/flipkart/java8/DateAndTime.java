package  com.flipkart.java8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;

public class DateAndTime {

	public static void main(String args[]) {

		displayCurrentDate();
		// please uncomment the below method for your testing
		System.out.println();
		
		 displayCurrentDateDetails();
		 System.out.println();
		 
		 displayCurrentTimeDetails();
		 System.out.println();
		 
		 displayIndependenceDay();
		 System.out.println();
		 
		 displayInTime();
		 System.out.println();
		 
//		 displayCurrentDateAndTime();
		 System.out.println();
		 
		 displayMeetingDateAndTime();
		 System.out.println();
		 
		 displayCurrentTimeDetails();
		 System.out.println();
		 
		 displayInstant();
		 System.out.println();
		 
		 isBeforeDate();
		 System.out.println();
		 
		 isAfterDate();
		 System.out.println();
		 
		 isEqualDate();
		 System.out.println();
		 
		 plusDays();
		 System.out.println();
		 
		 minusDays();
		 System.out.println();
		 
//		 isPasswordExpired();

	}

	private static void displayCurrentDate() {

		Date currentDate = new Date();
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		LocalDateTime localDateTime = LocalDateTime.now();

		System.out.println("Current Date and Time - Before Java 8 : " + currentDate);
		System.out.println("Current Date - From Java 8 : " + localDate);
		System.out.println("Current Time - From Java 8 : " + localTime);
		System.out.println("Current Date and Time From Java 8 : " + localDateTime);
	}

	private static void displayCurrentDateDetails() {
		// Current Date - : 2019-03-05
		LocalDate localDate = LocalDate.now();

		System.out.println("localDate.getYear(): " + localDate.getYear());
		System.out.println("localDate.getMonth(): " + localDate.getMonth());
		System.out.println("localDate.getDayOfWeek(): " + localDate.getDayOfWeek());
		System.out.println("localDate.getDayOfMonth(): " + localDate.getDayOfMonth());
		System.out.println("localDate.getDayOfYear(): " + localDate.getDayOfYear());

	}

	private static void displayCurrentTimeDetails() {

		LocalTime localTime = LocalTime.now();

		System.out.println("Current Time : " + localTime);
		System.out.println("localTime.getSecond(): " + localTime.getSecond());
		System.out.println("localTime.getMinute(): " + localTime.getMinute());
		System.out.println("localTime.getHour(): " + localTime.getHour());
	}

	// Specific Date
	private static void displayIndependenceDay() {

		LocalDate independenceDay = LocalDate.of(1947, Month.AUGUST, 15);
		System.out.println("IndependenceDay : " + independenceDay);
	}

	// Specific Time
	private static void displayInTime() {

		LocalTime inTime = LocalTime.of(8, 30, 00);
		System.out.println("In Time : " + inTime);
	}

	// Specific Date and Time
	private static void displayMeetingDateAndTime() {

		LocalDate meetingDate = LocalDate.of(2019, Month.MARCH, 5);
		LocalTime meetingTime = LocalTime.of(10, 30, 00);
		LocalDateTime meetingDateAndTime = LocalDateTime.of(meetingDate, meetingTime);
		System.out.println("Meeting Time and Date : " + meetingDateAndTime);
	}

	private static void displayInstant() {
		Instant startTime = Instant.now();
		for (int i = 0; i < 1000; i++) {
			System.out.println("Perform some logic");
		}
		Instant EndTime = Instant.now();
		Duration duration = Duration.between(startTime, EndTime);
		System.out.println("Time taken to perform some logic : " + duration.toMillis());

	}

	private static void isBeforeDate() {
		LocalDate localDate1 = LocalDate.now();
		LocalDate localDate2 = LocalDate.of(2019, 1, 1);
		if (localDate1.isBefore(localDate2)) {
			System.out.println("Date is before 1st Jan 2019");
		} else {
			System.out.println("Date is not before 1st Jan 2019");
		}

	}

	private static void isAfterDate() {
		LocalDate localDate1 = LocalDate.now();
		LocalDate localDate2 = LocalDate.of(2019, 1, 1);
		if (localDate1.isAfter(localDate2)) {
			System.out.println("Date is after 1st Jan 2019");
		} else {
			System.out.println("Date is not after 1st Jan 2019");
		}

	}

	private static void isEqualDate() {
		LocalDate localDate1 = LocalDate.now();
		LocalDate localDate2 = LocalDate.of(2019, 1, 1);
		if (localDate1.isEqual(localDate2)) {
			System.out.println("Date1 and Date2 are equal");
		} else {
			System.out.println("Date1 and Date2 are not equal");
		}

	}

	private static void plusDays() {

		LocalDate localDate = LocalDate.of(2019, 1, 1);
		LocalDate plusDays = localDate.plusDays(3);
		System.out.println("localDate : " + localDate);
		System.out.println("localDate + 3 days: " + plusDays);

	}

	private static void minusDays() {

		LocalDate localDate = LocalDate.of(2019, 1, 1);
		LocalDate minusDays = localDate.minusDays(3);
		System.out.println("localDate : " + localDate);
		System.out.println("localDate - 3 days: " + minusDays);

	}

}
