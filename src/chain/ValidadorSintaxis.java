package chain;

import documento.Bloque;
import documento.Contenido;
import interpreter.Contexto;


public class ValidadorSintaxis extends ProcesadorHandler {
    @Override
    protected void procesar(Contenido contenido, Contexto ctx, ReporteProceso r) {
        for (Bloque b : contenido.getBloques()) {
            for (String t : b.textos()) {
                int i = t.indexOf("#{");
                while (i >= 0) {
                    int cierre = t.indexOf('}', i);
                    int siguienteMarcador = t.indexOf("#{", i + 2);
                    if (cierre < 0 || (siguienteMarcador >= 0 && siguienteMarcador < cierre)) {
                        r.error("ValidadorSintaxis: marcador sin cerrar en \"" + t + "\"");
                        return;
                    }
                    i = siguienteMarcador;
                }
                if (t.lastIndexOf('<') > t.lastIndexOf('>')) {
                    r.error("ValidadorSintaxis: etiqueta corrupta en \"" + t + "\"");
                    return;
                }
            }
        }
        r.info("ValidadorSintaxis: sintaxis correcta");
    }
}
