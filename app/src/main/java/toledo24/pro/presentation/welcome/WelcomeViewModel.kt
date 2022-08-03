package toledo24.pro.presentation.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import toledo24.pro.domain.usecase.welcome.CheckingUserInfoRoomUseCase

class WelcomeViewModel(
    private val checkingUserInfoRoomUseCase: CheckingUserInfoRoomUseCase
    ): ViewModel() {

    private val _showAuthActivity = MutableSharedFlow<Boolean>()
    val showAuthActivity = _showAuthActivity.asSharedFlow()

    init{
        viewModelScope.launch(Dispatchers.IO){
            val result = checkingUserInfoRoomUseCase.checkUserData()
            _showAuthActivity.emitAll(
                flow {
                    emit(result)
                }
            )
        }
    }

}