package shejianguo.app1neard.corpapp.ui.composable.screen.statistics.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.theme.cardBorderStroke
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

private val ChartHeight = 140.dp
private val BarWidth = 20.dp
private val BarCorner = 6.dp
private const val TrackAlpha = 0.12f

/** Workouts per day of the current week as seven vertical bars, Monday to Sunday. */
@Composable
fun WeeklyBarChart(
    counts: List<Int>,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val maxValue = (counts.maxOrNull() ?: 0).coerceAtLeast(1)
    val primary = MaterialTheme.colorScheme.primary
    val locale = Locale.getDefault()

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        border = cardBorderStroke(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(dimens.cardPadding)) {
            Text(
                text = stringResource(R.string.statistics_weekly_chart_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(dimens.contentSpacing))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                DayOfWeek.entries.forEachIndexed { index, day ->
                    val value = counts.getOrElse(index) { 0 }

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = value.toString(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Box(
                            modifier = Modifier
                                .height(ChartHeight)
                                .width(BarWidth)
                                .background(primary.copy(alpha = TrackAlpha), RoundedCornerShape(BarCorner)),
                            contentAlignment = Alignment.BottomCenter,
                        ) {
                            if (value > 0) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(value.toFloat() / maxValue)
                                        .background(primary, RoundedCornerShape(BarCorner)),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(dimens.tightSpacing))

                        Text(
                            text = day.getDisplayName(TextStyle.SHORT, locale),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                        )
                    }
                }
            }
        }
    }
}
