package codigos.services;

import codigos.entidades.*;
import excecoes.*;
import excecoes.empresa.EnderecoEmpresaInvalidoException;
import excecoes.entrega.*;
import excecoes.pedido.PedidoNaoEncontradoException;
import excecoes.pedido.PedidoNaoProntoException;

import java.util.Map;

import static codigos.services.ProdutoService.serviceListarProdutos;
import static codigos.services.ValidadorService.*;
import static codigos.services.UtilitariosService.*;


public class EntregaService {

    public static Entrega serviceCriarEntrega(Map<Integer, Usuario> listaDeUsuarios, Map<Integer, Empresa> listaDeEmpresas, Map<Integer, Pedido> listaDePedidos,
                                              int idEntrega, String nomeCliente, String nomeEmpresa, int idPedido, int idEntregador, String destino) throws EmpresaNaoCadastradaException,
                                                UsuarioNaoCadastradoException, PedidoNaoEncontradoException, EnderecoInvalidoException, EnderecoEmpresaInvalidoException,
                                                DestinoInvalidoException, NaoEEntregadorException, PedidoNaoProntoException, NaoEEntregadorValidoException, EntregadorAindaEmEntregaException {

        //se o destino for nulo, setta automaticamente para o endereço do cliente que fez o pedido
        if(destino == null){
            destino = listaDeUsuarios.get(listaDePedidos.get(idPedido).getIdCliente()).getEndereco();
        }

        //Valida os atributos passados pelo o usuario (os demais já são validados na instanciação de outros objetos)
        validarPedidoPronto(listaDePedidos.get(idPedido));
        validarID(listaDeUsuarios, idEntregador, "usuario");
        validarID(listaDePedidos, idPedido, "pedido");
        validarEndereco(destino, "entrega");
        validarSeEEntregador(listaDeUsuarios, idEntregador, 2);
        validarSeEntregadorEstaLivre(listaDeUsuarios.get(idEntregador));

        Entrega entrega = new Entrega(idEntrega, nomeCliente, nomeEmpresa, idPedido, idEntregador, destino, utilitarioCriarListaStringDeProdutos(listaDePedidos.get(idPedido)));

        listaDePedidos.get(idPedido).setEstadoPedido("entregando");

        Entregador entregador = (Entregador) listaDeUsuarios.get(idEntregador);
        entregador.setLivreParaEntregar(false);
        entregador.getPedidosEntregados().add(listaDePedidos.get(idPedido));

        return entrega;
    }

    public static int serviceGetIdEntregaPorIdPedido(Map<Integer, Entrega> listaDeEntregas, int idPedido) throws NaoExisteEntregaComEsseIDException {
        for(Entrega entry : listaDeEntregas.values()) {
            if(entry.getIdPedido() == idPedido){
                return entry.getIdEntrega();
            }
        }

        throw new NaoExisteEntregaComEsseIDException();
    }

    public static void serviceEntregar(Map<Integer, Usuario> listaDeUsuarios, Map<Integer, Pedido> listaDePedidos, Map<Integer, Entrega> listaDeEntregas, int idEntrega)
                                        throws NaoExisteNadaParaEntregarComIdException {

        validarSeEntregaExiste(listaDeEntregas, idEntrega);
        listaDePedidos.get(listaDeEntregas.get(idEntrega).getIdPedido()).setEstadoPedido("entregue");
        Entregador entregador = (Entregador)listaDeUsuarios.get(listaDeEntregas.get(idEntrega).getIdEntregador());
        entregador.setLivreParaEntregar(true);
    }

    public static String serviceGetAtributoEntrega(Map<Integer, Usuario> listaDeUsuarios ,Map<Integer, Empresa> listaDeEmpresas, Map<Integer, Pedido> listaDePedidos,
                                                    Map<Integer, Entrega> listaDeEntregas, int idEntrega, String atributo) throws AtributoInvalidoException, EntregaInvalidaException, AtributoNaoExisteException {

        validarAtributo(atributo);
        Entrega essaEntrega = listaDeEntregas.get(idEntrega);
        if(essaEntrega == null){
            throw new EntregaInvalidaException();
        }

        switch (atributo){
            case "cliente":
                return listaDeEntregas.get(idEntrega).getNomeCliente();
            case "empresa":
                return listaDeEntregas.get(idEntrega).getNomeEmpresa();
            case "entregador":
                return listaDeUsuarios.get(listaDeEntregas.get(idEntrega).getIdEntregador()).getNome();
            case "pedido":
                return String.valueOf(listaDeEntregas.get(idEntrega).getIdPedido());
            case "produtos":
                return serviceListarProdutos(listaDePedidos.get(listaDeEntregas.get(idEntrega).getIdPedido()).getProdutos());
            case "destino":
                return listaDeEntregas.get(idEntrega).getDestino();
            default:
                throw new AtributoNaoExisteException();
        }

    }
}
