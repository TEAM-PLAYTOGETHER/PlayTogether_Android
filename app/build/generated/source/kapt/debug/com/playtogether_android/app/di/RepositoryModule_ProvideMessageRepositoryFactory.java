package com.playtogether_android.app.di;

import com.playtogether_android.data.datasource.message.MessageDataSource;
import com.playtogether_android.domain.repository.message.MessageRepository;
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
public final class RepositoryModule_ProvideMessageRepositoryFactory implements Factory<MessageRepository> {
  private final Provider<MessageDataSource> dataSourceProvider;

  public RepositoryModule_ProvideMessageRepositoryFactory(
      Provider<MessageDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public MessageRepository get() {
    return provideMessageRepository(dataSourceProvider.get());
  }

  public static RepositoryModule_ProvideMessageRepositoryFactory create(
      Provider<MessageDataSource> dataSourceProvider) {
    return new RepositoryModule_ProvideMessageRepositoryFactory(dataSourceProvider);
  }

  public static MessageRepository provideMessageRepository(MessageDataSource dataSource) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideMessageRepository(dataSource));
  }
}
