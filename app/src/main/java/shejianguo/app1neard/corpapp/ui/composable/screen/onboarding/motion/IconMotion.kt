package shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.motion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.motion.timing.IconMotionTiming
import shejianguo.app1neard.corpapp.ui.composable.screen.onboarding.motion.iconMotionEffect

@Composable
fun Modifier.iconMotion(index: Int): Modifier =
    if (LocalOnboardingPageActive.current) iconMotionEffect(index, IconMotionTiming) else this
