package com.playtogether_android.app.di;

import com.playtogether_android.data.datasource.message.ChatDataSource;
import com.playtogether_android.domain.repository.message.ChatRepository;
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
public final class RepositoryModule_ProvideChatRepositoryFactory implements Factory<ChatRepository> {
  private final Provider<ChatDataSource> dataSourceProvider;

  public RepositoryModule_ProvideChatRepositoryFactory(
      Provider<ChatDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public ChatRepository get() {
    return provideChatRepository(dataSourceProvider.get());
  }

  public static RepositoryModule_ProvideChatRepositoryFactory create(
      Provider<ChatDataSource> dataSourceProvider) {
    return new RepositoryModule_ProvideChatRepositoryFactory(dataSourceProvider);
  }

  public static ChatRepository provideChatRepository(ChatDataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideChatRepository(dataSource));
  }
}
