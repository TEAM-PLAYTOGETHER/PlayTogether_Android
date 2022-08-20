package com.playtogether_android.app.di;

import com.playtogether_android.data.datasource.light.LightDataSource;
import com.playtogether_android.domain.repository.light.LightRepository;
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
public final class RepositoryModule_ProvideLightRepositoryFactory implements Factory<LightRepository> {
  private final Provider<LightDataSource> dataSourceProvider;

  public RepositoryModule_ProvideLightRepositoryFactory(
      Provider<LightDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public LightRepository get() {
    return provideLightRepository(dataSourceProvider.get());
  }

  public static RepositoryModule_ProvideLightRepositoryFactory create(
      Provider<LightDataSource> dataSourceProvider) {
    return new RepositoryModule_ProvideLightRepositoryFactory(dataSourceProvider);
  }

  public static LightRepository provideLightRepository(LightDataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideLightRepository(dataSource));
  }
}
