import java.util.LinkedList;
import java.util.List;

import java.util.Queue;
public class RR implements Escalonamento {

    private int quantum;

    // Construtor que recebe o valor do quantum
    public RR(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public double[] calcular(List<Processo> processos) {
        int num_processos = processos.size();
        double tempoEspera = 0, tempoResposta = 0, tempoRetorno = 0;
        int tempoAtual = 0;

        
        int[] tempoRestante = new int[num_processos];
        boolean[] respondeu = new boolean[num_processos];  

        for (int i = 0; i < num_processos; i++) {
            tempoRestante[i] = processos.get(i).getDuracao();
        }

        
        Queue<Integer> fila = new LinkedList<>();
        int index = 0;  

        
        while (index < num_processos && processos.get(index).getChegada() <= tempoAtual) {
            fila.add(index);
            index++;
        }

       
        while (!fila.isEmpty()) {
            int idProcesso = fila.poll();

            
            if (!respondeu[idProcesso]) {
                tempoResposta += (tempoAtual - processos.get(idProcesso).getChegada());
                respondeu[idProcesso] = true;
            }

            // Executa o processo pelo quantum ou até o fim
            int tempoExecutado = Math.min(quantum, tempoRestante[idProcesso]);
            tempoAtual += tempoExecutado;
            tempoRestante[idProcesso] -= tempoExecutado;

            
            while (index < num_processos && processos.get(index).getChegada() <= tempoAtual) {
                fila.add(index);
                index++;
            }

            
            if (tempoRestante[idProcesso] > 0) {
                fila.add(idProcesso);
            } else {
                
                int tempoChegada = processos.get(idProcesso).getChegada();
                int duracao = processos.get(idProcesso).getDuracao();

                tempoRetorno += (tempoAtual - tempoChegada);
                tempoEspera += (tempoAtual - tempoChegada - duracao);
            }
        }

        
        return new double[] {
            tempoResposta / num_processos, 
            tempoEspera / num_processos,   
            tempoRetorno / num_processos   
        };
    }
}
