package shejianguo.app1neard.corpapp.ui.composable.screen.workout.typedifficulty

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.data.model.Difficulty
import shejianguo.app1neard.corpapp.data.model.WorkoutType

@Composable
fun WorkoutTypeDifficulty(
    type: WorkoutType,
    difficulty: Difficulty,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(
            R.string.workout_header_subtitle,
            stringResource(type.titleRes),
            stringResource(difficulty.titleRes),
        ),
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}
