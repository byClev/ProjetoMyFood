package codigos.entidades;

public class Produto {
    private int produtoID;
    private int empresaPossuidoraID;
    private String nome;
    private String categoria;
    private float preco;

    //Contructor vazio XMLencoder
    public Produto(){}

    public Produto(int prodID, String nome, String categoria, float preco) {
        this.produtoID = prodID;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    //GETTERS
    public int getProdutoID() {
        return produtoID;
    }

    public int getEmpresaPossuidoraID() {
        return empresaPossuidoraID;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public float getPreco() {
        return preco;
    }


    //SETTERS
    public void setProdutoID(int produtoID) {
        this.produtoID = produtoID;
    }

    public void setEmpresaPossuidoraID(int empresaPossuidoraID) {
        this.empresaPossuidoraID = empresaPossuidoraID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }


}
