package shejianguo.app1neard.corpapp.ui.composable.screen.shared.modifier.press

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.modifier.press.timing.PressTiming
import shejianguo.app1neard.corpapp.ui.composable.screen.shared.modifier.press.pressEffect

@Composable
fun Modifier.pressFeedback(
    interactionSource: InteractionSource,
    strength: Float = PressStrength.Button,
): Modifier {
    val progress = rememberPressProgress(interactionSource, PressTiming)

    return pressEffect(progress = { progress.value }, strength = strength)
}
