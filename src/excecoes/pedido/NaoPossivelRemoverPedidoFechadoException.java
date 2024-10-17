package excecoes.pedido;

public class NaoPossivelRemoverPedidoFechadoException extends Exception {
    public NaoPossivelRemoverPedidoFechadoException() {
        super("Nao e possivel remover produtos de um pedido fechado");
    }
}
