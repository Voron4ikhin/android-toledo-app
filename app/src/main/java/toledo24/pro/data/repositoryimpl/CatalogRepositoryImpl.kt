package toledo24.pro.data.repositoryimpl

import toledo24.pro.data.network.ResponseCatalogModel
import toledo24.pro.domain.repository.CatalogRepository
import toledo24.pro.data.network.RetrofitService
import toledo24.pro.data.network.RetrofitService.Companion.retrofitService
import toledo24.pro.data.room.RoomService
import toledo24.pro.data.room.catalog.CatalogEntity

class CatalogRepositoryImpl(retrofitService: RetrofitService): CatalogRepository {

    override suspend fun getCatalog(): ResponseCatalogModel = retrofitService!!.getCatalog()

    override suspend fun getCatalogRoom(IBLOCK_SECTION_ID: String?): List<CatalogEntity> {
        if(IBLOCK_SECTION_ID == "")
            return RoomService.INSTANCE?.catalogDao()!!.getCatalogRoomNull()
        return RoomService.INSTANCE?.catalogDao()!!.getCatalogRoom(IBLOCK_SECTION_ID)
    }

    override suspend fun insertCatalogRoom( catalogEntityList: List<CatalogEntity>){
        RoomService.INSTANCE?.catalogDao()!!.insertCatalog(catalogEntityList)
    }

}