package com.example.trainingschedule.ui.pathdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingschedule.data.model.Step
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PathDetailScreen(
    onBack: () -> Unit,
    viewModel: PathDetailViewModel = hiltViewModel()
) {
    val path by viewModel.path.collectAsState()
    val steps by viewModel.steps.collectAsState()
    var showAddStepDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(path?.name ?: "Path Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddStepDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Step")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            path?.let {
                Text(
                    text = it.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
            
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(steps) { step ->
                    StepItem(
                        step = step,
                        onToggle = { viewModel.toggleStep(step) }
                    )
                }
            }
        }

        if (showAddStepDialog) {
            AddStepDialog(
                onDismiss = { showAddStepDialog = false },
                onConfirm = { title, desc, date ->
                    viewModel.addStep(title, desc, date)
                    showAddStepDialog = false
                }
            )
        }
    }
}

@Composable
fun StepItem(
    step: Step,
    onToggle: () -> Unit
) {
    val dateFormatter = remember { SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = step.isCompleted,
                onCheckedChange = { onToggle() }
            )
            Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                Text(text = step.title, style = MaterialTheme.typography.titleMedium)
                Text(text = step.description, style = MaterialTheme.typography.bodySmall)
                Text(
                    text = "Due: ${dateFormatter.format(Date(step.dueDate))}",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun AddStepDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, Long) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    // For simplicity, just using current time for due date in this basic version
    // A real app would have a date picker

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Step") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Step Title") }
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
            Button(onClick = { onConfirm(title, description, System.currentTimeMillis()) }) {
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
