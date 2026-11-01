package shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.controls.label

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import shejianguo.app1neard.corpapp.R

@Composable
fun onboardingButtonLabel(
    isLastPage: Boolean,
    @Suppress("UNUSED_PARAMETER") currentPage: Int,
    @Suppress("UNUSED_PARAMETER") pageCount: Int,
): String {
    return stringResource(if (isLastPage) R.string.start_button_title else R.string.next_button_title)
}
