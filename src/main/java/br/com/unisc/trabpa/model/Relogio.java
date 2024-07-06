package br.com.unisc.trabpa.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Relogio {

    public String getDataHoraAtual() {

        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHoraFormatada = dataAtual.format(formato);

        return dataHoraFormatada;

    }

    public String getDataAtual() {

        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = dataAtual.format(formato);

        return dataFormatada;
    }
}
