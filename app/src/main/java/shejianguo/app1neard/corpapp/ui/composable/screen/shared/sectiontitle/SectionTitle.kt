package shejianguo.app1neard.corpapp.ui.composable.screen.shared.sectiontitle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

private val DashWidth = 20.dp
private val DashHeight = 3.dp
private val RuleHeight = 1.dp

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .width(DashWidth)
                .height(DashHeight)
                .clip(RoundedCornerShape(DashHeight / 2))
                .background(MaterialTheme.colorScheme.primary),
        )

        Spacer(modifier = Modifier.width(dimens.itemSpacing))

        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.width(dimens.itemSpacing))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(RuleHeight)
                .background(
                    Brush.horizontalGradient(
                        listOf(MaterialTheme.colorScheme.outlineVariant, Color.Transparent),
                    ),
                ),
        )
    }
}
