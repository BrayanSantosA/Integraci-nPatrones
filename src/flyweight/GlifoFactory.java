package flyweight;

import java.util.HashMap;
import java.util.Map;


public final class GlifoFactory {
    private static final GlifoFactory INSTANCIA = new GlifoFactory();

    private final Map<String, Glifo> glifos = new HashMap<>();
    private final Map<String, Icono> iconos = new HashMap<>();
    private int solicitudes;

    private GlifoFactory() {
    }

    public static GlifoFactory getInstancia() {
        return INSTANCIA;
    }

    public synchronized Glifo getGlifo(char c, String tipografia) {
        solicitudes++;
        return glifos.computeIfAbsent(tipografia + "|" + c, k -> new Glifo(c, tipografia));
    }

    public synchronized Icono getIcono(String nombre) {
        solicitudes++;
        return iconos.computeIfAbsent(nombre, Icono::new);
    }

    public synchronized int instanciasCreadas() {
        return glifos.size() + iconos.size();
    }

    public synchronized int solicitudes() {
        return solicitudes;
    }
}
