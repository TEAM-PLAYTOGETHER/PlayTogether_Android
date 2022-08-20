package com.playtogether_android.app.di;

import com.playtogether_android.data.api.message.ChatService;
import com.playtogether_android.data.datasource.message.ChatDataSource;
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
public final class DataSourceModule_ProvideChatDataSourceFactory implements Factory<ChatDataSource> {
  private final Provider<ChatService> serviceProvider;

  public DataSourceModule_ProvideChatDataSourceFactory(Provider<ChatService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public ChatDataSource get() {
    return provideChatDataSource(serviceProvider.get());
  }

  public static DataSourceModule_ProvideChatDataSourceFactory create(
      Provider<ChatService> serviceProvider) {
    return new DataSourceModule_ProvideChatDataSourceFactory(serviceProvider);
  }

  public static ChatDataSource provideChatDataSource(ChatService service) {
    return Preconditions.checkNotNullFromProvides(DataSourceModule.INSTANCE.provideChatDataSource(service));
  }
}
