package chain;

import documento.Contenido;
import interpreter.Contexto;

public abstract class ProcesadorHandler {
    private ProcesadorHandler siguiente;

    /** Devuelve el siguiente manejador para poder encadenar la configuración. */
    public ProcesadorHandler setSiguiente(ProcesadorHandler siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public final void manejar(Contenido contenido, Contexto ctx, ReporteProceso reporte) {
        procesar(contenido, ctx, reporte);
        if (reporte.isCritico()) {
            reporte.info(getClass().getSimpleName() + ": error crítico, cadena interrumpida");
            return;
        }
        if (siguiente != null) siguiente.manejar(contenido, ctx, reporte);
    }

    protected abstract void procesar(Contenido contenido, Contexto ctx, ReporteProceso reporte);
}
