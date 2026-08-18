package com.example.trainingschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trainingschedule.ui.pathdetail.PathDetailScreen
import com.example.trainingschedule.ui.pathlist.PathListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedBundle: Bundle?) {
        super.onCreate(savedBundle)
        setContent {
            TrainingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrainingAppNavHost()
                }
            }
        }
    }
}

@Composable
fun TrainingTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}

@Composable
fun TrainingAppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "path_list") {
        composable("path_list") {
            PathListScreen(
                onPathClick = { pathId ->
                    navController.navigate("path_detail/$pathId")
                }
            )
        }
        composable(
            route = "path_detail/{pathId}",
            arguments = listOf(navArgument("pathId") { type = NavType.LongType })
        ) {
            PathDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
