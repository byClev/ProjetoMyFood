package excecoes;

public class UsuarioNaoPodeCriarEmpresaException extends Exception{
    public UsuarioNaoPodeCriarEmpresaException(){
        super("Usuario nao pode criar uma empresa");
    }
}
