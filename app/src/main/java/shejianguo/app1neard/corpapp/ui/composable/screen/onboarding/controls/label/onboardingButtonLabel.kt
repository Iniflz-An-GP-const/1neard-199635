package shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.controls.label

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import shejianguo.app1neard.corpapp.R

@Composable
fun onboardingButtonLabel(
    isLastPage: Boolean,
    currentPage: Int,
    pageCount: Int,
): String {
    return stringResource(
        R.string.onboarding_button_progress,
        stringResource(if (isLastPage) R.string.start_button_title else R.string.next_button_title),
        currentPage + 1,
        pageCount,
    )
}
