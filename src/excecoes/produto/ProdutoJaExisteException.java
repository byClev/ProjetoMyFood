package excecoes.produto;

public class ProdutoJaExisteException extends Exception {
    public ProdutoJaExisteException() {
        super("Ja existe um produto com esse nome para essa empresa");
    }
}
