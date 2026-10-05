package motor.builder;

import motor.documento.Contenido;

public interface DocumentBuilder {
    DocumentBuilder addHeader(String texto);
    DocumentBuilder addParagraph(String texto);
    DocumentBuilder addTable(String[][] filas);
    DocumentBuilder addFooter(String texto);
    Contenido build();
}
