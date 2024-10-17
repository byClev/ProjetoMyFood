package excecoes.entrega;

public class NaoExisteNadaParaEntregarComIdException extends Exception{
    public NaoExisteNadaParaEntregarComIdException(){
        super("Nao existe nada para ser entregue com esse id");
    }
}
