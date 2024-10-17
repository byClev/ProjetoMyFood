package codigos.services;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//essa classe cuida da persistence do codigo
public class XMLService {
    // Método genérico para serializar dados de qualquer tipo de Map<Integer, T>
    public static <T> void serviceSerializarDados(Map<Integer, T> lista, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             XMLEncoder encoder = new XMLEncoder(fos)) {

            encoder.writeObject(lista); // Serializa o Map para o arquivo XML
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método genérico para desserializar dados de qualquer tipo de Map<Integer, T>
    public static <T> Map<Integer, T> serviceDeserializarDados(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             XMLDecoder decoder = new XMLDecoder(fis)) {

            return (Map<Integer, T>) decoder.readObject(); // Desserializa o Map
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

}
