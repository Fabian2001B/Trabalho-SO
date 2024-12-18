import java.util.List;
import java.util.PriorityQueue;

public class SRT implements Escalonamento {

    @Override
    public double[] calcular(List<Processo> processos) {
        int num_processos = processos.size();
        double tempoEspera = 0, tempoResposta = 0, tempoRetorno = 0;

        int[] tempoRestante = new int[num_processos];
        boolean[] respondeu = new boolean[num_processos];

        for (int i = 0; i < num_processos; i++) {
            tempoRestante[i] = processos.get(i).getDuracao();
        }

        processos.sort((p1, p2) -> Integer.compare(p1.getChegada(), p2.getChegada()));

        int processosConcluidos = 0;
        PriorityQueue<Integer> filaProntos = new PriorityQueue<>((i, j) -> Integer.compare(tempoRestante[i], tempoRestante[j]));
        int index = 0;

        // Inicializando tempoAtual com a chegada do primeiro processo
        int tempoAtual = processos.get(0).getChegada();

        while (processosConcluidos < num_processos) {
            while (index < num_processos && processos.get(index).getChegada() <= tempoAtual) {
                filaProntos.add(index);
                index++;
            }

            if (filaProntos.isEmpty()) {
                tempoAtual++;
            } else {
                int idProcesso = filaProntos.poll();
                if (!respondeu[idProcesso]) {
                    tempoResposta += (tempoAtual - processos.get(idProcesso).getChegada());
                    respondeu[idProcesso] = true;
                }
                tempoRestante[idProcesso]--;
                tempoAtual++;

                if (tempoRestante[idProcesso] > 0) {
                    filaProntos.add(idProcesso);
                } else {
                    processosConcluidos++;
                    int tempoChegada = processos.get(idProcesso).getChegada();
                    int duracao = processos.get(idProcesso).getDuracao();
                    tempoRetorno += (tempoAtual - tempoChegada);
                    tempoEspera += (tempoAtual - tempoChegada - duracao);
                }
            }
        }

        return new double[]{
            tempoResposta / num_processos, 
            tempoEspera / num_processos,    
            tempoRetorno / num_processos
        };
    }
}

