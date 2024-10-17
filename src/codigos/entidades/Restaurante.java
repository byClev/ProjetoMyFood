package codigos.entidades;

public class Restaurante extends Empresa {
    private String tipoCozinhaRestaurante;

    //contructor XMLencoder
    public Restaurante(){
        super();
    }

    public Restaurante(int ID_Restaurante,int ID_dono, String nome, String endereco, String tipoCozinha) {
        super(ID_Restaurante,ID_dono, nome, endereco);
        this.tipoCozinhaRestaurante = tipoCozinha;
    }

    //GETTERS E SETTERS
    public String getTipoCozinhaRestaurante() {
        return tipoCozinhaRestaurante;
    }
    public void setTipoCozinhaRestaurante(String tipoCozinhaRestaurante) {
        this.tipoCozinhaRestaurante = tipoCozinhaRestaurante;
    }

    @Override
    public String eDoTipo(){
        return "restaurante";
    }
}
