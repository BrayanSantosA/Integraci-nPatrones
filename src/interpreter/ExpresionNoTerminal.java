package interpreter;


public abstract class ExpresionNoTerminal implements Expresion {
    protected final Expresion izquierda;
    protected final Expresion derecha;

    protected ExpresionNoTerminal(Expresion izquierda, Expresion derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    protected abstract double operar(double a, double b);

    @Override
    public double interpretar(Contexto contexto) {
        return operar(izquierda.interpretar(contexto), derecha.interpretar(contexto));
    }
}

class Suma extends ExpresionNoTerminal {
    Suma(Expresion a, Expresion b) { super(a, b); }
    protected double operar(double a, double b) { return a + b; }
}

class Resta extends ExpresionNoTerminal {
    Resta(Expresion a, Expresion b) { super(a, b); }
    protected double operar(double a, double b) { return a - b; }
}

class Multiplicacion extends ExpresionNoTerminal {
    Multiplicacion(Expresion a, Expresion b) { super(a, b); }
    protected double operar(double a, double b) { return a * b; }
}

class Division extends ExpresionNoTerminal {
    Division(Expresion a, Expresion b) { super(a, b); }
    protected double operar(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("División por cero");
        return a / b;
    }
}
