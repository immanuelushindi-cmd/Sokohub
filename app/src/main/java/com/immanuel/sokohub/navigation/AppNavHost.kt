package com.immanuel.sokohub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.immanuel.sokohub.ui.screens.about.AboutScreen
import com.immanuel.sokohub.ui.screens.auth.LoginScreen
import com.immanuel.sokohub.ui.screens.auth.RegisterScreen
import com.immanuel.sokohub.ui.screens.home.HomeScreen
import com.immanuel.sokohub.ui.screens.intent.IntentScreen
import com.immanuel.sokohub.ui.screens.onboarding.OnBoardingScreen
import com.immanuel.sokohub.ui.screens.scaffold.ScaffoldScreen

import com.immanuel.sokohub.ui.screens.service.ServiceScreen
import com.immanuel.sokohub.ui.screens.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }

        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUT_REGISTER) {
            RegisterScreen(navController)
        }

        composable(ROUT_ONBOARDING) {
            OnBoardingScreen(navController)
        }

        composable(ROUT_SERVICE) {
            ServiceScreen(navController)
        }

        composable(ROUT_INTENT) {
            IntentScreen(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_SCAFFOLD) {
            ScaffoldScreen(navController)
        }

    }
}