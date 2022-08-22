package com.playtogether_android.app.di;

import com.playtogether_android.data.api.sign.SignService;
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
public final class ApiModule_ProvideSignServiceFactory implements Factory<SignService> {
  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideSignServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public SignService get() {
    return provideSignService(retrofitProvider.get());
  }

  public static ApiModule_ProvideSignServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new ApiModule_ProvideSignServiceFactory(retrofitProvider);
  }

  public static SignService provideSignService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideSignService(retrofit));
  }
}
