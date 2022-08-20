package com.playtogether_android.app.di;

import com.playtogether_android.data.api.onboarding.SubwayInfoService;
import com.playtogether_android.data.datasource.onboarding.SubwayDataSource;
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
public final class DataSourceModule_ProvideSubwayDataSourceFactory implements Factory<SubwayDataSource> {
  private final Provider<SubwayInfoService> serviceProvider;

  public DataSourceModule_ProvideSubwayDataSourceFactory(
      Provider<SubwayInfoService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public SubwayDataSource get() {
    return provideSubwayDataSource(serviceProvider.get());
  }

  public static DataSourceModule_ProvideSubwayDataSourceFactory create(
      Provider<SubwayInfoService> serviceProvider) {
    return new DataSourceModule_ProvideSubwayDataSourceFactory(serviceProvider);
  }

  public static SubwayDataSource provideSubwayDataSource(SubwayInfoService service) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideSubwayDataSource(service));
  }
}
