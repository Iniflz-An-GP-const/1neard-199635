package shejianguo.app1neard.corpapp.ui.composable.screen.home.filter.shell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import shejianguo.app1neard.corpapp.ui.composable.screen.home.filter.FilterChipItem
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

@Composable
fun WorkoutFilterRowShell(
    items: List<FilterChipItem>,
    modifier: Modifier = Modifier,
    content: @Composable (FilterChipItem) -> Unit,
) {
    val dimens = LocalDimens.current

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimens.screenPadding),
        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        items(items, key = { item -> item.label }) { item ->
            Box(modifier = Modifier.animateItem()) { content(item) }
        }
    }
}
