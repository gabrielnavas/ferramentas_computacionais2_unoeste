/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.Clock;
import java.time.LocalDate;

public class Anotacao {
    private LocalDate data;
    private String titulo;
    private String texto;
    
    public Anotacao(String titulo, String texto)
    {
        this.titulo = titulo;
        this.texto = texto;
        this.data = LocalDate.now(Clock.systemUTC());
    }

    public Anotacao() {
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
