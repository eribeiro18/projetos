package br.com.arsel.pdvfx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Item {

    private String codigo;
    private String descricao;
    private String dataCriacao;

    public StringProperty codigoProperty() {
        return new SimpleStringProperty(codigo);
    }

    public StringProperty descricaoProperty() {
        return new SimpleStringProperty(descricao);
    }

    public StringProperty dataCriacaoProperty() {
        return new SimpleStringProperty(dataCriacao);
    }
}
