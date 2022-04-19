package app.br.com.listadecompras.controller;

import java.util.List;

public interface ICrud<T> {

    public void insert(T obj) throws Exception;
    public void update(T obj) throws Exception;
    public void delete(T obj) throws Exception;
    public void deleteByID(Long id) throws Exception;
    public List<T> listar() throws Exception;
}
