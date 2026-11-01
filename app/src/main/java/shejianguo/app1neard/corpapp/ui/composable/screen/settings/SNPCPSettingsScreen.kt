package shejianguo.app1neard.corpapp.ui.composable.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.ui.composable.approot.LocalContentBottomPadding
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.button.ResetDataButton
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.card.AppInfoCard
import shejianguo.app1neard.corpapp.ui.composable.screen.profile.confirm.ResetDataConfirmDialog
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.sectiontitle.SectionTitle
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.viewmodel.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SNPCPSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel(),
) {
    val dimens = LocalDimens.current
    var showResetDialog by remember { mutableStateOf(false) }

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
    ) {
        SectionTitle(text = stringResource(R.string.profile_screen_about_section_title))

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        AppInfoCard(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(dimens.sectionGap))

        SectionTitle(text = stringResource(R.string.profile_screen_data_section_title))

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        ResetDataButton(
            onClick = { showResetDialog = true },
            modifier = Modifier.fillMaxWidth(),
        )
    }

    if (showResetDialog) {
        ResetDataConfirmDialog(
            onConfirm = {
                viewModel.onResetDataConfirmed()
                showResetDialog = false
            },
            onDismiss = { showResetDialog = false },
        )
    }
}
