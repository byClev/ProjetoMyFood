package excecoes.entrega;

public class NaoExisteEntregaComEsseIDException extends Exception{
    public NaoExisteEntregaComEsseIDException(){
        super("Nao existe entrega com esse id");
    }
}
