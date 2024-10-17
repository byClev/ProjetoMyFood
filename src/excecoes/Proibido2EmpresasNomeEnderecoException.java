package excecoes;

public class Proibido2EmpresasNomeEnderecoException extends Exception {
    public Proibido2EmpresasNomeEnderecoException() {
        super("Proibido cadastrar duas empresas com o mesmo nome e local");
    }
}
