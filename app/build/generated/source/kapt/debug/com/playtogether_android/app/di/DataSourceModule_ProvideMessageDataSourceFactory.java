package com.playtogether_android.app.di;

import com.playtogether_android.data.api.message.MessageService;
import com.playtogether_android.data.datasource.message.MessageDataSource;
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
public final class DataSourceModule_ProvideMessageDataSourceFactory implements Factory<MessageDataSource> {
  private final Provider<MessageService> serviceProvider;

  public DataSourceModule_ProvideMessageDataSourceFactory(
      Provider<MessageService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public MessageDataSource get() {
    return provideMessageDataSource(serviceProvider.get());
  }

  public static DataSourceModule_ProvideMessageDataSourceFactory create(
      Provider<MessageService> serviceProvider) {
    return new DataSourceModule_ProvideMessageDataSourceFactory(serviceProvider);
  }

  public static MessageDataSource provideMessageDataSource(MessageService service) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideMessageDataSource(service));
  }
}
