package app.modelo.meusclientes.controller;

import java.util.List;

public interface ICrud<T> {

    public boolean incluir(T obj) throws Exception;
    public boolean alterar(T obj) throws Exception;
    public boolean deletar(int id) throws Exception;
    public List<T> listar() throws Exception;

}
