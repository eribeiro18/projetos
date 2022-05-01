package br.com.aulaclientevip.clienteviprealm.db;
import android.util.Log;

import br.com.aulaclientevip.clienteviprealm.model.ClienteVip;
import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migracao implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        try {
            if (oldVersion == 1L) {
                schema.get("ClienteVip")
                      .addField("email", String.class, FieldAttribute.REQUIRED)
                      .transform(new RealmObjectSchema.Function() {
                          @Override
                          public void apply(DynamicRealmObject obj) {
                                String field = obj.getString("nome") + "@teste.com.br";
                                obj.set("email", field);
                          }
                      });
                oldVersion++;
            }
            if(oldVersion == 2L){
                schema.get("ClienteVip")
                        .addField("idade", Integer.class, FieldAttribute.REQUIRED)
                        .transform(new RealmObjectSchema.Function() {
                            @Override
                            public void apply(DynamicRealmObject obj) {
                                obj.set("idade", 18);
                            }
                        });
                oldVersion++;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("DEBUG", "ERRO -> " + e.getMessage());
        }
    }

//objeto complexos
    /*RealmObjectSchema petSchema = schema.create("Pet")
            .addField("name", String.class, FieldAttribute.REQUIRED)
            .addField("type", String.class, FieldAttribute.REQUIRED);

    // Add a new field to an old class and populate it with initial data
            schema.get("Person")
                    .addRealmListField("pets", petSchema)
                .transform(new RealmObjectSchema.Function() {
        @Override
        public void apply(DynamicRealmObject obj) {
            if (obj.getString("fullName").equals("JP McDonald")) {
                DynamicRealmObject pet = realm.createObject("Pet");
                pet.setString("name", "Jimbo");
                pet.setString("type", "dog");
                obj.getList("pets").add(pet);
            }
        }
    });*/
}
