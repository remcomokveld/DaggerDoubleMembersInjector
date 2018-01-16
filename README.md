This project demonstrates a possible bug in Dagger2.

Gradle project structure is:
`:app` depends on `:feature-a` depends on `:core`
and 
`:app` depends on `:feature-b` depends on `:core`

`:core` defines:
```
public class BaseClass {
  @Inject
  Dependency myDependency;
}
```
and 
```
public abstract class AbstractBaseClass extends BaseClass { }
```

`:feature-a` defines:
```
public class FeatureAClass extends AbstractBaseClass {
  @Inject
  String test;
}
```

`:feature-b` defines:
```
public class FeatureBClass extends AbstractBaseClass {
  @Inject
  String test;
}
```

`:app` defines
```
@Component(modules = AppModule.class)
public interface AppComponent {
  void inject(FeatureAClass featureAClass);
  void inject(FeatureBClass featureBClass);
}
```
and 
```
@Module
public abstract class AppModule {
  @Provides
  static String provideString() {
    return "";
  }
}
```

When trying to compile this project it will fail because the dagger annotation processor will generate a `AbstractBaseClass_MembersInjector` in both `feature-a` and `feature-b`. 
DexMerger will then throw a DexException because the same class exists twice.
This will only happen if the intermediate class does not contain `@Inject` annotations and the concrete classes do have them.
