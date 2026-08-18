package com.example.trainingschedule.ui.pathdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingschedule.data.model.Step
import com.example.trainingschedule.data.repository.TrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PathDetailViewModel @Inject constructor(
    private val repository: TrainingRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pathId: Long = checkNotNull(savedStateHandle["pathId"])

    val path = repository.getPathById(pathId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val steps = repository.getStepsForPath(pathId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addStep(title: String, description: String, dueDate: Long) {
        viewModelScope.launch {
            repository.addStep(pathId, title, description, dueDate)
        }
    }

    fun toggleStep(step: Step) {
        viewModelScope.launch {
            repository.toggleStepCompletion(step)
        }
    }
}
