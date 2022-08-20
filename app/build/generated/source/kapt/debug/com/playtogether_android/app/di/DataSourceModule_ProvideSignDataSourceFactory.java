package com.playtogether_android.app.di;

import com.playtogether_android.data.api.sign.SignService;
import com.playtogether_android.data.datasource.sign.SignDataSource;
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
public final class DataSourceModule_ProvideSignDataSourceFactory implements Factory<SignDataSource> {
  private final Provider<SignService> serviceProvider;

  public DataSourceModule_ProvideSignDataSourceFactory(Provider<SignService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public SignDataSource get() {
    return provideSignDataSource(serviceProvider.get());
  }

  public static DataSourceModule_ProvideSignDataSourceFactory create(
      Provider<SignService> serviceProvider) {
    return new DataSourceModule_ProvideSignDataSourceFactory(serviceProvider);
  }

  public static SignDataSource provideSignDataSource(SignService service) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideSignDataSource(service));
  }
}
