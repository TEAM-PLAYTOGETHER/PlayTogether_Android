package com.playtogether_android.app.di;

import com.playtogether_android.data.api.home.HomeService;
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
public final class ApiModule_ProvideHomeServiceFactory implements Factory<HomeService> {
  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideHomeServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public HomeService get() {
    return provideHomeService(retrofitProvider.get());
  }

  public static ApiModule_ProvideHomeServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new ApiModule_ProvideHomeServiceFactory(retrofitProvider);
  }

  public static HomeService provideHomeService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideHomeService(retrofit));
  }
}
