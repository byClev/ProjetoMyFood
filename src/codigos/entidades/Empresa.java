package codigos.entidades;

import java.util.ArrayList;
import java.util.List;

public abstract class Empresa {

    private int ID_Dono;
    private int ID_Empresa;
    private String nomeEmpresa;
    private String enderecoEmpresa;
    private List<Integer> produtosDaEmpresa;
    private List<Entregador> entregadoresDaEmpresa;
    private List<Pedido> pedidosDaEmpresa;

    public Empresa() {}

    public Empresa(int ID_Empresa, int ID_DonoDaEmpresa, String nome, String endereco) {
        this.ID_Dono = ID_DonoDaEmpresa;
        this.ID_Empresa = ID_Empresa;
        this.nomeEmpresa = nome;
        this.enderecoEmpresa = endereco;
        this.produtosDaEmpresa = new ArrayList<Integer>();
        this.entregadoresDaEmpresa = new ArrayList<>();
        this.pedidosDaEmpresa = new ArrayList<>();
    }

    //GETTERS
    public int getID_Dono(){
        return ID_Dono;
    }

    public int getID_Empresa() {
        return ID_Empresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getEnderecoEmpresa() {
        return enderecoEmpresa;
    }

    public List<Integer> getProdutosDaEmpresa() {
        return produtosDaEmpresa;
    }

    public List<Entregador> getEntregadoresDaEmpresa() {
        return entregadoresDaEmpresa;
    }

    public List<Pedido> getPedidosDaEmpresa() {
        return pedidosDaEmpresa;
    }

    //SETTERS
    public void setID_Dono(int ID_Dono) {
        this.ID_Dono = ID_Dono;
    }

    public void setID_Empresa(int ID_Empresa) {
        this.ID_Empresa = ID_Empresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public void setEnderecoEmpresa(String enderecoEmpresa) {
        this.enderecoEmpresa = enderecoEmpresa;
    }

    public void setProdutosDaEmpresa(List<Integer> produtosDaEmpresa) {
        this.produtosDaEmpresa = produtosDaEmpresa;
    }

    public void adicionarProdutoNaEmpresa(int produtoDaEmpresa) {
        this.produtosDaEmpresa.add(produtoDaEmpresa);
    }

    public void setEntregadoresDaEmpresa(List<Entregador> entregadoresDaEmpresa) {
        this.entregadoresDaEmpresa = entregadoresDaEmpresa;
    }

    public void setPedidosDaEmpresa(List<Pedido> pedidosDaEmpresa) {
        this.pedidosDaEmpresa = pedidosDaEmpresa;
    }

    //OUTROS METODOS
    public abstract String eDoTipo();
}
