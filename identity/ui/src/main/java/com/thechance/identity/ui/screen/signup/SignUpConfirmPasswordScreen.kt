package com.thechance.identity.ui.screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.ui.extension.navigateToSignupFullName
import com.thechance.identity.viewmodel.signup.SignupViewModel
import com.thechance.identity.viewmodel.signup.UserUIState


@Composable
fun SignUpConfirmPasswordScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    SignUpConfirmPasswordContent(
        state = state,
        onChangePassword = viewModel::onChangePassword,
        onChangeConfirmPassword = viewModel::onChangeConfirmPassword,
        onClickSignupFirstNameScreen = {
            navController.navigateToSignupFullName()
        },
        onClickBack = { navController.navigateUp() }
    )
}

@Composable
fun SignUpConfirmPasswordContent(
    state: UserUIState,
    onChangePassword: (String) -> Unit,
    onChangeConfirmPassword: (String) -> Unit,
    onClickSignupFirstNameScreen: () -> Unit,
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        BackButton(onClick = onClickBack)

        SpacerVertical(height = 36.dp)
        AuthText(
            text = stringResource(id = R.string.sign_up),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        SpacerVertical(height = 8.dp)
        EmailDescriptionText(
            text1 = stringResource(id = R.string.using),
            color1 = LightSecondaryBlackColor,
            text2 = stringResource(id = R.string.email_place_holder),
            color2 = LightPrimaryBrandColor,
            text3 = stringResource(id = R.string.to_login)
        )

        SpacerVertical(height = 24.dp)
        AuthText(
            text = stringResource(id = R.string.your_password),
            style = Typography.body2,
            color = LightSecondaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        SpacerVertical(height = 14.dp)
        InputText(
            type = KeyboardType.Password,
            image = R.drawable.ic_close,
            placeHolder = stringResource(id = R.string.password_place_holder),
            text = state.password,
            onTextChange = onChangePassword
        ) {

        }

        SpacerVertical(height = 24.dp)
        AuthText(
            text = stringResource(id = R.string.confirm_password),
            style = Typography.body2,
            color = LightSecondaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        SpacerVertical(height = 14.dp)
        InputText(
            type = KeyboardType.Password,
            image = R.drawable.ic_close,
            placeHolder = stringResource(id = R.string.password_place_holder),
            text = state.confirmPassword,
            onTextChange = onChangeConfirmPassword
        ) {

        }

        SpacerVertical(height = 24.dp)
        AuthButton(
            buttonModifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            onClick = onClickSignupFirstNameScreen,
            text = stringResource(id = R.string.continue_label)
        )
    }
}