package shejianguo.app1neard.corpapp.ui.composable.approot.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path

// The stripes rise from the left edge to the right one. All values are shares of the screen height.
private const val LeftY = 0.2f
private const val RightY = 0.02f
private const val MainThickness = 0.1f
private const val Gap = 0.025f
private const val SecondThickness = 0.035f

// Each stripe is stronger on the left and fades toward the right edge.
private const val MainStartAlpha = 0.09f
private const val MainEndAlpha = 0.03f
private const val SecondStartAlpha = 0.07f
private const val SecondEndAlpha = 0.025f

/**
 * Two diagonal stripes across the top of the screen, like the stripes on a team jersey: a wide
 * one and a thin one next to it, both in the primary color. Static, drawn once for the screen size.
 */
@Composable
fun AppBackground(modifier: Modifier = Modifier) {
    val color = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier
            .fillMaxSize()
            .drawWithCache {
                val mainTop = LeftY to RightY
                val secondTop = (LeftY + MainThickness + Gap) to (RightY + MainThickness + Gap)

                val main = stripe(mainTop.first, mainTop.second, MainThickness)
                val second = stripe(secondTop.first, secondTop.second, SecondThickness)

                val mainBrush = Brush.horizontalGradient(
                    colors = listOf(color.copy(alpha = MainStartAlpha), color.copy(alpha = MainEndAlpha)),
                    startX = 0f,
                    endX = size.width,
                )
                val secondBrush = Brush.horizontalGradient(
                    colors = listOf(color.copy(alpha = SecondStartAlpha), color.copy(alpha = SecondEndAlpha)),
                    startX = 0f,
                    endX = size.width,
                )

                onDrawBehind {
                    drawPath(path = main, brush = mainBrush)
                    drawPath(path = second, brush = secondBrush)
                }
            },
    )
}

/** A parallelogram from edge to edge whose top edge runs from [leftY] to [rightY]. */
private fun CacheDrawScope.stripe(
    leftY: Float,
    rightY: Float,
    thickness: Float,
): Path {
    val h = size.height
    val w = size.width

    return Path().apply {
        moveTo(0f, leftY * h)
        lineTo(w, rightY * h)
        lineTo(w, (rightY + thickness) * h)
        lineTo(0f, (leftY + thickness) * h)
        close()
    }
}
