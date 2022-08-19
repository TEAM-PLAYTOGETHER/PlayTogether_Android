package com.playtogether_android.app.di;

import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("com.playtogether_android.app.testdi.RetrofitModule.MoshiConverter")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RetrofitModule_ProvideRetrofitObjectMoshiFactory implements Factory<Retrofit> {
  private final Provider<Moshi> moshiProvider;

  public RetrofitModule_ProvideRetrofitObjectMoshiFactory(Provider<Moshi> moshiProvider) {
    this.moshiProvider = moshiProvider;
  }

  @Override
  public Retrofit get() {
    return provideRetrofitObjectMoshi(moshiProvider.get());
  }

  public static RetrofitModule_ProvideRetrofitObjectMoshiFactory create(
      Provider<Moshi> moshiProvider) {
    return new RetrofitModule_ProvideRetrofitObjectMoshiFactory(moshiProvider);
  }

  public static Retrofit provideRetrofitObjectMoshi(Moshi moshi) {
    return Preconditions.checkNotNullFromProvides(RetrofitModule.INSTANCE.provideRetrofitObjectMoshi(moshi));
  }
}
