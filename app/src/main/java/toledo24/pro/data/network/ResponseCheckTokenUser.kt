package toledo24.pro.data.network

import com.google.gson.annotations.SerializedName

data class ResponseCheckTokenUser(

    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: Boolean

)
