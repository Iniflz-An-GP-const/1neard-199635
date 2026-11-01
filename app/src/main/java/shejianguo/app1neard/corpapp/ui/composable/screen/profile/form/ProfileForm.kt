package shejianguo.app1neard.corpapp.ui.composable.screen.profile.form

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.data.model.Difficulty
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens
import shejianguo.app1neard.corpapp.ui.viewmodel.ProfileForm as ProfileFormState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileForm(
    form: ProfileFormState,
    onNameChanged: (String) -> Unit,
    onAgeChanged: (String) -> Unit,
    onFitnessLevelChanged: (Difficulty) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val shape = RoundedCornerShape(dimens.cornerRadius)
    var levelMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        OutlinedTextField(
            value = form.name,
            onValueChange = onNameChanged,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.profile_name_label)) },
            singleLine = true,
            shape = shape,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next,
            ),
        )

        OutlinedTextField(
            value = form.ageText,
            onValueChange = onAgeChanged,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.profile_age_label)) },
            singleLine = true,
            shape = shape,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
        )

        ExposedDropdownMenuBox(
            expanded = levelMenuExpanded,
            onExpandedChange = { levelMenuExpanded = it },
        ) {
            OutlinedTextField(
                value = stringResource(form.fitnessLevel.titleRes),
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                label = { Text(stringResource(R.string.profile_fitness_level_label)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = levelMenuExpanded) },
                singleLine = true,
                shape = shape,
            )

            ExposedDropdownMenu(
                expanded = levelMenuExpanded,
                onDismissRequest = { levelMenuExpanded = false },
            ) {
                Difficulty.entries.forEach { level ->
                    DropdownMenuItem(
                        text = { Text(stringResource(level.titleRes)) },
                        onClick = {
                            onFitnessLevelChanged(level)
                            levelMenuExpanded = false
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsLink(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(dimens.cardCornerRadius),
            )
            .clickable(onClick = onClick)
            .padding(dimens.cardPadding),
        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = stringResource(R.string.profile_settings_link),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
