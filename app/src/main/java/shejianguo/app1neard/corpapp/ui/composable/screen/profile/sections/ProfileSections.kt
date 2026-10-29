package shejianguo.app1neard.corpapp.ui.composable.screen.profile.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.button.ResetDataButton
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.card.AppInfoCard
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.fields.ProfileFields
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.sectiontitle.SectionTitle
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

@Composable
fun ProfileSections(
    weeklyGoalText: String,
    heightText: String,
    weightText: String,
    onWeeklyGoalChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        SectionTitle(text = stringResource(R.string.profile_screen_goals_section_title))

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        ProfileFields(
            weeklyGoalText = weeklyGoalText,
            heightText = heightText,
            weightText = weightText,
            onWeeklyGoalChanged = onWeeklyGoalChanged,
            onHeightChanged = onHeightChanged,
            onWeightChanged = onWeightChanged,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        SectionTitle(text = stringResource(R.string.profile_screen_data_section_title))

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        ResetDataButton(
            onClick = onResetClick,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        SectionTitle(text = stringResource(R.string.profile_screen_about_section_title))

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        AppInfoCard(modifier = Modifier.fillMaxWidth())
    }
}
