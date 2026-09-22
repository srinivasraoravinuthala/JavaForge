package pkg13libs;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/*
 * libs1DateTimeApi.java
 * ---------------------
 * The modern Date/Time API (java.time, Java 8+) — immutable and thread-safe.
 *
 * DEFINITION:
 *   java.time models dates, times, instants, durations, and zones with clear,
 *   immutable types. It replaces the error-prone java.util.Date/Calendar.
 *
 * KEY POINTS:
 *   - LocalDate/LocalTime/LocalDateTime: no time zone. ZonedDateTime: with zone.
 *   - Instant: a point on the UTC timeline (good for timestamps).
 *   - Period = date-based amount (years/months/days); Duration = time-based.
 *   - All types are immutable: plusDays() returns a NEW object.
 */
public class libs1DateTimeApi {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Today        : " + today);
        System.out.println("In 30 days   : " + today.plusDays(30));
        System.out.println("Day of week  : " + today.getDayOfWeek());

        LocalDateTime now = LocalDateTime.now();
        System.out.println("\nNow          : " + now);
        System.out.println("Formatted    : " +
                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // Time zones
        ZonedDateTime tokyo = now.atZone(ZoneId.systemDefault())
                                 .withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("\nSame instant in Tokyo: " + tokyo);

        // Period (date span) vs Duration (time span)
        LocalDate launch = LocalDate.of(1995, 5, 23);   // Java's public debut
        Period age = Period.between(launch, today);
        System.out.printf("%nJava is %d years, %d months, %d days old%n",
                age.getYears(), age.getMonths(), age.getDays());
        System.out.println("That is " + ChronoUnit.DAYS.between(launch, today) + " days");

        Duration meeting = Duration.ofHours(1).plusMinutes(30);
        System.out.println("\nDuration     : " + meeting + " = " + meeting.toMinutes() + " minutes");

        // Parsing
        LocalDate parsed = LocalDate.parse("2030-12-25");
        System.out.println("\nParsed date  : " + parsed + " (a " + parsed.getDayOfWeek() + ")");
    }
}
