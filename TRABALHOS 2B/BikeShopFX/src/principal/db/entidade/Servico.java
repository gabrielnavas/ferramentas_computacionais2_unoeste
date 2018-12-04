package principal.db.entidade;

public class Servico 
{
    private int cod;
    private String descr;
    private double preco;

    public Servico() { this(0,"",0); }
    
    public Servico(String descr, double preco) { 
        this(0,descr,preco);
    }
    
    public Servico(int cod, String descr, double preco) 
    {  
        this.cod = cod; 
        this.descr = descr; 
        this.preco = preco;  
    }
    
    public int getCod() { 
        return cod; 
    }
    
    public void setCod(int cod) { 
        this.cod = cod; 
    }
    
    public String getDescr() { 
        return descr; 
    }
    
    public void setDescr(String descr) { 
        this.descr = descr;  
    }
    
    public double getPreco() { 
        return preco;
    }
    
    public void setPreco(double preco) { 
        this.preco = preco; 
    }
    
    @Override
    public String toString() 
    { 
        return descr; 
    }  

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.cod;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Servico other = (Servico) obj;
        if (this.cod != other.cod) {
            return false;
        }
        return true;
    }
    
    
}
