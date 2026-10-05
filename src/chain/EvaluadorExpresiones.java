package chain;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import documento.Bloque;
import documento.Contenido;
import interpreter.Contexto;
import interpreter.ExpresionParser;


public class EvaluadorExpresiones extends ProcesadorHandler {
    private static final Pattern MARCADOR = Pattern.compile("#\\{([^}]*)\\}");

    @Override
    protected void procesar(Contenido contenido, Contexto ctx, ReporteProceso r) {
        try {
            for (Bloque b : contenido.getBloques()) b.transformar(t -> evaluar(t, ctx, r));
            r.info("EvaluadorExpresiones: expresiones evaluadas");
        } catch (IllegalArgumentException e) {
            r.error("EvaluadorExpresiones: " + e.getMessage());
        }
    }

    private String evaluar(String texto, Contexto ctx, ReporteProceso r) {
        Matcher m = MARCADOR.matcher(texto);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            String expr = m.group(1).trim();
            String valor;
            if (ctx.tieneTexto(expr)) {
                valor = ctx.getTexto(expr);
            } else {
                double v = new ExpresionParser().parsear(expr).interpretar(ctx);
                valor = String.format(Locale.US, "%.2f", v);
            }
            r.info("  #{" + expr + "} = " + valor);
            m.appendReplacement(sb, Matcher.quoteReplacement(valor));
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
