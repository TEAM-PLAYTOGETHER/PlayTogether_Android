package com.playtogether_android.app.di;

import com.playtogether_android.data.api.message.RoomIdService;
import com.playtogether_android.domain.repository.message.RoomIdRepository;
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
public final class RepositoryModule_ProvideRoomIdRepositoryFactory implements Factory<RoomIdRepository> {
  private final Provider<RoomIdService> serviceProvider;

  public RepositoryModule_ProvideRoomIdRepositoryFactory(Provider<RoomIdService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public RoomIdRepository get() {
    return provideRoomIdRepository(serviceProvider.get());
  }

  public static RepositoryModule_ProvideRoomIdRepositoryFactory create(
      Provider<RoomIdService> serviceProvider) {
    return new RepositoryModule_ProvideRoomIdRepositoryFactory(serviceProvider);
  }

  public static RoomIdRepository provideRoomIdRepository(RoomIdService service) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideRoomIdRepository(service));
  }
}
