package tarefa4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Monitor que controla o acesso aos garfos.
 * Implementa fairness usando uma fila FIFO.
 */
public class Mesa {

    private final int numFilosofos;
    private final boolean[] garfoOcupado;
    private final Queue<Integer> filaEspera = new LinkedList<>();

    public Mesa(int numFilosofos) {
        this.numFilosofos = numFilosofos;
        this.garfoOcupado = new boolean[numFilosofos];
    }

    public synchronized void pegarGarfos(int id) throws InterruptedException {
        filaEspera.add(id);
        log(id, "entrou na fila.");

        while (!podeComer(id)) {
            wait();
        }

        filaEspera.poll();
        garfoOcupado[id] = true;
        garfoOcupado[(id + 1) % numFilosofos] = true;

        log(id, "pegou os garfos e começou a comer.");
    }

    public synchronized void devolverGarfos(int id) {
        garfoOcupado[id] = false;
        garfoOcupado[(id + 1) % numFilosofos] = false;

        log(id, "liberou os garfos.");
        notifyAll();
    }

    private boolean podeComer(int id) {
        int esquerdo = id;
        int direito = (id + 1) % numFilosofos;

        return !filaEspera.isEmpty()
                && filaEspera.peek() == id
                && !garfoOcupado[esquerdo]
                && !garfoOcupado[direito];
    }

    private void log(int id, String msg) {
        System.out.println("[MESA] F" + (id + 1) + ": " + msg);
    }
}
