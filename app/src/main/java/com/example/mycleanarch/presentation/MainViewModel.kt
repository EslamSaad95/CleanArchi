package com.example.mycleanarch.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycleanarch.common.Resource
import com.example.mycleanarch.domain.entity.Receipe
import com.example.mycleanarch.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(
    val recipeUseCase: RecipeUseCase,
    @Named("auth_token") val token: String
) : ViewModel() {

    val loading = MutableLiveData(false)
    val recipeData = MutableLiveData<Receipe>()
    val error = MutableLiveData<String>()

    init {

        loading.value = true
        getRecipeDetails()
    }

    private fun getRecipeDetails() {
        viewModelScope.launch {
            loading.value = false
            recipeUseCase.invoke(token, 1).collect {
                when (it) {
                    is Resource.Loading -> loading.value = true
                    is Resource.Success -> {
                        loading.value = false
                        recipeData.value = it.data!!
                    }

                    else -> {
                        loading.value = false
                        error.value = it.message.toString()
                    }
                }
            }
        }

    }
}