package br.com.aulaclientevip.clienteviprealm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.aulaclientevip.clienteviprealm.model.ClienteVip;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class ClienteVipController {

    public void insert(ClienteVip clienteVip){
        Realm realm = Realm.getDefaultInstance();
        Number pk = realm.where(ClienteVip.class).max("id");
        pk = ((pk != null ? pk.intValue() : 0) + 1);
        clienteVip.setId(pk.intValue());
        realm.beginTransaction();
        realm.copyToRealm(clienteVip);
        realm.commitTransaction();
        realm.close();
    }

    public void update(ClienteVip clienteVip){
        Realm realm = Realm.getDefaultInstance();

        ClienteVip orm = realm.where(ClienteVip.class).
                equalTo("id", clienteVip.getId()).findFirst();
        if(orm != null) {
            realm.beginTransaction();
            orm.setNome(clienteVip.getNome());
            orm.setBairro(clienteVip.getBairro());
            orm.setCep(clienteVip.getCep());
            orm.setCidade(clienteVip.getCidade());
            /*orm.setEmail(clienteVip.getEmail());*/
            orm.setEstado(clienteVip.getEstado());
            orm.setLogradouro(clienteVip.getLogradouro());
            orm.setNumero(clienteVip.getNumero());
            orm.setTelefone(clienteVip.getTelefone());
            orm.setTermoUso(clienteVip.getTermoUso());
            realm.commitTransaction();
        }
        realm.close();
    }

    public void delete(Integer id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<ClienteVip> results = realm
                .where(ClienteVip.class)
                .equalTo("id", id).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public List<ClienteVip> list(){
        List<ClienteVip> result = new ArrayList<>();
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            RealmResults<ClienteVip> results = realm
                    .where(ClienteVip.class).findAll();
            result = realm.copyFromRealm(results);
        }catch (RealmException e){
            e.printStackTrace();
        }finally {
            if(realm != null) realm.close();
        }
        return result;
    }

    public ClienteVip getById(Integer id){
        ClienteVip result = new ClienteVip();
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            result = realm.copyFromRealm(Objects.requireNonNull(realm.where(ClienteVip.class)
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
