package excecoes.entrega;

public class EntregadorAindaEmEntregaException extends Exception {
    public EntregadorAindaEmEntregaException() {
        super("Entregador ainda em entrega");
    }
}
