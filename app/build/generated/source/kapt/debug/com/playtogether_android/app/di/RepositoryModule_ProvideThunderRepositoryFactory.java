package com.playtogether_android.app.di;

import com.playtogether_android.data.datasource.thunder.ThunderDataSource;
import com.playtogether_android.domain.repository.thunder.ThunderRepository;
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
public final class RepositoryModule_ProvideThunderRepositoryFactory implements Factory<ThunderRepository> {
  private final Provider<ThunderDataSource> dataSourceProvider;

  public RepositoryModule_ProvideThunderRepositoryFactory(
      Provider<ThunderDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public ThunderRepository get() {
    return provideThunderRepository(dataSourceProvider.get());
  }

  public static RepositoryModule_ProvideThunderRepositoryFactory create(
      Provider<ThunderDataSource> dataSourceProvider) {
    return new RepositoryModule_ProvideThunderRepositoryFactory(dataSourceProvider);
  }

  public static ThunderRepository provideThunderRepository(ThunderDataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideThunderRepository(dataSource));
  }
}
