package excecoes.produto;

public class EmpresaNaoEncontradaException extends Exception{
    public EmpresaNaoEncontradaException(){
        super("Empresa nao encontrada");
    }
}
