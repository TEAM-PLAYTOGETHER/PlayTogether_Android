package com.playtogether_android.app.di;

import com.playtogether_android.data.datasource.onboarding.OnBoardingDataSource;
import com.playtogether_android.domain.repository.onboarding.OnBoardingRepository;
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
public final class RepositoryModule_ProvideOnBoardingRepositoryFactory implements Factory<OnBoardingRepository> {
  private final Provider<OnBoardingDataSource> dataSourceProvider;

  public RepositoryModule_ProvideOnBoardingRepositoryFactory(
      Provider<OnBoardingDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public OnBoardingRepository get() {
    return provideOnBoardingRepository(dataSourceProvider.get());
  }

  public static RepositoryModule_ProvideOnBoardingRepositoryFactory create(
      Provider<OnBoardingDataSource> dataSourceProvider) {
    return new RepositoryModule_ProvideOnBoardingRepositoryFactory(dataSourceProvider);
  }

  public static OnBoardingRepository provideOnBoardingRepository(OnBoardingDataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideOnBoardingRepository(dataSource));
  }
}
