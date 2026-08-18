package com.example.educationpath.ui.screens

import androidx.compose.foundation.layout.*\nimport androidx.compose.material3.*\nimport androidx.compose.runtime.Composable\nimport androidx.compose.runtime.collectAsState\nimport androidx.compose.runtime.getValue\nimport androidx.compose.ui.Modifier\nimport androidx.compose.ui.unit.dp\nimport androidx.hilt.navigation.compose.hiltViewModel\nimport androidx.lifecycle.viewmodel.compose.viewModel\nimport com.example.educationpath.data.model.Step\nimport com.example.educationpath.ui.viewmodel.PathDetailViewModel\nimport com.example.educationpath.ui.viewmodel.PathDetailViewModelFactory\n
@Composable
fun PathDetailScreen(
    viewModel: PathDetailViewModel = hiltViewModel<PathDetailViewModel>(),
    modifier: Modifier = Modifier
) {
    val steps by viewModel.steps.collectAsState()

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text("Steps for this Path", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn{
            items(steps) { step ->
                StepItem(step = step) {
                    // Action to toggle completion
                    viewModel.toggleStepCompletion(step.id)
                }
            }
        }
        
        // Display completion status based on viewModel logic
        if (viewModel.isPathCompleted()) { 
            Text("🎉 Congratulations! This Path is Complete!", color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun StepItem(step: Step, onToggle: () -> Unit) {
    Row(modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Checkbox(checked = step.isCompleted, onCheckedChange = { onToggle() })
        Spacer(modifier = Modifier.width(16.dp))
        Column \
        {
            Text(text = step.title, style = MaterialTheme.typography.titleMedium)
            Text(text = step.description, style = MaterialTheme.typography.bodySmall)
            // Optionally display due date here
        }
    }
}
