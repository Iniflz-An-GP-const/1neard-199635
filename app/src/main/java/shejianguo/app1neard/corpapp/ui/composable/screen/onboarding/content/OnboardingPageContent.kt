package shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.textmotion.textMotion
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.WorkoutImage
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

private const val ImageScreenShare = 0.5f
private val ImageCorner = 32.dp
private val TitleSize = 22.sp
private val DescriptionSize = 14.sp

/** A slide: a stock photo over the top half of the screen, then the title and the description. */
@Composable
fun OnboardingPageContent(
    title: String,
    description: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val imageHeight = LocalConfiguration.current.screenHeightDp.dp * ImageScreenShare

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WorkoutImage(
            imageUrl = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .clip(RoundedCornerShape(bottomStart = ImageCorner, bottomEnd = ImageCorner)),
        )

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        Column(
            modifier = Modifier
                .widthIn(max = OnboardingContentMaxWidth)
                .fillMaxWidth()
                .padding(horizontal = dimens.screenPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                modifier = Modifier.textMotion(0),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = TitleSize, lineHeight = 28.sp),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(dimens.itemSpacing))

            Text(
                text = description,
                modifier = Modifier.textMotion(1),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = DescriptionSize, lineHeight = 20.sp),
                textAlign = TextAlign.Center,
            )
        }
    }
}
