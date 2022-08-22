package com.playtogether_android.app.di;

import com.playtogether_android.data.api.mypage.MyPageService;
import com.playtogether_android.data.datasource.mypage.MyPageDataSource;
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
public final class DataSourceModule_ProvideMyPageDataSourceFactory implements Factory<MyPageDataSource> {
  private final Provider<MyPageService> serviceProvider;

  public DataSourceModule_ProvideMyPageDataSourceFactory(Provider<MyPageService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public MyPageDataSource get() {
    return provideMyPageDataSource(serviceProvider.get());
  }

  public static DataSourceModule_ProvideMyPageDataSourceFactory create(
      Provider<MyPageService> serviceProvider) {
    return new DataSourceModule_ProvideMyPageDataSourceFactory(serviceProvider);
  }

  public static MyPageDataSource provideMyPageDataSource(MyPageService service) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideMyPageDataSource(service));
  }
}
