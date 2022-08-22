package com.playtogether_android.app.di;

import com.playtogether_android.data.api.message.ChatService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("com.playtogether_android.app.di.RetrofitModule.GsonConverter")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiModule_ProvideChatServiceFactory implements Factory<ChatService> {
  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideChatServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ChatService get() {
    return provideChatService(retrofitProvider.get());
  }

  public static ApiModule_ProvideChatServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new ApiModule_ProvideChatServiceFactory(retrofitProvider);
  }

  public static ChatService provideChatService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideChatService(retrofit));
  }
}
