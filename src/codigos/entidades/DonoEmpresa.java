package codigos.entidades;

import java.util.ArrayList;
import java.util.List;

public class DonoEmpresa extends Usuario {

    private String CPF;
    private List<Integer> empresasDoUsuario;

    //contrutor vazio para o XMLencoder
    public DonoEmpresa(){
        super();
    }

    public DonoEmpresa(int ID, String nome, String email, String senha, String endereco, String CPF) {
        super(ID, nome, email, senha, endereco);
        this.CPF = CPF;
        this.empresasDoUsuario = new ArrayList<>();
    }

    @Override
    public String eDoTipo(){return "dono";}

    public List<Integer> getEmpresasDoUsuario() {
        return empresasDoUsuario;
    }
    public String getCPF() {
        return CPF;
    }

    public void setEmpresasDoUsuario(List<Integer> empresasDoUsuario) {
        this.empresasDoUsuario = empresasDoUsuario;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

}
