package main;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

public class App {
    
    //private DataVinho dataVinho; 
    //private List<Vinho> vinhos;
    Adega adega; 
    
    public App()
    {
        //this.dataVinho = new DataVinho("data.txt");
        //this.vinhos = dataVinho.read();
        adega = new Adega(20, 10);
        
        this.logicMenuPrincipal();
    }
    
    private void logicMenuPrincipal()
    {
        int opcao;
        do
        {
            opcao = this.menuPrincipal();
            
            if(opcao == 1)
                this.inserirVinho();
                
            else if(opcao == 2)
                this.inserirPosicaoAleatoria();
                
            else if(opcao == 3)
                JOptionPane.showMessageDialog(null, "VINHO BRANCO: " + this.adega.getQuantidadeVinhoBranco());
                
            else if(opcao == 4)
                JOptionPane.showMessageDialog(null, "VINHO TINTO: " + this.adega.getQuantidadeVinhoTinto());
                
                
            else if(opcao == 5)
                JOptionPane.showMessageDialog(null, "TOTAL ADEGA: " + this.adega.getQuantidadeTotalAdega());
                
            else if(opcao == 6)
                JOptionPane.showMessageDialog(null, "TOTAL: R$" + this.adega.getTotalPrecoVinhosAdega());
                
            else if(opcao == 7)
                this.mostrarVinhoMaisAntigo();
            
            else if(opcao == 8)
                this.pegarVinho();
            
            else if(opcao == 9)
                this.mostrarMapaAdega();
            
        }while(opcao != 0);
        
    }
    
    private int menuPrincipal()
    {
        return Integer.parseInt(JOptionPane.showInputDialog(null, "[ 1 ] - INCLUIR UMA GARRAFA NA ADEGA.  \n"
                + "[ 2 ] - INCLUIR UMA GARRAFA EM POSICAO ALEATORIA. \n"
                + "[ 3 ] - OBTER A QUANTIDADE DE VINHOS BRANCO.\n"
                + "[ 4 ] - OBTER A QUANTIDADE DE VINHOS TINTO.\n"
                + "[ 5 ] - OBTER A QUANTIDADE TOTAL DE VINHOS.\n"
                + "[ 6 ] - OBTER O VALOR AGREDADO NA ADEGA.\n"
                + "[ 7 ] - OBTER O VINHO MAIS ANTIGO.\n"
                + "[ 8 ] - PEGAR UM VINHO COM SUA LOCALIZACAO.\n"
                + "[ 9 ] - VER MAPA DA ADEGA.\n"));
       
    }
    
    private void inserirVinho()
    {
        String tipo = JOptionPane.showInputDialog(null, "TIPO DO VINHO");
        double volume = Double.parseDouble(JOptionPane.showInputDialog(null, "VOLUME EM ML: "));
        int dataFabricacao = Integer.parseInt(JOptionPane.showInputDialog(null, "ANO DE FABRICACAO: "));
        double valorVenda = Double.parseDouble(JOptionPane.showInputDialog(null, "VALOR DE VENDA: "));
                
        int fila = Integer.parseInt(JOptionPane.showInputDialog(null, "FILA: "));
        int coluna = Integer.parseInt(JOptionPane.showInputDialog(null, "COLUNA: "));    
        
      
        if(fila >= 0 && coluna >= 0)
        {
            boolean inseriu = this.adega.addVinho(fila, coluna, new Vinho(tipo, volume, dataFabricacao, valorVenda));
            if(inseriu)
                JOptionPane.showMessageDialog(null, "INSERIDO COM SUCESSO!");
            else
                JOptionPane.showMessageDialog(null, "PROBLEMA AO INSERIR!");
        }
        else
           JOptionPane.showMessageDialog(null, "PROBLEMA AO INSERIR!"); 
    }
    
    private void inserirPosicaoAleatoria()
    {
        String tipo = JOptionPane.showInputDialog(null, "TIPO DO VINHO");
        double volume = Double.parseDouble(JOptionPane.showInputDialog(null, "VOLUME EM ML: "));
        int dataFabricacao = Integer.parseInt(JOptionPane.showInputDialog(null, "ANO DE FABRICACAO: "));
        double valorVenda = Double.parseDouble(JOptionPane.showInputDialog(null, "VALOR DE VENDA: "));   
        
        boolean inseriu = this.adega.addRandom(new Vinho(tipo, volume, dataFabricacao, valorVenda));
        if(inseriu)
            JOptionPane.showMessageDialog(null, "INSERIDO COM SUCESSO!");
        else
            JOptionPane.showMessageDialog(null, "PROBLEMA AO INSERIR!");
    }
    
    private void pegarVinho()
    {
        int fila = Integer.parseInt(JOptionPane.showInputDialog(null, "FILA: "));
        int coluna = Integer.parseInt(JOptionPane.showInputDialog(null, "COLUNA: "));    
      
        if(fila >= 0 && coluna >= 0)
        {
            Vinho vinho = this.adega.getVinho(fila, coluna);
            if(vinho != null)
                JOptionPane.showMessageDialog(null, 
                        "TIPO: " + vinho.getTipo()
                        + "VOLUME: " + vinho.getVolume()
                        + "ANO FABRICACAO: " + vinho.getDataFabricacao()
                        + "VALOR DE VENDA: " + vinho.getValorVenda());
            else
                JOptionPane.showMessageDialog(null, "NAO TEM VINHO NESSA LOCALIZACAO");
        }
        else
           JOptionPane.showMessageDialog(null, "PROBLEMA AO INSERIR!");
    }
    
    private void mostrarMapaAdega()
    {
        boolean[][] mapa = this.adega.getMap();
        
        String showMap = "";
        
        for(int i=0 ; i < mapa.length ; i++)
        {
            for(int j=0 ; j < mapa[i].length ; j++)
                if(mapa[i][j])
                    showMap += "[ X ]";
                else
                    showMap += " [ - ] ";

            showMap += "\n";
        }
                
        
        JOptionPane.showMessageDialog(null, "MAPA DE TODA ADEGA: \n\n"
                + showMap);
    }
    
    private void mostrarVinhoMaisAntigo()
    {
        Vinho vinhoPlusOld = this.adega.vinhoOld();
        if(vinhoPlusOld != null)
            JOptionPane.showMessageDialog(null, "ANO DO VINHO MAIS ANTIGO: " + vinhoPlusOld.getDataFabricacao());
        else
            JOptionPane.showMessageDialog(null, "ADEGA VAZIA");
    }
                
    
    public static void main(String[] args) 
    {
//        
//        for(Vinho v : vinhos)
//            adega.addRandom(v);
//        
//        System.out.println("QUANTIDADE VINHOS TINTO: "  + adega.getQuantidadeVinhoTinto());
//        System.out.println("QUANTIDADE VINHOS BRANCO: " + adega.getQuantidadeVinhoBranco());
//        System.out.println("QUANTIDADE TOTAL: "         + adega.getQuantidadeTotalAdega());        
//        System.out.println("PRECO TOTAL TODOS VINHOS: " + adega.getTotalPrecoVinhosAdega());
//        
//        Vinho vinhoMaisAntigo = adega.vinhoOld();
//        System.out.println("VINHO MAIS ANTIGO: " + vinhoMaisAntigo.getTipo() 
//                + "  ANO: " + vinhoMaisAntigo.getDataFabricacao());
//        
//        boolean inseriu = dataVinho.insertList(new Vinho[]{
//            new Vinho("branco", 1000, 1950, 1500.90),
//            new Vinho("tinto", 950, 1250, 9500.90),
//            new Vinho("tinto", 450, 1750, 950.00),
//            new Vinho("branco", 750, 1350, 750.00)
//        });
//        
//        if(inseriu)
//           System.out.println("INSERIU"); 
        
        new App();
    }
    
}
