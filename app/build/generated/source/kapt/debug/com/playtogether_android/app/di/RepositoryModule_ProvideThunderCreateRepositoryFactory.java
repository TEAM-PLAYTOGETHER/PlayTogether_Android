package com.playtogether_android.app.di;

import com.playtogether_android.data.datasource.thunder.ThunderCreateDataSource;
import com.playtogether_android.domain.repository.thunder.ThunderCreateRepository;
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
public final class RepositoryModule_ProvideThunderCreateRepositoryFactory implements Factory<ThunderCreateRepository> {
  private final Provider<ThunderCreateDataSource> dataSourceProvider;

  public RepositoryModule_ProvideThunderCreateRepositoryFactory(
      Provider<ThunderCreateDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public ThunderCreateRepository get() {
    return provideThunderCreateRepository(dataSourceProvider.get());
  }

  public static RepositoryModule_ProvideThunderCreateRepositoryFactory create(
      Provider<ThunderCreateDataSource> dataSourceProvider) {
    return new RepositoryModule_ProvideThunderCreateRepositoryFactory(dataSourceProvider);
  }

  public static ThunderCreateRepository provideThunderCreateRepository(
      ThunderCreateDataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideThunderCreateRepository(dataSource));
  }
}
