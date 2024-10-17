package codigos.entidades;

public abstract class Usuario {
    private int ID_Usuario;
    private String nome;
    private String email;
    private String senha;
    private String endereco;

    //contrutor vazio para o XMLencoder
    public Usuario() {}

    public Usuario(int ID_Usuario, String nome, String email, String senha, String endereco) {
        this.ID_Usuario = ID_Usuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    //GETTERS
    public int getID_Usuario() {
        return ID_Usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    //SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    //DEMAIS METODOS
    public abstract String eDoTipo();
}
