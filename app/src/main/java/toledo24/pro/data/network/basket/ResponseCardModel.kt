package toledo24.pro.data.network.basket

import com.google.gson.annotations.SerializedName
import toledo24.pro.data.network.catalog.CatalogListModel

data class ResponseCardModel(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: ResultCardModel
)

data class ResultCardModel(
    @SerializedName("BASKET_LIST")
    val BASKET_LIST: Map<String, BasketModel>,
    @SerializedName("FUSER_ID")
    val FUSER_ID: String,
)

data class BasketModel(
    @SerializedName("PRODUCT_ID")
    val PRODUCT_ID: String,
    @SerializedName("NAME")
    val NAME: String,
    @SerializedName("PREVIEW_PICTURE")
    val PREVIEW_PICTURE: String,
    @SerializedName("AMOUNT")
    val AMOUNT: Int,
    @SerializedName("PRICE")
    val PRICE: String,
    @SerializedName("NORMAL_PRICE")
    val NORMAL_PRICE: String,
    @SerializedName("WEIGHT")
    val WEIGHT: String,
    @SerializedName("RATE")
    val RATE: String,
    @SerializedName("CODE_PRODUCT")
    val CODE_PRODUCT: String,
    @SerializedName("BASIC_UNIT")
    val BASIC_UNIT: String,
    @SerializedName("SALE")
    val SALE: String?=null,
    @SerializedName("NORMAL_SALE")
    val NORMAL_SALE: String?=null,
    @SerializedName("DIFF")
    val DIFF: String?=null,
    @SerializedName("QUANTITY")
    val QUANTITY: String,
    @SerializedName("QUANTITY_INSTOCK")
    val QUANTITY_INSTOCK: Int,
    @SerializedName("QUANTITY_UNDER_ORDER")
    val QUANTITY_UNDER_ORDER: Int,
    @SerializedName("FINAL_PRICE_INSTOCK")
    val FINAL_PRICE_INSTOCK: Float,
    @SerializedName("FINAL_PRICE_UNDER_ORDER")
    val FINAL_PRICE_UNDER_ORDER: Float,
)