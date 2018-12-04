/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author navas
 */
public class Pessoa implements Serializable{
    private String nome;
    private String endereco;

    public Pessoa() {
        this.nome = "";
        this.endereco = "";
    }
    
    public Pessoa(String nome, String endereco) {
        this();
        
        this.nome = nome;
        this.endereco = endereco;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public void gravar()
    {
        FileOutputStream fout = null;
        ObjectOutputStream out;
        try {
            fout = new FileOutputStream("objeto.dat");
            out = new ObjectOutputStream(fout);
            out.writeObject(this);
            out.close();
        } 
        catch (Exception e) { System.out.println("Erro:"+e.getMessage()); }
    }
    
    public static Pessoa carregar()
    {   Pessoa p=null;
        FileInputStream fin = null;
        ObjectInputStream in;
        try {
            fin = new FileInputStream("objeto.dat");
            in = new ObjectInputStream(fin);
            p = (Pessoa) in.readObject();
            in.close();
        } 
        catch (Exception e) {
            System.out.println("Erro:"+e.getMessage());
            p=null;
        }
        return p;
    }
    
}
