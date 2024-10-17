package excecoes.pedido;

public class PedidoNaoProntoException extends Exception {
    public PedidoNaoProntoException() {
        super("Pedido nao esta pronto para entrega");
    }
}
