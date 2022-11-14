package ir.proprog.enrollassist.domain.section;


import ir.proprog.enrollassist.Exception.ExceptionList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class PresentationScheduleTests {
    @BeforeEach
    void init() {

    }

    @AfterAll
    static void tearDown() {

    }


    @Test
    @DisplayName("when schedules dayOfWeeks is different, hasConflict should returns false")
    void hasConflict_false_dayOfWeeksOfEachScheduleIsDifferent() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Saturday", "18:00", "20:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertFalse(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule startTime is after the presentationSchedule, hasConflict should returns false")
    void hasConflict_false_otherPresentationScheduleIsAfterThePresentationSchedule() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "21:00", "22:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertFalse(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule starts right at the end of the presentationSchedule, hasConflict should returns false")
    void hasConflict_false_otherPresentationStartsRightAtTheEndOfThePresentationSchedule() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "20:00", "21:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertFalse(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule over before start of the presentationSchedule, hasConflict should returns false")
    void hasConflict_false_otherPresentationScheduleOverBeforeStartOfThePresentationSchedule() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "16:00", "17:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertFalse(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule ends just in time for the presentationSchedule to start, hasConflict should returns false")
    void hasConflict_false_otherPresentationScheduleEndsJustInTimeForThePresentationScheduleToStart() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "17:00", "18:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertFalse(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule ends between the presentationSchedule time, hasConflict should returns true")
    void hasConflict_false_otherPresentationScheduleEndsBetweenThePresentationScheduleTime() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "17:00", "19:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertTrue(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule starts between the presentationSchedule time, hasConflict should returns true")
    void hasConflict_false_otherPresentationScheduleStartsBetweenThePresentationScheduleTime() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "19:00", "22:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertTrue(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule starts and over between the presentationSchedule time, hasConflict should returns true")
    void hasConflict_false_otherPresentationScheduleStartsAndEndsBetweenThePresentationSchedule() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "19:00", "19:30");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertTrue(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule starts before and over after the presentationSchedule, hasConflict should returns true")
    void hasConflict_false_otherPresentationScheduleStartsBeforeAndEndsAfterThePresentationSchedule() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "16:00", "22:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertTrue(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule starts right at the start of the presentationSchedule and ends right at the end of it, hasConflict should returns true")
    void hasConflict_false_otherPresentationScheduleStartsAndEndsRightThePresentationSchedule() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertTrue(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule starts right at the presentationSchedule start time, hasConflict should returns true")
    void hasConflict_false_otherPresentationScheduleStartsRightThePresentationScheduleStarts() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "18:00", "19:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertTrue(hasConflict);
    }

    @Test
    @DisplayName("when otherSchedule over right at the presentationSchedule is over, hasConflict should returns true")
    void hasConflict_false_otherPresentationScheduleOverRightThePresentationScheduleOver() throws ExceptionList {
        PresentationSchedule presentationSchedule = new PresentationSchedule("Monday", "18:00", "20:00");
        PresentationSchedule otherPresentationSchedule = new PresentationSchedule("Monday", "17:00", "20:00");
        boolean hasConflict = presentationSchedule.hasConflict(otherPresentationSchedule);
        assertTrue(hasConflict);
    }
}
