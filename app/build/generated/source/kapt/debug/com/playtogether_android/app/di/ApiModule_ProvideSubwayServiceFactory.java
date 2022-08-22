package com.playtogether_android.app.di;

import com.playtogether_android.data.api.onboarding.SubwayInfoService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class ApiModule_ProvideSubwayServiceFactory implements Factory<SubwayInfoService> {
  @Override
  public SubwayInfoService get() {
    return provideSubwayService();
  }

  public static ApiModule_ProvideSubwayServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SubwayInfoService provideSubwayService() {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideSubwayService());
  }

  private static final class InstanceHolder {
    private static final ApiModule_ProvideSubwayServiceFactory INSTANCE = new ApiModule_ProvideSubwayServiceFactory();
  }
}
