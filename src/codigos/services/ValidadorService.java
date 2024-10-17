package codigos.services;
import codigos.entidades.*;
import excecoes.*;
import excecoes.empresa.*;
import excecoes.entrega.*;
import excecoes.pedido.PedidoNaoEncontradoException;
import excecoes.pedido.PedidoNaoProntoException;
import excecoes.pedido.ProdutoInvalidoException;
import excecoes.produto.*;
import excecoes.usuario.PlacaInvalidaException;
import excecoes.usuario.PlacaJaExisteException;
import excecoes.usuario.VeiculoInvalidoException;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static codigos.services.BuscarService.*;
import static codigos.services.UtilitariosService.*;

public class ValidadorService {

    //alguns metodos aparentam estar repetidos mas disparam exceptions diferentes

    //METODOS GERAIS
    public static boolean validarNome (String nome) throws NomeInvalidoException {
        if(nome != null){
            if(nome.length() >= 3){

                return true;
            }
        }
        throw new NomeInvalidoException();

    }

    public static boolean validarEndereco(String endereco, String tipoEndereco) throws EnderecoInvalidoException, EnderecoEmpresaInvalidoException, DestinoInvalidoException {
        //Expressão pra validar formato do endereço
        String regex = "^([A-Z][a-zA-Z]*\\s)+N \\d+$";

        if(endereco != null){
            if(endereco.length() >= 8 && endereco.matches(regex)) {
                return true;
            }
        }
        switch(tipoEndereco){
            case "usuario":
                throw new EnderecoInvalidoException();
            case "empresa":
                throw new EnderecoEmpresaInvalidoException();
            case "entrega":
                throw new DestinoInvalidoException();
            default:
                return false;
        }

    }

    public static void validarAtributo(String atributo) throws AtributoInvalidoException {
        if(atributo == null){
            throw new AtributoInvalidoException();
        } else if (atributo.length() < 3) {
            throw new AtributoInvalidoException();
        }
    }

    public static <T> boolean validarID(Map<Integer, T> lista, int ID, String tipoDeID) throws UsuarioNaoCadastradoException, EmpresaNaoCadastradaException, PedidoNaoEncontradoException {
        switch(tipoDeID){
            case "usuario":
                if(lista.get(ID) != null){
                    return true;
                }else{
                    throw new UsuarioNaoCadastradoException();
                }
            case "empresa":
                if(lista.get(ID) != null){
                    return true;
                }else{
                    throw new EmpresaNaoCadastradaException();
                }
            case "pedido":
                if(lista.get(ID) != null){
                    return true;
                }else{
                    throw new PedidoNaoEncontradoException();
                }
            default:
                System.out.println("Escreva o tipo correto de ID");

        }
        return false;
    }

    public static <T> boolean validarList(List<T> lista, String tipoExcecao) throws PedidoNaoEncontradoException {
        if(lista == null){
            switch (tipoExcecao){
                case "pedido":
                    throw new PedidoNaoEncontradoException();
                default:
                    throw new RuntimeException("Problema com validarList");
            }
        }else{
            return true;
        }
    }

    public static boolean validarLimitesIndice(int indice, int limite) throws IndiceInvalidoException, IndiceMaiorQueEsperadoException {
        // Verifica se o índice está dentro dos limites da lista
        if (indice >= 0 && indice < limite) {
            return true;
        } else if (indice < 0) {
            throw new IndiceInvalidoException();
        } else {
            throw new IndiceMaiorQueEsperadoException();
        }
    }



    //METODOS USUARIO
    public static boolean validarEmail(String email) throws EmailInvalidoException {
        // Expressão regular para validar o email
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if(email != null){
            if(email.matches(regex)) {
                return true;
            }
        }
        throw new EmailInvalidoException();


    }

    public static boolean validarSenha(String senha) throws SenhaInvalidaException {
        if(senha != null){
            if(senha.length() >= 6 && senha.length() <= 16){
                return true;
            }
        }
        throw new SenhaInvalidaException();
    }

    public static boolean validarCPF(String cpf) throws CPFinvalidoException {

        String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        if(cpf != null){
            if(cpf.length() == 14 && cpf.matches(regex)){
                return true;
            }
        }
        throw new CPFinvalidoException();

    }

    public static boolean validarVeiculo(String veiculo) throws VeiculoInvalidoException {

        if(veiculo == null || veiculo.isEmpty()){
            throw new VeiculoInvalidoException();
        }
        return true;
    }

    public static boolean validarPlaca(Map<Integer, Usuario> listaDeUsuarios, String placa) throws PlacaInvalidaException, PlacaJaExisteException {

        int idDonoDaPlaca;
        if(placa == null){
            throw new PlacaInvalidaException();
        }

        idDonoDaPlaca = buscaPlacaVeiculo(listaDeUsuarios, placa);

        if(idDonoDaPlaca != -1){
            throw new PlacaInvalidaException();
        }else{
            return true;
        }
    }

    public static boolean validarStringPlaca(String placa) throws PlacaInvalidaException {
        if(placa == null || placa.isEmpty()){
            throw new PlacaInvalidaException();
        }
        return true;
    }

    //valida criação de usuario do tipo cliente
    public static boolean validarCriacaoUsuario(Map<Integer, Usuario> listaDeUsuarios, String nome, String email, String senha, String endereco) throws EnderecoInvalidoException, SenhaInvalidaException, EmailInvalidoException, NomeInvalidoException, JaExisteContaComEsseEmailException, EnderecoEmpresaInvalidoException, DestinoInvalidoException {

        int emailJaExiste;

        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);
        validarEndereco(endereco, "usuario");
        emailJaExiste = buscarIdUsuarioPorEmail(listaDeUsuarios ,email);

        if(emailJaExiste != 0){
            throw new JaExisteContaComEsseEmailException();
        }

        return true;
    }

    //valida usuarios do tipo donoRestaurante
    public static boolean validarCriacaoUsuario(Map<Integer, Usuario> listaDeUsuarios, String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException, SenhaInvalidaException, CPFinvalidoException, EnderecoInvalidoException, JaExisteContaComEsseEmailException, EnderecoEmpresaInvalidoException, DestinoInvalidoException {

        int emailJaExiste;
        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);
        validarEndereco(endereco, "usuario");
        validarCPF(cpf);
        emailJaExiste = buscarIdUsuarioPorEmail(listaDeUsuarios, email);

        if(emailJaExiste != 0){
            throw new JaExisteContaComEsseEmailException();
        }
        return true;
    }

    public static boolean validarSeEEntregador(Map <Integer, Usuario> listaDeUsuarios, int idUsuario, int tipoExceção) throws NaoEEntregadorException, UsuarioNaoCadastradoException, NaoEEntregadorValidoException {
        Usuario usuario = listaDeUsuarios.get(idUsuario);
        if(usuario == null){
            throw new UsuarioNaoCadastradoException();
        }

        if(!usuario.eDoTipo().equals("entregador")){
            if(tipoExceção == 1){
                throw new NaoEEntregadorException();
            }else{
                throw new NaoEEntregadorValidoException();
            }


        }
        return true;

    }


    //METODOS EMPRESA
    public static boolean validarUnicidadeNomeEmpresa(Map<Integer, Empresa> listaDeEmpresas, int IdDono, String nomeDaEmpresa, String enderecoEmpresa) throws NomeInvalidoException, NomeEmpresaJaExisteException, Proibido2EmpresasNomeEnderecoException {

        for (Empresa empresa1 : listaDeEmpresas.values()) {
            // Verifica se o nome da empresa coincide com o nome pesquisado
            if (empresa1.getNomeEmpresa().equalsIgnoreCase(nomeDaEmpresa)) {
                if(empresa1.getID_Dono() != IdDono){
                    throw new NomeEmpresaJaExisteException();

                } else if (empresa1.getEnderecoEmpresa().equalsIgnoreCase(enderecoEmpresa)) {
                    throw new Proibido2EmpresasNomeEnderecoException();
                }
                break;
            }
        }
        return true;

    }

    public static boolean validarTipoEmpresa(String tipoEmpresa) throws TipoEmpresaInvalidoException {

        if(tipoEmpresa == null){
            throw new TipoEmpresaInvalidoException();
        }
        switch(tipoEmpresa){
            case "restaurante":
                return true;
            case "mercado":
                return true;
            default:
                return false;
        }
    }

    public static boolean validarHorarios(String horaAbertura, String horaFechamento) throws FormatoHoraInvalidoException, HorarioInvalidoException {

        if(horaAbertura == null || horaFechamento == null){
            throw new HorarioInvalidoException();
        }

        // Regex para validar o formato de hora HH:MM
        String REGEX_HORARIO1 = "^\\d{2}:\\d{2}$"; //verifica apenas o formato
        String REGEX_HORARIO2 = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"; //verifica se a hora é valida (está dentro dos limites: 00:00-23:59)
        Pattern PATTERN_FORMATO_HORARIO = Pattern.compile(REGEX_HORARIO1); //setta um pattern para deixar a validação mais eficiente
        Pattern PATTERN_VALIDADE_HORARIO = Pattern.compile(REGEX_HORARIO2); //idem o de cima

        // Valida os horários usando o regex
        if (!PATTERN_FORMATO_HORARIO.matcher(horaAbertura).matches() || !PATTERN_FORMATO_HORARIO.matcher(horaFechamento).matches()) {
            throw new FormatoHoraInvalidoException();
        }else if(!PATTERN_VALIDADE_HORARIO.matcher(horaAbertura).matches() || !PATTERN_VALIDADE_HORARIO.matcher(horaFechamento).matches()){
            throw new HorarioInvalidoException();
        }

        // Converte os horários para minutos totais desde 00:00
        int minutosAbertura = utilitarioConverterHorarioParaMinutos(horaAbertura); //esse é um metodo de UtilitarioService
        int minutosFechamento = utilitarioConverterHorarioParaMinutos(horaFechamento); //idem

        // Verifica se o horário de fechamento é posterior ao de abertura
        if(minutosFechamento < minutosAbertura){
            throw new HorarioInvalidoException();
        }

        return true;
    }

    public static boolean validarTipoMercado(String tipoMercado) throws TipoMercadoInvalidoException {
        if(tipoMercado == null){
            throw new TipoMercadoInvalidoException();
        }
        return true;
    }



    //METODOS PRODUTO
    public static boolean validarPreco(float preco) throws ValorInvalidoException{
        if(preco < 0){
            throw new RuntimeException("Valor invalido");//ValorInvalidoException();
        }else{
            return true;
        }
    }

    public static boolean validarCategoria (String nome) throws CategoriaInvalidoException {
        if(nome != null){
            if(nome.length() >= 3){

                return true;
            }
        }
        throw new CategoriaInvalidoException();

    }

    public static boolean validarNomeProdutoJaExisteNessaEmpresa(Map<Integer, Produto> listaDeProdutos, String nome, int idEmpresa) throws ProdutoJaExisteException {
        Produto produtoBuscado = buscarProdutoPorEmpresaENome(listaDeProdutos, idEmpresa, nome);
        if(produtoBuscado == null){
            return true;
        }else{
            throw new ProdutoJaExisteException();
        }
    }

    public static boolean validarExistenciaProduto(Produto p, String tipoExcecaoDesejada) throws ProdutoNaoCadastradoException, ProdutoNaoEncontradoException {
        if(p != null){
            return true;
        }else{
            if(tipoExcecaoDesejada.equalsIgnoreCase("cadastrado")){
                throw new ProdutoNaoCadastradoException();
            }else{
                throw new ProdutoNaoEncontradoException();
            }

        }
    }

    public static boolean validarExistenciaEmpresa(Empresa empresa) throws EmpresaNaoEncontradaException {
        if(empresa != null){
            return true;
        }else{
            throw new EmpresaNaoEncontradaException();
        }
    }



    //METODOS PEDIDO
    public static boolean validarNomeProdutoNulo(String nomeProduto) throws ProdutoInvalidoException {
        if(nomeProduto == null || nomeProduto.length() < 1){
            throw new ProdutoInvalidoException();
        }else{
            return true;
        }
    }

    public static boolean validarPedidoPronto(Pedido pedido) throws PedidoNaoProntoException {
        if(pedido.getEstadoPedido().equals("pronto")){
            return true;
        }else {
            throw new PedidoNaoProntoException();
        }
    }

    //METODOS ENTREGA
    public static boolean validarSeEntregadorEstaLivre(Usuario usuario) throws EntregadorAindaEmEntregaException {

        if(((Entregador)usuario).getLivreParaEntregar()){
            return true;
        }else{
            throw new EntregadorAindaEmEntregaException();
        }
    }

    public static boolean validarSeEntregaExiste(Map<Integer, Entrega> listaDeEntregas, int idEntrega) throws NaoExisteNadaParaEntregarComIdException {
        if(listaDeEntregas.get(idEntrega) != null){
            return true;
        }else{
            throw new NaoExisteNadaParaEntregarComIdException();
        }
    }


}
