package com.playtogether_android.app.di;

import com.playtogether_android.data.api.onboarding.OnboardingService;
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
public final class ApiModule_ProvideOnboardingServiceFactory implements Factory<OnboardingService> {
  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideOnboardingServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public OnboardingService get() {
    return provideOnboardingService(retrofitProvider.get());
  }

  public static ApiModule_ProvideOnboardingServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new ApiModule_ProvideOnboardingServiceFactory(retrofitProvider);
  }

  public static OnboardingService provideOnboardingService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideOnboardingService(retrofit));
  }
}
