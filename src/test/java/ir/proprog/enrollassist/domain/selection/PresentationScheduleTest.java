package ir.proprog.enrollassist.domain.selection;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.section.PresentationSchedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class PresentationScheduleTest {

    PresentationSchedule schedule;

    @BeforeEach
    public void initialize() {
        schedule = PresentationSchedule.DEFAULT;
    }

    @AfterEach
    public void cleanUp() {
        schedule = null;
    }

    @Test
    public void hasConflictTest_with_itself_conflict() {
        assertTrue(schedule.hasConflict(schedule));
    }

    @Test
    public void hasConflictTest_with_equal_schedule_conflict() {
        assertTrue(schedule.hasConflict(PresentationSchedule.DEFAULT));
    }

    @Test
    public void hasConflictTest_enter_null_value() {
        assertThrows(NullPointerException.class, () -> assertFalse(schedule.hasConflict(null)));
    }

    @Test
    public void hasConflictTest_enter_invalid_day_value() {
        Locale.setDefault(Locale.ENGLISH);
        assertThrows(ExceptionList.class,
                () -> assertFalse(schedule.hasConflict(new PresentationSchedule("ASD", "07:00", "09:00"))));
    }

    @Test
    public void hasConflictTest_enter_invalid_time_value() {
        Locale.setDefault(Locale.ENGLISH);
        assertThrows(ExceptionList.class,
                () -> assertFalse(schedule.hasConflict(new PresentationSchedule("Saturday", "45:00", "09:00"))));
    }

    @Test
    public void hasConflictTest_other_schedule_ends_before_main_schedule_starts_include_same_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Saturday", "07:30", "09:00")));
    }

    @Test
    public void hasConflictTest_other_schedule_ends_before_main_schedule_starts_exclude_same_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Saturday", "07:00", "09:00")));
    }

    @Test
    public void hasConflictTest_other_schedule_ends_before_main_schedule_starts_include_same_day_different_locale_no_conflict() {
        Locale.setDefault(Locale.forLanguageTag("fa"));
        assertThrows(ExceptionList.class,
                () -> assertFalse(schedule.hasConflict(new PresentationSchedule("Saturday", "07:30", "09:00"))));
    }

    @Test
    public void hasConflictTest_other_schedule_ends_before_main_schedule_starts_exclude_same_day_different_locale_no_conflict() {
        Locale.setDefault(Locale.forLanguageTag("fa"));
        assertThrows(ExceptionList.class,
                () -> assertFalse(schedule.hasConflict(new PresentationSchedule("Saturday", "07:00", "09:00"))));
    }

    @Test
    public void hasConflictTest_other_schedule_starts_after_main_schedule_ends_include_same_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Saturday", "10:30", "13:00")));
    }

    @Test
    public void hasConflictTest_other_schedule_starts_after_main_schedule_ends_exclude_same_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Saturday", "11:00", "12:30")));
    }

    @Test
    public void hasConflictTest_other_schedule_ends_before_main_schedule_starts_include_another_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Monday", "07:30", "09:00")));
    }

    @Test
    public void hasConflictTest_other_schedule_ends_before_main_schedule_starts_exclude_another_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Monday", "07:00", "09:00")));
    }

    @Test
    public void hasConflictTest_other_schedule_starts_after_main_schedule_ends_include_another_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Monday", "10:30", "13:00")));
    }

    @Test
    public void hasConflictTest_other_schedule_starts_after_main_schedule_ends_exclude_another_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Monday", "11:00", "12:30")));
    }

    @Test
    public void hasConflictTest_main_schedule_subset_of_other_schedule_same_day_same_locale_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertTrue(schedule.hasConflict(new PresentationSchedule("Saturday", "07:00", "11:30")));
    }

    @Test
    public void hasConflictTest_main_schedule_subset_of_other_schedule_another_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Monday", "07:00", "11:30")));
    }

    @Test
    public void hasConflictTest_other_schedule_subset_of_main_schedule_same_day_same_local_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertTrue(new PresentationSchedule("Saturday", "07:00", "11:30").hasConflict(schedule));
    }

    @Test
    public void hasConflictTest_main_other_subset_of_main_schedule_another_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(new PresentationSchedule("Monday", "07:00", "11:30").hasConflict(schedule));
    }

    @Test
    public void hasConflictTest_other_schedule_starts_before_main_schedule_starts_ends_before_it_ends_same_day_same_locale_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertTrue(schedule.hasConflict(new PresentationSchedule("Saturday", "07:30", "09:30")));
    }

    @Test
    public void hasConflictTest_other_schedule_starts_before_main_schedule_starts_ends_before_it_ends_another_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Sunday", "07:30", "09:30")));
    }

    @Test
    public void hasConflictTest_other_schedule_starts_before_main_schedule_ends_ends_after_it_ends_same_day_same_locale_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertTrue(schedule.hasConflict(new PresentationSchedule("Saturday", "10:00", "12:30")));
    }

    @Test
    public void hasConflictTest_other_schedule_starts_before_main_schedule_ends_ends_after_it_ends_another_day_same_locale_no_conflict() throws ExceptionList {
        Locale.setDefault(Locale.ENGLISH);
        assertFalse(schedule.hasConflict(new PresentationSchedule("Sunday", "10:00", "12:30")));
    }

}
