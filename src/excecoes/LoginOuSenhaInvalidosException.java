package excecoes;

public class LoginOuSenhaInvalidosException extends Exception{
    public LoginOuSenhaInvalidosException() {
        super("Login ou senha invalidos");
    }
}
