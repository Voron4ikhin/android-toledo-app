package toledo24.pro.di


import org.koin.dsl.module
import toledo24.pro.data.repositoryimpl.CatalogRepositoryImpl
import toledo24.pro.data.repositoryimpl.UserRepositoryImpl
import toledo24.pro.domain.repository.CatalogRepository
import toledo24.pro.domain.repository.UserRepository
import toledo24.pro.data.network.RetrofitService
import toledo24.pro.data.repositoryimpl.MainPageRepositoryImpl
import toledo24.pro.data.room.RoomService
import toledo24.pro.domain.repository.MainPageRepository


val dataModule = module {

    single<RetrofitService> {
        RetrofitService.getInstance()
    }

    single<RoomService> {
        RoomService.getInstance(context = get())
    }

    single<CatalogRepository> {
        CatalogRepositoryImpl(
            retrofitService = get()
        )
    }

    single<UserRepository> {
        UserRepositoryImpl(
            retrofitService = get(),
            roomService = get()
        )
    }

    single<MainPageRepository> {
        MainPageRepositoryImpl(
            retrofitService = get(),
        )
    }

}