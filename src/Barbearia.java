import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Barbearia {

    int qntCadeirasParaCorte, qntBarbeiros, qntbarbeirosLivres, qntMaximaCliente, qntMaxSofa, duracaoCorte, duracaoPagamento, qntMaxPagamento;
    List < Cliente > sofa;
    List < Cliente > clientesEmPe;
    ArrayList<Cliente> cadeirasParaCorte;
    ArrayList <Cliente> maquina;

    public Barbearia(int qntBarbeiros, int qntCadeirasParaCorte, int qntMaximaCliente, int qntMaxSofa, int duracaoCorte,int duracaoPagamento,int qntMaxPagamento) {
        this.duracaoCorte = duracaoCorte;
        this.qntMaxSofa = qntMaxSofa;
        this.qntMaxPagamento = qntMaxPagamento;
        this.qntMaximaCliente = qntMaximaCliente;
        this.duracaoPagamento = duracaoPagamento;
        //this.qntCadeirasParaCorte = qntCadeirasParaCorte;
        this.qntBarbeiros = qntBarbeiros;
        
        qntbarbeirosLivres = qntBarbeiros;
        
        sofa = new LinkedList < Cliente > ();
        clientesEmPe = new LinkedList < Cliente > ();
        cadeirasParaCorte = new ArrayList<Cliente>();
        maquina = new ArrayList<Cliente>();
    }

    public int trabalhar(Barbeiro barbeiro){
        int indexCadeiraAtualCliente = cortarCabelo(barbeiro);
        return indexCadeiraAtualCliente;
        
    }

    public void addCliente(Cliente cliente) {
        synchronized(sofa) {
            if (sofa.size() + clientesEmPe.size() == qntMaximaCliente) { 
                
                // Caso 
                // Esta na lotação maxima da barbearia
                // Então
                // O cliente vai embora
                System.out.println("\nCliente " + cliente.getIdCliente() + " foi embora porque a barbearia esta cheia");
            } else if (qntbarbeirosLivres > 0) { 

                // Caso 
                // Não esta na lotação maxima da barbearia
                // Tenha barbeiros livres
                // Então 
                // O cliente acorda o barbeiro que esta dormindo e começa a cortar o cabelo
                System.out.println("\nCliente " + cliente.getIdCliente() + " entrou");
                ((LinkedList < Cliente > ) sofa).offer(cliente);
                sofa.notify();
            } else if (sofa.size() < qntMaxSofa) { 

                // Caso 
                // Não esta na lotação maxima da barbearia
                // Não tenha barbeiros livres
                // Tenha espaço no sofá
                // Então 
                // O cliente senta no sofa
                System.out.println("\nCliente " + cliente.getIdCliente() + " entrou");
                ((LinkedList < Cliente > ) sofa).offer(cliente);
                System.out.println("Cliente " + cliente.getIdCliente() + " sentou no sofa");
                
                // if (sofa.size() == 1)
                //     sofa.notify();
            } else {

                synchronized(clientesEmPe) {
                // Caso 
                // Não esta na lotação maxima da barbearia
                // Não tenha barbeiros livres
                // Não enha espaço no sofá
                // Então 
                // O cliente fica em pé
                System.out.println("\nCliente " + cliente.getIdCliente() + " entrou");
                ((LinkedList < Cliente > ) clientesEmPe).offer(cliente);
                System.out.println("Cliente " + cliente.getIdCliente() + " ficou em pe");
                }
            }
        }
    }

    public int cortarCabelo(Barbeiro barbeiro) {
        Cliente proximoCliente, clienteVaiSofa;
        
        // Sofá é um recurso compartilhado plas threads, foi sincronizado para prevenir qualquer erro de acessos simutaneos
        synchronized(sofa) { 
            // Caso o sofa esteja vazio, o barbeiro vai dormir
            while (sofa.size() == 0) {

                System.out.println("\nBarbeiro " + barbeiro.getIdBarbeiro() + " esta dormindo na cadeira");
                
                try {
                    sofa.wait(); // barbeiro indo dormir
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            // Cliente acordando o barbeiro
            proximoCliente = (Cliente)((LinkedList < ? > ) sofa).poll();
            System.out.println("Cliente " + proximoCliente.getIdCliente() + " acordou o barbeiro " + barbeiro.getIdBarbeiro());
        }

        // O cliente a mais tempo em pé vai pro sofá
        synchronized(clientesEmPe) {
            if(clientesEmPe.size() > 1) {
                clienteVaiSofa = (Cliente)((LinkedList < ? > ) clientesEmPe).poll();
                ((LinkedList < Cliente > ) sofa).offer(clienteVaiSofa);
                System.out.println("Cliente " + clienteVaiSofa.getIdCliente() + " parou de ficar em pe e sentou no sofa");
            }
        }

        // Corta o cabelo
        int indexCadeiraAtualCliente;
        synchronized(cadeirasParaCorte) { 
            cadeirasParaCorte.add(proximoCliente);
            indexCadeiraAtualCliente = cadeirasParaCorte.indexOf(proximoCliente);
        }
        
        try {
            // diminui a quantidade de barbeiros livres
            qntbarbeirosLivres--;

            System.out.println("Barbeiro " + barbeiro.getIdBarbeiro() + " cortando cabelo do cliente " + cadeirasParaCorte.get(indexCadeiraAtualCliente).getIdCliente());

            // Duração do corte
            Thread.sleep(duracaoCorte);
            System.out.println("\nBareiro " + barbeiro.getIdBarbeiro() + " finalizou o corte do cliente " + cadeirasParaCorte.get(indexCadeiraAtualCliente).getIdCliente());

            cobrar(barbeiro, proximoCliente);

            // Adiciona quantidade de barbeiros livres
            qntbarbeirosLivres++;
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        
        return indexCadeiraAtualCliente;
    }

    public void cobrar(Barbeiro barbeiro,Cliente cliente) throws InterruptedException {
        synchronized(maquina){
                if(maquina.size() == 0){
                    maquina.add(cliente);
                    System.out.println("O barbeiro "+ barbeiro.getIdBarbeiro() +" esta cobrando o Cliente " + cliente.getIdCliente() + " esta pagando");
                    Thread.sleep(duracaoPagamento);
                    maquina.remove(cliente);
                }
        }
    }
}