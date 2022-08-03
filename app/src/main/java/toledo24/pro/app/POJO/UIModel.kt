package toledo24.pro.app.POJO

sealed class UIModel {

    class BannerModel(val bannerItem: BannerItem): UIModel()
    class BestProductModel(val BestProductItem: BestProductItem): UIModel()

}
