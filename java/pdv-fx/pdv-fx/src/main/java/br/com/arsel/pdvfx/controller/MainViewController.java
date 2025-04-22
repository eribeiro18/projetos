package br.com.arsel.pdvfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML
    private StackPane contentArea;

    @FXML
    private void handleItemClick() {
        loadView2("/interface/children/itemView.fxml");

    }

    @FXML
    private void handlePDVClick() {
        //loadView("/interface/children/pdvView.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadView2(String fxmlPath){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Minha Janela Modal");
            stage.setScene(new Scene(root));
            //stage.setFullScreen(true);
            stage.initModality(Modality.APPLICATION_MODAL); // TRAVA a janela principal
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}