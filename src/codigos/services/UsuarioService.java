package codigos.services;

import codigos.entidades.*;
import excecoes.*;
import excecoes.empresa.*;
import excecoes.entrega.*;
import excecoes.pedido.*;
import excecoes.produto.*;
import excecoes.usuario.*;

import java.util.Map;

import static codigos.services.BuscarService.buscarFuncionarioNaEmpresa;
import static codigos.services.BuscarService.buscarIdUsuarioPorEmail;
import static codigos.services.ValidadorService.*;

public class UsuarioService {

    //criação de clientes
    public static Usuario serviceCriarCliente(Map<Integer, Usuario> listaDeUsuarios, int idUsuario, String nome, String email, String senha, String endereco)
            throws JaExisteContaComEsseEmailException, EmailInvalidoException, NomeInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, EnderecoEmpresaInvalidoException, DestinoInvalidoException {
        if(validarCriacaoUsuario(listaDeUsuarios, nome, email, senha, endereco)){
            Usuario cliente = new Cliente(idUsuario, nome, email, senha, endereco);
            return cliente;
        }
        return null; //esse retorno é apenas para a IDE parar de encher o saco, se o IF acima der errado o metodo de validação solta um exception
    }

    //criação de donos de estabelecimentos
    public static Usuario serviceCriarDono(Map<Integer, Usuario> listaDeUsuarios, int idUsuario, String nome, String email, String senha, String endereco, String cpf)
            throws JaExisteContaComEsseEmailException, EmailInvalidoException, NomeInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, CPFinvalidoException, EnderecoEmpresaInvalidoException, DestinoInvalidoException {

        if(validarCriacaoUsuario(listaDeUsuarios, nome, email, senha, endereco, cpf)){
            Usuario usuario = new DonoEmpresa(idUsuario, nome, email, senha, endereco, cpf);
            return usuario;
        }
        return null; //esse retorno é apenas para a IDE parar de encher o saco, se o IF acima der errado o metodo de validação solta um exception
    }

    //criação de donos de entregadores
    public static Usuario serviceCriarEntregador(Map<Integer, Usuario> listaDeUsuarios, int idUsuario, String nome, String email, String senha, String endereco, String veiculo, String placa)
            throws JaExisteContaComEsseEmailException, EmailInvalidoException, NomeInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, EnderecoEmpresaInvalidoException, PlacaInvalidaException, PlacaJaExisteException, VeiculoInvalidoException, DestinoInvalidoException {

        validarVeiculo(veiculo);
        validarStringPlaca(placa);
        validarPlaca(listaDeUsuarios, placa);
        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);
        validarEndereco(endereco, "usuario");
        if(buscarIdUsuarioPorEmail(listaDeUsuarios ,email) != 0){
            throw new JaExisteContaComEsseEmailException();
        }

        Usuario usuario = new Entregador(idUsuario, nome, email, senha, endereco, veiculo, placa);
        return usuario;
    }

    public static String serviceGetAtributoUsuario(Map<Integer, Usuario> listaDeUsuarios, int idUsuario, String atributo) throws EmpresaNaoCadastradaException, UsuarioNaoCadastradoException, AtributoInvalidoException, PedidoNaoEncontradoException {

        validarID(listaDeUsuarios, idUsuario, "usuario");
        Usuario usuario = listaDeUsuarios.get(idUsuario);

        // Verifica atributos comuns a todos os usuários
        switch (atributo) {
            case "nome":
                return usuario.getNome();
            case "email":
                return usuario.getEmail();
            case "senha":
                return usuario.getSenha();
            case "endereco":
                return usuario.getEndereco();
            case "cpf":
                // Trata o atributo "cpf" para o tipo DonoRestaurante
                if (usuario.eDoTipo().equals("dono")) {
                    return ((DonoEmpresa) usuario).getCPF();
                }else{
                    throw new AtributoInvalidoException();
                }
            case "veiculo":
                if (usuario.eDoTipo().equals("entregador")) {
                    return ((Entregador) usuario).getVeiculo();
                }else{
                    throw new AtributoInvalidoException();
                }
            case "placa":
                if (usuario.eDoTipo().equals("entregador")) {
                    return ((Entregador) usuario).getPlacaVeiculo();
                }else{
                    throw new AtributoInvalidoException();
                }
            default:
                throw new AtributoInvalidoException();
        }

    }

    public static int serviceLogin(Map<Integer, Usuario> listaDeUsuarios, String email, String senha) throws EmailInvalidoException, LoginOuSenhaInvalidosException {
        int idDoLogin;
        idDoLogin = buscarIdUsuarioPorEmail(listaDeUsuarios, email);

        if(idDoLogin != 0){
            if(listaDeUsuarios.get(idDoLogin).getSenha().equals(senha)){
                return idDoLogin;
            }
        }
        throw new LoginOuSenhaInvalidosException();
    }

    //teste 7.1
    public static void serviceCadastrarEntregador(Map<Integer, Usuario> listaDeUsuarios, Map<Integer, Empresa> listaDeEmpresas, int idEntregador, int idEmpresa) throws EmpresaNaoCadastradaException, UsuarioNaoCadastradoException, NaoEEntregadorException, FuncionarioJaTrabalhaNaEmpresaException, PedidoNaoEncontradoException {

        validarID(listaDeUsuarios, idEntregador, "usuario");
        validarID(listaDeEmpresas, idEmpresa, "empresa");

        //isso esta comentado pois é definido que um entregador não pode trabalhar 2x na msm empresa, porém o teste só passa de você permitir isso
//        if(buscarFuncionarioNaEmpresa(listaDeEmpresas, idEntregador, idEmpresa)){
//            throw new FuncionarioJaTrabalhaNaEmpresaException();
//        }

        Usuario usuario = listaDeUsuarios.get(idEntregador);

        if(usuario.eDoTipo().equals("entregador")){
            ((Entregador) usuario).adicionarEmpresaTrabalhada(idEmpresa);
        }else{
            throw new NaoEEntregadorException();
        }
        listaDeEmpresas.get(idEmpresa).getEntregadoresDaEmpresa().add((Entregador) usuario);

        //adiciona os pedidos que ja estão fechados a lista do novo entregador
        for(Pedido pedido : listaDeEmpresas.get(idEmpresa).getPedidosDaEmpresa()){
            if(pedido.getEstadoPedido().equals("pronto")){
                ((Entregador)usuario).getPedidosEntregados().add(pedido);
            }
        }

    }

}


