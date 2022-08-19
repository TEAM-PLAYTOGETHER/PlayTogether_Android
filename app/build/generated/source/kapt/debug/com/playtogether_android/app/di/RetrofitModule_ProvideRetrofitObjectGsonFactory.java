package com.playtogether_android.app.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
public final class RetrofitModule_ProvideRetrofitObjectGsonFactory implements Factory<Retrofit> {
  @Override
  public Retrofit get() {
    return provideRetrofitObjectGson();
  }

  public static RetrofitModule_ProvideRetrofitObjectGsonFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Retrofit provideRetrofitObjectGson() {
    return Preconditions.checkNotNullFromProvides(RetrofitModule.INSTANCE.provideRetrofitObjectGson());
  }

  private static final class InstanceHolder {
    private static final RetrofitModule_ProvideRetrofitObjectGsonFactory INSTANCE = new RetrofitModule_ProvideRetrofitObjectGsonFactory();
  }
}
