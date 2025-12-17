package tarefa2;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int quantidade = 5;
        List<Thread> threads = new ArrayList<>();
        Object[] garfos = new Object[quantidade];

        // Cria os garfos (recursos compartilhados)
        for (int i = 0; i < quantidade; i++) {
            garfos[i] = new Object();
        }

        for (int i = 0; i < quantidade; i++) {
            Object garfoEsquerdo = garfos[i];
            Object garfoDireito = garfos[(i + 1) % quantidade];

            // PREVENÇÃO DE DEADLOCK:
            // O último filósofo pega os garfos em ordem inversa aos outros.
            // Se todos pegam (Esq -> Dir), o último pega (Dir -> Esq).
            if (i == quantidade - 1) {
                threads.add(new Thread(new Filosofo("F" + (i + 1), garfoEsquerdo, garfoDireito, true)));
            } else {
                threads.add(new Thread(new Filosofo("F" + (i + 1), garfoEsquerdo, garfoDireito, false)));
            }
        }

        for (Thread t : threads) {
            t.start();
        }
    }
}