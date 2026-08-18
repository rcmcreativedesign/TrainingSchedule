package com.example.educationpath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.educationpath.ui.theme.EducationPathTheme
import com.example.educationpath.ui.navigation.AppNavigationGraph
import com.example.educationpath.ui.viewmodel.PathListViewModelFactory
import com.example.educationpath.ui.viewmodel.PathDetailViewModelFactory
import com.example.educationpath.ui.screens.PathListScreen
import com.example.educationpath.ui.screens.PathDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() { // ... existing code ... 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EducationPathTheme { // ... existing code ... 
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigationGraph()
                }
            }
        }
    }
}
