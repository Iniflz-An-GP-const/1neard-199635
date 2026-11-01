package shejianguo.app1neard.corpapp.ui.composable.screen.home.layout

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import shejianguo.app1neard.corpapp.R
import shejianguo.app1neard.corpapp.data.model.TipModel
import shejianguo.app1neard.corpapp.ui.composable.screen.home.greeting.HomeGreeting
import shejianguo.app1neard.corpapp.ui.composable.screen.home.tips.TipCard
import shejianguo.app1neard.corpapp.ui.theme.LocalDimens

fun LazyListScope.homeTopSection(
    tips: List<TipModel>,
) {
    item {
        val dimens = LocalDimens.current
        HomeGreeting(
            title = stringResource(R.string.home_greeting_title),
            text = stringResource(R.string.home_greeting_text),
            modifier = Modifier
                .padding(horizontal = dimens.screenPadding),
        )
    }

    if (tips.isNotEmpty()) {
        item {
            val dimens = LocalDimens.current
            TipCard(
                tips = tips,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.screenPadding),
            )
        }
    }
}
