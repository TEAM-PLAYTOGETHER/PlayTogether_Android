package com.playtogether_android.app.di;

import com.playtogether_android.data.api.thunder.ThunderService;
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
public final class ApiModule_ProvideThunderServiceFactory implements Factory<ThunderService> {
  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideThunderServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ThunderService get() {
    return provideThunderService(retrofitProvider.get());
  }

  public static ApiModule_ProvideThunderServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new ApiModule_ProvideThunderServiceFactory(retrofitProvider);
  }

  public static ThunderService provideThunderService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideThunderService(retrofit));
  }
}
