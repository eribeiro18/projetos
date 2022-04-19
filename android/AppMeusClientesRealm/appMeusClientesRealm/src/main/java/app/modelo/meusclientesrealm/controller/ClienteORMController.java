package app.modelo.meusclientesrealm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.modelo.meusclientesrealm.model.ClienteORM;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class ClienteORMController {

    public void insert(ClienteORM clienteORM){
        Realm realm = Realm.getDefaultInstance();
        Number pk = realm.where(ClienteORM.class).max("id");
        pk = ((pk != null ? pk.intValue() : 0) + 1);
        clienteORM.setId(pk.intValue());
        realm.beginTransaction();
        realm.copyToRealm(clienteORM);
        realm.commitTransaction();
        realm.close();
    }

    public void update(ClienteORM clienteORM){
        Realm realm = Realm.getDefaultInstance();

        ClienteORM orm = realm.where(ClienteORM.class).
                equalTo("id", clienteORM.getId()).findFirst();
        if(orm != null) {
            realm.beginTransaction();
            orm.setNome(clienteORM.getNome());
            orm.setBairro(clienteORM.getBairro());
            orm.setCep(clienteORM.getCep());
            orm.setCidade(clienteORM.getCidade());
            orm.setEmail(clienteORM.getEmail());
            orm.setEstado(clienteORM.getEstado());
            orm.setLogradouro(clienteORM.getLogradouro());
            orm.setNumero(clienteORM.getNumero());
            orm.setTelefone(clienteORM.getTelefone());
            orm.setTermoUso(clienteORM.getTermoUso());
            realm.commitTransaction();
        }
        realm.close();
    }

    public void delete(Integer id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ClienteORM> results = realm
                .where(ClienteORM.class)
                .equalTo("id", id).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public List<ClienteORM> list(){
        List<ClienteORM> result = new ArrayList<>();
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            RealmResults<ClienteORM> results = realm
                    .where(ClienteORM.class).findAll();
            result = realm.copyFromRealm(results);
        }catch (RealmException e){
            e.printStackTrace();
        }finally {
            if(realm != null) realm.close();
        }
        return result;
    }

    public ClienteORM getById(Integer id){
        ClienteORM result = new ClienteORM();
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            result = realm.copyFromRealm(Objects.requireNonNull(realm.where(ClienteORM.class)
                                              .equalTo("id", id)
                                              .findFirst()));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(realm != null) realm.close();
        }
        return result;
    }
}
