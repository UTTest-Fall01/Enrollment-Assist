package ir.proprog.enrollassist.domain.section;

import ir.proprog.enrollassist.Exception.ExceptionList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentationScheduleTest {
    @Test
    void reps_in_different_days() throws ExceptionList {
        PresentationSchedule ps1 = new PresentationSchedule("Saturday", "09:00", "10:30");
        PresentationSchedule ps2 = new PresentationSchedule("Sunday", "09:00", "10:30");
        assertFalse(ps1.hasConflict(ps2));
    }

    @Test
    void reps_in_same_day_other_rep_before_rep_no_overlap() throws ExceptionList {
        PresentationSchedule ps1 = new PresentationSchedule("Saturday", "09:00", "10:30");
        PresentationSchedule ps2 = new PresentationSchedule("Saturday", "11:30", "12:00");
        assertFalse(ps1.hasConflict(ps2));
    }

    @Test
    void reps_in_same_day_other_rep_after_rep_no_overlap() throws ExceptionList {
        PresentationSchedule ps1 = new PresentationSchedule("Saturday", "09:00", "10:30");
        PresentationSchedule ps2 = new PresentationSchedule("Saturday", "11:30", "12:00");
        assertFalse(ps2.hasConflict(ps1));
    }

    @Test
    void other_rep_end_time_after_rep_start_time() throws ExceptionList {
        PresentationSchedule ps1 = new PresentationSchedule("Saturday", "09:00", "10:30");
        PresentationSchedule ps2 = new PresentationSchedule("Saturday", "9:30", "12:00");
        assertTrue(ps2.hasConflict(ps1));
    }

    @Test
    void other_rep_start_time_before_rep_end_time() throws ExceptionList {
        PresentationSchedule ps1 = new PresentationSchedule("Saturday", "10:00", "13:30");
        PresentationSchedule ps2 = new PresentationSchedule("Saturday", "11:00", "14:00");
        assertTrue(ps1.hasConflict(ps2));
    }
}
