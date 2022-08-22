package com.playtogether_android.app.di;

import com.playtogether_android.data.api.thunder.ThunderService;
import com.playtogether_android.data.datasource.thunder.ThunderDataSource;
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
public final class DataSourceModule_ProvideThunderDataSourceFactory implements Factory<ThunderDataSource> {
  private final Provider<ThunderService> serviceProvider;

  public DataSourceModule_ProvideThunderDataSourceFactory(
      Provider<ThunderService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public ThunderDataSource get() {
    return provideThunderDataSource(serviceProvider.get());
  }

  public static DataSourceModule_ProvideThunderDataSourceFactory create(
      Provider<ThunderService> serviceProvider) {
    return new DataSourceModule_ProvideThunderDataSourceFactory(serviceProvider);
  }

  public static ThunderDataSource provideThunderDataSource(ThunderService service) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideThunderDataSource(service));
  }
}
