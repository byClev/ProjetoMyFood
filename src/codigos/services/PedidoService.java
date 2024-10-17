package codigos.services;

import codigos.entidades.*;
import excecoes.AtributoInvalidoException;
import excecoes.AtributoNaoExisteException;
import excecoes.UsuarioNaoCadastradoException;
import excecoes.entrega.NaoEEntregadorException;
import excecoes.entrega.NaoEEntregadorValidoException;
import excecoes.pedido.*;
import excecoes.produto.ProdutoNaoEncontradoException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import static codigos.services.BuscarService.*;
import static codigos.services.ValidadorService.*;
import static codigos.services.ProdutoService.*;

public class PedidoService {

    public static Pedido serviceCriarPedido(Map<Integer, Pedido> listaDePedidos,Map<Integer, Usuario> listaDeUsuarios,Map<Integer, Empresa> listaDeEmpresas, int idPedido, int idCliente,int idEmpresa) throws NaoPermitido2PedidosAbertosException {

        buscarPedidoEmAbertoEmpresa(listaDePedidos, idCliente, idEmpresa);
        Pedido pedido = new Pedido(idPedido, idCliente, idEmpresa);
        listaDeEmpresas.get(idEmpresa).getPedidosDaEmpresa().add(pedido);
        return pedido;
    }

    public static int serviceGetNumeroPedido(Map<Integer, Pedido> listaDePedidos, int idCliente, int idEmpresa, int indice) throws PedidoNaoEncontradoException {
        List<Pedido> lista = buscarPedidosUsuarioEmpresa(listaDePedidos, idCliente, idEmpresa);

        validarList(lista,"pedido"); //se a lista for nula dispara uma exceção
        return lista.get(indice).getIdPedido();
    }

    public static void serviceAdicionarProduto(Map<Integer, Pedido> listaDePedidos, Map<Integer, Produto> listaDeProdutos, Map<Integer, Empresa> listaDeEmpresas,
                                               int idPedido, int idProduto) throws NaoExistePedidoAbertoException, ProdutoNaoEDessaEmpresaException, NaoPossivelAdicionarProdutoPedidoFechadoException {

        Pedido essePedido = listaDePedidos.get(idPedido);
        Produto esseProduto = listaDeProdutos.get(idProduto);



        if(essePedido != null){
            if(essePedido.getEstadoPedido().equals("aberto")){
                if(!listaDeEmpresas.get(essePedido.getIdEmpresa()).getProdutosDaEmpresa().contains(idProduto)) {
                    throw new ProdutoNaoEDessaEmpresaException();
                }
                essePedido.getProdutos().add(esseProduto);
                essePedido.incrementarValorTotal(esseProduto.getPreco());
            }else{
                throw new NaoPossivelAdicionarProdutoPedidoFechadoException();
            }
        }else{
            throw new NaoExistePedidoAbertoException();
        }
    }

    public static String serviceGetAtributoPedido(Map<Integer, Pedido> listaDePedidos, Map<Integer, Usuario> listaDeUsuarios,Map<Integer, Empresa> listaDeEmpresas,
                                                  int idPedido, String atributo) throws PedidoNaoEncontradoException, AtributoInvalidoException, AtributoNaoExisteException {

        validarAtributo(atributo);
        Pedido essePedido = listaDePedidos.get(idPedido);
        Usuario usuario = listaDeUsuarios.get(essePedido.getIdCliente());

        if(essePedido != null){
            switch(atributo){
                case "idPedido":
                    return Integer.toString(essePedido.getIdPedido());
                case "valor":
                    return String.format(Locale.US, "%.2f", essePedido.getValorTotal());
                case "cliente":
                    return usuario.getNome();
                case "empresa":
                    return listaDeEmpresas.get(essePedido.getIdEmpresa()).getNomeEmpresa();
                case "estado":
                    return essePedido.getEstadoPedido();
                case "produtos":
                    return serviceListarProdutos(essePedido.getProdutos());
                default:
                    throw new AtributoNaoExisteException();
            }
        }else{
            throw new PedidoNaoEncontradoException();
        }
    }


    public static void serviceRemoverProduto(Map<Integer, Pedido> listaDePedidos, int idPedido, String nomeProduto) throws NaoPossivelRemoverPedidoFechadoException, ProdutoNaoEncontradoException, ProdutoInvalidoException {

        validarNomeProdutoNulo(nomeProduto);
        Pedido essePedido = listaDePedidos.get(idPedido);
        int indice = buscarIndiceProdutoPedidoPorNome(essePedido, nomeProduto);

        if(essePedido == null){
            throw new ProdutoInvalidoException();
        }

        if(essePedido.getEstadoPedido().equals("aberto")){
            if( indice == -1){ //verifica se o produto está no pedido e pega o indice desse produto
                throw new ProdutoNaoEncontradoException();
            }else{
                essePedido.descrementarValorTotal(essePedido.getProdutos().get(indice).getPreco()); //subtrai o valor do produto a ser removido do total do pedido
                essePedido.getProdutos().remove(indice);
            }
        }else{
            throw new NaoPossivelRemoverPedidoFechadoException();
        }
    }

    public static void serviceFecharPedido(Map<Integer, Pedido> listaDePedidos, int idPedido) throws PedidoNaoEncontradoException {
        Pedido essePedido = listaDePedidos.get(idPedido);
        if(essePedido != null){
            if(essePedido.getEstadoPedido().equals("aberto")){
                essePedido.fecharPedido();
            }
        }else{
            throw new PedidoNaoEncontradoException();
        }
    }

    //teste 8.1
    public static void serviceLiberarPedido(Map<Integer, Empresa> listaDeEmpresas, Map<Integer, Pedido> listaDePedidos, int idPedido) throws PedidoNaoEncontradoException, PedidoJaLiberadoException, NaoPossivelLiberarPedidoNaoPreparadoException {
        Pedido essePedido = listaDePedidos.get(idPedido);

        //System.out.println("o estado desse pedido é: " + essePedido.getEstadoPedido());
        if(essePedido.getEstadoPedido().equals("pronto")){
            throw new PedidoJaLiberadoException();
        }

        if(essePedido.getEstadoPedido().equals("preparando")){
            if(essePedido != null){
                essePedido.setEstadoPedido("pronto");
            }else{
                throw new PedidoNaoEncontradoException();
            }
        }else{
            throw new NaoPossivelLiberarPedidoNaoPreparadoException();
        }

        Empresa essaEmpresa = listaDeEmpresas.get(essePedido.getIdEmpresa());
        for(Entregador entregador : essaEmpresa.getEntregadoresDaEmpresa()){
            entregador.getPedidosEntregados().add(essePedido);
        }

    }

    public static int serviceObterPedidosDoEntregador(Map <Integer, Usuario> listaDeUsuarios, Map<Integer, Empresa> listaDeEmpresas, int idEntregador)
                                                        throws UsuarioNaoCadastradoException, NaoEEntregadorException, EntregadorNaoEstaEmEmpresaException, NaoEEntregadorValidoException, NaoExistePedidoParaEntregarException {

        validarSeEEntregador(listaDeUsuarios, idEntregador, 1);
        Entregador entregador = (Entregador)listaDeUsuarios.get(idEntregador);
        if(entregador.getEmpresasTrabalhadas().isEmpty()){
            throw new EntregadorNaoEstaEmEmpresaException();
        }

        int idPedidoFarmacia = -1;
        int idPedidoOutraEmpresa = -1;

        for(Pedido pedido : entregador.getPedidosEntregados()){
            if(pedido.getEstadoPedido().equals("pronto")){
                if(listaDeEmpresas.get(pedido.getIdEmpresa()).eDoTipo().equals("farmacia")){
                    if(idPedidoFarmacia == -1){
                        idPedidoFarmacia = pedido.getIdPedido();
                    }
                }else{
                    if(idPedidoOutraEmpresa == -1){
                        idPedidoOutraEmpresa = pedido.getIdPedido();
                    }
                }
            }
        }

        if(idPedidoFarmacia != -1){
            return idPedidoFarmacia;
        }else if(idPedidoFarmacia == -1 && idPedidoOutraEmpresa == -1){
            throw new NaoExistePedidoParaEntregarException();
        } else{
            return idPedidoOutraEmpresa;
        }
    }

}
