package ucaldas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase "ContadorPalabras" es responsable de gestionar la 
 * lectura de archivos de texto, contar la aparición 
 * de una palabra en el contenido de esos archivos y mostrar 
 * los resultados.
 */
public class ContadorPalabras {

    private ArrayList<Documento> documentos = new ArrayList<>();

    /**
     * Crea un nuevo documento leyendo el contenido de un archivo y almacenándolo en
     * un objeto Documento.
     *
     * @param ruta Ruta al archivo que se va a leer y utilizar para crear el
     *             documento.
     * @throws IOException Si ocurre un error durante la lectura del archivo o si el
     *                     archivo no es un archivo de texto válido.
     */

    public void crearDocumento(String ruta) throws IOException {
        File archivo = new File(ruta);

        String extension = archivo.getName().substring(archivo.getName().lastIndexOf(".") + 1);
        if (!(extension.equals("txt") || extension.equals("xml") || extension.equals("json")
                || extension.equals("csv"))) {
            throw new IOException("El archivo " + archivo.getName() + " no es un archivo de texto válido.");
        }

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        StringBuilder sb = new StringBuilder();
        String linea;
        while ((linea = br.readLine()) != null) {
            sb.append(linea);
            sb.append(System.lineSeparator());
        }
        br.close();

        Documento nuevoDocumento = new Documento(archivo.getName(), sb.toString());
        documentos.add(nuevoDocumento);
    }

    /**
     * Lee todos los archivos de una carpeta y los agrega como documentos a la
     * lista.
     *
     * @param rutaCarpeta Ruta de la carpeta que se va a leer.
     * @throws IOException Si la carpeta no existe, no contiene archivos o hay un
     *                     error al procesar un archivo.
     */
    public void leerCarpeta(String rutaCarpeta) throws IOException {
        File carpeta = new File(rutaCarpeta);
        if (!carpeta.isDirectory()) {
            throw new IOException("No se encontró la carpeta indicada.");
        }
        File[] archivosCarpeta = carpeta.listFiles();
        if (archivosCarpeta == null) {
            throw new IOException("No se encontraron archivos en la carpeta indicada.");
        } else {
            for (File archivo : archivosCarpeta) {
                if (!archivo.isDirectory()) {
                    try {
                        crearDocumento(archivo.getAbsolutePath());
                    } catch (IOException e) {
                        System.out.println("Error al procesar el archivo " + archivo.getName() + ": " + e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Cuenta cuántas veces aparece una palabra en el contenido de todos los
     * documentos y muestra los resultados.
     *
     * @param palabra Palabra que se va a buscar y contar en el contenido de los
     *                documentos.
     */
    public void contarPalabra(String palabra) {
        int total = 0;
        Pattern pattern = Pattern.compile("\\b" + palabra + "\\b");
        for (Documento documento : documentos) {
            int contador = 0;
            String contenido = documento.getContenido();
            Matcher matcher = pattern.matcher(contenido);
            while (matcher.find()) {
                contador++;
            }
            System.out.println("La palabra \"" + palabra + "\" aparece " + contador + " veces en el documento "
                    + documento.getTitulo());
            total += contador;
        }
        System.out.println("La palabra \"" + palabra + "\" aparece " + total + " veces en total.");
    }
}
