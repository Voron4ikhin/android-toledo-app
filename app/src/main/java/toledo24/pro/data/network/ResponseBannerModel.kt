package toledo24.pro.data.network

import com.google.gson.annotations.SerializedName


data class ResponseBannerModel(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: Map<String, Any?>
)
