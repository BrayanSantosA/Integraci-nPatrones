package bridge;

import documento.Bloque;


public class PdfRenderEngine implements RenderizadorEngine {
    public String formato() { return "PDF"; }

    public String encabezado(Bloque b) {
        return "BT /F1 18 Tf [" + b.getIcono().getNombre() + "] (" + b.getTexto() + ") Tj ET";
    }

    public String parrafo(Bloque b) {
        return "BT /F1 11 Tf (" + b.getTexto() + ") Tj ET  % " + b.getGlifos().size() + " glifos";
    }

    public String tabla(Bloque b) {
        StringBuilder sb = new StringBuilder("TABLA");
        for (String[] f : b.getFilas()) sb.append("\n  ").append(String.join(" | ", f));
        return sb.toString();
    }

    public String pie(Bloque b) { return "BT /F1 8 Tf (" + b.getTexto() + ") Tj ET"; }

    public String separadorPagina(int n) { return "--- fin de página " + n + " ---"; }

    public String envolver(String titulo, String cuerpo) {
        return "%PDF-1.7 (simulado) titulo=\"" + titulo + "\"\n" + cuerpo + "\n%%EOF";
    }
}
