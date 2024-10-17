package codigos.entidades;

import java.util.ArrayList;
import java.util.List;

public class Entrega {

    private int idEntrega;
    private String nomeCliente;
    private String nomeEmpresa;
    private int idPedido;
    private int idEntregador;
    private String destino;
    private List<String> listaDeProdutos;

    //XMLendcoder
    public Entrega(){}

    public Entrega(int idEntrega, String nomeCliente, String nomeEmpresa, int idPedido, int idEntregador, String destino, List<String> produtosObtidos) {
        this.idEntrega = idEntrega;
        this.nomeCliente = nomeCliente;
        this.nomeEmpresa = nomeEmpresa;
        this.idPedido = idPedido;
        this.idEntregador = idEntregador;
        this.destino = destino;
        this.listaDeProdutos = produtosObtidos;
    }

    //GETTERS
    public int getIdEntrega() {
        return idEntrega;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdEntregador() {
        return idEntregador;
    }

    public String getDestino() {
        return destino;
    }

    public List<String> getListaDeProdutos() {
        return listaDeProdutos;
    }

    //SETTERS
    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setIdEntregador(int idEntregador) {
        this.idEntregador = idEntregador;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setListaDeProdutos(List<String> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }

}