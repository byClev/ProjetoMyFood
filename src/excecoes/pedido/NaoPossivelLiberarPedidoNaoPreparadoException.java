package excecoes.pedido;

public class NaoPossivelLiberarPedidoNaoPreparadoException extends Exception{
    public NaoPossivelLiberarPedidoNaoPreparadoException(){
        super("Nao e possivel liberar um produto que nao esta sendo preparado");
    }
}
