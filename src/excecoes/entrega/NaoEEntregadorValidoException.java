package excecoes.entrega;

public class NaoEEntregadorValidoException extends Exception{
    public NaoEEntregadorValidoException(){
        super("Nao e um entregador valido");
    }
}
