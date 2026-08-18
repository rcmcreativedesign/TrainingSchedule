package com.example.trainingschedule.ui.pathlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingschedule.data.model.PathWithSteps

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PathListScreen(
    onPathClick: (Long) -> Unit,
    viewModel: PathListViewModel = hiltViewModel()
) {
    val paths by viewModel.pathsWithSteps.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Education Paths") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Path")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(paths) { pathWithSteps ->
                PathItem(
                    pathWithSteps = pathWithSteps,
                    onClick = { onPathClick(pathWithSteps.path.id) }
                )
            }
        }

        if (showDialog) {
            AddPathDialog(
                onDismiss = { showDialog = false },
                onConfirm = { name, desc ->
                    viewModel.addPath(name, desc)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun PathItem(
    pathWithSteps: PathWithSteps,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = pathWithSteps.path.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = pathWithSteps.path.description, style = MaterialTheme.typography.bodyMedium)
            
            if (pathWithSteps.isComplete) {
                Text(
                    text = "Completed!",
                    color = Color.Green,
                    modifier = Modifier.padding(top = 8.dp)
                )
            } else {
                val completedSteps = pathWithSteps.steps.count { it.isCompleted }
                val totalSteps = pathWithSteps.steps.size
                Text(
                    text = "Progress: $completedSteps/$totalSteps steps",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun AddPathDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Path") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Path Name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, description) }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
