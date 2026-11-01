package shejianguo.app1neard.corpapp.ui.composable.screen.workout.exerciserow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.data.model.ExerciseModel
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

private val NumberBadgeSize = 32.dp

/** One numbered exercise of the workout; [isAlternate] rows take the surface color, others the background. */
@Composable
fun ExerciseRow(
    number: Int,
    exercise: ExerciseModel,
    isAlternate: Boolean,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                if (isAlternate) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background
            )
            .padding(horizontal = dimens.screenPadding, vertical = dimens.itemSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(NumberBadgeSize)
                .background(MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = number.toString(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }

        Spacer(modifier = Modifier.width(dimens.itemSpacing))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = exercise.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = exercise.workSubtitle(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        exercise.restDuration?.let { rest ->
            Spacer(modifier = Modifier.width(dimens.itemSpacing))

            Text(
                text = stringResource(R.string.exercise_rest_format, (rest / 1000).toInt()),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun ExerciseModel.workSubtitle(): String {
    return if (sets != null && repetitions != null) {
        stringResource(R.string.exercise_sets_reps, sets, repetitions)
    } else {
        val seconds = ((duration ?: 0L) / 1000).toInt()
        stringResource(R.string.exercise_duration_seconds, seconds)
    }
}
