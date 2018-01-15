package nl.remcomokveld.doublemembersinjectorbug;

import dagger.Component;
import nl.remcomokveld.feature_a.FeatureAClass;
import nl.remcomokveld.feature_b.FeatureBClass;

@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(FeatureAClass featureAClass);
    void inject(FeatureBClass featureBClass);
    void inject(AppClass appClass);
}
