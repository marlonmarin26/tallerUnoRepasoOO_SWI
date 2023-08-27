package ucaldas;

/**
 * La clase "Documento" representa un documento creado a partir de un archivo. 
 * Contiene información sobre
 * el título y el contenido del documento.
 */
class Documento {
    private String titulo;
    private String contenido;

    public Documento(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }
}

