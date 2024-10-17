package excecoes.usuario;

public class UsuarioNaoEEntregadoException extends Exception {
    public UsuarioNaoEEntregadoException() {
        super("Usuario nao e um entregador");
    }
}
