package excecoes.pedido;

public class ProdutoInvalidoException extends Exception {
    public ProdutoInvalidoException() {
        super("Produto invalido");
    }
}
