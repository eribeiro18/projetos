package br.com.arsel.pdvfx.controller;

import br.com.arsel.pdvfx.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Pagination;

public class ItemViewController {

    @FXML
    private TextField codigoField;

    @FXML
    private TextField descricaoField;

    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, String> codigoColumn;

    @FXML
    private TableColumn<Item, String> descricaoColumn;

    @FXML
    private TableColumn<Item, String> dataCriacaoColumn;

    @FXML
    private Pagination pagination;

    // Inicialização da tela
    @FXML
    private void initialize() {
        // Inicializa as colunas da tabela
        codigoColumn.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());
        descricaoColumn.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
        dataCriacaoColumn.setCellValueFactory(cellData -> cellData.getValue().dataCriacaoProperty());

        // Exemplo de um item para a tabela
        itemTable.getItems().add(new Item("001", "Exemplo de Item", "2025-04-12"));

        // Configuração de paginação (exemplo simples)
        pagination.setPageFactory(this::createPage);
    }

    // Método para lidar com o clique no botão "Cadastrar"
    @FXML
    private void onCadastrar(ActionEvent event) {
        String codigo = codigoField.getText();
        String descricao = descricaoField.getText();

        if (codigo.isEmpty() || descricao.isEmpty()) {
            // Exibe mensagem de erro, caso algum campo esteja vazio
            System.out.println("Preencha todos os campos!");
            return;
        }

        // Cria um novo item e adiciona à tabela
        itemTable.getItems().add(new Item(codigo, descricao, "2025-04-12"));

        // Limpa os campos após o cadastro
        codigoField.clear();
        descricaoField.clear();
    }

    // Método para configurar a paginação
    private TableView<Item> createPage(int pageIndex) {
        // Aqui você poderia configurar os dados a serem exibidos por página
        // Por enquanto, retornando a tabela inteira (exemplo simples)
        return itemTable;
    }
}