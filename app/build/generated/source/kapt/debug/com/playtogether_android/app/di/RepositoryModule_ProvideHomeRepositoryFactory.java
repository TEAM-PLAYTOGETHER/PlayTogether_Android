package com.playtogether_android.app.di;

import com.playtogether_android.data.datasource.home.HomeDataSource;
import com.playtogether_android.domain.repository.home.HomeRepository;
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
public final class RepositoryModule_ProvideHomeRepositoryFactory implements Factory<HomeRepository> {
  private final Provider<HomeDataSource> dataSourceProvider;

  public RepositoryModule_ProvideHomeRepositoryFactory(
      Provider<HomeDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public HomeRepository get() {
    return provideHomeRepository(dataSourceProvider.get());
  }

  public static RepositoryModule_ProvideHomeRepositoryFactory create(
      Provider<HomeDataSource> dataSourceProvider) {
    return new RepositoryModule_ProvideHomeRepositoryFactory(dataSourceProvider);
  }

  public static HomeRepository provideHomeRepository(HomeDataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideHomeRepository(dataSource));
  }
}
