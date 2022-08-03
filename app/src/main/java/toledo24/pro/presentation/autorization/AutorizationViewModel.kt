package toledo24.pro.presentation.autorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import toledo24.pro.domain.usecase.autorization.CheckingPhoneUserUseCase


class AutorizationViewModel(private val checkingPhoneUserUseCase: CheckingPhoneUserUseCase): ViewModel() {

    private val _checkStatePhone = MutableSharedFlow<String>()
    val checkStatePhone = _checkStatePhone.asSharedFlow()

    //Отправляем номер телефона и получаем ответ от сервера
    fun getAutorizationCode(phone: String){
        viewModelScope.launch(Dispatchers.IO){
            val result = checkingPhoneUserUseCase.getStateSmsCode(phone)
            if (result.result == "success"){
                _checkStatePhone.emitAll(
                    flow {
                        emit(result.result)
                    }
                )}
        }
    }

    //Проверка введенного номера телефона
    fun validationUserPhone(phone: String): Boolean {
        val REG = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$"
        return (phone.matches(Regex(REG)))
    }

    override fun onCleared() {
        super.onCleared()
    }

}