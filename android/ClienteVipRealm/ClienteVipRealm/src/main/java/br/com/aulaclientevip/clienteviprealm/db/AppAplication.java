package br.com.aulaclientevip.clienteviprealm.db;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppAplication extends Application {

    public static final String DB_NAME = "appClientesVip.realm";
    public static final int DB_VERSION = 3;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(new Long(DB_VERSION))
                .migration(new Migracao())
                .allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
