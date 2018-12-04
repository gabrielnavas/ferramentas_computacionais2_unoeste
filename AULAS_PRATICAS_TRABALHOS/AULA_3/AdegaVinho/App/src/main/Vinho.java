/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.time.LocalDate;

public class Vinho {
    private String tipo;
    private double volume;
    private int dataFabricacao;
    private double valorVenda;

    public Vinho() {
        this.tipo = "";
        this.volume = 0.0;
        this.dataFabricacao = 0;
        this.valorVenda = 0.0;
    }

    public Vinho(String tipo, double volume, int dataFabricacao, double valorVenda) {
        this.setTipo(tipo);
        this.setVolume(volume);
        this.setDataFabricacao(dataFabricacao);
        this.setValorVenda(valorVenda);
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if(tipo.length() > 0)
            this.tipo = tipo;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        if(volume > 0.0)
            this.volume = volume;
    }

    public int getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(int dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
}
