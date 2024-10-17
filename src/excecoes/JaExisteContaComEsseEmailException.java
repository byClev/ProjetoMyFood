package excecoes;

public class JaExisteContaComEsseEmailException extends Exception {
    public JaExisteContaComEsseEmailException() {
        super("Conta com esse email ja existe");
    }
}
