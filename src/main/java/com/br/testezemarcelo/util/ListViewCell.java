package com.br.testezemarcelo.util;

import com.br.testezemarcelo.model.Produto;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;

public class ListViewCell extends ListCell<Produto>
{
    @Override
    public void updateItem(Produto produto, boolean empty)
    {
        super.updateItem(produto, empty);

        if (produto != null) {

            CustomCellView customCellView = new CustomCellView();
            customCellView.setLabelCodigo(String.valueOf(produto.getCodigo()));
            customCellView.setLabelDescricao(produto.getDescricao());
            customCellView.setLabelIsDisponivel(String.valueOf(produto.getDisponivel()));
            customCellView.setLabelDataCadastro(String.valueOf(produto.getDataCadastro()));
            customCellView.setLabelQuantidadeEstoque(String.valueOf(produto.getQuantidadeEstoque()));

            EventHandler addHandler = (EventHandler) (Event event) -> {
                System.out.println("Add " + produto.getDescricao());
            };
            customCellView.setBtnAddAction(addHandler);

            EventHandler removeHandler = (EventHandler) (Event event) -> {
                System.out.println("Remove " + produto.getDescricao());
            };
            customCellView.setBtnRemoveAction(removeHandler);

            setGraphic(customCellView.getHBoxRoot());
        }
    }
}