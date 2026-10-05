package bridge;

import documento.Bloque;

public class HtmlRenderEngine implements RenderizadorEngine {
    public String formato() { return "HTML"; }

    public String encabezado(Bloque b) {
        return "<h1><span class=\"icono\">" + b.getIcono().getNombre() + "</span> " + b.getTexto() + "</h1>";
    }

    public String parrafo(Bloque b) { return "<p>" + b.getTexto() + "</p>"; }

    public String tabla(Bloque b) {
        StringBuilder sb = new StringBuilder("<table>");
        for (String[] f : b.getFilas()) {
            sb.append("\n  <tr>");
            for (String c : f) sb.append("<td>").append(c).append("</td>");
            sb.append("</tr>");
        }
        return sb.append("\n</table>").toString();
    }

    public String pie(Bloque b) { return "<footer>" + b.getTexto() + "</footer>"; }

    public String separadorPagina(int n) { return "<hr/>"; }

    public String envolver(String titulo, String cuerpo) {
        return "<html><head><title>" + titulo + "</title></head>\n<body>\n" + cuerpo + "\n</body></html>";
    }
}
