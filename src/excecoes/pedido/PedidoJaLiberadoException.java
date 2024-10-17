package excecoes.pedido;

public class PedidoJaLiberadoException extends Exception{
    public PedidoJaLiberadoException(){
        super("Pedido ja liberado");
    }
}
