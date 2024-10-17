package codigos.entidades;

import java.util.ArrayList;
import java.util.List;

public class Entregador extends Usuario{

    private String veiculo;
    private String placaVeiculo;
    private List<Integer> empresasTrabalhadas;
    private boolean livreParaEntregar;
    private List<Pedido> pedidosEntregados;

    //contructor para o XMLencoder
    public Entregador() {
        super();
    }


    public Entregador(int ID_Usuario, String nome, String email, String senha, String endereco, String veiculo, String placaVeiculo) {
        super(ID_Usuario, nome, email, senha, endereco);
        this.veiculo = veiculo;
        this.placaVeiculo = placaVeiculo;
        empresasTrabalhadas = new ArrayList<Integer>();
        livreParaEntregar = true;
        pedidosEntregados = new ArrayList<Pedido>();
    }

    //GETTERS
    public String getVeiculo() {
        return veiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public List<Integer> getEmpresasTrabalhadas() {
        return empresasTrabalhadas;
    }

    public boolean getLivreParaEntregar() {
        return livreParaEntregar;
    }

    public List<Pedido> getPedidosEntregados() {
        return pedidosEntregados;
    }


    //SETTERS
    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public void setEmpresasTrabalhadas(List<Integer> empresasTrabalhadas) {
        this.empresasTrabalhadas = empresasTrabalhadas;
    }

    public void setLivreParaEntregar(boolean livreParaEntregar) {
        this.livreParaEntregar = livreParaEntregar;
    }

    public void setPedidosEntregados(List<Pedido> pedidosEntregados) {
        this.pedidosEntregados = pedidosEntregados;
    }

    //DEMAIS METODOS
    @Override
    public String eDoTipo() {
        return "entregador";
    }

    public void adicionarEmpresaTrabalhada(Integer empresaTrabalhada) {
        empresasTrabalhadas.add(empresaTrabalhada);
    }


}
