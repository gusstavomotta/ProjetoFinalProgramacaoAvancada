package br.com.unisc.trabpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjetoVoador {

    private final String id;
    private final String data;
    private final String nome;
    private final Double diametroMinKm;
    private final Double diametroMaxKm;
    private final Boolean risco;
    private final String dataDeAproximacao;
    private final double velocidadeAproxKm;
    private final Double distancia;

    public ObjetoVoador(String id, String data, String nome, Double diametroMinKm, Double diametroMaxKm, Boolean risco, String dataDeAproximacao, double velocidadeAproxKm, Double distancia) {
        this.id = id;
        this.data = data;
        this.nome = nome;
        this.diametroMinKm = diametroMinKm;
        this.diametroMaxKm = diametroMaxKm;
        this.risco = risco;
        this.dataDeAproximacao = dataDeAproximacao;
        this.velocidadeAproxKm = velocidadeAproxKm;
        this.distancia = distancia;
    }

    public String getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getDiametroMinKm() {
        return diametroMinKm;
    }

    public Double getDiametroMaxKm() {
        return diametroMaxKm;
    }

    public Boolean getRisco() {
        return risco;
    }

    public boolean isRisco() {
        return risco != null && risco;
    }

    public String getDataDeAproximacao() {
        return dataDeAproximacao;
    }

    public double getVelocidadeAproxKm() {
        return velocidadeAproxKm;
    }

    public Double getDistancia() {
        return distancia;
    }

//    public String getCategoriaRisco() {;
//        if (distancia != null) {
//            if (distancia <= 100000) {
//                return "Alto";
//            } else if (distancia <= 10000000) {
//                return "Médio";
//            } else {
//                return "Baixo";
//            }
//        }
//        return "Desconhecido";
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nID: ").append(id);
        sb.append("\nData: ").append(data);
        sb.append("\nNome: ").append(nome);
        sb.append("\nDiâmetro mínimo em KM: ").append(diametroMinKm);
        sb.append("\nDiâmetro máximo em KM: ").append(diametroMaxKm);
        sb.append("\nRisco: ").append(risco);
        sb.append("\nData de aproximação: ").append(dataDeAproximacao);
        sb.append("\nVelocidade de aproximação em KM/H: ").append(velocidadeAproxKm);
        sb.append("\nDistância da terra: ").append(distancia);
        sb.append("\nCategoria de Risco: ").append(risco);
        return sb.toString();
    }
}
