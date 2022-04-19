package app.br.com.listadecompras.controller.util;

import io.realm.Realm;

public class FullUtilRealm {

    public Long createSequencePk(Class cls, String fieldPk){
        Realm realm = Realm.getDefaultInstance();
        Number primaryKey = realm.where(cls).max("id");
        Integer pk = (primaryKey == null) ? 1 : primaryKey.intValue() + 1;
        return pk.longValue();
    }
}
