package codigos.entidades;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private int idCliente;
    private int idEmpresa;
    private String estadoPedido;
    private List<Produto> produtos;
    private float valorTotal;

    //constructor vazio XMLencoder
    public Pedido(){}

    public Pedido(int idPedido, int idCliente, int idEmpresa) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idEmpresa = idEmpresa;
        this.estadoPedido = "aberto";
        produtos = new ArrayList<Produto>();
        valorTotal = 0;
    }

    //GETTERS

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    //SETTERS
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public void setEstadoPedido(String estadoDoPedido) {
        this.estadoPedido = estadoDoPedido;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    //DEMAIS METODOS
    public void incrementarValorTotal(float incremento) {
        this.valorTotal += incremento;
    }
    public void descrementarValorTotal(float decremento){
        this.valorTotal -= decremento;
    }
    public void fecharPedido(){
        this.estadoPedido = "preparando";
    }

}
