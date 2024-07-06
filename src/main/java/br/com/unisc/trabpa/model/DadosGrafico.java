package br.com.unisc.trabpa.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class DadosGrafico {

    private ArrayList<LocalDate> dias;
    private ArrayList<ArrayList<ObjetoVoador>> matriz;

    public DadosGrafico(ArrayList<LocalDate> dias, ArrayList<ArrayList<ObjetoVoador>> matriz) {
        this.dias = dias;
        this.matriz = matriz;
    }

    public ArrayList<LocalDate> getDias() {
        return dias;
    }

    public ArrayList<ArrayList<ObjetoVoador>> getMatriz() {
        return matriz;
    }
}
