package shejianguo.app1neard.corpapp.ui.composable.screen.statistics.summary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.theme.cardBorderStroke

private val ValueSize = 28.sp
private val LabelSize = 12.sp

@Composable
fun StatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        border = cardBorderStroke(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(dimens.cardPadding)) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = ValueSize, lineHeight = 34.sp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(dimens.tightSpacing))

            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = LabelSize),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
