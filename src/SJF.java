import java.util.List;
import java.util.PriorityQueue;

public class SJF implements Escalonamento {

    @Override
    public double[] calcular(List<Processo> processos) {
        int num_processos = processos.size();
        double tempoEspera = 0, tempoResposta = 0, tempoRetorno = 0;
        int tempoAtual = 0, processosConcluidos = 0, index = 0;

       
        processos.sort((p1, p2) -> Integer.compare(p1.getChegada(), p2.getChegada()));

      
        PriorityQueue<Processo> filaProntos = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.getDuracao(), p2.getDuracao()));

        while (processosConcluidos < num_processos) {
           
            while (index < num_processos && processos.get(index).getChegada() <= tempoAtual) {
                filaProntos.add(processos.get(index));
                index++;
            }

            if (!filaProntos.isEmpty()) {
              
                Processo processoAtual = filaProntos.poll();

              
                tempoEspera += (tempoAtual - processoAtual.getChegada());
                tempoResposta += (tempoAtual - processoAtual.getChegada());
                tempoRetorno += (tempoAtual - processoAtual.getChegada() + processoAtual.getDuracao());

              
                tempoAtual += processoAtual.getDuracao();
                processosConcluidos++;
            } else {
                
                tempoAtual++;
            }
        }

        return new double[] {
            tempoResposta / num_processos, 
            tempoEspera / num_processos,  
            tempoRetorno / num_processos   
        };
    }
}
