import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Resultados {
    public static void salvarResultados(String caminho_arquivo, double[] resultados) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho_arquivo))) {
            DecimalFormat df = new DecimalFormat("0.000");
            writer.write(String.format("%s %s %s",
                df.format(resultados[0]),
                df.format(resultados[1]),
                df.format(resultados[2])
            ));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
