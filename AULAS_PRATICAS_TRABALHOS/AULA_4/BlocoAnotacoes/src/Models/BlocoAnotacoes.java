package Models;

import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BlocoAnotacoes {
    private List<Anotacao> anotacoes;
    
    private static final String DIRECTORY_FILE = "anotacoes.txt";
    
    public BlocoAnotacoes(){
        this.anotacoes = new ArrayList();
    }
    
    public boolean inserirAnotacao(Anotacao anotacao)
    {
        if(anotacao.getTitulo().length() > 0 && anotacao.getTitulo().length() > 0)
        {
            return anotacoes.add(anotacao);
        }
        
        return false;
    }
    
    public boolean apagarAnotacao(Anotacao anotacao)
    {
        return this.anotacoes.remove(anotacao);
    }
    
    public List<Anotacao> buscarAnotacao(String titleFiltro)
    {
        List<Anotacao> anotacoesAchadas = new ArrayList();
        
        for(Anotacao anotacao : this.anotacoes)
            if(anotacao.getTitulo().contains(titleFiltro) ||
                    anotacao.getTexto().contains(titleFiltro))
                anotacoesAchadas.add(anotacao);
        
        return anotacoesAchadas;
    }
    
    public Anotacao buscarAnotacoes(String title)
    {
        for(Anotacao anotacao : this.anotacoes)
            if(anotacao.getTitulo().equals(title))
                return anotacao;
        return null;
    }
    
    public List<Anotacao> buscarAnotacoes(LocalDate date)
    {
        List<Anotacao> anotacoesAchadas = new ArrayList();
        
        for(Anotacao anotacao : this.anotacoes)
            if(anotacao.getData().equals(date))
                anotacoesAchadas.add(anotacao);
        
        return anotacoesAchadas;
    }
    
    public List<Anotacao> getAllAnotacoes()
    {
        return this.anotacoes;
    }
    
    public boolean writeAnotacoes()
    {
        RandomAccessFile file;
        try
        {
            file = new RandomAccessFile("anotacoes.txt", "rw");
            
            while(file.getFilePointer() < file.length())
                file.read();
            
            for(Anotacao anotacao : this.anotacoes)
            {
                file.writeBytes(anotacao.getData().toString()+ "\n" + 
                        anotacao.getTitulo() + "\n" +  
                        anotacao.getTexto() + "\n\n");
            }
            
            file.close();
            
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("ERROR: " + ex.toString());
        }
        
        return false;
    }
    
    public boolean readAnotacoes()
    {
        RandomAccessFile file;
        try
        {
            file = new RandomAccessFile(this.DIRECTORY_FILE, "r");
            
            if(file.length() > 0)
            {   
                do
                {
                    Anotacao anotacao = new Anotacao();
                    
                    anotacao.setData(LocalDate.parse(file.readLine().toString()));
                    anotacao.setTitulo(file.readLine());
                    anotacao.setTexto(file.readLine());
                    
                    file.readLine();
                    
                    this.anotacoes.add(anotacao);
                
                }while(file.getFilePointer() < file.length());
            }
            
            file.close();
            
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("ERROR: " + ex.toString());
        }
        
        return false;
    }
    
    
}
