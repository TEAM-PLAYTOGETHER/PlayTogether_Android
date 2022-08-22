package com.playtogether_android.app.di;

import com.playtogether_android.data.api.light.LightService;
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
public final class ApiModule_ProvideLightServiceFactory implements Factory<LightService> {
  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideLightServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public LightService get() {
    return provideLightService(retrofitProvider.get());
  }

  public static ApiModule_ProvideLightServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new ApiModule_ProvideLightServiceFactory(retrofitProvider);
  }

  public static LightService provideLightService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideLightService(retrofit));
  }
}
