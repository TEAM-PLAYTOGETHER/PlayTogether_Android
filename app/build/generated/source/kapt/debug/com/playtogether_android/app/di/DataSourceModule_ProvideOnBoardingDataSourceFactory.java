package com.playtogether_android.app.di;

import com.playtogether_android.data.api.onboarding.OnboardingService;
import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DataSourceModule_ProvideOnBoardingDataSourceFactory implements Factory<OnBoardingDataSource> {
  private final Provider<OnboardingService> serviceProvider;

  public DataSourceModule_ProvideOnBoardingDataSourceFactory(
      Provider<OnboardingService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public OnBoardingDataSource get() {
    return provideOnBoardingDataSource(serviceProvider.get());
  }

  public static DataSourceModule_ProvideOnBoardingDataSourceFactory create(
      Provider<OnboardingService> serviceProvider) {
    return new DataSourceModule_ProvideOnBoardingDataSourceFactory(serviceProvider);
  }

  public static OnBoardingDataSource provideOnBoardingDataSource(OnboardingService service) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideOnBoardingDataSource(service));
  }
}
