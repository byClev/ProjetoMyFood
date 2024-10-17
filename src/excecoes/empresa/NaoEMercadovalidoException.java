package excecoes.empresa;

public class NaoEMercadovalidoException extends Exception {
    public NaoEMercadovalidoException() {
        super("Nao e um mercado valido");
    }
}
