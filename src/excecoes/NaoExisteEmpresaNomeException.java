package excecoes;

public class NaoExisteEmpresaNomeException extends Exception{
    public NaoExisteEmpresaNomeException(){
        super("Nao existe empresa com esse nome");
    }
}
