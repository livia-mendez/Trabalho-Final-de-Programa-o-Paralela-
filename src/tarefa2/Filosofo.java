package tarefa2;
import java.util.Random;

public class Filosofo implements Runnable {

    private final String nome;
    private final Object garfoEsquerdo;
    private final Object garfoDireito;
    private final boolean inverterOrdem; 
    private final Random random = new Random();

    // ESTE É O CONSTRUTOR QUE O MAIN ESTÁ PROCURANDO:
    public Filosofo(String nome, Object garfoEsquerdo, Object garfoDireito, boolean inverterOrdem) {
        this.nome = nome;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
        this.inverterOrdem = inverterOrdem;
    }

    @Override
    public void run() {
        try {
            for (int turno = 1; turno <= 10; turno++) {
                pensar();
                if (inverterOrdem) {
                    tentarComer(garfoDireito, garfoEsquerdo, turno);
                } else {
                    tentarComer(garfoEsquerdo, garfoDireito, turno);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filosofo " + nome + " está pensando...");
        Thread.sleep(random.nextInt(1000));
    }

    private void tentarComer(Object primeiroGarfo, Object segundoGarfo, int turno) throws InterruptedException {
        synchronized (primeiroGarfo) {
            System.out.println("Filosofo " + nome + " pegou o PRIMEIRO garfo.");
            synchronized (segundoGarfo) {
                System.out.println("Filosofo " + nome + " pegou o SEGUNDO garfo e está jantando (Turno " + turno + ").");
                Thread.sleep(random.nextInt(1000, 2000)); 
                System.out.println("Filosofo " + nome + " terminou de comer e soltou os garfos.");
            }
        }
    }
}