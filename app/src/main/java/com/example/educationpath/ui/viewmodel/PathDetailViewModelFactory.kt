package com.example.educationpath.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import com.example.educationpath.repository.EducationRepository
import com.example.educationpath.ui.viewmodel.PathDetailViewModel
import javax.inject.Inject

class PathDetailViewModelFactory @Inject constructor(
    private val repository: EducationRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModelProvider.Factory {
    
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) throws java.lang.reflect.InvocationTargetException {
        if (modelClass.isAssignableFrom(PathDetailViewModel.class)) {
            return (T) new PathDetailViewModel(repository, savedStateHandle);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}