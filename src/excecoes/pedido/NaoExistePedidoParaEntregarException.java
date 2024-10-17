package excecoes.pedido;

public class NaoExistePedidoParaEntregarException extends Exception{
    public NaoExistePedidoParaEntregarException(){
        super("Nao existe pedido para entrega");
    }
}
