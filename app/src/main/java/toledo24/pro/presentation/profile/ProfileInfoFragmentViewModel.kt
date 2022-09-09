package toledo24.pro.presentation.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import toledo24.pro.data.room.user.UserEntity
import toledo24.pro.domain.usecase.profile.GetUserInfoUseCase

class ProfileInfoFragmentViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase
):ViewModel(){

    val userInfo = MutableLiveData<UserEntity>()

    init{

        viewModelScope.launch {
            userInfo.value = getUserInfoUseCase.getUserInfo()
            Log.d("tag", "Kekw data user")
        }

    }

    fun exit(userInfo : UserEntity){
        viewModelScope.launch {
            getUserInfoUseCase.deleteUserInfo(userInfo)
            Log.d("tag", "Удалили данные")
        }
    }

}