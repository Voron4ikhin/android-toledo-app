package toledo24.pro.data.network.catalog

import com.google.gson.annotations.SerializedName

data class ResponseAnalogRelatedModel(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: AnalogRelatedModel
)

data class AnalogRelatedModel(
    @SerializedName("ANALOG_PRODUCT")
    val ANALOG_PRODUCT: List<DetailProductModel>,
//    @SerializedName("RELATED_PRODUCTS")
//    val RELATED_PRODUCTS: List<DetailProductModel>
)