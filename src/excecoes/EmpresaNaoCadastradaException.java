package excecoes;

public class EmpresaNaoCadastradaException extends Exception{
    public EmpresaNaoCadastradaException(){
        super("Empresa nao cadastrada");
    }
}
