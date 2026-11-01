package shejianguo.app1neard.corpapp.ui.composable.screen.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.ui.composable.approot.LocalContentBottomPadding
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.header.ProfileHeader
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.header.ProfileStatsRow
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.form.ProfileForm
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.form.SettingsLink
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.sectiontitle.SectionTitle
import shejianguo.app1neard.corpapp.ui.state.formatHours
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

private val SaveButtonHeight = 52.dp

@Composable
fun SNPCPProfileScreen(
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val saved by viewModel.saved.collectAsStateWithLifecycle()
    val draft by viewModel.draft.collectAsStateWithLifecycle()
    val stats by viewModel.stats.collectAsStateWithLifecycle()
    val dimens = LocalDimens.current

    val displayName = saved.name.ifBlank { stringResource(R.string.profile_default_name) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                start = dimens.screenPadding,
                end = dimens.screenPadding,
                top = dimens.screenPadding,
                bottom = dimens.screenPadding + LocalContentBottomPadding.current,
            ),
        verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
    ) {
        ProfileHeader(
            name = displayName,
            fitnessLevelLabel = stringResource(saved.fitnessLevel.titleRes),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        )

        ProfileStatsRow(
            workouts = stats.workouts.toString(),
            hours = formatHours(stats.totalMinutes),
            activeDays = stats.activeDays.toString(),
            modifier = Modifier.fillMaxWidth(),
        )

        SectionTitle(text = stringResource(R.string.profile_details_section_title))

        ProfileForm(
            form = draft,
            onNameChanged = viewModel::onNameChanged,
            onAgeChanged = viewModel::onAgeChanged,
            onFitnessLevelChanged = viewModel::onFitnessLevelChanged,
            modifier = Modifier.fillMaxWidth(),
        )

        AnimatedVisibility(visible = draft != saved) {
            Button(
                onClick = viewModel::onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SaveButtonHeight),
                shape = RoundedCornerShape(dimens.controlCornerRadius),
            ) {
                Text(stringResource(R.string.profile_save_button))
            }
        }

        SettingsLink(
            onClick = onSettingsClick,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
