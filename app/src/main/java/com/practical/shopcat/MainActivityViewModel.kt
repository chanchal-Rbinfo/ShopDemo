package com.practical.shopcat

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.practical.shopcat.data.DataRepositorySource
import com.practical.shopcat.data.Resource
import com.practical.shopcat.model.ShopByType
import com.practical.shopcat.model.ShopItem
import com.practical.shopcat.utils.SingleEvent
import com.practical.shopcat.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainActivityViewModel  @Inject constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel()  {

    val recipesLiveDataPrivate = MutableLiveData<Resource<ShopByType>>()
    val shopsLiveData: LiveData<Resource<ShopByType>> get() = recipesLiveDataPrivate

    val recipeSearchFoundPrivate: MutableLiveData<ShopItem> = MutableLiveData()
    val shopSearchFound: LiveData<ShopItem> get() = recipeSearchFoundPrivate

    val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> get() = noSearchFoundPrivate

    private val openRecipeDetailsPrivate = MutableLiveData<SingleEvent<ShopItem>>()
    val openRecipeDetails: LiveData<SingleEvent<ShopItem>> get() = openRecipeDetailsPrivate

    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun getRecipes() {
        viewModelScope.launch {
            recipesLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestShopByCat().collect {
                    recipesLiveDataPrivate.value = it
                }
            }
        }
    }

    fun openRecipeDetails(recipe: ShopItem) {
        openRecipeDetailsPrivate.value = SingleEvent(recipe)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }

    fun onSearchClick(recipeName: String) {
        recipesLiveDataPrivate.value?.data?.recipesList?.let {
            if (it.isNotEmpty()) {
                for (shopItem in it) {
//                    if (shopItem.name.toLowerCase(Locale.ROOT).contains(recipeName.toLowerCase(Locale.ROOT))) {
//                        recipeSearchFoundPrivate.value = shopItem
//                        return
//                    }
                }
            }
        }
        return noSearchFoundPrivate.postValue(Unit)
    }
}