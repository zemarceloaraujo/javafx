package com.br.testezemarcelo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonTypeName("cupom")
@JsonSerialize
@JsonDeserialize
public class Cupom {

    @JsonProperty("dataCadastro")
    private String dataCadastro;

    @JsonProperty("dataFechamento")
    private String dataFechamento;

    @JsonProperty("valorTotal")
    private String valorTotal;

    @JsonProperty("detalheCupom")
    private List<DetalheCupom> listaDetalheCupom;

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setDetalheCupom(List<DetalheCupom> listaDetalheCupom) {
        this.listaDetalheCupom = listaDetalheCupom;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public String getDataFechamento() {
        return dataFechamento;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public List<DetalheCupom> getDetalheCupom() {
        return listaDetalheCupom;
    }
}
