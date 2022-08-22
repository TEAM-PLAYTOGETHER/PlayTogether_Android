package com.playtogether_android.app.di;

import com.playtogether_android.data.datasource.mypage.MyPageDataSource;
import com.playtogether_android.domain.repository.mypage.MyPageRepository;
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
public final class RepositoryModule_ProvideMyPageRepositoryFactory implements Factory<MyPageRepository> {
  private final Provider<MyPageDataSource> dataSourceProvider;

  public RepositoryModule_ProvideMyPageRepositoryFactory(
      Provider<MyPageDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public MyPageRepository get() {
    return provideMyPageRepository(dataSourceProvider.get());
  }

  public static RepositoryModule_ProvideMyPageRepositoryFactory create(
      Provider<MyPageDataSource> dataSourceProvider) {
    return new RepositoryModule_ProvideMyPageRepositoryFactory(dataSourceProvider);
  }

  public static MyPageRepository provideMyPageRepository(MyPageDataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideMyPageRepository(dataSource));
  }
}
