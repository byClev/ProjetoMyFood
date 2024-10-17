package codigos.entidades;

public class Farmacia extends Empresa{

    private boolean aberto24h;
    private int numeroDeFuncionarios;

    //contructor para o XMLencoder
    public Farmacia() {
        super();
    }

    public Farmacia(int ID_Empresa, int ID_DonoDaEmpresa, String nome, String endereco, boolean aberto24h, int numeroDeFuncionarios) {
        super(ID_Empresa, ID_DonoDaEmpresa, nome, endereco);
        this.aberto24h = aberto24h;
        this.numeroDeFuncionarios = numeroDeFuncionarios;
    }

    //GETTERS
    public boolean isAberto24h() {
        return aberto24h;
    }

    public int getNumeroDeFuncionarios() {
        return numeroDeFuncionarios;
    }

    //SETTERS
    public void setAberto24h(boolean aberto24h) {
        this.aberto24h = aberto24h;
    }

    public void setNumeroDeFuncionarios(int numeroDeFuncionarios) {
        this.numeroDeFuncionarios = numeroDeFuncionarios;
    }

    //DEMAIS METODOS
    public String eDoTipo(){
        return "farmacia";
    }
}
