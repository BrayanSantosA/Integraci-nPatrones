package chain;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import documento.Bloque;
import documento.Contenido;
import interpreter.Contexto;


public class SanitizadorPalabras extends ProcesadorHandler {
    private final List<Pattern> patrones;

    public SanitizadorPalabras(List<String> prohibidas) {
        patrones = prohibidas.stream()
                .map(p -> Pattern.compile(Pattern.quote(p), Pattern.CASE_INSENSITIVE))
                .toList();
    }

    @Override
    protected void procesar(Contenido contenido, Contexto ctx, ReporteProceso r) {
        int[] ocultas = {0};
        for (Bloque b : contenido.getBloques()) {
            b.transformar(texto -> {
                String res = texto;
                for (Pattern p : patrones) {
                    Matcher m = p.matcher(res);
                    StringBuilder sb = new StringBuilder();
                    while (m.find()) {
                        ocultas[0]++;
                        m.appendReplacement(sb, "*".repeat(m.group().length()));
                    }
                    m.appendTail(sb);
                    res = sb.toString();
                }
                return res;
            });
        }
        r.info("SanitizadorPalabras: " + ocultas[0] + " palabra(s) ocultada(s)");
    }
}
