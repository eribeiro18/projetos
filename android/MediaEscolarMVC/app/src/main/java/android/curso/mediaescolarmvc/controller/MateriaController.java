package android.curso.mediaescolarmvc.controller;

import android.content.ContentValues;
import android.content.Context;
import android.curso.mediaescolarmvc.datamodel.MateriaDataModel;
import android.curso.mediaescolarmvc.datamodel.MediaEscolarDataModel;
import android.curso.mediaescolarmvc.datasource.DataSource;
import android.curso.mediaescolarmvc.model.Materia;
import java.util.List;

public class MateriaController extends DataSource {
    ContentValues dados;

    public MateriaController(Context context) {
        super(context);
    }

    public boolean salvar(Materia obj) {
        boolean sucesso = true;
        dados = new ContentValues();
        dados.put(MateriaDataModel.getCodigoDaMateria(), obj.getCodigoDaMateria());
        dados.put(MateriaDataModel.getNomeDaMateria(), obj.getNomeDaMateria());
        sucesso = insert(MediaEscolarDataModel.getTABELA(), dados);
        return sucesso;
    }

    public boolean deletar(Materia obj) {
        boolean sucesso = true;
        sucesso = deletar(MateriaDataModel.getTABELA(), obj.getCodigoDaMateria());
        return sucesso;
    }

    public boolean alterar(Materia obj) {
        boolean sucesso = true;
        dados = new ContentValues();
        dados.put(MateriaDataModel.getId(), obj.getId());
        dados.put(MateriaDataModel.getCodigoDaMateria(), obj.getCodigoDaMateria());
        dados.put(MateriaDataModel.getNomeDaMateria(), obj.getNomeDaMateria());
        sucesso = alterar(MateriaDataModel.getTABELA(), dados);
        return sucesso;
    }

    public List<Materia> listar() {
        return getAllMateria();
    }

}
