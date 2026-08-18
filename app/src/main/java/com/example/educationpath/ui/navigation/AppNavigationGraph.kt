package com.example.educationpath.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.educationpath.ui.screens.PathDetailScreen
import com.example.educationpath.ui.screens.PathListScreen
import com.example.educationpath.ui.viewmodel.PathListViewModel\nimport com.example.educationpath.ui.viewmodel.PathDetailViewModel

@Composable
fun AppNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "path_list") { 
        composable("path_list") { 
            // Inject ViewModel in the composable scope
            val listViewModel: PathListViewModel = hiltViewModel() // Assuming hilt setup
            PathListScreen(viewModel = listViewModel)
        }
        composable("path_detail/{pathId}") { backStackEntry ->
            val pathId = backStackEntry.arguments?.getString("pathId")?.toLongOrNull() ?: 0L
            // Inject ViewModel specific to the path ID
            val detailViewModel: PathDetailViewModel = hiltViewModel<PathDetailViewModel> { parameters -> 
                // Manual parameter passing to simulate hilt/nav arg passing if necessary
                // For now, assuming the ViewModel factory handles pathId injection correctly
                PathDetailViewModelFactory(parameters)
            }
            PathDetailScreen(viewModel = detailViewModel)
        }
    }
}