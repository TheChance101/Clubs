package com.devfalah.ui.screen.postDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devfalah.viewmodels.postDetails.PostDetailsArgs
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


const val POST_DETAILS_SCREEN = "POST_DETAILS_SCREEN"
fun NavController.navigateToPostDetails(
    id: Int, publisherId: Int, publisherName: String, publisherUrl: String
) {
    val encodedPublisherImageUrl =
        URLEncoder.encode(publisherUrl, StandardCharsets.UTF_8.toString())
    navigate("${POST_DETAILS_SCREEN}/${id}/${publisherId}/${publisherName}/${encodedPublisherImageUrl}")
}

fun NavGraphBuilder.postDetailsRoute(navController: NavController) {
    composable(
        route = "${POST_DETAILS_SCREEN}/{${PostDetailsArgs.POST_ID}}/{${PostDetailsArgs.PUBLISHER_ID}}/{${PostDetailsArgs.PUBLISHER_NAME}}/{${PostDetailsArgs.PUBLISHER_IMAGE_URL}}",
        arguments = listOf(
            navArgument(PostDetailsArgs.POST_ID) { NavType.IntType },
            navArgument(PostDetailsArgs.PUBLISHER_ID) { NavType.IntType },
            navArgument(PostDetailsArgs.PUBLISHER_NAME) { NavType.StringType },
            navArgument(PostDetailsArgs.PUBLISHER_IMAGE_URL) { NavType.StringType },
        )
    ) {
        PostDetailsScreen(navController)
    }
}