package excecoes.pedido;

public class NaoPossivelAdicionarProdutoPedidoFechadoException extends Exception {
    public NaoPossivelAdicionarProdutoPedidoFechadoException() {
        super("Nao e possivel adcionar produtos a um pedido fechado");
    }
}
