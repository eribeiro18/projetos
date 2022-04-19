package app.modelo.meusclientesrealm.view;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppAplication extends Application {

    public static final String DB_NAME = "appMeusClientes.realm";
    public static final int DB_VERSION = 1;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(DB_VERSION)
                .allowWritesOnUiThread(true)
                .build();
        Realm realm = Realm.getInstance(config);
    }
}
