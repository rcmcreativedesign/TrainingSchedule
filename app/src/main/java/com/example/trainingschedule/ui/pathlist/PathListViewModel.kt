package com.example.trainingschedule.ui.pathlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingschedule.data.repository.TrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PathListViewModel @Inject constructor(
    private val repository: TrainingRepository
) : ViewModel() {

    val pathsWithSteps: StateFlow<List<com.example.trainingschedule.data.model.PathWithSteps>> = 
        repository.getPathsWithSteps()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addPath(name: String, description: String) {
        viewModelScope.launch {
            repository.addPath(name, description)
        }
    }
}
