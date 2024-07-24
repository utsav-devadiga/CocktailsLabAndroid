package com.applabs.cockatilslab.cocktailsList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applabs.cockatilslab.cocktailsList.domain.repository.CocktailListRepository
import com.applabs.cockatilslab.cocktailsList.util.Category
import com.applabs.cockatilslab.cocktailsList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailListViewmodel @Inject constructor(
    private val cocktailListRepository: CocktailListRepository
) : ViewModel() {
    private var _cocktailListState = MutableStateFlow(CocktailListState())

    val cocktailListState = _cocktailListState.asStateFlow()

    init {
        getPopularCocktails(forceFetchFromRemote = true)
    }

    fun onEvent(event: CocktailListUIEvent) {
        when (event) {
            CocktailListUIEvent.Navigate -> {
                _cocktailListState.update {
                    it.copy(
                        isCurrentPopularScreen = !cocktailListState.value.isCurrentPopularScreen
                    )
                }
            }

            is CocktailListUIEvent.Paginate -> TODO()
        }
    }

    private fun getPopularCocktails(forceFetchFromRemote: Boolean) {

        viewModelScope.launch {
            _cocktailListState.update {
                it.copy(isLoading = true)
            }

            cocktailListRepository.getCocktailList(
                forceFetchFromRemote, Category.POPULAR
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _cocktailListState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Success -> {

                        result.data?.let { popularList ->
                            _cocktailListState.update {
                                it.copy(
                                    popularCocktailList = cocktailListState.value.popularCocktailList
                                            + popularList.shuffled()
                                )
                            }
                        }

                    }

                    is Resource.Loading -> {
                        _cocktailListState.update {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }

    }
}