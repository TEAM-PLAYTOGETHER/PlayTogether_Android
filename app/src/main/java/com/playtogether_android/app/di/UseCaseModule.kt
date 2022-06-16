//
//package com.playtogether_android.app.di
//
//
//import com.playtogether_android.domain.usecase.home.GetThunderJoinEndMemberUseCase
//import com.playtogether_android.domain.usecase.home.GetThunderJoinEndOrganizerUseCase
//import com.playtogether_android.domain.usecase.home.GetThunderJoinEndUseCase
//import com.playtogether_android.domain.usecase.home.PostJoinThunderUseCase
//import com.playtogether_android.domain.usecase.light.GetThunderCategoryUseCase
//import com.playtogether_android.domain.usecase.message.GetChatUseCase
//import com.playtogether_android.domain.usecase.message.GetMessageUseCase
//import com.playtogether_android.domain.usecase.message.PostSendMessageUseCase
//import com.playtogether_android.domain.usecase.mypage.GetUserCheckUseCase
//import com.playtogether_android.domain.usecase.onboarding.PostRegisterCrewUseCase
//import com.playtogether_android.domain.usecase.sign.PostSignIdUseCase
//import com.playtogether_android.domain.usecase.sign.PostSignInUseCase
//import com.playtogether_android.domain.usecase.sign.PostSignUpUseCaes
//import com.playtogether_android.domain.usecase.thunder.*
//import org.koin.dsl.module
//
//val useCaseModule = module {
//
//    //onboarding
//    single { PostRegisterCrewUseCase(get()) }
//    single { PostSignIdUseCase(get()) }
//    single { PostSignUpUseCaes(get()) }
//    single { PostSignInUseCase(get()) }
//
//    //message
//    single { GetMessageUseCase(get()) }
//    single { PostSendMessageUseCase(get()) }
//    single { GetChatUseCase(get()) }
//
//    //thunderList
//    single { GetThunderCategoryUseCase(get()) }
//
//    //thunder
//    single { GetApplyListUseCase(get()) }
//    single { GetOpenListUseCase(get()) }
//    single { GetLikeListUseCase(get()) }
//    single { PostThunderCreateUseCase(get()) }
//
//
////    thunderDetail
//    single { PostThunderJoinCancelUseCase(get()) }
//    single { GetThunderDetailUseCase(get()) }
//    single { GetThunderDetailMemberUseCase(get()) }
//    single { GetThunderDetailOrganizerUseCase(get()) }
//    single { PostThunderDeleteUseCase(get()) }
//
//
//    //mypage
//    single { GetUserCheckUseCase(get()) }
//
//    //home
//    single { PostJoinThunderUseCase(get()) }
//    single { GetThunderJoinEndUseCase(get()) }
//    single { GetThunderJoinEndMemberUseCase(get()) }
//    single { GetThunderJoinEndOrganizerUseCase(get()) }
//
//}
//
//
