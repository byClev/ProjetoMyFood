package codigos.entidades;

public class Mercado extends Empresa{
    private String abre;
    private String fecha;
    private String tipoMercado;

    public Mercado(){
        super();
    }

    public Mercado(int ID_Empresa, int ID_DonoDaEmpresa, String nome, String endereco, String abre, String fecha, String tipoMercado) {
        super(ID_Empresa, ID_DonoDaEmpresa, nome, endereco);
        this.abre = abre;
        this.fecha = fecha;
        this.tipoMercado = tipoMercado;
    }

    //GETTERS
    public String getAbre() {
        return abre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipoMercado() {
        return tipoMercado;
    }

    //SETTERS
    public void setAbre(String abre) {
        this.abre = abre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTipoMercado(String tipoMercado) {
        this.tipoMercado = tipoMercado;
    }

    //OUTROS METODOS
    public String eDoTipo(){
        return "mercado";
    }

}
