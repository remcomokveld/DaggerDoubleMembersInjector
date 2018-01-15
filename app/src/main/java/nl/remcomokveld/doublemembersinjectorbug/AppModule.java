package nl.remcomokveld.doublemembersinjectorbug;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Provides
    static String provideString() {
        return "";
    }
}
