package toledo24.pro.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import toledo24.pro.domain.usecase.basket.GetCardUseCase

class ProfileViewModel(): ViewModel() {

    val fragmentName = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            fragmentName.value = "profile"
        }
    }

}