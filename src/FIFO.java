import java.util.List;

public class FIFO implements Escalonamento {

    @Override
    public double[] calcular(List<Processo> processos) {
        int num_processos = processos.size();
        double tempoEspera = 0, tempoResposta = 0, tempoRetorno = 0;

        processos.sort((p1, p2) -> Integer.compare(p1.getChegada(), p2.getChegada()));

        // Inicializando tempoAtual com a chegada do primeiro processo
        int tempoAtual = processos.get(0).getChegada();

        for (Processo processo : processos) {
            if (tempoAtual < processo.getChegada()) {
                tempoAtual = processo.getChegada();  
            }

            tempoEspera += (tempoAtual - processo.getChegada());
            tempoResposta += (tempoAtual - processo.getChegada());
            tempoRetorno += (tempoAtual - processo.getChegada() + processo.getDuracao());

            tempoAtual += processo.getDuracao();
        }

        return new double[] {
            tempoResposta / num_processos, 
            tempoEspera / num_processos,    
            tempoRetorno / num_processos   
        };
    }
}
