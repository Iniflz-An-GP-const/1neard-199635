package shejianguo.app1neard.corpapp.ui.composable.screen.home.tips.shell

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import shejianguo.app1neard.corpapp.data.model.TipModel
import shejianguo.app1neard.corpapp.ui.composable.screen.home.tips.transition.TipTransition
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

private val OutlineWidth = 1.5.dp
private const val OutlineAlpha = 0.6f

@Composable
fun TipCardShell(
    tip: TipModel,
    onAdvance: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (TipModel) -> Unit,
) {
    val dimens = LocalDimens.current
    val shape = RoundedCornerShape(dimens.cardCornerRadius)

    Box(
        modifier = modifier
            .clip(shape)
            .border(
                border = BorderStroke(
                    OutlineWidth,
                    MaterialTheme.colorScheme.primary.copy(alpha = OutlineAlpha)
                ),
                shape = shape,
            )
            .clickable(onClick = onAdvance)
            .padding(dimens.cardPadding),
    ) {
        TipTransition(tip = tip, content = content)
    }
}
