package main;

import java.util.ArrayList;
import java.util.List;

public class Adega {   
    private Vinho[][] vinhos;
    
    private double totalPrecoVinhosAdega;
    
    private int quantidadeFilas;
    private int quantidadeColunas;
    
    private int quantidadeTotalAdega;
    private int quantidadeVinhoBranco;
    private int quantidadeVinhoTinto;
    
    public Adega(int filas, int colunas)
    {
        this.setQuantidadeFilas(filas);
        this.setQuantidadeColunas(colunas);
        
        this.quantidadeTotalAdega = 0;
        this.quantidadeVinhoBranco = 0;
        this.quantidadeVinhoTinto = 0;
        
        this.totalPrecoVinhosAdega = 0.00;
        
        this.mountVinhos();
    } 
    
    public boolean addVinho(int fila, int coluna, Vinho vinho)
    {
        if( !this.isEmptyPosition(fila, coluna) )
            return false;
        
        boolean dadoVinhoCorretos = this.verificaDadosVinho(vinho);
        if(dadoVinhoCorretos)
        {
            this.vinhos[fila][coluna]=vinho;
            if(vinho.getTipo().equals("branco"))
                this.quantidadeVinhoBranco++;
            else if(vinho.getTipo().equals("tinto"))
                this.quantidadeVinhoTinto++;
                
            this.quantidadeTotalAdega++;
            this.totalPrecoVinhosAdega += vinho.getValorVenda();
       
            return true;
        }
        
        return false;
    }
    
    public boolean addRandom(Vinho vinho)
    {   
        if( !this.isFull() )
        {
            int fila;
            int coluna;
            do
            {
                fila = (int) (Math.random() * this.quantidadeFilas);
                coluna = (int) (Math.random() * this.quantidadeColunas);
            }while( !isEmptyPosition(fila, coluna) );
            
            return this.addVinho(fila, coluna, vinho);                        
        }
        
        return false;
    } 
    
    public Vinho vinhoOld()
    {
        if( !this.isEmpty())
        {
            int[] position = this.getPositionNotEmptyNext();
            Vinho vinhoOld = this.vinhos[position[0]][position[1]];

            for(int i=0 ; i<this.vinhos.length ; i++)
               for(int j=0 ; j<this.vinhos[i].length ; j++)
                    if(vinhos[i][j] != null && 
                            vinhoOld != null &&
                            vinhos[i][j].getDataFabricacao() < vinhoOld.getDataFabricacao()
                    )
                       vinhoOld = vinhos[i][j];

            return vinhoOld;
        }
        
        return null;
    }
    
    public Vinho getVinho(int fila, int coluna)
    {
        if(fila >= 0 && coluna >= 0 && !this.isEmptyPosition(fila, coluna))
        {
            Vinho vinho = this.vinhos[fila][coluna];
            this.vinhos[fila][coluna] = null;
            this.quantidadeTotalAdega--;
            return vinho;
        }

        return null;
    }
    
    /////
    
    public boolean[][] getMap()
    {
        boolean[][] map = new boolean[this.quantidadeFilas][this.quantidadeColunas];
        
        for(int i=0 ; i < this.vinhos.length ; i++)
            for(int j=0 ; j < this.vinhos[i].length ; j++)
                if(vinhos[i][j] != null)
                    map[i][j] = true;
        
        return map;
    }
    
    public List<Vinho> getVinhosList()
    {
        List<Vinho> vinhos = new ArrayList<>();
        
        for(int i=0 ; i<this.vinhos.length ; i++)
            for(int j=0 ; j<this.vinhos[i].length ; j++)
                if(this.vinhos[i][j] != null)
                    vinhos.add(this.vinhos[i][j]);
        
        return vinhos;
    }
    
    public int[] getPositionEmptyNext()
    {
        int[] position = new int[2];
        
        for(int i=0 ; i<this.vinhos.length ; i++)
            for(int j=0 ; j<this.vinhos[i].length ; j++)
                if(vinhos[i][j] == null)
                {
                    position[0] = i;
                    position[1] = j;
                    
                    return position;
                }
        
        return null;
    }
    
    public int[] getPositionNotEmptyNext()
    {
        int[] position = new int[2];
        
        for(int i=0 ; i<this.vinhos.length ; i++)
            for(int j=0 ; j<this.vinhos[i].length ; j++)
                if(vinhos[i][j] != null)
                {
                    position[0] = i;
                    position[1] = j;
                    
                    return position;
                }
        
        return null;
    }
    
    public boolean isEmptyPosition(int fila, int coluna)
    {
        return this.vinhos[fila][coluna] == null;
    }
            
    public boolean isEmpty()
    {
        return this.getQuantidadeTotalAdega() == 0;
    }
    
    public boolean isFull()
    {       
        return this.getQuantidadeTotalAdega() == 
                (this.quantidadeColunas*this.quantidadeFilas);
    }
    
    private void mountVinhos()
    {
        if(this.quantidadeFilas > 0 && this.quantidadeColunas > 0)
            vinhos = new Vinho[this.quantidadeFilas][this.quantidadeColunas];
    }

    private boolean verificaDadosVinho(Vinho vinho)
    {
        if(vinho.getDataFabricacao() > 0 && 
                (vinho.getTipo().equals("branco") || vinho.getTipo().equals("tinto")) &&
                vinho.getValorVenda() >= 0.00 &&
                vinho.getVolume() > 0)
                return true;
        
        return false;
    }
    
    public double getTotalPrecoVinhosAdega() {
        return totalPrecoVinhosAdega;
    }

    public int getQuantidadeTotalAdega() {
        return quantidadeTotalAdega;
    }

    public int getQuantidadeVinhoBranco() {
        return quantidadeVinhoBranco;
    }

    public int getQuantidadeVinhoTinto() {
        return quantidadeVinhoTinto;
    }

    public int getQuantidadeFilas() {
        return quantidadeFilas;
    }

    public void setQuantidadeFilas(int quantidadeFilas) {
        this.quantidadeFilas = quantidadeFilas;
    }

    public int getQuantidadeColunas() {
        return quantidadeColunas;
    }

    public void setQuantidadeColunas(int quantidadeColunas) {
        this.quantidadeColunas = quantidadeColunas;
    }
}
