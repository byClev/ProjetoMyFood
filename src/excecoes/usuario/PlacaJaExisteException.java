package excecoes.usuario;

public class PlacaJaExisteException extends Exception{
    public PlacaJaExisteException(){
        super("Placa ja existe");
    }
}
