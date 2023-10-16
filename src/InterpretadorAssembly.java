import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Essa é a Classe Client
public class InterpretadorAssembly {
    public static void main(String[] args) {
        List<String> programa = new ArrayList<>();
        programa.add("MOV AX 10");
        programa.add("MOV BX 5");
        programa.add("ADD AX BX");
        programa.add("SUB AX BX");
        programa.add("MOV AX 20");
        programa.add("MOV BX 15");
        programa.add("SUB AX BX");
        programa.add("ADD AX BX");
        programa.add("ADD AX 35");
        programa.add("SUB BX 5");
        programa.add("ADD AX BX");

        Map<String, Integer> registradores = new HashMap<>();
        for (String linha : programa) {
            String[] partes = linha.split("\\s+");
            if (partes.length >= 3) {
                String instrucao = partes[0];
                String dest = partes[1];
                String src = partes[2];
                if (instrucao.equals("MOV")) {
                    // Cria e interpreta uma instrução de movimentação (TerminalExpression)
                    TerminalExpression move = new TerminalExpression(dest, src);
                    move.interpret(registradores);
                    System.out.println(move);
                } else if (instrucao.equals("ADD") || instrucao.equals("SUB")) {
                    String operando1 = dest;
                    String operador = instrucao;
                    String operando2 = src;
                    // Cria e interpreta uma instrução aritmética (NonterminalExpression)
                    NonterminalExpression aritmetica = new NonterminalExpression(dest, operando1, operador, operando2);
                    aritmetica.interpret(registradores);
                    System.out.println(aritmetica);
                }
            }
        }

        // Imprime os valores dos registradores após a execução do programa
        System.out.println("AX = " + registradores.get("AX"));
        System.out.println("BX = " + registradores.get("BX"));
    }
}
