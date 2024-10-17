package excecoes.pedido;

public class PedidoFechadoException extends Exception {
    public PedidoFechadoException() {
        super("Pedido fechado");
    }
}
