package com.playtogether_android.app.di;

import com.playtogether_android.data.api.home.HomeService;
import com.playtogether_android.data.datasource.home.HomeDataSource;
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
public final class DataSourceModule_ProvideHomeDataSourceFactory implements Factory<HomeDataSource> {
  private final Provider<HomeService> serviceProvider;

  public DataSourceModule_ProvideHomeDataSourceFactory(Provider<HomeService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public HomeDataSource get() {
    return provideHomeDataSource(serviceProvider.get());
  }

  public static DataSourceModule_ProvideHomeDataSourceFactory create(
      Provider<HomeService> serviceProvider) {
    return new DataSourceModule_ProvideHomeDataSourceFactory(serviceProvider);
  }

  public static HomeDataSource provideHomeDataSource(HomeService service) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideHomeDataSource(service));
  }
}
