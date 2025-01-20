package com.br.testezemarcelo.service;

import com.br.testezemarcelo.model.Cupom;
import com.br.testezemarcelo.model.Produto;
import com.br.testezemarcelo.util.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {

    private static String urlApiConsulta    = "http://localhost:8090/gereproduto/consultar/";
    private static String urlApiInserir     = "http://localhost:8090/gereproduto/inserir";
    private static String urlApiListar      = "http://localhost:8090/gereproduto/listar";



    public Produto consultarProduto(Integer codigo){

        Gson gson            = new Gson();
        RestTemplate restTemplate    = new RestTemplate();
        HttpHeaders headers         = new HttpHeaders();
        HttpEntity<String> entity          = new HttpEntity<String>(headers);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.exchange(urlApiConsulta + codigo, HttpMethod.GET,entity, String.class);

        return gson.fromJson(response.getBody(), Produto.class);
    }

    public int inserirProduto(Produto produto){

        RestTemplate        restTemplate    = new RestTemplate();
        HttpHeaders         headers         = new HttpHeaders();
        HttpEntity<Produto>  entity          = new HttpEntity<Produto>(produto);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.exchange(urlApiInserir, HttpMethod.GET,entity, String.class);

        return response.getStatusCode().value();
    }


    public List<Produto> listarProduto(){

        RestTemplate restTemplate   = new RestTemplate();
        HttpHeaders headers         = new HttpHeaders();
        HttpEntity<String> entity   = new HttpEntity<String>(headers);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.exchange(urlApiListar, HttpMethod.GET,entity, String.class);

        Gson gson = new GsonBuilder() .registerTypeAdapter(LocalDateTime.class, LocalDateTimeAdapter.getSerializer())
                                      .registerTypeAdapter(LocalDateTime.class, LocalDateTimeAdapter.getDeserializer()) .create();

        Type produtoListType = new TypeToken<List<Produto>>() {}.getType();
        List<Produto> listaProduto = gson.fromJson(response.getBody(), produtoListType);

        return listaProduto;
    }




}
