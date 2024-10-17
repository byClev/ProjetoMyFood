package excecoes.entrega;

public class NaoEEntregadorException extends Exception{
    public NaoEEntregadorException(){
        super("Usuario nao e um entregador");
    }
}
