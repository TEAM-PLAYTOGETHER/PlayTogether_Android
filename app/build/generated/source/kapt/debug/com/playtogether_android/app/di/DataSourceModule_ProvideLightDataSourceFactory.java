package com.playtogether_android.app.di;

import com.playtogether_android.data.api.light.LightService;
import com.playtogether_android.data.datasource.light.LightDataSource;
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
public final class DataSourceModule_ProvideLightDataSourceFactory implements Factory<LightDataSource> {
  private final Provider<LightService> serviceProvider;

  public DataSourceModule_ProvideLightDataSourceFactory(Provider<LightService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public LightDataSource get() {
    return provideLightDataSource(serviceProvider.get());
  }

  public static DataSourceModule_ProvideLightDataSourceFactory create(
      Provider<LightService> serviceProvider) {
    return new DataSourceModule_ProvideLightDataSourceFactory(serviceProvider);
  }

  public static LightDataSource provideLightDataSource(LightService service) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideLightDataSource(service));
  }
}
