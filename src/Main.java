import java.util.Map;

// Essa é a Classe AbstractExpression
abstract class AbstractExpression {
    public abstract void interpret(Map<String, Integer> registradores);

    public abstract String toString();
}

// Essa é a Classe TerminalExpression
class TerminalExpression extends AbstractExpression {
    private String destino;
    private String origem;

    public TerminalExpression(String destino, String origem) {
        this.destino = destino;
        this.origem = origem;
    }

    @Override
    public void interpret(Map<String, Integer> registradores) {
        int valor = 0;
        if (Character.isDigit(origem.charAt(0)) || (origem.charAt(0) == '-' && Character.isDigit(origem.charAt(1)))) {
            // Verifica se a origem é um número
            valor = Integer.parseInt(origem);
        } else {
            // Se a origem for um registrador, obtém o valor do mapa de registradores
            valor = registradores.get(origem);
        }
        // Atribui o valor ao registrador de destino
        registradores.put(destino, valor);
    }

    @Override
    public String toString() {
        return destino + " = " + origem;
    }
}

// Essa é a Classe NonterminalExpression
class NonterminalExpression extends AbstractExpression {
    private String destino;
    private String operando1;
    private String operador;
    private String operando2;

    public NonterminalExpression(String destino, String operando1, String operador, String operando2) {
        this.destino = destino;
        this.operando1 = operando1;
        this.operador = operador;
        this.operando2 = operando2;
    }

    @Override
    public void interpret(Map<String, Integer> registradores) {
        int valor1 = registradores.get(operando1);
        int valor2 = 0;
        if (Character.isDigit(operando2.charAt(0)) || (operando2.charAt(0) == '-' && Character.isDigit(operando2.charAt(1)))) {
            // Verifica se o operando2 é um número
            valor2 = Integer.parseInt(operando2);
        } else {
            // Se o operando2 for um registrador, obtém o valor do mapa de registradores
            valor2 = registradores.get(operando2);
        }

        int resultado = 0;
        switch (operador) {
            case "ADD":
                resultado = valor1 + valor2;
                break;
            case "SUB":
                resultado = valor1 - valor2;
                break;
        }

        // Atribui o resultado ao registrador de destino
        registradores.put(destino, resultado);
    }

    @Override
    public String toString() {
        return destino + " = " + operando1 + " " + operador + " " + operando2;
    }
}

