package shejianguo.app1neard.corpapp.ui.composable.screen.home.workoutcard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shejianguo.app1neard.corpapp.data.model.WorkoutModel
import shejianguo.app1neard.corpapp.ui.composable.screen.home.workoutcard.content.WorkoutCardContent
import shejianguo.app1neard.corpapp.ui.composable.screen.home.workoutcard.shell.WorkoutCardShell
import shejianguo.app1neard.corpapp.ui.state.estimatedMinutes

@Composable
fun WorkoutCard(
    workout: WorkoutModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val info = WorkoutCardInfo(
        name = workout.name,
        typeLabel = stringResource(workout.type.titleRes),
        typeIcon = workout.type.icon,
        difficultyLabel = stringResource(workout.difficulty.titleRes),
        difficultyLevel = workout.difficulty.level,
        exerciseCount = workout.exercises.size,
        durationMinutes = workout.estimatedMinutes(),
        typeOrdinal = workout.type.ordinal,
    )

    WorkoutCardShell(
        imageUrl = workout.imageUrl,
        info = info,
        onClick = onClick,
        modifier = modifier,
    ) { cardInfo -> WorkoutCardContent(cardInfo) }
}
