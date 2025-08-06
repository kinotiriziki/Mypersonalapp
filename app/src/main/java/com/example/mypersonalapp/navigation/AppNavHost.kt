package com.example.mypersonalapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mypersonalapp.ui.theme.screens.clients.AddClientScreen
import com.example.mypersonalapp.ui.theme.screens.clients.ClientListScreen
import com.example.mypersonalapp.ui.theme.screens.clients.UpdateClientScreen
import com.example.mypersonalapp.ui.theme.screens.dashboard.DashboardScreen
import com.example.mypersonalapp.ui.theme.screens.login.loginScreen
import com.example.mypersonalapp.ui.theme.screens.register.registerScreen

@Composable
fun AppNavHost(navController: NavHostController= rememberNavController(),startDestination: String= ROUTE_REGISTER){
    NavHost(navController=navController, startDestination = startDestination){
        composable(ROUTE_REGISTER) { registerScreen(navController) }
        composable(ROUTE_LOGIN) { loginScreen(navController)  }
        composable(ROUTE_DASHBOARD) { DashboardScreen(navController) }
        composable(ROUTE_ADDCLIENT) { AddClientScreen(navController) }
        composable(ROUTE_VIEWCLIENT) { ClientListScreen(navController) }
        composable(ROUTE_UPDATECLIENT,
            arguments = listOf(navArgument("clientId")
            {type = NavType.StringType})) {
            backStackEntry ->
            val clientId = backStackEntry.arguments?.getString("clientId")!!
            UpdateClientScreen(navController,clientId)
        }


    }
}