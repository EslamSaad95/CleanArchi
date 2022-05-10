package com.example.mycleanarch.domain.usecase

import android.util.Log
import com.example.mycleanarch.common.Resource
import com.example.mycleanarch.domain.RecipeRepository
import com.example.mycleanarch.domain.entity.Receipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecipeUseCase @Inject constructor(val recipeRepo: RecipeRepository) {
    suspend fun invoke(token: String, id: Int): Flow<Resource<Receipe>> = flow {
        try {
            emit(Resource.Loading<Receipe>())
            val coins = recipeRepo.getRecipe(token, id)
            Log.i("succ", "t")
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            Log.i("succ", "t")
            emit(Resource.Error<Receipe>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            Log.i("succ", "t")
            emit(Resource.Error<Receipe>("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error<Receipe>("Couldn't reach server. Check your internet connection."))
        }

    }
}