package toledo24.pro.data.network.catalog

import com.google.gson.annotations.SerializedName

data class ResponseCatalogListModel(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("result")
    val result: CatalogListModel
)

data class CatalogListModel(
    @SerializedName("body")
    val body: BodyCatalogModel,
)

data class BodyCatalogModel(
    @SerializedName("page")
    val page: String,
    @SerializedName("result")
    val result: List<CatalogItemModel>,
    @SerializedName("countItemsCategory")
    val countItemsCategory: Int,
)

data class CatalogItemModel(
    @SerializedName("ID")
    val ID: String,
    @SerializedName("CODE")
    val CODE: String,
    @SerializedName("NAME")
    val NAME: String,
    @SerializedName("PRODUCT_ID")
    val PRODUCT_ID: String,
    @SerializedName("PRICE")
    val PRICE: String,
    @SerializedName("NORMAL_PRICE")
    val NORMAL_PRICE: String,
    @SerializedName("SALE")
    val SALE: String,
    @SerializedName("NORMAL_SALE")
    val NORMAL_SALE: String,
    @SerializedName("DIFF")
    val DIFF: String,
    @SerializedName("DETAIL_PICTURE")
    val DETAIL_PICTURE: String,
    @SerializedName("QUANTITY")
    val QUANTITY: String,
    @SerializedName("WEIGHT")
    val WEIGHT: String,
    @SerializedName("WIDTH")
    val WIDTH: String,
    @SerializedName("LENGTH")
    val LENGTH: String,
    @SerializedName("HEIGHT")
    val HEIGHT: String,
    @SerializedName("CATEGORY_NAME")
    val CATEGORY_NAME: String,
    @SerializedName("RATE")
    val RATE: String,
    @SerializedName("CODE_PRODUCT")
    val CODE_PRODUCT: String,
    @SerializedName("BASIC_UNIT")
    val BASIC_UNIT: String,
    @SerializedName("AMOUNT")
    val AMOUNT: String,
    @SerializedName("PREPAIR_QUANTITY")
    val PREPAIR_QUANTITY: String,
)