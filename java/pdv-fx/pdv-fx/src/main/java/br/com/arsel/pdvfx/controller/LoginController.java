package br.com.arsel.pdvfx.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    @FXML
    private JFXTextField usuarioField;
    @FXML private JFXPasswordField senhaField;
    @FXML private Label mensagemErro;

    @FXML
    private void initialize() {
        mensagemErro.setVisible(false);
    }

    @FXML
    private void onEntrar() {
        String usuario = usuarioField.getText();
        String senha = senhaField.getText();

        if ("admin".equals(usuario) && "admin".equals(senha)) {
            fillMainView();
        } else {
            mensagemErro.setText("Usuário ou senha inválidos");
            mensagemErro.setVisible(true);
        }
    }

    private void fillMainView(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interface/mainView.fxml"));
            Parent root = loader.load();

            Stage mainStage = new Stage();
            mainStage.setTitle("Sistema Principal");
            mainStage.setScene(new Scene(root));
            mainStage.setMaximized(true);
            mainStage.show();

            Stage loginStage = (Stage) usuarioField.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
