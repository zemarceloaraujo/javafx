package com.br.testezemarcelo.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomCellView extends HBox
{
    @FXML
    HBox hboxRoot;
    @FXML
    Label labelCodigo, labelDescricao, labelDataCadastro, labelIsDisponivel, labelQuantidadeEstoque;

    @FXML
    Button btnAdd, btnRemove;

    public CustomCellView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/br/testezemarcelo/CellView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    HBox getHBoxRoot(){
        return hboxRoot;
    }

    void setLabelCodigo(String text){
        labelCodigo.setText(text);
    }

    void setLabelDescricao(String text){
        labelDescricao.setText(text);
    }

    void setLabelDataCadastro(String text){
        labelDataCadastro.setText(text);
    }

    void setLabelIsDisponivel(String text){
        labelIsDisponivel.setText(text);
    }

    void setLabelQuantidadeEstoque(String text){
        labelQuantidadeEstoque.setText(text);
    }

    void setBtnAddAction(EventHandler actionEvent){
        btnAdd.setOnAction(actionEvent);
    }

    void setBtnRemoveAction(EventHandler actionEvent){
        btnRemove.setOnAction(actionEvent);
    }
}
