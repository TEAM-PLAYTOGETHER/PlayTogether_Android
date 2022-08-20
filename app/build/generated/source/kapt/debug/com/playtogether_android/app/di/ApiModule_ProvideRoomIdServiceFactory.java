package com.playtogether_android.app.di;

import com.playtogether_android.data.api.message.RoomIdService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("com.playtogether_android.app.testdi.RetrofitModule.GsonConverter")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiModule_ProvideRoomIdServiceFactory implements Factory<RoomIdService> {
  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideRoomIdServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public RoomIdService get() {
    return provideRoomIdService(retrofitProvider.get());
  }

  public static ApiModule_ProvideRoomIdServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new ApiModule_ProvideRoomIdServiceFactory(retrofitProvider);
  }

  public static RoomIdService provideRoomIdService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideRoomIdService(retrofit));
  }
}
