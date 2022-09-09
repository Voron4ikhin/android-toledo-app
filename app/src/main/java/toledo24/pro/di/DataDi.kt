package toledo24.pro.di


import org.koin.dsl.module
import toledo24.pro.data.network.RetrofitService
import toledo24.pro.data.repositoryimpl.*
import toledo24.pro.data.room.RoomService
import toledo24.pro.domain.repository.*


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

    single<CardRepository> {
        CardRepositoryImpl(
            retrofitService = get()
        )
    }

    single<ProfileRepository>{
        ProfileRepositoryImpl(
            retrofitService = get()
        )
    }

}