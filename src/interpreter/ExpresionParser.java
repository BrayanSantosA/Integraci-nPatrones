package interpreter;


public class ExpresionParser {
    private String s;
    private int pos;

    public Expresion parsear(String texto) {
        s = texto;
        pos = 0;
        Expresion e = expr();
        saltar();
        if (pos < s.length()) throw new IllegalArgumentException("Carácter inesperado '" + s.charAt(pos) + "' en: " + s);
        return e;
    }

    private Expresion expr() {
        Expresion izq = term();
        while (true) {
            if (match('+')) izq = new Suma(izq, term());
            else if (match('-')) izq = new Resta(izq, term());
            else return izq;
        }
    }

    private Expresion term() {
        Expresion izq = factor();
        while (true) {
            if (match('*')) izq = new Multiplicacion(izq, factor());
            else if (match('/')) izq = new Division(izq, factor());
            else return izq;
        }
    }

    private Expresion factor() {
        saltar();
        if (pos >= s.length()) throw new IllegalArgumentException("Expresión incompleta: " + s);
        char c = s.charAt(pos);
        if (c == '(') {
            pos++;
            Expresion e = expr();
            if (!match(')')) throw new IllegalArgumentException("Falta ')' en: " + s);
            return e;
        }
        int ini = pos;
        if (Character.isDigit(c) || c == '.') {
            while (pos < s.length() && (Character.isDigit(s.charAt(pos)) || s.charAt(pos) == '.')) pos++;
            return new ExpresionNumero(Double.parseDouble(s.substring(ini, pos)));
        }
        if (Character.isLetter(c) || c == '_') {
            while (pos < s.length() && (Character.isLetterOrDigit(s.charAt(pos)) || s.charAt(pos) == '_')) pos++;
            return new ExpresionVariable(s.substring(ini, pos));
        }
        throw new IllegalArgumentException("Carácter inesperado '" + c + "' en: " + s);
    }

    private boolean match(char c) {
        saltar();
        if (pos < s.length() && s.charAt(pos) == c) {
            pos++;
            return true;
        }
        return false;
    }

    private void saltar() {
        while (pos < s.length() && Character.isWhitespace(s.charAt(pos))) pos++;
    }
}
