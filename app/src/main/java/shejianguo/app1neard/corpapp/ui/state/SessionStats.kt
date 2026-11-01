package shejianguo.app1neard.corpapp.ui.state

import shejianguo.app1neard.corpapp.data.entity.WorkoutSessionEntity
import java.time.DayOfWeek
import java.time.LocalDate

/** A saved session together with the estimated length of its workout. */
data class SessionEntryUi(
    val session: WorkoutSessionEntity,
    val durationMinutes: Int,
)

/**
 * Consecutive days with at least one workout, ending today — or yesterday, so the
 * streak is not lost before today's workout is done.
 */
fun List<WorkoutSessionEntity>.currentStreak(today: LocalDate = LocalDate.now()): Int {
    val dates = map { it.timestamp.toLocalDate() }.toSet()
    var day = if (today in dates) today else today.minusDays(1)
    var streak = 0
    while (day in dates) {
        streak++
        day = day.minusDays(1)
    }
    return streak
}

/** Number of distinct days with a workout. */
fun List<WorkoutSessionEntity>.activeDays(): Int = map { it.timestamp.toLocalDate() }.toSet().size

/** Workouts per day of the current week, Monday first. */
fun List<WorkoutSessionEntity>.currentWeekCounts(today: LocalDate = LocalDate.now()): List<Int> {
    val weekStart = today.with(DayOfWeek.MONDAY)
    return (0 until 7).map { offset ->
        val day = weekStart.plusDays(offset.toLong())
        count { it.timestamp.toLocalDate() == day }
    }
}

/** Total minutes as hours with one decimal, e.g. "1.5". */
fun formatHours(totalMinutes: Int): String = "%.1f".format(totalMinutes / 60f)
