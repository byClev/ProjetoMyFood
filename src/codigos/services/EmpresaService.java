package codigos.services;

import codigos.entidades.*;
import excecoes.*;
import excecoes.empresa.*;
import excecoes.entrega.DestinoInvalidoException;
import excecoes.pedido.PedidoNaoEncontradoException;
import excecoes.usuario.UsuarioNaoEEntregadoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static codigos.services.ValidadorService.*;
import static codigos.services.UtilitariosService.*;

public class EmpresaService {
    //criação de restaurante
    public static Empresa serviceCriarEmpresa(Map <Integer, Usuario> listaDeUsuarios, Map<Integer, Empresa> listaDeEmpresas, String tipoEmpresa, int idDono,
                                              int idEmpresa, String nome, String endereco, String tipoCozinha) throws UsuarioNaoPodeCriarEmpresaException, NomeInvalidoException, NomeEmpresaJaExisteException,
            Proibido2EmpresasNomeEnderecoException, EnderecoInvalidoException, EmpresaNaoCadastradaException, UsuarioNaoCadastradoException, TipoEmpresaInvalidoException, EnderecoEmpresaInvalidoException, DestinoInvalidoException, PedidoNaoEncontradoException {

        validarNome(nome); //valida a string do nome
        validarUnicidadeNomeEmpresa(listaDeEmpresas, idDono, nome, endereco); //valida caso o nome ja esteja sendo usado em outra empresa
        validarEndereco(endereco, "empresa");
        validarID(listaDeUsuarios, idDono, "usuario");
        validarTipoEmpresa(tipoEmpresa);

        Usuario usuario = listaDeUsuarios.get(idDono);

        if(usuario.eDoTipo().equals("dono")){
            ((DonoEmpresa) usuario).getEmpresasDoUsuario().add(idEmpresa);
            Empresa essaEmpresa = new Restaurante(idEmpresa, idDono, nome, endereco, tipoCozinha);
            return essaEmpresa;
        }else{
            throw new UsuarioNaoPodeCriarEmpresaException();
        }

    }
    //criação de mercado
    public static Empresa serviceCriarEmpresa(Map <Integer, Usuario> listaDeUsuarios, Map<Integer, Empresa> listaDeEmpresas, String tipoEmpresa, int idDono,
                                              int idEmpresa, String nome, String endereco, String abre, String fecha, String tipoMercado) throws UsuarioNaoPodeCriarEmpresaException, NomeInvalidoException,
            NomeEmpresaJaExisteException, Proibido2EmpresasNomeEnderecoException, EnderecoInvalidoException, EmpresaNaoCadastradaException, UsuarioNaoCadastradoException,
            FormatoHoraInvalidoException, HorarioInvalidoException, TipoEmpresaInvalidoException, EnderecoEmpresaInvalidoException, TipoMercadoInvalidoException, DestinoInvalidoException, PedidoNaoEncontradoException {

        validarNome(nome); //valida a string do nome
        validarUnicidadeNomeEmpresa(listaDeEmpresas, idDono, nome, endereco); //valida caso o nome ja esteja sendo usado em outra empresa
        validarEndereco(endereco, "empresa");
        validarID(listaDeUsuarios, idDono, "usuario");
        validarTipoEmpresa(tipoEmpresa);
        validarTipoMercado(tipoMercado);
        validarHorarios(abre, fecha);

        Usuario usuario = listaDeUsuarios.get(idDono);

        if(usuario.eDoTipo().equals("dono")){
            ((DonoEmpresa) usuario).getEmpresasDoUsuario().add(idEmpresa);
            Empresa essaEmpresa = new Mercado(idEmpresa, idDono, nome, endereco, abre, fecha, tipoMercado);
            return essaEmpresa;
        }else{
            throw new UsuarioNaoPodeCriarEmpresaException();
        }

    }

    //criação de farmacia
    public static Empresa serviceCriarEmpresa(Map <Integer, Usuario> listaDeUsuarios, Map<Integer, Empresa> listaDeEmpresas, String tipoEmpresa, int idDono,
                                              int idEmpresa, String nome, String endereco, boolean aberto24h, int numeroFuncionarios) throws UsuarioNaoPodeCriarEmpresaException, NomeInvalidoException,
            NomeEmpresaJaExisteException, Proibido2EmpresasNomeEnderecoException, EnderecoInvalidoException, EmpresaNaoCadastradaException, UsuarioNaoCadastradoException,
            TipoEmpresaInvalidoException, EnderecoEmpresaInvalidoException, DestinoInvalidoException, PedidoNaoEncontradoException {

        validarNome(nome); //valida a string do nome
        validarUnicidadeNomeEmpresa(listaDeEmpresas, idDono, nome, endereco); //valida caso o nome ja esteja sendo usado em outra empresa
        validarEndereco(endereco, "empresa");
        validarID(listaDeUsuarios, idDono, "usuario");
        validarTipoEmpresa(tipoEmpresa);


        Usuario usuario = listaDeUsuarios.get(idDono);

        if(usuario.eDoTipo().equals("dono")){
            ((DonoEmpresa) usuario).getEmpresasDoUsuario().add(idEmpresa);
            Empresa essaEmpresa = new Farmacia(idEmpresa, idDono, nome, endereco, aberto24h, numeroFuncionarios);
            return essaEmpresa;
        }else{
            throw new UsuarioNaoPodeCriarEmpresaException();
        }

    }

    public static String serviceGetEmpresasDoUsuario(Map <Integer, Usuario> listaDeUsuarios, Map<Integer, Empresa> listaDeEmpresas, int idUsuario, String tipoUsuario)
            throws UsuarioNaoPodeCriarEmpresaException, UsuarioNaoCadastradoException, UsuarioNaoEEntregadoException {

        Usuario usuario1 = listaDeUsuarios.get(idUsuario);
        if(usuario1 == null){
            throw new UsuarioNaoCadastradoException();
        }

        //esse método foi adaptado para funcionar com os testes 2.1 e 7.1 já que ambos requeriam um retorno de nome e endereco de empresas no mesmo formato mudando
        //apenas a exceção disparada, o "miolo" de funcionamento foi parar na classe UtiitariosService
        if(tipoUsuario.equals("dono")){
            if(usuario1.eDoTipo().equals("dono")){
                List<Integer> listaComEmpresasDoUsuario = ((DonoEmpresa) usuario1).getEmpresasDoUsuario();
                return utilitarioConcatenarNomeEnderecoEmpresa(listaDeEmpresas, listaComEmpresasDoUsuario);
            }else{
                throw new UsuarioNaoPodeCriarEmpresaException();
            }
        }else{
            if(usuario1.eDoTipo().equals("entregador")){
                List<Integer> listaComEmpresasDoUsuario = ((Entregador) usuario1).getEmpresasTrabalhadas();
                return utilitarioConcatenarNomeEnderecoEmpresa(listaDeEmpresas, listaComEmpresasDoUsuario);
            }else{
                throw new UsuarioNaoEEntregadoException();
            }
        }

    }

    public static int serviceGetIdEmpresa(Map <Integer, Usuario> listaDeUsuarios, Map<Integer, Empresa> listaDeEmpresas, int idDono, String nome, int indice) throws IndiceInvalidoException, NomeInvalidoException, TipoDeUsuarioInvalidoException, NaoExisteEmpresaNomeException, IndiceMaiorQueEsperadoException {

        validarNome(nome);
        if(indice < 0){
            throw new IndiceInvalidoException(); //validação dupla de indice (tem uma mais especifica abaixo) senão a ordem das exception fica errada
        }

        //checa se o usuario é do tipo dono
        Usuario usuario1 = listaDeUsuarios.get(idDono);
        if(!usuario1.eDoTipo().equals("dono")){
            throw new TipoDeUsuarioInvalidoException();
        }

        // Cria uma lista temporária para armazenar as empresas que correspondem ao nome fornecido
        List<Integer> empresasComMesmoNome = new ArrayList<>();

        // Percorre o mapa de empresas
        for (Map.Entry<Integer, Empresa> entry : listaDeEmpresas.entrySet()) {
            Empresa empresa = entry.getValue();
            // Se o nome da empresa corresponder ao nome fornecido, adiciona o ID à lista temporária
            if (empresa.getNomeEmpresa().equalsIgnoreCase(nome)) {
                empresasComMesmoNome.add(entry.getKey()); // Adiciona o ID (chave do mapa)
            }
        }

        if(empresasComMesmoNome.isEmpty()){
            throw new NaoExisteEmpresaNomeException();
        }

        validarLimitesIndice(indice, empresasComMesmoNome.size());
        return empresasComMesmoNome.get(indice);
    }

    public static String serviceGetAtributoEmpresa(Map <Integer, Usuario> listaDeUsuarios, Map<Integer, Empresa> listaDeEmpresas, int IdEmpresa, String atributo) throws EmpresaNaoCadastradaException, UsuarioNaoCadastradoException, AtributoInvalidoException, PedidoNaoEncontradoException {

        Empresa empresa1 = listaDeEmpresas.get(IdEmpresa);
        validarID(listaDeEmpresas, IdEmpresa, "empresa");
        validarAtributo(atributo);

        if(empresa1 != null){
            switch(atributo){
                case "dono":
                    return listaDeUsuarios.get(listaDeEmpresas.get(IdEmpresa).getID_Dono()).getNome();
                case "nome":
                    return empresa1.getNomeEmpresa();
                case "endereco":
                    return empresa1.getEnderecoEmpresa();
                case "tipoCozinha":
                    if(empresa1.eDoTipo().equals("restaurante")){
                        return ((Restaurante) empresa1).getTipoCozinhaRestaurante();
                    }else{
                        throw new AtributoInvalidoException();
                    }
                case "abre":
                    if(empresa1.eDoTipo().equals("mercado")){
                        return ((Mercado) empresa1).getAbre();
                    }else{
                        throw new AtributoInvalidoException();
                    }
                case "fecha":
                    if(empresa1.eDoTipo().equals("mercado")){
                        return ((Mercado) empresa1).getFecha();
                    }else{
                        throw new AtributoInvalidoException();
                    }
                case "tipoMercado":
                    if(empresa1.eDoTipo().equals("mercado")){
                        return ((Mercado) empresa1).getTipoMercado();
                    }else{
                        throw new AtributoInvalidoException();
                    }
                case "aberto24Horas":
                    if(empresa1.eDoTipo().equals("farmacia")){
                        return String.valueOf(((Farmacia) empresa1).isAberto24h());
                    }else{
                        throw new AtributoInvalidoException();
                    }
                case "numeroFuncionarios":
                    if(empresa1.eDoTipo().equals("farmacia")){
                        return String.valueOf(((Farmacia) empresa1).getNumeroDeFuncionarios());
                    }else{
                        throw new AtributoInvalidoException();
                    }
                default:
                    throw new AtributoInvalidoException();
            }
        }else{
            throw new EmpresaNaoCadastradaException();
        }
    }

    public static void serviceAlterarFuncionamento(Map<Integer, Empresa> listaDeEmpresas, int idMercado, String abre, String fecha) throws HorarioInvalidoException, FormatoHoraInvalidoException, NaoEMercadovalidoException {
        Empresa essaEmpresa = listaDeEmpresas.get(idMercado);
        if(essaEmpresa == null || !essaEmpresa.eDoTipo().equals("mercado")){
            throw new NaoEMercadovalidoException();
        }

        validarHorarios(abre, fecha);
        ((Mercado)essaEmpresa).setAbre(abre);
        ((Mercado)essaEmpresa).setFecha(fecha);
    }

    //teste 7.1
    public static String serviceGetEntregadoresDaEmpresa(Map<Integer, Usuario> listaDeUsuarios, Map<Integer, Empresa> listaDeEmpresas, int idEmpresa) throws EmpresaNaoCadastradaException {

        //checa se a empresa existe
        Empresa empresa = listaDeEmpresas.get(idEmpresa);
        if(empresa == null){
            throw new EmpresaNaoCadastradaException();
        }

        //recupera os IDs de entregadores daquela empresa
        List<Integer> listaComEntregadoresDaEmpresa = new ArrayList<>();
        for(Entregador entregador : empresa.getEntregadoresDaEmpresa()){
            listaComEntregadoresDaEmpresa.add(entregador.getID_Usuario());
        }

        //inicia o contrutor de string
        StringBuilder emailsEntregadores = new StringBuilder("{[");

        //itera sobre os IDs dos entregadores e recupera seus emails adicionando a string em construção
        for (int idEntregador : listaComEntregadoresDaEmpresa) {
            Usuario esseEntregador = listaDeUsuarios.get(idEntregador); // recupera o entregador pelo ID

            if (esseEntregador != null) {
                // Adiciona o nome e o endereço da empresa ao formato desejado
                emailsEntregadores.append(esseEntregador.getEmail()).append(", ");
            }
        }

        // Remove a última vírgula e espaço, se houver
        if (emailsEntregadores.length() > 2) {
            emailsEntregadores.setLength(emailsEntregadores.length() - 2); // Remove o ", " extra
        }

        //fecha a string
        emailsEntregadores.append("]}");

        //retorna a string criada
        return emailsEntregadores.toString();

    }

}
