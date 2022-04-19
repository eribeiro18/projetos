package app.modelo.meusclientesrealm.controller;

//implements ICrud<Cliente>
public class ClienteController {

 /*   ContentValues value;

    public ClienteController(Context context) {
        super(context);
    }

    @Override
    public boolean incluir(Cliente obj) throws Exception {
        value = new ContentValues();
        value.put(ClienteDataModel.NOME, obj.getNome());
        value.put(ClienteDataModel.EMAIL, obj.getEmail());
        value.put(ClienteDataModel.TELEFONE, obj.getTelefone());
        value.put(ClienteDataModel.CEP, obj.getCep());
        value.put(ClienteDataModel.LOGRADOURO, obj.getLogradouro());
        value.put(ClienteDataModel.NUMERO, obj.getNumero());
        value.put(ClienteDataModel.BAIRRO, obj.getBairro());
        value.put(ClienteDataModel.CIDADE, obj.getCidade());
        value.put(ClienteDataModel.ESTADO, obj.getEstado());
        value.put(ClienteDataModel.TERMO_USO, obj.getTermoUso());
        return insert(ClienteDataModel.TABELA, value);
    }

    @Override
    public boolean alterar(Cliente obj) throws Exception {
        value = new ContentValues();
        value.put(ClienteDataModel.ID, obj.getId());
        value.put(ClienteDataModel.NOME, obj.getNome());
        value.put(ClienteDataModel.EMAIL, obj.getEmail());
        value.put(ClienteDataModel.TELEFONE, obj.getTelefone());
        value.put(ClienteDataModel.CEP, obj.getCep());
        value.put(ClienteDataModel.LOGRADOURO, obj.getLogradouro());
        value.put(ClienteDataModel.NUMERO, obj.getNumero());
        value.put(ClienteDataModel.BAIRRO, obj.getBairro());
        value.put(ClienteDataModel.CIDADE, obj.getCidade());
        value.put(ClienteDataModel.ESTADO, obj.getEstado());
        value.put(ClienteDataModel.TERMO_USO, obj.getTermoUso());
        return update(ClienteDataModel.TABELA, value);
    }

    @Override
    public boolean deletar(int id) throws Exception {
        return delete(ClienteDataModel.TABELA, id);
    }

    @Override
    public List<Cliente> listar() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        List<Map<String, String>> list = getAll(ClienteDataModel.TABELA);
        for (Map<String, String> map : list) {
            Cliente c = new Cliente();
            for (Map.Entry<String, String> e : map.entrySet()) {
                if (e.getKey().equals("id")) {
                    c.setId(Integer.parseInt(e.getValue()));
                }
                if (e.getKey().equals("nome")) {
                    c.setNome(e.getValue());
                }
                if (e.getKey().equals("email")) {
                    c.setEmail(e.getValue());
                }
                if(e.getKey().equals("telefone")){
                    c.setTelefone(e.getValue());
                }
                if(e.getKey().equals("cep")){
                    c.setCep(Integer.parseInt(e.getValue()));
                }
                if(e.getKey().equals("logradouro")){
                    c.setLogradouro(e.getValue());
                }
                if(e.getKey().equals("numero")){
                    c.setNumero(Integer.parseInt(e.getValue()));
                }
                if(e.getKey().equals("bairro")){
                    c.setBairro(e.getValue());
                }
                if(e.getKey().equals("cidade")){
                    c.setCidade(e.getValue());
                }
                if(e.getKey().equals("estado")){
                    c.setEstado(e.getValue());
                }
            }
            clientes.add(c);
        }
        return clientes;
    }

    public List<String> listarClintesView() throws Exception {
        List<String> clientes = new ArrayList<>();
        List<Cliente> result = listar();
        for (Cliente c: result) {
            clientes.add(c.getId() + " - " + c.getNome());
        }
        return clientes;
    }*/
}
