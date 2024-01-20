import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SICXEMontador {

    // Tabela de Códigos de Operação (OPTAB)
    private static final Map<String, String> OPTAB = new HashMap<>();

    // Tabela de Símbolos (Symbol Table)
    private static final Map<String, Integer> SYMBOL_TABLE = new HashMap<>();

    // Localização do Contador (LOCCTR)
    private static int LOCCTR;

    public static void main(String[] args) {
        initializeOPTAB();
        firstPass("programa.txt"); // Primeira passagem com o arquivo de entrada
        secondPass(); // Segunda passagem
        System.out.println("Processamento concluído com sucesso.");
    
    }

    private static void initializeOPTAB() {
        // Inicialização da tabela de códigos de operação
        OPTAB.put("LDA", "00");
        OPTAB.put("STA", "0C");
        OPTAB.put("ADD", "18");
        // ... adicione mais instruções conforme necessário
    }

    private static void firstPass(String inputFile) {
        // Simulação da primeira passagem
        // Leitura do código-fonte e construção da tabela de símbolos

        LOCCTR = 0x1000; // Inicializa o Local Counter
        // Leia o código-fonte e processe cada linha
        // Atualize a tabela de símbolos conforme necessário
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Processar a linha e atualizar a tabela de símbolos conforme necessário
                processLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void processLine(String line) {
        // Lógica para processar cada linha do código-fonte
        // Atualize a tabela de símbolos conforme necessário
        // Exemplo: Identificar diretivas START, RES, etc.
        // Remover espaços extras e dividir a linha em partes
    String[] tokens = line.trim().split("\\s+");

    // Verificar se a linha está vazia ou é um comentário
    if (tokens.length == 0 || tokens[0].startsWith(".")) {
        // Ignorar linhas vazias ou comentários
        return;
    }

    // Obter o primeiro token que representa a operação ou rótulo
    String operationOrLabel = tokens[0].toUpperCase();

    // Verificar se a linha contém uma diretiva START
    if ("START".equals(operationOrLabel)) {
        // Exemplo de processamento da diretiva START
        // Atualizar o valor de LOCCTR conforme necessário
        LOCCTR = Integer.parseInt(tokens[1], 16);
        System.out.println("oi1");
    } else if ("NUM1".equals(operationOrLabel)) {
        // Exemplo de processamento da diretiva RES
        // Atualizar a tabela de símbolos com o rótulo e seu valor
        String label = tokens[1];
        int length = Integer.parseInt(tokens[2]);
        SYMBOL_TABLE.put(label, LOCCTR);
        LOCCTR += length;
        System.out.println("oi2"); System.out.println(LOCCTR); 
    } else {
        // A linha contém uma instrução normal
        // Atualizar a tabela de símbolos com o rótulo e seu valor
        if (!operationOrLabel.isEmpty()) {
            SYMBOL_TABLE.put(operationOrLabel, LOCCTR);
        }

        // Atualizar LOCCTR com base no formato da instrução (pode variar)
        LOCCTR += 3; // Tamanho padrão para instruções
    }
    }

    private static void secondPass() {
        // Simulação da segunda passagem
        // Leitura do código-fonte e geração do código de máquina

        LOCCTR = 0x1000; // Reinicia o Local Counter
        // Leia o código-fonte novamente e gere o código de máquina
        // Use as tabelas OPTAB e SYMBOL_TABLE para obter opcodes e endereços
    }
}
