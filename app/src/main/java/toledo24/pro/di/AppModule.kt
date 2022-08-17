package toledo24.pro.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import toledo24.pro.presentation.MainViewModel
import toledo24.pro.presentation.autorization.AutorizationCodeViewModel
import toledo24.pro.presentation.autorization.AutorizationViewModel
import toledo24.pro.presentation.cart.CardViewModel
import toledo24.pro.presentation.catalog.CatalogDetailFragmentViewModel
import toledo24.pro.presentation.catalog.CatalogFragmentViewModel
import toledo24.pro.presentation.catalog.CatalogProductsViewModel
import toledo24.pro.presentation.home.NavigationViewModel
import toledo24.pro.presentation.profile.ProfileViewModel
import toledo24.pro.presentation.welcome.WelcomeViewModel

val appModule = module {

    viewModel {
        AutorizationViewModel(checkingPhoneUserUseCase = get())
    }

    viewModel {
        AutorizationCodeViewModel(checkingCodeUserUseCase = get())
    }

    viewModel {
        WelcomeViewModel(checkingUserInfoRoomUseCase = get())
    }

    viewModel {
        NavigationViewModel(getMainPageInfoUseCase = get())
    }

    viewModel{
        CatalogFragmentViewModel(
            getCategoriesUseCase = get(),
            showCategoriesUseCase = get(),
        )
    }

    viewModel{
        CatalogProductsViewModel(
            getProductListUseCase = get(),
            addToBasketUseCase = get(),
        )
    }

    viewModel{
        CatalogDetailFragmentViewModel(
            getDetailProductUseCase = get(),
            getAnalogsRelatedUseCase = get()
        )
    }

    viewModel{
        MainViewModel(
            getMainPageInfoUseCase = get(),
            userRepository = get()
        )
    }

    viewModel{
        CardViewModel(
            getCardUseCase = get(),
            addToBasketUseCase = get()
        )
    }

    viewModel{
        ProfileViewModel()
    }

}