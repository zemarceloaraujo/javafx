package com.br.testezemarcelo;

import com.br.testezemarcelo.model.Cupom;
import com.br.testezemarcelo.model.DetalheCupom;
import com.br.testezemarcelo.model.Produto;
import com.br.testezemarcelo.service.CupomService;
import com.br.testezemarcelo.service.ProdutoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloController implements Initializable {


    private ProdutoService  produtoService = new ProdutoService();
    private CupomService    cupomService   = new CupomService();

    @FXML
    private ComboBox<String> selecaoProduto;

    @FXML
    private TextArea descricaoCupom;

    @FXML
    private TextField vlTotal;

    @FXML
    private TextField vlUnitario;

    @FXML
    private Button submitCupom;

    private ObservableList<String> items = FXCollections.observableArrayList("");

    private List<BigDecimal>    listaValorSelecionado       = new ArrayList<>();
    private List<String>        listaNomeProdutoSelecionado = new ArrayList<>();
    private List<Produto>       listaProduto                = new ArrayList<>();
    private LocalDateTime       dataAberturaCupom           = LocalDateTime.now();
    private LocalDateTime       dataFechamentoCupom         = LocalDateTime.now();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listaProduto = produtoService.listarProduto();

        listaProduto.stream().forEach(item -> {
            items.add(item.getDescricao());
        });

        selecaoProduto.setItems(items);
        selecaoProduto.setEditable(true);

        selecaoProduto.setOnAction(event -> {

            dataAberturaCupom = LocalDateTime.now();

            String produtoSelecionado = selecaoProduto.getSelectionModel().getSelectedItem();
            listaNomeProdutoSelecionado.add(produtoSelecionado);
            listaNomeProdutoSelecionado.add("\n");
            descricaoCupom.setText(listaNomeProdutoSelecionado.toString().trim().replace("[", "").replace("]", ""). replace(",", ""));

            contabilizarProduto(listaProduto, produtoSelecionado);
        });
    }

    @FXML
    private void inserirCupom() {

        dataFechamentoCupom = LocalDateTime.now();
        cupomService.inserirCupom(montarCupom());
    }

    public void contabilizarProduto(List<Produto> lista, String nomeProduto){

        Optional<Produto> produtoSelecionadodaLista = lista.stream().filter(item -> item.getDescricao().equals(nomeProduto)).map(item -> item).findFirst();

        vlUnitario.setText(String.valueOf(produtoSelecionadodaLista.get().getValorUnitario()));
        listaValorSelecionado.add(BigDecimal.valueOf(produtoSelecionadodaLista.get().getValorUnitario()));
        vlTotal.setText(String.valueOf(listaValorSelecionado.stream().mapToDouble(BigDecimal::doubleValue).sum()));
    }


    public Cupom montarCupom() {

        List<Produto> listaProdutoParaInserir           = new ArrayList<>();
        Cupom                   cupom                   = new Cupom();
        DetalheCupom            detalheCupom            = new DetalheCupom();
        List<DetalheCupom>      listaDetalheCupom       = new ArrayList<>();


        listaProdutoParaInserir = listaProduto.stream() .filter(produto -> listaNomeProdutoSelecionado.contains(produto.getDescricao())) .collect(Collectors.toList());

        listaProdutoParaInserir.stream().forEach(item -> {

            if(!(item == null)) {

                DetalheCupom detCupom = new DetalheCupom();

                detCupom.setCodigoProduto(item.getCodigo());
                detCupom.setValorUnitario(BigDecimal.valueOf(item.getValorUnitario()));
                detCupom.setValorLogico(BigDecimal.valueOf(item.getValorUnitario()));

                listaDetalheCupom.add(detCupom);
            }
        });

        cupom.setDetalheCupom(listaDetalheCupom);
        cupom.setDataCadastro(dataAberturaCupom.toString());
        cupom.setDataFechamento(dataFechamentoCupom.toString());
        cupom.setValorTotal(vlTotal.getText());

        return cupom;
    }

}