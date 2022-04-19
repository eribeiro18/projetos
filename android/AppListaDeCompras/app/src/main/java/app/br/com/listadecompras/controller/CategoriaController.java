package app.br.com.listadecompras.controller;

import java.util.List;

import app.br.com.listadecompras.controller.util.FullUtilRealm;
import app.br.com.listadecompras.model.Categoria;
import io.realm.Realm;
import io.realm.RealmResults;

public class CategoriaController implements ICrud<Categoria> {

    private final FullUtilRealm fullUtilRealm = new FullUtilRealm();

    @Override
    public void insert(Categoria obj) throws Exception {
        try (Realm realm = Realm.getDefaultInstance()) {
            obj.setId(fullUtilRealm.createSequencePk(Categoria.class, "id"));
            realm.beginTransaction();
            realm.copyToRealm(obj);
            realm.commitTransaction();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void update(Categoria obj) throws Exception {
        try (Realm realm = Realm.getDefaultInstance()) {
            Categoria categoria = realm.where(Categoria.class).equalTo("id", obj.getId())
                    .findFirst();
            if (categoria != null) {
                realm.beginTransaction();
                categoria.setCategorias(obj.getCategorias());
                categoria.setImagem(obj.getImagem());
                categoria.setTituloCategoriaSpinner(obj.getTituloCategoriaSpinner());
                categoria.setNome(obj.getNome());
                categoria.setTotalDeProdutos(obj.getTotalDeProdutos());
                realm.commitTransaction();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(Categoria obj) throws Exception {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            RealmResults<Categoria> results = realm.where(Categoria.class).equalTo("id",
                    obj.getId()).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteByID(Long id) throws Exception {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            RealmResults<Categoria> results = realm.where(Categoria.class).equalTo("id",
                    id).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Categoria> listar() throws Exception {
        RealmResults<Categoria> results;
        List<Categoria> list;
        try (Realm realm = Realm.getDefaultInstance()) {
            results = realm.where(Categoria.class).findAll();
            list = realm.copyFromRealm(results);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return list;
    }
}
