/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ObjetoVoador {

    @Override
    public String toString() {
        return "ObjetoVoador{" +"id=" + id + ", data=" + data + ", nome=" + nome + ", diametroMinKm=" + diametroMinKm + ", diametroMaxKm=" + diametroMaxKm + ", risco=" + risco + ", dataDeAproximacao=" + dataDeAproximacao + ", velocidadeAproxKm=" + velocidadeAproxKm + '}';
    }
    
    private String id;
    private String data;
    private String nome;
    private String diametroMinKm;
    private String diametroMaxKm;
    private Boolean risco;
    private String dataDeAproximacao;
    private double velocidadeAproxKm;
    

    public ObjetoVoador( String id, String data, String nome, String diametroMinKm, String diametroMaxKm, Boolean risco, String dataDeAproximacao, double velocidadeAproxKm) {
        
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

}
