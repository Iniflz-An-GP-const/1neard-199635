package shejianguo.app1neard.corpapp.ui.composable.screen.history.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

private const val GradientEndAlpha = 0.75f

/** Total workouts and the current streak on a primary gradient. */
@Composable
fun HistorySummaryCard(
    totalWorkouts: Int,
    streak: Int,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val primary = MaterialTheme.colorScheme.primary
    val onPrimary = MaterialTheme.colorScheme.onPrimary

    Row(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(listOf(primary, primary.copy(alpha = GradientEndAlpha))),
                shape = RoundedCornerShape(dimens.cardCornerRadius),
            )
            .padding(dimens.cardPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SummaryValue(
            value = totalWorkouts.toString(),
            label = stringResource(R.string.history_summary_total),
            modifier = Modifier.weight(1f),
        )

        VerticalDivider(
            modifier = Modifier.height(40.dp),
            color = onPrimary.copy(alpha = 0.4f),
        )

        SummaryValue(
            value = streak.toString(),
            label = stringResource(R.string.history_summary_streak),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun SummaryValue(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    val onPrimary = MaterialTheme.colorScheme.onPrimary

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 28.sp),
            fontWeight = FontWeight.Bold,
            color = onPrimary,
        )

        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = onPrimary.copy(alpha = 0.85f),
        )
    }
}
