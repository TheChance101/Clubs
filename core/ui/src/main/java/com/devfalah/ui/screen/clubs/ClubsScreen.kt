package com.devfalah.ui.screen.clubs

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.screen.clubRequests.navigateToClubRequests

@Composable
fun ClubsScreen(
    navController: NavController,
) {
    Column() {
        AppBar(title = stringResource(R.string.clubs), navHostController = navController)

        Button(onClick = { navController.navigateToClubRequests(clubId = 256, ownerId = 6) }) {
            Text(text = "Requests")
        }
    }

}
