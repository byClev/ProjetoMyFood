package codigos.services;
import codigos.entidades.Empresa;
import codigos.entidades.Produto;
import excecoes.AtributoNaoExisteException;
import excecoes.NomeInvalidoException;
import excecoes.produto.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import static codigos.services.ValidadorService.*;

public class ProdutoService {

    public static Produto serviceCriarProduto(int prodID, int empresaPossuidoraID, String nome, float valor, String categoria, Map<Integer, Produto> listaDeProdutos) throws NomeInvalidoException, CategoriaInvalidoException, ValorInvalidoException, ProdutoJaExisteException {
        if(validarNome(nome) && validarPreco(valor) &&validarCategoria(categoria) && validarNomeProdutoJaExisteNessaEmpresa(listaDeProdutos, nome, empresaPossuidoraID)){}
        Produto p = new Produto();
        p.setProdutoID(prodID);
        p.setEmpresaPossuidoraID(empresaPossuidoraID);
        p.setNome(nome);
        p.setPreco(valor);
        p.setCategoria(categoria);
        return p;
    }

    public static void serviceEditarProduto(Produto p, String nome, float valor, String categoria) throws Exception {

        if(validarNome(nome) && validarPreco(valor) &&validarCategoria(categoria) && validarExistenciaProduto(p, "cadastrado")){
            p.setNome(nome);
            p.setPreco(valor);
            p.setCategoria(categoria);
        }
    }

    public static String serviceGetAtributoProduto(Empresa e, Produto p, String atributo) throws ProdutoNaoCadastradoException, AtributoNaoExisteException, ProdutoNaoEncontradoException {
        validarExistenciaProduto(p, "encontrado");

        switch (atributo){
            case "nome":
                return p.getNome();
            case "valor":
                return String.format(Locale.US, "%.2f", p.getPreco());
            case "categoria":
                return p.getCategoria();
            case "empresa" :
                return e.getNomeEmpresa();
            default:
                throw new AtributoNaoExisteException();
        }
    }

    public static String serviceListarProdutos(List<Produto> produtos){
        if(produtos.isEmpty()){
            //verificar se precisa de uma exception
        }

        return produtos.stream()
                .map(Produto::getNome) // Mapeia cada produto para seu nome
                .collect(Collectors.joining(", ", "{[", "]}")); // Concatena com ", " entre os nomes, iniciando com "{" e terminando com "}"

    }




}
