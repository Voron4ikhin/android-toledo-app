package toledo24.pro.data.network.catalog

import com.google.gson.annotations.SerializedName

data class ResponseDetailProductModel (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: DetailProductModel
)

data class DetailProductModel(
    @SerializedName("ID")
    val ID: String,
    @SerializedName("NAME")
    val NAME: String,
    @SerializedName("PREVIEW_TEXT")
    val PREVIEW_TEXT: String,
    @SerializedName("DETAIL_TEXT")
    val DETAIL_TEXT: String,
    @SerializedName("DETAIL_PAGE_URL")
    val DETAIL_PAGE_URL: String,
    @SerializedName("CODE")
    val CODE: String,
    @SerializedName("IBLOCK_SECTION_ID")
    val IBLOCK_SECTION_ID: String,
    @SerializedName("PRODUCT_ID")
    val PRODUCT_ID: String,
    @SerializedName("IMAGE")
    val IMAGE: String,
    @SerializedName("PRICE")
    val PRICE: String,
    @SerializedName("AMOUNT")
    val AMOUNT: String,
    @SerializedName("PROPERTIES")
    val PROPERTIES: Map<String, PropertiesModel>,
    @SerializedName("ANALOGI")
    val ANALOGI: List<String>,
    @SerializedName("RELATED")
    val RELATED: List<String>,
    @SerializedName("CODE_PRODUCT")
    val CODE_PRODUCT: String,
    @SerializedName("RATE")
    val RATE: String,
)

data class PropertiesModel(
    @SerializedName("NAME")
    val NAME: String,
    @SerializedName("VALUE")
    val VALUE: String,
)