package com.playtogether_android.app.di;

import com.playtogether_android.data.datasource.sign.SignDataSource;
import com.playtogether_android.domain.repository.sign.SignRepository;
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
public final class RepositoryModule_ProvideSignRepositoryFactory implements Factory<SignRepository> {
  private final Provider<SignDataSource> dataSourceProvider;

  public RepositoryModule_ProvideSignRepositoryFactory(
      Provider<SignDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public SignRepository get() {
    return provideSignRepository(dataSourceProvider.get());
  }

  public static RepositoryModule_ProvideSignRepositoryFactory create(
      Provider<SignDataSource> dataSourceProvider) {
    return new RepositoryModule_ProvideSignRepositoryFactory(dataSourceProvider);
  }

  public static SignRepository provideSignRepository(SignDataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideSignRepository(dataSource));
  }
}
