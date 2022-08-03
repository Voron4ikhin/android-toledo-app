package toledo24.pro.data.network.autorization

import com.google.gson.annotations.SerializedName

data class ResponseUserInfoModel(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: UserModel,
)

data class UserModel(
    @SerializedName("USER_DATA")
    val userData: UserData,
    @SerializedName("AUTH_TOKEN")
    val authToken: String,
    @SerializedName("BASKET_LIST")
    val basketList: List<String>,
    @SerializedName("Compare")
    val compare: List<String>,
    @SerializedName("Favorite")
    val favorite: List<String>,
)

data class UserData(
    @SerializedName("AUTHORIZED")
    val authorized: String,
    @SerializedName("USER_ID")
    val userId: String,
    @SerializedName("LOGIN")
    val login: String,
    @SerializedName("EMAIL")
    val email: String,
    @SerializedName("NAME")
    val name: String,
    @SerializedName("FIRST_NAME")
    val firstName: String,
    @SerializedName("SECOND_NAME")
    val secondName: String,
    @SerializedName("LAST_NAME")
    val lastName: String,
)
