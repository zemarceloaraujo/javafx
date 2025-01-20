module com.br.testezemarcelo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.annotation;
    requires spring.web;
    requires org.mapstruct;
    requires spring.core;
    requires com.google.gson;
    requires spring.beans;
    requires spring.context;
    requires com.fasterxml.jackson.databind;

    opens com.br.testezemarcelo to javafx.fxml;
    opens com.br.testezemarcelo.model to com.google.gson;
    opens com.br.testezemarcelo.util to javafx.fxml;
    exports com.br.testezemarcelo;
    exports com.br.testezemarcelo.model to com.fasterxml.jackson.databind;
}