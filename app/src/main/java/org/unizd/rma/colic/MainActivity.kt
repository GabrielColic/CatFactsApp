package org.unizd.rma.colic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.unizd.rma.colic.ui.screens.CatFactListScreen
import org.unizd.rma.colic.ui.screens.CatFactDetailScreen
import org.unizd.rma.colic.viewmodel.CatFactViewModel
import org.unizd.rma.colic.ui.theme.CatFactsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatFactsAppTheme {
                val navController = rememberNavController()
                val viewModel: CatFactViewModel = viewModel()

                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        CatFactListScreen(viewModel, navController)
                    }
                    composable("detail/{fact}/{length}") { backStackEntry ->
                        val fact = backStackEntry.arguments?.getString("fact") ?: ""
                        val length = backStackEntry.arguments?.getString("length")?.toIntOrNull() ?: 0
                        CatFactDetailScreen(fact, length, navController)
                    }
                }
            }
        }
    }
}
