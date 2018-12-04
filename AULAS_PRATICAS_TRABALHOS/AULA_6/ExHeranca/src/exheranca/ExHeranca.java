package exheranca;
public class ExHeranca
{
    private static void mostraFicha(Funcionario f)
    {
        System.out.println("Nome: "+f.getNome());
        System.out.println("Salário de R$ "+f.getSalario()+" semanal");
        
        if(f instanceof Gerente)
            System.out.println("Adicional de "+((Gerente)f).getAdicional()+"%");    // get adicional não tem no funcionario, é apenas do gerente
    }
    public static void main(String[] args) {
        
        Funcionario f1=new Funcionario(123,"Rudolph",40,18.5);
        Funcionario f2=new Funcionario(321,"Pracer",40,12.30);
        Gerente g = new Gerente(999,"Papai Noel",40,25,50);
        
        System.out.println(f1);
        System.out.println(f1.equals(g));
        
        Funcionario[] equipe = new Funcionario[30];
        
        equipe[0] = f1;
        equipe[1] = f2;
        equipe[2] = g;
        
        for(int i=0; i<3; i++)
            mostraFicha(equipe[i]);
        
        mostraFicha(g);
        mostraFicha(f1);
        mostraFicha(f2);
    }
    
}
