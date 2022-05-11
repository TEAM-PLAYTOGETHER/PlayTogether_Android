package com.playtogether_android.app.di

import com.playtogether_android.domain.usecase.light.GetThunderCategoryUseCase
import com.playtogether_android.domain.usecase.onboarding.PostRegisterCrewUseCase
import com.playtogether_android.domain.usecase.sign.PostSignIdUseCase
import com.playtogether_android.domain.usecase.sign.PostSignInUseCase
import com.playtogether_android.domain.usecase.sign.PostSignUpUseCaes
import com.playtogether_android.domain.usecase.thunder.GetApplyListUseCase
import com.playtogether_android.domain.usecase.thunder.GetLikeListUseCase
import com.playtogether_android.domain.usecase.thunder.GetOpenListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    //sign
//    single {PostSignIdUseCase(get())}
//    single {PostSignUpUseCaes(get())}
//    single {PostSignInUseCase(get())}

    //onboarding
    single {PostRegisterCrewUseCase(get())}
    single { PostSignIdUseCase(get()) }
    single { PostSignUpUseCaes(get()) }
    single { PostSignInUseCase(get()) }

    //thunderList
    single { GetThunderCategoryUseCase(get()) }

    //thunder
    single {GetApplyListUseCase(get())}
    single {GetOpenListUseCase(get())}
    single {GetLikeListUseCase(get())}
}


