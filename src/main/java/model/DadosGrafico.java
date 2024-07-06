package model;

import java.time.LocalDate;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caiok
 */
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