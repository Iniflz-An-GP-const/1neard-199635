package shejianguo.app1neard.corpapp.ui.composable.screen.onboarding

import androidx.annotation.StringRes
import shejianguo.app1neard.corpapp.R

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    val imageUrl: String,
)

val onboardingPagesContent = listOf(
    OnboardingContent(
        titleRes = R.string.snpcp_page_expertise_title,
        descriptionRes = R.string.snpcp_page_expertise_description,
        imageUrl = "https://images.unsplash.com/photo-1534258936925-c58bed479fcb?w=800&q=80",
    ),
    OnboardingContent(
        titleRes = R.string.snpcp_page_reliability_title,
        descriptionRes = R.string.snpcp_page_reliability_description,
        imageUrl = "https://images.unsplash.com/photo-1728532483490-708f6562b738?w=800&q=80",
    ),
    OnboardingContent(
        titleRes = R.string.snpcp_page_guarantee_title,
        descriptionRes = R.string.snpcp_page_guarantee_description,
        imageUrl = "https://images.unsplash.com/photo-1632766814518-09285fcc69c2?w=800&q=80",
    ),
)
