import java.util.List;
import java.util.PriorityQueue;

public class SRT implements Escalonamento {

    @Override
    public double[] calcular(List<Processo> processos) {
        int num_processos = processos.size();
        double tempoEspera = 0, tempoResposta = 0, tempoRetorno = 0;

        // Array auxiliar para tempo restante de cada processo
        int[] tempoRestante = new int[num_processos];
        boolean[] respondeu = new boolean[num_processos]; // Para calcular o tempo de resposta

        for (int i = 0; i < num_processos; i++) {
            tempoRestante[i] = processos.get(i).getDuracao();
        }

        // Ordena processos por tempo de chegada
        processos.sort((p1, p2) -> Integer.compare(p1.getChegada(), p2.getChegada()));

        int tempoAtual = 0; // Tempo do sistema
        int processosConcluidos = 0;

        PriorityQueue<Integer> filaProntos = new PriorityQueue<>((i, j) -> {
            int diff = Integer.compare(tempoRestante[i], tempoRestante[j]);
            if (diff == 0) return Integer.compare(processos.get(i).getChegada(), processos.get(j).getChegada());
            return diff;
        });

        int index = 0; // Índice dos processos a serem adicionados

        while (processosConcluidos < num_processos) {
            // Adiciona processos prontos à fila
            while (index < num_processos && processos.get(index).getChegada() <= tempoAtual) {
                filaProntos.add(index);
                index++;
            }

            if (filaProntos.isEmpty()) {
                // Não há processos prontos, avança o tempo
                tempoAtual++;
            } else {
                // Pega o processo com o menor tempo restante
                int idProcesso = filaProntos.poll();

                // Marca o tempo de resposta apenas na primeira execução do processo
                if (!respondeu[idProcesso]) {
                    tempoResposta += (tempoAtual - processos.get(idProcesso).getChegada());
                    respondeu[idProcesso] = true;
                }

                // Executa o processo por 1 unidade de tempo
                tempoRestante[idProcesso]--;
                tempoAtual++;

                // Se o processo ainda não terminou, adiciona de volta na fila
                if (tempoRestante[idProcesso] > 0) {
                    filaProntos.add(idProcesso);
                } else {
                    // Processo concluído
                    processosConcluidos++;
                    int tempoChegada = processos.get(idProcesso).getChegada();
                    int duracao = processos.get(idProcesso).getDuracao();

                    // Calcula tempos
                    tempoRetorno += (tempoAtual - tempoChegada);
                    tempoEspera += (tempoAtual - tempoChegada - duracao);
                }
            }
        }

        return new double[]{
            tempoResposta / num_processos, // Tempo médio de resposta
            tempoEspera / num_processos,   // Tempo médio de espera
            tempoRetorno / num_processos   // Tempo médio de retorno
        };
    }
}
