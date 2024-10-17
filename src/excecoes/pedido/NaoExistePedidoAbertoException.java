package excecoes.pedido;

public class NaoExistePedidoAbertoException extends Exception{
    public NaoExistePedidoAbertoException(){
        super("Nao existe pedido em aberto");
    }
}
