
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    // Constantes de configuração
    private static final int intervaloGeradorCliente = 100;
    private static final int duracaoCorte = 1000;
    private static final int qntClientesASerAtendido = 100; 
    private static final int qntBarbeiros = 3;
    private static final int qntCadeirasParaCorte = 3;
    private static final int qntMaxCliente = 20;
    private static final int qntMaxSofa = 4;
<<<<<<< HEAD
    private static final int duracaoPagamento = 1000;
    private static final int qntMaxPagamento = 1;
=======
>>>>>>> 15a529cbfbf654708a614752e5659610baf74b4b

    public static void main(String a[]) throws InterruptedException {

        // Inicializando com 12 threads que é multiplo de cores da CPU (6 cores aqui)
        ExecutorService exec = Executors.newFixedThreadPool(12);
        
        // Inicia a barbearia
<<<<<<< HEAD
        Barbearia barbearia = new Barbearia(qntBarbeiros, qntCadeirasParaCorte, qntMaxCliente, qntMaxSofa, duracaoCorte, duracaoPagamento, qntMaxPagamento);
=======
        Barbearia barbearia = new Barbearia(qntBarbeiros, qntCadeirasParaCorte, qntMaxCliente, qntMaxSofa, duracaoCorte);
>>>>>>> 15a529cbfbf654708a614752e5659610baf74b4b
        
        System.out.println("\nA barbearia abriu com " + qntBarbeiros + " barbeiros(s)\n");
        
        // Gerador de barbeiros
        for (int i = 1; i <= qntBarbeiros; i++) { //generating the specified number of threads for barber
            Barbeiro barbeiro = new Barbeiro(barbearia, i);
            Thread thBarbeiro = new Thread(barbeiro);
            exec.execute(thBarbeiro);
        }
        
        // Gerador de clientes
        for (int i = 1; i < qntClientesASerAtendido; i++) {
            Cliente cliente = new Cliente(barbearia, i);
            Thread thCliente = new Thread(cliente);
            exec.execute(thCliente);
            Thread.sleep(intervaloGeradorCliente);
        }
        
        exec.shutdown(); // Derruba o serviço de execução e liberando os recursos
        
        System.out.println("\nA barbearia fechou");
    }
}





