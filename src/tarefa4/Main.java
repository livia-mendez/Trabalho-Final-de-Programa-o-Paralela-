package tarefa4;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal responsável por iniciar o jantar
 * e exibir estatísticas finais.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        final int NUM_FILOSOFOS = 5;
        final long TEMPO_EXECUCAO = 120_000; // 2 minutos

        Mesa mesa = new Mesa(NUM_FILOSOFOS);

        List<Filosofo> filosofos = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        System.out.println("Iniciando jantar com monitor e fairness...\n");

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Filosofo f = new Filosofo(i, "F" + (i + 1), mesa);
            filosofos.add(f);

            Thread t = new Thread(f);
            threads.add(t);
            t.start();
        }

        Thread.sleep(TEMPO_EXECUCAO);

        System.out.println("\nEncerrando execução...");
        for (Thread t : threads) {
            t.interrupt();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\n=== ESTATÍSTICAS FINAIS ===");
        for (Filosofo f : filosofos) {
            System.out.println(
                "Filósofo " + f.getNome() +
                " comeu " + f.getRefeicoes() + " vezes."
            );
        }
    }
}
