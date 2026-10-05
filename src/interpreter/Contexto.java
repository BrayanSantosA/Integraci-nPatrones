package interpreter;

import java.util.HashMap;
import java.util.Map;


public class Contexto {
    private final Map<String, Double> variables = new HashMap<>();
    private final Map<String, String> textos = new HashMap<>();

    public void setVariable(String nombre, double valor) { variables.put(nombre, valor); }
    public void setTexto(String nombre, String valor) { textos.put(nombre, valor); }

    public double getVariable(String nombre) {
        Double v = variables.get(nombre);
        if (v == null) throw new IllegalArgumentException("Variable no definida: " + nombre);
        return v;
    }

    public boolean tieneTexto(String nombre) { return textos.containsKey(nombre); }
    public String getTexto(String nombre) { return textos.get(nombre); }
}
