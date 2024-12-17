import java.util.List;

public class FIFO implements Escalonamento {
    @Override
    public double[] calcular(List<Processo> processos) {
        int num_processos = processos.size();
        double tempoEspera = 0, tempoResposta = 0, retorno = 0;
        int tempoAtual = 0;

        for (Processo processo : processos) {
            if (tempoAtual < processo.getChegada()) {
                tempoAtual = processo.getChegada();
            }
            tempoEspera += (tempoAtual - processo.getChegada());
            tempoResposta += (tempoAtual - processo.getChegada() + processo.getDuracao());
            retorno += (tempoAtual - processo.getChegada() + processo.getDuracao());
            tempoAtual += processo.getDuracao();
        }

        return new double[] {
            tempoResposta / num_processos,
            tempoEspera / num_processos,
            retorno / num_processos
        };
    }
}