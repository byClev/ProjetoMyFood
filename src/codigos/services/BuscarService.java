package codigos.services;
import codigos.entidades.*;
import excecoes.EmailInvalidoException;
import excecoes.pedido.NaoPermitido2PedidosAbertosException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuscarService {

    public static int buscarIdUsuarioPorEmail(Map<Integer, Usuario> listaDeUsuarios, String email) throws EmailInvalidoException {
        for (Map.Entry<Integer, Usuario> entry : listaDeUsuarios.entrySet()) {
            // Verificar se o email do usuário atual corresponde ao email procurado
            if (entry.getValue().getEmail().equals(email)) {
                return entry.getKey(); // Retorna a chave (ID) correspondente
            }
        }
        return 0; // Retorna 0 se não encontrar o email
    }

    public static boolean buscarFuncionarioNaEmpresa(Map<Integer, Empresa> listaDeEmpresas, int idFuncionario, int idEmpresa){
        Empresa empresa = listaDeEmpresas.get(idEmpresa);

        if (empresa == null) {
            return false;
        }

        for(Entregador funcionario : empresa.getEntregadoresDaEmpresa()){
            if (funcionario.getID_Usuario() == idFuncionario) {
                return false;
            }
        }

        return true;
    }

    public static int buscaPlacaVeiculo(Map<Integer, Usuario> listaDeUsuarios, String placa) {

        //metodo feito pensando em possiveis reutilizações futuras. Pesquisa se uma placa já existe, caso positivo retorna o id do usuario que possui essa placa
        //caso contrário retorna uma chave nula (-1)

        for (Map.Entry<Integer, Usuario> entry : listaDeUsuarios.entrySet()) {
            Usuario usuario = entry.getValue();
            if(usuario.eDoTipo().equals("entregador")){
                if(((Entregador)usuario).getPlacaVeiculo().equals(placa)){
                    return entry.getKey();
                }
            }
        }

        return -1;
    }

    // Método para buscar um produto pelo ID da empresa e nome do produto
    public static Produto buscarProdutoPorEmpresaENome(Map<Integer, Produto> listaDeProdutos, int empresaID, String nomeProduto) {
        // Itera sobre todos os produtos no mapa
        for (Produto produto : listaDeProdutos.values()) {
            // Verifica se o produto pertence à empresa e se o nome corresponde
            if (produto.getEmpresaPossuidoraID() == empresaID && produto.getNome().equalsIgnoreCase(nomeProduto)) {
                return produto; // Retorna o produto encontrado
            }
        }
        return null; // Retorna null caso nenhum produto seja encontrado
    }

    // Método para criar uma sublista de produtos filtrando pelo ID
    public static List<Produto> buscarProdutosPorID(Map<Integer, Produto> listaDeProdutos, List<Integer> idsDosProdutos) throws Exception{
        List<Produto> produtosFiltrados = new ArrayList<>();

        // Itera sobre a lista de IDs e busca os produtos no mapa
        for (Integer id : idsDosProdutos) {
            Produto produto = listaDeProdutos.get(id);
            if (produto != null) { // Verifica se o produto existe no mapa
                produtosFiltrados.add(produto); // Adiciona o produto à sublista
            }
        }

        return produtosFiltrados; // Retorna a sublista de produtos filtrados
    }

    public static List<Pedido> buscarPedidosUsuarioEmpresa(Map<Integer, Pedido> listaDePedidos, int idCliente, int idEmpresa){
        List<Pedido> pedidosFiltrados = new ArrayList<>();

        for(Pedido pedido : listaDePedidos.values()) {
            if(pedido.getIdCliente() == idCliente && pedido.getIdEmpresa() == idEmpresa){
                pedidosFiltrados.add(pedido);
            }
        }
        if(pedidosFiltrados.isEmpty()){
            return null;
        }else{
            return pedidosFiltrados;
        }
    }

    public static boolean buscarPedidoEmAbertoEmpresa(Map<Integer, Pedido> listaDePedidos, int idCliente, int idEmpresa) throws NaoPermitido2PedidosAbertosException {
        for(Pedido pedido : listaDePedidos.values()) {
            if(pedido.getIdCliente() == idCliente && pedido.getIdEmpresa() == idEmpresa){
                if(pedido.getEstadoPedido().equals("aberto")){
                    throw new NaoPermitido2PedidosAbertosException();
                }
            }
        }
        return true;
    }

    public static int buscarIndiceProdutoPedidoPorNome(Pedido essePedido, String nomeProduto) {
        for (int i = 0; i < essePedido.getProdutos().size(); i++) {
            Produto esseProduto = essePedido.getProdutos().get(i);
            if (esseProduto.getNome().equals(nomeProduto)) {
                return i; // Retorna o índice do primeiro produto encontrado
            }
        }
        return -1; // Retorna -1 se não encontrar o produto com o nome dado
    }
}
