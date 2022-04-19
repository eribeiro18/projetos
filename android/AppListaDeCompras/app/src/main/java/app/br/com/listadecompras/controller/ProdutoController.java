package app.br.com.listadecompras.controller;

import java.util.List;

import app.br.com.listadecompras.controller.util.FullUtilRealm;
import app.br.com.listadecompras.model.Produto;
import io.realm.Realm;
import io.realm.RealmResults;

public class ProdutoController implements ICrud<Produto> {

    private final FullUtilRealm fullUtilRealm = new FullUtilRealm();

    @Override
    public void insert(Produto obj) throws Exception {
        try (Realm realm = Realm.getDefaultInstance()) {
            obj.setId(fullUtilRealm.createSequencePk(Produto.class, "id"));
            realm.beginTransaction();
            realm.copyToRealm(obj);
            realm.commitTransaction();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void update(Produto obj) throws Exception {
        try (Realm realm = Realm.getDefaultInstance()) {
            Produto produto = realm.where(Produto.class).equalTo("id", obj.getId())
                    .findFirst();
            if (produto != null) {
                realm.beginTransaction();
                produto.setImagem(obj.getImagem());
                produto.setProdutos(obj.getProdutos());
                produto.setCodigoBarra(obj.getCodigoBarra());
                produto.setDataInclusao(obj.getDataInclusao());
                produto.setPrecoPago(obj.getPrecoPago());
                produto.setNome(obj.getNome());
                produto.setQuantidade(obj.getQuantidade());
                produto.setUnidadeMedida(obj.getUnidadeMedida());
                realm.commitTransaction();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(Produto obj) throws Exception {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            RealmResults<Produto> results = realm.where(Produto.class).equalTo("id",
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
            RealmResults<Produto> results = realm.where(Produto.class).equalTo("id",
                    id).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Produto> listar() throws Exception {
        RealmResults<Produto> results;
        List<Produto> list;
        try (Realm realm = Realm.getDefaultInstance()) {
            results = realm.where(Produto.class).findAll();
            list = realm.copyFromRealm(results);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return list;
    }
}
