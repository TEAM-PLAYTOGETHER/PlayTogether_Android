package com.playtogether_android.app.di;

import com.playtogether_android.data.api.mypage.MyPageService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("com.playtogether_android.app.di.RetrofitModule.GsonConverter")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiModule_ProvideMyPageServiceFactory implements Factory<MyPageService> {
  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideMyPageServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public MyPageService get() {
    return provideMyPageService(retrofitProvider.get());
  }

  public static ApiModule_ProvideMyPageServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new ApiModule_ProvideMyPageServiceFactory(retrofitProvider);
  }

  public static MyPageService provideMyPageService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideMyPageService(retrofit));
  }
}
