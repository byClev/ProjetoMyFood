package codigos.entidades;

public class Cliente extends Usuario {



    //contrutor vazio para o XMLencoder
    public Cliente(){
        super();
    }


    public Cliente(int ID, String nome, String email, String senha, String endereco) {
        super(ID, nome, email, senha, endereco);
    }

    @Override
    public String eDoTipo() {
        return "cliente";
    }
}
