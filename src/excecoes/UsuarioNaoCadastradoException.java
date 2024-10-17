package excecoes;

public class UsuarioNaoCadastradoException extends Exception{
    public UsuarioNaoCadastradoException() {
        super("Usuario nao cadastrado.");
    }
}
