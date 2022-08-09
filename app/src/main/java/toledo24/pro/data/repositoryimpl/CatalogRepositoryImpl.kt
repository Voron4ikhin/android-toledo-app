package toledo24.pro.data.repositoryimpl

import toledo24.pro.data.network.ResponseCatalogModel
import toledo24.pro.domain.repository.CatalogRepository
import toledo24.pro.data.network.RetrofitService
import toledo24.pro.data.network.RetrofitService.Companion.retrofitService
import toledo24.pro.data.network.catalog.ResponseCatalogListModel
import toledo24.pro.data.network.catalog.ResponseDetailProductModel
import toledo24.pro.data.room.RoomService
import toledo24.pro.data.room.catalog.CatalogEntity

class CatalogRepositoryImpl(retrofitService: RetrofitService): CatalogRepository {

    override suspend fun getCatalog(): ResponseCatalogModel = retrofitService!!.getCatalog()

    override suspend fun getDetailProduct(name :String, category :String): ResponseDetailProductModel = retrofitService!!.getDetailProduct(name, category)

    override suspend fun getCatalogRoom(IBLOCK_SECTION_ID: String?): List<CatalogEntity> {
        if(IBLOCK_SECTION_ID == "")
            return RoomService.INSTANCE?.catalogDao()!!.getCatalogRoomNull()
        return RoomService.INSTANCE?.catalogDao()!!.getCatalogRoom(IBLOCK_SECTION_ID)
    }

    override suspend fun insertCatalogRoom( catalogEntityList: List<CatalogEntity>){
        RoomService.INSTANCE?.catalogDao()!!.insertCatalog(catalogEntityList)
    }

    override suspend fun getProductList(category : String, page : String):
            ResponseCatalogListModel = retrofitService!!.getCatalogList(category, page)

}