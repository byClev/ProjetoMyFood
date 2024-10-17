package excecoes.pedido;

public class NaoPermitido2PedidosAbertosException extends Exception{
    public NaoPermitido2PedidosAbertosException() {
        super("Nao e permitido ter dois pedidos em aberto para a mesma empresa");
    }
}
