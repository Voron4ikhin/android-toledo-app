package toledo24.pro.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import toledo24.pro.domain.usecase.profile.GetFavoriteUseCase

class ProfileViewModel(
    private val getFavoriteUseCase: GetFavoriteUseCase,
): ViewModel() {

    private val _favoriteCount = MutableStateFlow<Int>(0)
    val favoriteCount = _favoriteCount.asStateFlow()


    fun getFavoriteCount(){
        viewModelScope.launch{
            val favoriteCount = getFavoriteUseCase.getCount();
            _favoriteCount.emit(favoriteCount)
        }
    }


}