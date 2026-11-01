package shejianguo.app1neard.corpapp.ui.state

import shejianguo.app1neard.corpapp.data.model.ExerciseModel
import shejianguo.app1neard.corpapp.data.model.WorkoutModel

/** Approximate time of one repetition, used to estimate rep-based sets. */
private const val SECONDS_PER_REP = 3L

/** Estimated workout length in whole minutes: every set plus the rest after it. */
fun WorkoutModel.estimatedMinutes(): Int {
    val totalSeconds = exercises.sumOf { exercise -> exercise.estimatedSeconds() }
    return ((totalSeconds + 59) / 60).toInt().coerceAtLeast(1)
}

private fun ExerciseModel.estimatedSeconds(): Long {
    val setCount = (sets ?: 1).toLong()
    val workSeconds = duration?.let { it / 1000 } ?: ((repetitions ?: 0) * SECONDS_PER_REP)
    val restSeconds = (restDuration ?: 0L) / 1000
    return setCount * (workSeconds + restSeconds)
}
