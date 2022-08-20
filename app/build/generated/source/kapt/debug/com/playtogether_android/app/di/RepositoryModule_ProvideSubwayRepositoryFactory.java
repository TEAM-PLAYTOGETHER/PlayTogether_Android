package com.playtogether_android.app.di;

import com.playtogether_android.data.datasource.onboarding.SubwayDataSource;
import com.playtogether_android.domain.repository.onboarding.SubwayRepository;
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
public final class RepositoryModule_ProvideSubwayRepositoryFactory implements Factory<SubwayRepository> {
  private final Provider<SubwayDataSource> dataSourceProvider;

  public RepositoryModule_ProvideSubwayRepositoryFactory(
      Provider<SubwayDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public SubwayRepository get() {
    return provideSubwayRepository(dataSourceProvider.get());
  }

  public static RepositoryModule_ProvideSubwayRepositoryFactory create(
      Provider<SubwayDataSource> dataSourceProvider) {
    return new RepositoryModule_ProvideSubwayRepositoryFactory(dataSourceProvider);
  }

  public static SubwayRepository provideSubwayRepository(SubwayDataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideSubwayRepository(dataSource));
  }
}
