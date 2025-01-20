package com.br.testezemarcelo.service;

import com.br.testezemarcelo.model.Cupom;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class CupomService {

    private static String urlApiInseriCupom = "http://localhost:8090/gerecupom/inserir";


    public void inserirCupom(Cupom cupom){

        RestTemplate restTemplate    = new RestTemplate();
        HttpHeaders headers         = new HttpHeaders();
        HttpEntity<Cupom> entity          = new HttpEntity<Cupom>(cupom);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.exchange(urlApiInseriCupom, HttpMethod.POST,entity, String.class);

    }
}
