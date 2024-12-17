import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        try {
            int numArquivos = 3; // Quantidade de arquivos de teste
            Escalonamento fifo = new FIFO(); // Instância da classe FIFO

            for (int i = 1; i <= numArquivos; i++) {
                // Caminhos dos arquivos de entrada e saída
                String nomeArquivoEntrada = String.format(
                    "C:\\Users\\fabia\\Downloads\\TESTE-%02d.txt", i);
                String nomeArquivoSaida = String.format(
                    "C:\\Users\\fabia\\Downloads\\TESTE-%02d-RESULTADO.txt", i);

                // Lendo o arquivo de entrada
                List<String> linhas = ler_Arquivo(nomeArquivoEntrada);

                // Convertendo os dados lidos para uma lista de processos
                List<Processo> processos = new ArrayList<>();
                for (String linha : linhas) {
                    if (linha.trim().isEmpty()) continue;

                    String[] partes = linha.trim().split(" ");
                    if (partes.length < 2) continue;

                    try {
                        int chegada = Integer.parseInt(partes[0]);
                        int duracao = Integer.parseInt(partes[1]);
                        processos.add(new Processo(chegada, duracao));
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter linha: " + linha);
                    }
                }

                // Executando o algoritmo FIFO e salvando resultados
                double[] resultadosFIFO = fifo.calcular(processos);
                Resultados.salvarResultados(nomeArquivoSaida, resultadosFIFO);

                System.out.println("Processado TESTE-" + i + ".txt -> Resultados salvos em " + nomeArquivoSaida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para ler arquivos
    public static List<String> ler_Arquivo(String caminho) throws IOException {
        Path path = Paths.get(caminho);
        return Files.readAllLines(path);
    }
}
