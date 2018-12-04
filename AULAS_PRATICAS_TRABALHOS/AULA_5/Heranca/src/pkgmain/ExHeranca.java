package pkgmain;


public class ExHeranca {

    private static void mostraFicha(Funcionario f)
    {
        System.out.println("Nome: " + f.getNome());
        System.out.println("Salario de R$: " + f.getSalario() + " semanal");
        
        if(f instanceof Gerente)
            System.out.println("Adicional de " + ((Gerente) f).getAdicional() + "%");
    }
    
    public static void main(String[] args) {
        Funcionario f = new Funcionario(123, "Gabriel", 5, 10.0);
        Funcionario f2 = new Funcionario(123, "Miguel", 5, 10.0);
        
        Gerente g = new Gerente(123, "Navas", 5, 10.0, 50);
        
        Funcionario[] equipe = new Funcionario[30];
        equipe[0]=f;
        equipe[1]=f2;
        equipe[0]=g;
        
        System.out.println(g.getSalario());
        System.out.println(g.getSalario());
        mostraFicha(f);
    }
    
}
