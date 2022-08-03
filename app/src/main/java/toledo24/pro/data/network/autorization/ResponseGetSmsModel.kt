package toledo24.pro.data.network.autorization

import com.google.gson.annotations.SerializedName

data class ResponseGetSmsModel(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: String,
)