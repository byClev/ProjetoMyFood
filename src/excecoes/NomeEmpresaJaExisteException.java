package excecoes;

public class NomeEmpresaJaExisteException extends Exception{
    public NomeEmpresaJaExisteException(){
        super("Empresa com esse nome ja existe");
    }
}
