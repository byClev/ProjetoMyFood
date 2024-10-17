package excecoes.pedido;

public class EntregadorNaoEstaEmEmpresaException extends Exception {
    public EntregadorNaoEstaEmEmpresaException() {
        super("Entregador nao estar em nenhuma empresa.");
    }
}
