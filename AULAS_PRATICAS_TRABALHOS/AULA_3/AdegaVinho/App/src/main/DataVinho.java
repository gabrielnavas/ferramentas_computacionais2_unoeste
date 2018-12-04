package main;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class DataVinho {
    private String directory;

    public DataVinho() {
        this.directory = "";
    }
    
    public DataVinho(String directory)
    {
        this.setDirectory(directory);
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        if(directory.length() > 0)
            this.directory = directory;
    }
    
    public List<Vinho> read()
    {
        List<Vinho> vinhos = new ArrayList<>();
        RandomAccessFile file;
        
        try
        {
            file = new RandomAccessFile(this.directory, "r");

            String[] lineDados = file.readLine().split(",");
            while(file.getFilePointer() < file.length())
            {
                vinhos.add(new Vinho(
                        lineDados[1],
                        Double.parseDouble(lineDados[3]),
                        Integer.parseInt(lineDados[0]),
                        Double.parseDouble(lineDados[2])
                ));

                lineDados = file.readLine().split(",");
            }

            file.close();
            
            return vinhos;
        }
        catch(IOException | NumberFormatException ex)
        {
            System.out.println("ERROR" + ex.toString());
        }
        
        return null;
    }
    
    public Vinho search(Vinho vinhoKey)
    {
        RandomAccessFile file;
        try
        {
            file = new RandomAccessFile(this.directory, "r");

            String[] lineDados = file.readLine().split(",");
            while(file.getFilePointer() < file.length())
            {
                Vinho vinhoLido = new Vinho(
                        lineDados[1],
                        Double.parseDouble(lineDados[3]),
                        Integer.parseInt(lineDados[0]),
                        Double.parseDouble(lineDados[2])
                );

                if(vinhoLido.equals(vinhoKey))
                    return vinhoLido;
                
                lineDados = file.readLine().split(",");
            }
        }
        catch(IOException | NumberFormatException ex)
        {
            System.out.println("ERROR: " + ex.toString());
        }
        
        return null;
    }
    
    public boolean insertList(Vinho[] vinhos)
    {
        RandomAccessFile file;
        
        try
        {
            file = new RandomAccessFile(this.directory, "rw");
            
            file.readLine();
            while(file.getFilePointer() < file.length())
                file.readLine();
            
            for(Vinho vinho : vinhos)
            {
                file.writeBytes(vinho.getDataFabricacao() + "," +
                                vinho.getTipo() + "," + 
                                vinho.getValorVenda() + "," + 
                                vinho.getVolume() +
                                "\n"
                );
            }
            
            file.close();
            
            return true;
        }
        catch(IOException | NumberFormatException ex)
        {
            System.out.println("ERROR" + ex.toString());
        }
        
        return false;
    }
}
