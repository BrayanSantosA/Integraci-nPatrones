package bridge;

import documento.Bloque;

public class MarkdownRenderEngine implements RenderizadorEngine {
    public String formato() { return "MARKDOWN"; }

    public String encabezado(Bloque b) { return "# [" + b.getIcono().getNombre() + "] " + b.getTexto(); }

    public String parrafo(Bloque b) { return b.getTexto(); }

    public String tabla(Bloque b) {
        String[][] filas = b.getFilas();
        StringBuilder sb = new StringBuilder("| " + String.join(" | ", filas[0]) + " |\n");
        sb.append("|").append(" --- |".repeat(filas[0].length));
        for (int i = 1; i < filas.length; i++) sb.append("\n| ").append(String.join(" | ", filas[i])).append(" |");
        return sb.toString();
    }

    public String pie(Bloque b) { return "_" + b.getTexto() + "_"; }

    public String separadorPagina(int n) { return "---"; }

    public String envolver(String titulo, String cuerpo) { return cuerpo; }
}
