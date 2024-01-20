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
        secondPass("programa.txt"); // Segunda passagem
        System.out.println("Processamento concluído com sucesso.");
    
    }

    private static void initializeOPTAB() {
        // Inicialização da tabela de códigos de operação
        OPTAB.put("LDA", "00");
        OPTAB.put("STA", "0C");
        OPTAB.put("ADD", "18");
        
        // ... adicione mais instruções 
    }

    private static void firstPass(String inputFile) {
        // Simulação da primeira passagem
        // Leitura do código-fonte e construção da tabela de símbolos

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
        System.out.println(operationOrLabel + " inicio");System.out.println(LOCCTR);

    } else if ("RES".equals(operationOrLabel)) {
        // Exemplo de processamento da diretiva RES
        // Atualizar a tabela de símbolos com o rótulo e seu valor
        String label = tokens[1];
        int length = Integer.parseInt(tokens[2]);
        SYMBOL_TABLE.put(label, LOCCTR);
        LOCCTR += length;
        System.out.println(label + " label"); System.out.println(LOCCTR); 

    } else if ("LDA".equals(operationOrLabel) || "ADD".equals(operationOrLabel) || "STA".equals(operationOrLabel) || "END".equals(operationOrLabel)){
        // Exemplo de processamento de uma instrução normal
        // Atualizar LOCCTR com base no formato da instrução (pode variar)
        LOCCTR += 3; // Tamanho padrão para instruções
        System.out.println(operationOrLabel + " operation"); System.out.println(LOCCTR); 

    } else {
        // A linha contém uma instrução normal (lABEL + RES + 1)
        // Atualizar a tabela de símbolos com o rótulo e seu valor

        if (!operationOrLabel.isEmpty()) {
            SYMBOL_TABLE.put(operationOrLabel, LOCCTR);
        }
        // Atualizar LOCCTR com base no formato da instrução (pode variar)
        LOCCTR += 2; // Tamanho padrão para instruções
        System.out.println(operationOrLabel + " label"); System.out.println(LOCCTR); 
    }
    }

    private static void secondPass(String inputFile) {
        System.out.println("ooooooooi");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processSecondPassLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void processSecondPassLine(String line) {
        // Lógica para processar cada linha do código-fonte durante a segunda passagem
        // Utilize a tabela de códigos de operação (OPTAB) e a tabela de símbolos (SYMBOL_TABLE)
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
            // Não precisa fazer nada na segunda passagem para a diretiva START
            return;
        } else if ("RES".equals(operationOrLabel)) {
            // Não precisa fazer nada na segunda passagem para a diretiva RES
            return;
        } else if ("LABEL".equals(operationOrLabel)) {
            // Não precisa fazer nada na segunda passagem para rótulos isolados
            return;
        } else if ("END".equals(operationOrLabel)) {
            // Aqui você pode realizar ações específicas associadas ao final do código, se necessário
            return;
        }
    
        // Se chegou aqui, é uma instrução normal
        // Obter o código de operação da tabela de códigos de operação (OPTAB)
        String opcode = OPTAB.get(operationOrLabel);
    
        // Verificar se há um rótulo associado à instrução
        String label = (tokens.length > 1 && !tokens[1].isEmpty()) ? tokens[1] : null;
        Integer address = (label != null) ? SYMBOL_TABLE.get(label) : null;
    
        // Gerar código de máquina
        if (opcode != null) {
            // Aqui você pode processar a instrução e gerar o código de máquina
            // Utilize opcode, label (se houver) e endereço associado ao rótulo (se houver)
            System.out.println("Opcode: " + opcode);
            System.out.println("Label: " + label);
            System.out.println("Address: " + Integer.toHexString(address));
        } else {
            // Mensagem de erro se a instrução não estiver na tabela de códigos de operação
            System.err.println("Erro: Instrução não reconhecida - " + operationOrLabel);
        }
    }
    
}

