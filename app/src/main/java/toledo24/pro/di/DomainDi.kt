package toledo24.pro.di

import org.koin.dsl.module
import toledo24.pro.domain.usecase.autorization.CheckingCodeUserUseCase
import toledo24.pro.domain.usecase.catalog.GetCategoriesUseCase
import toledo24.pro.domain.usecase.autorization.CheckingPhoneUserUseCase
import toledo24.pro.domain.usecase.basket.AddToBasketUseCase
import toledo24.pro.domain.usecase.catalog.GetDetailProductUseCase
import toledo24.pro.domain.usecase.catalog.GetProductListUseCase
import toledo24.pro.domain.usecase.catalog.ShowCategoriesUseCase
import toledo24.pro.domain.usecase.main.GetMainPageInfoUseCase
import toledo24.pro.domain.usecase.welcome.CheckingUserInfoRoomUseCase


val domainModule = module {

    factory<GetCategoriesUseCase> {
        GetCategoriesUseCase(catalogRepository = get())
    }

    factory<CheckingPhoneUserUseCase> {
        CheckingPhoneUserUseCase(userRepository = get())
    }

    factory<CheckingCodeUserUseCase> {
        CheckingCodeUserUseCase(userRepository = get())
    }

    factory<CheckingUserInfoRoomUseCase> {
        CheckingUserInfoRoomUseCase(userRepository = get())
    }

    factory<ShowCategoriesUseCase> {
        ShowCategoriesUseCase(catalogRepository = get())
    }

    factory<GetMainPageInfoUseCase> {
        GetMainPageInfoUseCase(mainPageRepository = get())
    }

    factory<GetProductListUseCase> {
        GetProductListUseCase(catalogRepository = get())
    }

    factory<GetDetailProductUseCase> {
        GetDetailProductUseCase(catalogRepository = get())
    }

    factory<AddToBasketUseCase> {
        AddToBasketUseCase(cardRepository = get(), userRepository = get())
    }

}