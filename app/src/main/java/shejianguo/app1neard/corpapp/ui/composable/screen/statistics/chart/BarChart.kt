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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

private val BarHeight = 20.dp
private val BarCornerRadius = 6.dp
private val LabelWidth = 88.dp
private val ValueWidth = 28.dp

@Composable
fun BarChart(
    entries: List<Pair<String, Int>>,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val maxValue = (entries.maxOfOrNull { it.second } ?: 0).coerceAtLeast(1)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
    ) {
        entries.forEach { (label, value) ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = label,
                    modifier = Modifier.width(LabelWidth),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.width(dimens.itemSpacing))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(BarHeight),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(fraction = value.toFloat() / maxValue)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(
                                    topEnd = BarCornerRadius,
                                    bottomEnd = BarCornerRadius,
                                ),
                            ),
                    )
                }

                Spacer(modifier = Modifier.width(dimens.itemSpacing))

                Text(
                    text = value.toString(),
                    modifier = Modifier.width(ValueWidth),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
