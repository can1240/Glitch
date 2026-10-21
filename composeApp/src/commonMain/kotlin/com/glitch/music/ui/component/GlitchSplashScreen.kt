package com.glitch.music.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.app_icon
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

/**
 * YouTube-style splash: black background, logo scales in with a subtle glitch offset,
 * then fades out into the main app.
 */
@Composable
fun GlitchSplashScreen(
    onFinished: () -> Unit,
) {
    val scale = remember { Animatable(0.4f) }
    val alpha = remember { Animatable(0f) }
    val exitAlpha = remember { Animatable(1f) }

    val infinite = rememberInfiniteTransition(label = "glitch")
    val glitchX by infinite.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(60, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "glitchX",
    )
    val glitchY by infinite.animateFloat(
        initialValue = 1f,
        targetValue = -1f,
        animationSpec = infiniteRepeatable(
            animation = tween(45, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "glitchY",
    )

    var showGlitch by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Appear
        alpha.animateTo(1f, tween(350, easing = FastOutSlowInEasing))
        scale.animateTo(1f, tween(700, easing = FastOutSlowInEasing))
        delay(900)
        showGlitch = false
        // Hold
        delay(400)
        // Fade out
        exitAlpha.animateTo(0f, tween(450, easing = FastOutSlowInEasing))
        onFinished()
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.Black)
                .alpha(exitAlpha.value),
        contentAlignment = Alignment.Center,
    ) {
        // RGB split / glitch layers
        if (showGlitch) {
            Image(
                painter = painterResource(Res.drawable.app_icon),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier =
                    Modifier
                        .size(180.dp)
                        .offset(x = (glitchX * 1.5f).dp, y = glitchY.dp)
                        .scale(scale.value)
                        .alpha(0.35f)
                        .graphicsLayer { this.translationX = glitchX * 2f },
            )
            Image(
                painter = painterResource(Res.drawable.app_icon),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier =
                    Modifier
                        .size(180.dp)
                        .offset(x = (-glitchX).dp, y = (-glitchY).dp)
                        .scale(scale.value)
                        .alpha(0.25f),
            )
        }
        // Main logo
        Image(
            painter = painterResource(Res.drawable.app_icon),
            contentDescription = "Glitch",
            contentScale = ContentScale.Fit,
            modifier =
                Modifier
                    .size(180.dp)
                    .scale(scale.value)
                    .alpha(alpha.value),
        )
    }
}
