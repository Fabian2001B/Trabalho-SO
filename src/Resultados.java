import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Resultados {
    public static void salvarResultados(String caminho_arquivo, double[] resultadosFIFO, double[] resultadosSJF, double[] resultadosSRT, double[] resultadosRR) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho_arquivo, true))) { // 'true' para anexar no arquivo
            DecimalFormat df = new DecimalFormat("0.000");

            // Salvando resultados do FIFO
            writer.write(String.format("%s %s %s", 
                df.format(resultadosFIFO[0]), 
                df.format(resultadosFIFO[1]), 
                df.format(resultadosFIFO[2])
            ));
            writer.newLine();  // Nova linha após o FIFO

            // Salvando resultados do SJF
            writer.write(String.format("%s %s %s", 
                df.format(resultadosSJF[0]), 
                df.format(resultadosSJF[1]), 
                df.format(resultadosSJF[2])
            ));
            writer.newLine();  // Nova linha após o SJF


            writer.write(String.format("%s %s %s", 
                df.format(resultadosSRT[0]), 
                df.format(resultadosSRT[1]), 
                df.format(resultadosSRT[2])
            ));
            writer.newLine(); 

            writer.write(String.format("%s %s %s", 
            df.format(resultadosRR[0]), 
            df.format(resultadosRR[1]), 
            df.format(resultadosRR[2])
        ));
        writer.newLine(); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
