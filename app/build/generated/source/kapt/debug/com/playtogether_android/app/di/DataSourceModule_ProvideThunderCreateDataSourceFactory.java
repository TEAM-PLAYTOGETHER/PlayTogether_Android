package com.playtogether_android.app.di;

import com.playtogether_android.data.api.thunder.ThunderCreateService;
import com.playtogether_android.data.datasource.thunder.ThunderCreateDataSource;
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
public final class DataSourceModule_ProvideThunderCreateDataSourceFactory implements Factory<ThunderCreateDataSource> {
  private final Provider<ThunderCreateService> serviceProvider;

  public DataSourceModule_ProvideThunderCreateDataSourceFactory(
      Provider<ThunderCreateService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public ThunderCreateDataSource get() {
    return provideThunderCreateDataSource(serviceProvider.get());
  }

  public static DataSourceModule_ProvideThunderCreateDataSourceFactory create(
      Provider<ThunderCreateService> serviceProvider) {
    return new DataSourceModule_ProvideThunderCreateDataSourceFactory(serviceProvider);
  }

  public static ThunderCreateDataSource provideThunderCreateDataSource(
      ThunderCreateService service) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideThunderCreateDataSource(service));
  }
}
