package com.devfalah.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.devfalah.ui.R

@Composable
fun ErrorItem(
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
) {
    val context = LocalContext.current
    val customView = remember { LottieAnimationView(context) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AndroidView({ customView }) { view ->
            with(view) {
                setAnimation(R.raw.error)
                playAnimation()
                repeatCount = LottieDrawable.INFINITE
                repeatMode = LottieDrawable.RESTART
            }
        }

        HeightSpacer24()

        Button(onClick = onClickRetry) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}


@Preview
@Composable
fun PreviewErrorItem() {
    ErrorItem(
        onClickRetry = {}
    )
}