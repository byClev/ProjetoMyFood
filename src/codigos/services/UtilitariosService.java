package codigos.services;

import codigos.entidades.Empresa;
import codigos.entidades.Pedido;
import codigos.entidades.Produto;
import excecoes.empresa.HorarioInvalidoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UtilitariosService {

    public static int utilitarioConverterHorarioParaMinutos(String horario) throws HorarioInvalidoException {
        String[] partes = horario.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);

        return horas * 60 + minutos;
    }

    public static String utilitarioConcatenarNomeEnderecoEmpresa(Map<Integer, Empresa> listaDeEmpresas, List<Integer> listaComEmpresasDoUsuario) {
        StringBuilder nomesEmpresas = new StringBuilder("{[");
        for (int IDEmpresa : listaComEmpresasDoUsuario) {
            Empresa empresa1 = listaDeEmpresas.get(IDEmpresa); // Recupera a empresa pelo ID

            if (empresa1 != null) {
                // Adiciona o nome e o endereço da empresa ao formato desejado
                nomesEmpresas.append("[")
                        .append(empresa1.getNomeEmpresa()).append(", ")
                        .append(empresa1.getEnderecoEmpresa())
                        .append("], ");
            }
        }

        // Remove a última vírgula e espaço, se houver
        if (nomesEmpresas.length() > 2) {
            nomesEmpresas.setLength(nomesEmpresas.length() - 2); // Remove o ", " extra
        }

        nomesEmpresas.append("]}");

        return nomesEmpresas.toString();
    }

    public static List<String> utilitarioCriarListaStringDeProdutos(Pedido pedido) {
        List <String> listaDeProdutos = new ArrayList<>();

        for (Produto produto : pedido.getProdutos()) {
            if(produto != null){
                listaDeProdutos.add(produto.getNome());
            }
        }

        return listaDeProdutos;
    }
}
