package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor

@Composable
fun ReadMorePopup(
    text: String,
    onShowDescription: () -> Unit,
    modifier: Modifier = Modifier,
    minimizedMaxLines: Int = 1,
    style: TextStyle
) {
    var cutText by remember(text) { mutableStateOf<String?>(null) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val seeMoreSizeState = remember { mutableStateOf<IntSize?>(null) }
    val seeMoreOffsetState = remember { mutableStateOf<Offset?>(null) }

    val expanded = false
    val textLayoutResult = textLayoutResultState.value
    val seeMoreSize = seeMoreSizeState.value
    val seeMoreOffset = seeMoreOffsetState.value

    LaunchedEffect(text, expanded, textLayoutResult, seeMoreSize) {
        val lastLineIndex = minimizedMaxLines - 1
        if (!expanded && textLayoutResult != null && seeMoreSize != null
            && lastLineIndex + 1 == textLayoutResult.lineCount
            && textLayoutResult.isLineEllipsized(lastLineIndex)
        ) {
            var lastCharIndex = textLayoutResult.getLineEnd(lastLineIndex, visibleEnd = true) + 1
            var charRect: Rect
            do {
                lastCharIndex -= 1
                charRect = textLayoutResult.getCursorRect(lastCharIndex)
            } while (
                charRect.left > textLayoutResult.size.width - seeMoreSize.width
            )
            seeMoreOffsetState.value = Offset(charRect.left, charRect.bottom - seeMoreSize.height)
            cutText = text.substring(startIndex = 0, endIndex = lastCharIndex)
        }
    }

    Box(modifier) {
        Text(
            text = cutText ?: text,
            modifier = Modifier.padding(horizontal = 16.dp),
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResultState.value = it },
            style = style
        )
        if (!expanded) {
            val density = LocalDensity.current
            Text(
                text = stringResource(id = R.string.read_more),
                onTextLayout = { seeMoreSizeState.value = it.size },
                style = TextStyle(
                    color = WhiteColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    background = MaterialTheme.colors.onBackground,
                    fontFamily = PlusJakartaSans
                ),
                modifier = Modifier
                    .then(
                        if (seeMoreOffset != null)
                            Modifier.offset(
                                x = with(density) { seeMoreOffset.x.toDp() },
                                y = with(density) { seeMoreOffset.y.toDp() },
                            )
                        else
                            Modifier
                    )
                    .nonRippleEffect { onShowDescription() }
                    .alpha(if (seeMoreOffset != null) 1f else 0f)
            )
        }
    }
}