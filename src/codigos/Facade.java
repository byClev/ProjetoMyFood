package codigos;

public class Facade {
    Sistema sistema1 = new Sistema();

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        sistema1.sistemaCriarUsuario(nome, email, senha, endereco, cpf);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        sistema1.sistemaCriarUsuario(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String veiculo, String placa) throws Exception {
        sistema1.sistemaCriarUsuario(nome, email, senha, endereco, veiculo, placa);
    }

    public String getAtributoUsuario(Integer id, String atributo) throws Exception {
        return sistema1.sistemaGetAtributoUsuario(id, atributo);
    }

    public int login(String email, String senha) throws Exception {
        return sistema1.sistemaLogin(email, senha);
    }

    public void cadastrarEntregador(int idEmpresa, int idEntregador) throws Exception {
        sistema1.sistemaCadastrarEntregador(idEntregador, idEmpresa);
    }

    public String getEmpresas (int idDono) throws Exception {
        return sistema1.sistemaGetEmpresasDoUsuario(idDono, "entregador");
    }

    public int criarEmpresa(String tipoEmpresa, int ID_dono, String nome, String endereco, String tipoCozinha) throws Exception{
        return sistema1.sistemaCriarEmpresa(tipoEmpresa, ID_dono, nome, endereco, tipoCozinha);
    }

    public int criarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, String horaAbertura, String horaFechamento, String tipoMercado) throws Exception{
        return sistema1.sistemaCriarEmpresa(tipoEmpresa, idDono, nome, endereco, horaAbertura, horaFechamento, tipoMercado);
    }

    public int criarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, boolean aberto24h, int numeroFuncionarios) throws Exception{
        return sistema1.sistemaCriarEmpresa(tipoEmpresa, idDono, nome, endereco, aberto24h, numeroFuncionarios);
    }

    public int getIdEmpresa (int idDono, String nome, int indice) throws Exception {
        return sistema1.sistemaGetIdEmpresa(idDono, nome, indice);
    }

    public String getEmpresasDoUsuario (int idDono) throws Exception {
        return sistema1.sistemaGetEmpresasDoUsuario(idDono, "dono");
    }

    public String getAtributoEmpresa (int IdEmpresa, String atributo) throws Exception {
        return sistema1.sistemaGetAtributoEmpresa(IdEmpresa, atributo);
    }

    public void alterarFuncionamento(int idEmpresa, String abre, String fecha) throws Exception {
        sistema1.sistemaAlterarFuncionamento(idEmpresa, abre, fecha);
    }

    public String getEntregadores(int idEmpresa) throws Exception{
        return sistema1.sistemaGetEntregadoresDaEmpresa(idEmpresa);
    }

    public int criarProduto(int empresa, String nome, float valor, String categoria) throws Exception {
        return sistema1.sistemaCriarProduto(empresa, nome, valor, categoria);
    }

    public void editarProduto(int produto, String nome, Float valor, String categoria) throws Exception {
        sistema1.sistemaEditarProduto(produto, nome, valor, categoria);
    }

    public String getProduto(String nome, int empresa, String atributo) throws Exception {
        return sistema1.sistemaGetAtributoProduto(nome, empresa, atributo);
    }

    public String listarProdutos(int empresa) throws Exception {
        return sistema1.sistemaListarProdutos(empresa);
    }

    public int criarPedido(int cliente, int empresa) throws Exception {
        return sistema1.sistemaCriarPedido(cliente, empresa);
    }
    public int getNumeroPedido(int cliente, int empresa, int indice) throws Exception {
        return sistema1.sistemaGetNumeroPedido(cliente, empresa, indice);
    }

    public String getPedidos(int numero, String atributo) throws Exception {
        return sistema1.sistemaGetAtributoPedido(numero, atributo);
    }

    public void fecharPedido(int numero) throws Exception{
        sistema1.sistemaFecharPedido(numero);
    }

    public void removerProduto(int  pedido, String produto) throws Exception{
        sistema1.sistemaRemoverProduto(pedido, produto);
    }

    public void adicionarProduto(int numero, int produto) throws Exception{
        sistema1.sistemaAdicionarProduto(numero, produto);
    }

    public void liberarPedido(int numero) throws Exception{
        sistema1.sistemaLiberarPedido(numero);
    }

    public int obterPedido(int entregador) throws Exception{
        return sistema1.sistemaObterPedidoDoEntregador(entregador);
    }

    public int criarEntrega(int pedido, int entregador, String destino) throws Exception {
        return sistema1.sistemaCriarEntrega(pedido, entregador, destino);
    }

    public int getIdEntrega(int pedido) throws Exception{
        return sistema1.sistemaGetIdEntregaPorPedido(pedido);
    }

    public void entregar(int entrega) throws Exception {
        sistema1.sistemaEntregarPedido(entrega);
    }

    public String getEntrega(int id, String atributo) throws Exception {
        return sistema1.sistemaGetAtributoEntrega(id, atributo);
    }


    public void zerarSistema(){
        sistema1.zerarSistema();
    }
    public void encerrarSistema(){
        sistema1.encerrarSistema();
    }

}
