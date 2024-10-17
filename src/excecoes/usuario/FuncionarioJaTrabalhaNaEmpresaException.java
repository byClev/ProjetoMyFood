package excecoes.usuario;

public class FuncionarioJaTrabalhaNaEmpresaException extends Exception {
    public FuncionarioJaTrabalhaNaEmpresaException() {
        super("Funcionario ja trabalha na empresa");
    }
}
