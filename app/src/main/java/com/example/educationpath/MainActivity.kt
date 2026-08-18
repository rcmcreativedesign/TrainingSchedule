package com.rcmcreativedesign.educationpath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rcmcreativedesign.educationpath.ui.theme.EducationPathTheme
import com.rcmcreativedesign.educationpath.ui.navigation.AppNavigationGraph
import com.rcmcreativedesign.educationpath.ui.viewmodel.PathListViewModelFactory
import com.rcmcreativedesign.educationpath.ui.viewmodel.PathDetailViewModelFactory
import com.rcmcreativedesign.educationpath.ui.screens.PathListScreen
import com.rcmcreativedesign.educationpath.ui.screens.PathDetailScreen
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
