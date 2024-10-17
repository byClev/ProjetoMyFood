package excecoes.pedido;

public class PedidoNaoEncontradoException extends Exception{
    public PedidoNaoEncontradoException(){
        super("Pedido nao encontrado");
    }
}
