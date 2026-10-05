package bridge;

import documento.Bloque;


public interface RenderizadorEngine {
    String formato();
    String encabezado(Bloque b);
    String parrafo(Bloque b);
    String tabla(Bloque b);
    String pie(Bloque b);
    String separadorPagina(int numero);
    String envolver(String titulo, String cuerpo);
}
