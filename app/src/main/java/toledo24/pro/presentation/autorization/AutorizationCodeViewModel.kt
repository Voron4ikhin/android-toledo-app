package toledo24.pro.presentation.autorization

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import toledo24.pro.domain.usecase.autorization.CheckingCodeUserUseCase


class AutorizationCodeViewModel(
    private val checkingCodeUserUseCase: CheckingCodeUserUseCase
    ):ViewModel(){

    private val _checkStatusCode = MutableStateFlow<Boolean>(false)
    val checkStatusCode = _checkStatusCode.asStateFlow()

    //Отправляем sms код и получаем информацию о пользователе и токен авторизации
    fun checkAutorizationCode(phone:String, code: String){
        viewModelScope.launch(Dispatchers.IO){
            val result = checkingCodeUserUseCase.checkStateSmsCode(phone, code)
            if(result.result.userData.userId.isNotEmpty()){
                checkingCodeUserUseCase.saveUserInfoRoom(result.result.userData)
                _checkStatusCode.emitAll(
                    flow {
                        emit(true)
                    }
                )}
        }
    }

    //Проверка введенного sms кода
    fun validationUserCode(code: String): Boolean {
        val REG = "([0-9]{6})"
        return (code.matches(Regex(REG)))
    }

    override fun onCleared() {
        super.onCleared()
    }

}