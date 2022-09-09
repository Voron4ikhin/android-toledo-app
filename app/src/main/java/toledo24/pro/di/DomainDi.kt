package toledo24.pro.di

import org.koin.dsl.module
import toledo24.pro.domain.usecase.autorization.CheckingCodeUserUseCase
import toledo24.pro.domain.usecase.autorization.CheckingPhoneUserUseCase
import toledo24.pro.domain.usecase.basket.AddToBasketUseCase
import toledo24.pro.domain.usecase.basket.GetCardUseCase
import toledo24.pro.domain.usecase.catalog.*
import toledo24.pro.domain.usecase.main.GetMainPageInfoUseCase
import toledo24.pro.domain.usecase.profile.GetFavoriteUseCase
import toledo24.pro.domain.usecase.profile.GetUserInfoUseCase
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
        GetMainPageInfoUseCase(mainPageRepository = get(), cardRepository = get())
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

    factory<GetAnalogsRelatedUseCase>{
        GetAnalogsRelatedUseCase(catalogRepository = get())
    }

    factory<GetCardUseCase>{
        GetCardUseCase(cardRepository = get(), userRepository = get())
    }

    factory<GetFavoriteUseCase>{
        GetFavoriteUseCase(profileRepository = get())
    }

    factory<GetUserInfoUseCase>{
        GetUserInfoUseCase(profileRepository = get())
    }

}