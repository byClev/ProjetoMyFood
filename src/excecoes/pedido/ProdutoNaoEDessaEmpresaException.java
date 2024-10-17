package excecoes.pedido;

public class ProdutoNaoEDessaEmpresaException extends Exception{
    public ProdutoNaoEDessaEmpresaException(){
        super("O produto nao pertence a essa empresa");
    }
}
