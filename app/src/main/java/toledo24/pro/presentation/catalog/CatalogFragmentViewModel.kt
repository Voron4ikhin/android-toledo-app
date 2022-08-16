package toledo24.pro.presentation.catalog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import toledo24.pro.data.room.catalog.CatalogEntity
import toledo24.pro.domain.usecase.catalog.GetCategoriesUseCase
import toledo24.pro.domain.usecase.catalog.ShowCategoriesUseCase

class CatalogFragmentViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val showCategoriesUseCase: ShowCategoriesUseCase,
): ViewModel() {

    private val _categoriesList = MutableSharedFlow<List<CatalogEntity>>()
    val categoriesList = _categoriesList.asSharedFlow()

    private val _stateClickItem = MutableStateFlow<String>("")
    val stateClickItem = _stateClickItem.asStateFlow()

    private val _countCategories =  MutableSharedFlow<Int>()
    val countCategories = _countCategories.asSharedFlow()

    val fragmentName = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            val catalog = getCategoriesUseCase.execute();
            getCategoriesUseCase.saveCatalogInRoom(catalog)
            val res = showCategoriesUseCase.getListCategories(_stateClickItem.value)
            fragmentName.value = "catalog"
            _categoriesList.emit(res)
        }
    }


    fun getRoomItemsList(item: String){
        viewModelScope.launch {
            _stateClickItem.emit(item)
            _categoriesList.emit(showCategoriesUseCase.getListCategories(item))
        }
    }

    fun getCountCategoriesList(item: String){
        viewModelScope.launch {
            _countCategories.emit(showCategoriesUseCase.getListCategories(item).size)
        }
    }

}