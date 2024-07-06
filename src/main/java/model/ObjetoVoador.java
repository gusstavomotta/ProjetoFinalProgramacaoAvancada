package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ObjetoVoador {

    private final String id;
    private final String data;
    private final String nome;
    private final String diametroMinKm;
    private final String diametroMaxKm;
    private final Boolean risco;
    private final String dataDeAproximacao;
    private final double velocidadeAproxKm;

    public ObjetoVoador(String id, String data, String nome, String diametroMinKm, String diametroMaxKm, Boolean risco, String dataDeAproximacao, double velocidadeAproxKm) {

        this.id = id;
        this.data = data;
        this.nome = nome;
        this.diametroMinKm = diametroMinKm;
        this.diametroMaxKm = diametroMaxKm;
        this.risco = risco;
        this.dataDeAproximacao = dataDeAproximacao;
        this.velocidadeAproxKm = velocidadeAproxKm;
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

    public String getDiametroMinKm() {
        return diametroMinKm;
    }

    public String getDiametroMaxKm() {
        return diametroMaxKm;
    }

    public Boolean getRisco() {
        return risco;
    }

    public String getDataDeAproximacao() {
        return dataDeAproximacao;
    }

    public double getVelocidadeAproxKm() {
        return velocidadeAproxKm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id);
        sb.append("\nData: ").append(data);
        sb.append("\nNome: ").append(nome);
        sb.append("\nDiâmetro mínimo em KM: ").append(diametroMinKm);
        sb.append("\nDiâmetro máximo em KM: ").append(diametroMaxKm);
        sb.append("\nRisco: ").append(risco);
        sb.append("\nData de aproximação: ").append(dataDeAproximacao);
        sb.append("\nVelocidade de aproximação em KM/H: ").append(velocidadeAproxKm);
        return sb.toString();
    }

}
