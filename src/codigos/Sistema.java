package codigos;
import codigos.entidades.*;
import excecoes.*;
import excecoes.empresa.*;
import excecoes.entrega.*;
import excecoes.pedido.*;
import excecoes.produto.*;
import excecoes.usuario.*;

import java.util.HashMap;
import java.util.Map;
import java.io.File;

import static codigos.services.ValidadorService.*;
import static codigos.services.ProdutoService.*;
import static codigos.services.BuscarService.*;
import static codigos.services.PedidoService.*;
import static codigos.services.XMLService.*;
import static codigos.services.UsuarioService.*;
import static codigos.services.EmpresaService.*;
import static codigos.services.EntregaService.*;

public class Sistema {
    private Map <Integer, Usuario> listaDeUsuarios = new HashMap<>();
    private Map <Integer, Usuario> usuariosLogados = new HashMap<>();
    private Map <Integer, Empresa> listaDeEmpresas = new HashMap<>();
    private Map <Integer, Produto> listaDeProdutos = new HashMap<>();
    private Map <Integer, Pedido> listaDePedidos = new HashMap<>();
    private Map <Integer, Entrega> listaDeEntregas = new HashMap<>();
    private int contadorID_Usuario;
    private int contadorID_Empresa;
    private int contadorID_Produto;
    private int contadorID_Pedido;
    private int contadorID_Entrega;

    //contructor inicia tod o sistema desserializando os dados para suas respectivas lists/maps e gera os IDs iniciais
    public Sistema(){
        listaDeUsuarios = serviceDeserializarDados("ListaDeUsuarios.xml");
        listaDeEmpresas = serviceDeserializarDados("ListaDeEmpresas.xml");
        listaDeProdutos = serviceDeserializarDados("ListaDeProdutos.xml");
        listaDePedidos = serviceDeserializarDados("ListaDePedidos.xml");
        listaDeEntregas = serviceDeserializarDados("ListaDeEntregas.xml");
        gerarIDsIniciais();
    }

    public void gerarIDsIniciais(){
        if(listaDeUsuarios.isEmpty()){
            contadorID_Usuario = 1;
        } else{
            contadorID_Usuario = listaDeUsuarios.size() + 1;
        }

        if(listaDeEmpresas.isEmpty()){
            contadorID_Empresa = 1;
        } else{
            contadorID_Empresa = listaDeEmpresas.size() + 1;
        }

        if(listaDeProdutos.isEmpty()){
            contadorID_Produto = 1;
        } else{
            contadorID_Produto = listaDeProdutos.size() + 1;
        }

        if(listaDePedidos.isEmpty()){
            contadorID_Pedido = 1;
        } else{
            contadorID_Pedido = listaDePedidos.size() + 1;
        }

        if(listaDeEntregas.isEmpty()){
            contadorID_Pedido = 1;
        } else{
            contadorID_Pedido = listaDeEntregas.size() + 1;
        }
    }

    public int getAtualContadorID_Usuario() {
        contadorID_Usuario++;
        return contadorID_Usuario - 1;
    }
    public int getAtualContadorID_Empresa() {
        contadorID_Empresa++;
        return contadorID_Empresa - 1;
    }
    public int getAtualContadorID_Produto() {
        contadorID_Produto++;
        return contadorID_Produto - 1;
    }
    public int getAtualContadorID_Pedido() {
        contadorID_Pedido++;
        return contadorID_Pedido - 1;
    }
    public int getAtualContadorID_Entrega() {
        contadorID_Entrega++;
        return contadorID_Entrega - 1;
    }


    //#############################################################################################################################################################
    //-------------------------------------------METODOS DO USUARIO -------------------------------------------------------
    //#############################################################################################################################################################

    public void sistemaCriarUsuario(String nome, String email, String senha, String endereco) throws EmailInvalidoException, NomeInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, JaExisteContaComEsseEmailException, EnderecoEmpresaInvalidoException, DestinoInvalidoException {
        int idDesseUsuario = getAtualContadorID_Usuario();
        listaDeUsuarios.put(idDesseUsuario, serviceCriarCliente(listaDeUsuarios,idDesseUsuario, nome, email, senha, endereco));
    }

    public void sistemaCriarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, CPFinvalidoException, JaExisteContaComEsseEmailException, EnderecoEmpresaInvalidoException, DestinoInvalidoException {
        int idDesseUsuario = getAtualContadorID_Usuario();
        listaDeUsuarios.put(idDesseUsuario, serviceCriarDono(listaDeUsuarios,idDesseUsuario, nome, email, senha, endereco, cpf));
    }

    public void sistemaCriarUsuario(String nome, String email, String senha, String endereco, String veiculo, String placa) throws EmailInvalidoException, NomeInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, JaExisteContaComEsseEmailException, EnderecoEmpresaInvalidoException, PlacaInvalidaException, PlacaJaExisteException, VeiculoInvalidoException, DestinoInvalidoException {
        int idDesseUsuario = getAtualContadorID_Usuario();
        listaDeUsuarios.put(idDesseUsuario, serviceCriarEntregador(listaDeUsuarios,idDesseUsuario, nome, email, senha, endereco, veiculo, placa));
    }

    public String sistemaGetAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException, AtributoInvalidoException, EmpresaNaoCadastradaException, PedidoNaoEncontradoException {
        return serviceGetAtributoUsuario(listaDeUsuarios, id, atributo);
    }

    public int sistemaLogin(String email, String senha) throws EmailInvalidoException, LoginOuSenhaInvalidosException {
        int idLogin = serviceLogin(listaDeUsuarios, email, senha);
        usuariosLogados.put(idLogin, listaDeUsuarios.get(idLogin));
        return idLogin;
    }

    public void sistemaCadastrarEntregador(int idEntregador, int idEmpresa) throws EmpresaNaoCadastradaException, UsuarioNaoCadastradoException, NaoEEntregadorException, FuncionarioJaTrabalhaNaEmpresaException, PedidoNaoEncontradoException {
        serviceCadastrarEntregador(listaDeUsuarios, listaDeEmpresas, idEntregador, idEmpresa);
    }

    //#############################################################################################################################################################
    //-------------------------------------METODOS DA EMPRESA -----------------------------------------------------------------------------------
    //#############################################################################################################################################################

    public int sistemaCriarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, String tipoCozinha) throws NomeInvalidoException, NomeEmpresaJaExisteException, Proibido2EmpresasNomeEnderecoException, EnderecoInvalidoException, EmpresaNaoCadastradaException, UsuarioNaoCadastradoException, UsuarioNaoPodeCriarEmpresaException, TipoEmpresaInvalidoException, EnderecoEmpresaInvalidoException, PedidoNaoEncontradoException, DestinoInvalidoException {

        int idDessaEmpresa = getAtualContadorID_Empresa();
        Empresa essaEmpresa = serviceCriarEmpresa(listaDeUsuarios, listaDeEmpresas, tipoEmpresa, idDono, idDessaEmpresa, nome, endereco, tipoCozinha);
        listaDeEmpresas.put(idDessaEmpresa, essaEmpresa);
        return idDessaEmpresa;
    }

    //esse metodo funciona para dois métodos diferentes na facade, recuperando as empresas de donos de empresa e de entregadores dependendo da var tipoUsuario passada
    String sistemaGetEmpresasDoUsuario(int idDono, String tipoUsuario) throws UsuarioNaoPodeCriarEmpresaException, UsuarioNaoCadastradoException, UsuarioNaoEEntregadoException {
        return serviceGetEmpresasDoUsuario(listaDeUsuarios, listaDeEmpresas, idDono, tipoUsuario);
    }

    int sistemaGetIdEmpresa(int idDono, String nome, int indice) throws TipoDeUsuarioInvalidoException, NaoExisteEmpresaNomeException, IndiceMaiorQueEsperadoException, IndiceInvalidoException, NomeInvalidoException {
        return serviceGetIdEmpresa(listaDeUsuarios, listaDeEmpresas, idDono, nome, indice);
    }

    String sistemaGetAtributoEmpresa(int idEmpresa, String atributo) throws AtributoInvalidoException, EmpresaNaoCadastradaException, UsuarioNaoCadastradoException, PedidoNaoEncontradoException {
        return serviceGetAtributoEmpresa(listaDeUsuarios, listaDeEmpresas, idEmpresa, atributo);
    }

    //metodos do teste 5.1 (mercado)
    public int sistemaCriarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, String horaAbertura, String horaFechamento, String tipoMercado) throws EmpresaNaoCadastradaException, NomeInvalidoException, EnderecoInvalidoException, UsuarioNaoPodeCriarEmpresaException, NomeEmpresaJaExisteException, UsuarioNaoCadastradoException, Proibido2EmpresasNomeEnderecoException, FormatoHoraInvalidoException, HorarioInvalidoException, TipoEmpresaInvalidoException, EnderecoEmpresaInvalidoException, TipoMercadoInvalidoException, PedidoNaoEncontradoException, DestinoInvalidoException {
        int idDessaEmpresa = getAtualContadorID_Empresa();
        Empresa essaEmpresa = serviceCriarEmpresa(listaDeUsuarios, listaDeEmpresas, tipoEmpresa, idDono, idDessaEmpresa, nome, endereco, horaAbertura, horaFechamento, tipoMercado);
        listaDeEmpresas.put(idDessaEmpresa, essaEmpresa);
        return idDessaEmpresa;
    }

    public void sistemaAlterarFuncionamento(int idEmpresa, String abre, String fecha) throws HorarioInvalidoException, NaoEMercadovalidoException, FormatoHoraInvalidoException {
        serviceAlterarFuncionamento(listaDeEmpresas, idEmpresa, abre, fecha);
    }

    //metodos do teste 6.1
    public int sistemaCriarEmpresa(String tipoEmpresa, int idDono, String nome, String endereco, boolean aberto24h, int numeroFuncionarios) throws EmpresaNaoCadastradaException, NomeInvalidoException, EnderecoInvalidoException, UsuarioNaoPodeCriarEmpresaException, NomeEmpresaJaExisteException, UsuarioNaoCadastradoException, Proibido2EmpresasNomeEnderecoException, FormatoHoraInvalidoException, HorarioInvalidoException, TipoEmpresaInvalidoException, EnderecoEmpresaInvalidoException, TipoMercadoInvalidoException, PedidoNaoEncontradoException, DestinoInvalidoException {
        int idDessaEmpresa = getAtualContadorID_Empresa();
        Empresa essaEmpresa = serviceCriarEmpresa(listaDeUsuarios, listaDeEmpresas, tipoEmpresa, idDono, idDessaEmpresa, nome, endereco, aberto24h, numeroFuncionarios);
        listaDeEmpresas.put(idDessaEmpresa, essaEmpresa);
        return idDessaEmpresa;
    }

    public String sistemaGetEntregadoresDaEmpresa(int idEmpresa) throws EmpresaNaoCadastradaException {
        return serviceGetEntregadoresDaEmpresa(listaDeUsuarios, listaDeEmpresas, idEmpresa);
    }


    //#############################################################################################################################################################
    //---------------------------------------------------------METODOS DE PRODUTOS------------------------
    //#############################################################################################################################################################

    public int sistemaCriarProduto(int empresa, String nome, float valor, String categoria) throws NomeInvalidoException, CategoriaInvalidoException, ProdutoJaExisteException, ValorInvalidoException {
        int idDesseProduto = getAtualContadorID_Produto();
        listaDeProdutos.put(idDesseProduto,serviceCriarProduto(idDesseProduto, empresa, nome, valor, categoria, listaDeProdutos));
        listaDeEmpresas.get(empresa).adicionarProdutoNaEmpresa(idDesseProduto);
        return idDesseProduto;
    }

    public void sistemaEditarProduto(int idProduto, String nomeProduto, Float valorProduto, String categoriaProduto) throws Exception {
        serviceEditarProduto(listaDeProdutos.get(idProduto), nomeProduto, valorProduto, categoriaProduto);
    }

    public String sistemaGetAtributoProduto(String nome, int empresa, String atributo) throws Exception {
        return serviceGetAtributoProduto(listaDeEmpresas.get(empresa), buscarProdutoPorEmpresaENome(listaDeProdutos, empresa, nome), atributo);
    }

    public String sistemaListarProdutos(int empresaID) throws Exception {
        validarExistenciaEmpresa(listaDeEmpresas.get(empresaID));
        return serviceListarProdutos(buscarProdutosPorID(listaDeProdutos, listaDeEmpresas.get(empresaID).getProdutosDaEmpresa()));
    }


    //#############################################################################################################################################################
    //-------------------------------------------------AREA DE PEDIDOS-------------------------
    //#############################################################################################################################################################

    public int sistemaCriarPedido(int idCliente, int idEmpresa) throws DonoNaoPodePedirException, NaoPermitido2PedidosAbertosException {
        if(!listaDeUsuarios.get(idCliente).eDoTipo().equals("dono")){
            int essePedido = getAtualContadorID_Pedido();
            listaDePedidos.put(essePedido, serviceCriarPedido(listaDePedidos, listaDeUsuarios,listaDeEmpresas, essePedido, idCliente, idEmpresa));
            return essePedido;
        }else{
            throw new DonoNaoPodePedirException();
        }

    }

    public int sistemaGetNumeroPedido(int idCliente, int idEmpresa, int indice) throws PedidoNaoEncontradoException {
        return serviceGetNumeroPedido(listaDePedidos, idCliente, idEmpresa, indice);
    }

    public void sistemaAdicionarProduto(int idPedido, int idProduto) throws NaoExistePedidoAbertoException, ProdutoNaoEDessaEmpresaException, NaoPossivelAdicionarProdutoPedidoFechadoException {
        serviceAdicionarProduto(listaDePedidos, listaDeProdutos, listaDeEmpresas, idPedido, idProduto);
    }

    public String sistemaGetAtributoPedido(int idPedido, String atributo) throws AtributoInvalidoException, PedidoNaoEncontradoException, AtributoNaoExisteException {
        return serviceGetAtributoPedido(listaDePedidos, listaDeUsuarios,listaDeEmpresas, idPedido, atributo);
    }

    public void sistemaFecharPedido(int idPedido) throws PedidoNaoEncontradoException {
        serviceFecharPedido(listaDePedidos, idPedido);
    }

    public void sistemaRemoverProduto(int idPedido, String nomeProduto) throws  NaoPossivelRemoverPedidoFechadoException, ProdutoNaoEncontradoException, ProdutoInvalidoException {
        serviceRemoverProduto(listaDePedidos,idPedido, nomeProduto);
    }

    public void sistemaLiberarPedido(int idPedido) throws PedidoNaoEncontradoException, PedidoJaLiberadoException, NaoPossivelLiberarPedidoNaoPreparadoException {
        serviceLiberarPedido(listaDeEmpresas ,listaDePedidos, idPedido);
    }

    public int sistemaObterPedidoDoEntregador(int idEntregador) throws UsuarioNaoCadastradoException, NaoEEntregadorException, EntregadorNaoEstaEmEmpresaException, NaoEEntregadorValidoException, NaoExistePedidoParaEntregarException {
        return serviceObterPedidosDoEntregador(listaDeUsuarios, listaDeEmpresas, idEntregador);
    }



    //#############################################################################################################################################################
    //-------------------------------------------------AREA DE ENTREGAS-------------------------
    //#############################################################################################################################################################

    public int sistemaCriarEntrega(int idPedido, int idEntregador, String destino) throws EmpresaNaoCadastradaException, UsuarioNaoCadastradoException, PedidoNaoEncontradoException, EnderecoInvalidoException, EnderecoEmpresaInvalidoException, DestinoInvalidoException, NaoEEntregadorException, PedidoNaoProntoException, NaoEEntregadorValidoException, EntregadorAindaEmEntregaException {
        int essaEntrega = getAtualContadorID_Entrega();
        Entrega entrega = serviceCriarEntrega(listaDeUsuarios, listaDeEmpresas, listaDePedidos, essaEntrega, listaDeUsuarios.get(listaDePedidos.get(idPedido).getIdCliente()).getNome(),
                                                listaDeEmpresas.get(listaDePedidos.get(idPedido).getIdEmpresa()).getNomeEmpresa(), idPedido, idEntregador, destino);
        listaDeEntregas.put(essaEntrega, entrega);
        return essaEntrega;
    }

    public int sistemaGetIdEntregaPorPedido(int idPedido) throws NaoExisteEntregaComEsseIDException {
        return serviceGetIdEntregaPorIdPedido(listaDeEntregas, idPedido);
    }

    public void sistemaEntregarPedido(int idEntrega) throws NaoExisteNadaParaEntregarComIdException {
        serviceEntregar(listaDeUsuarios, listaDePedidos, listaDeEntregas, idEntrega);
    }

    public String sistemaGetAtributoEntrega(int idEntrega, String atributo) throws AtributoInvalidoException, EntregaInvalidaException, AtributoNaoExisteException {
        return serviceGetAtributoEntrega(listaDeUsuarios, listaDeEmpresas, listaDePedidos, listaDeEntregas, idEntrega, atributo);
    }

    //####################################################################################################################################################
    //----------------------------------SERIALIZAÇÃO DE DADOS
    //####################################################################################################################################################

    public void serializarDadosUsuario(){
        serviceSerializarDados(listaDeUsuarios,"ListaDeUsuarios.xml");
    }

    public void serializarDadosEmpresa(){
        serviceSerializarDados(listaDeEmpresas, "ListaDeEmpresas.xml");
    }

    public void serializarDadosProduto() {
        serviceSerializarDados(listaDeProdutos, "ListaDeProdutos.xml");
    }

    public void serializarDadosPedido(){
        serviceSerializarDados(listaDePedidos, "ListaDePedidos.xml");
    }

    public void serializarDadosLogados(){
        serviceSerializarDados(usuariosLogados, "UsuariosLogados.xml");
    }

    public void serializarDadosEntrega(){
        serviceSerializarDados(listaDeEntregas, "ListaDeEntregas.xml");
    }

    //####################################################################################################################################################
    //----------------------------------UTILITARIOS DO SISTEMA
    //####################################################################################################################################################

    public void zerarSistema(){

        File arquivo = new File("ListaDeUsuarios.xml");
        arquivo.delete();
        File arquivo2 = new File("ListaDeEmpresas.xml");
        arquivo2.delete();
        File arquivo3 = new File("ListaDeProdutos.xml");
        arquivo3.delete();
        File arquivo4 = new File("ListaDePedidos.xml");
        arquivo4.delete();
        File arquivo5 = new File("UsuariosLogados.xml");
        arquivo5.delete();
        File arquivo6 = new File("ListaDeEntregas.xml");
        arquivo5.delete();
        listaDeUsuarios.clear();
        listaDeEmpresas.clear();
        usuariosLogados.clear();
        listaDeProdutos.clear();
        listaDePedidos.clear();
        listaDeEntregas.clear();
    }
    public void encerrarSistema(){
        //serializa todos os dados para criar a persistencia
        serializarDadosUsuario();
        serializarDadosEmpresa();
        serializarDadosProduto();
        serializarDadosPedido();
        serializarDadosLogados();
        serializarDadosEntrega();
    }
}
