public class Cliente implements Runnable {

    private int idCliente;
    Barbearia barbearia;

    public Cliente(Barbearia barbearia, int idCliente) {
        this.barbearia = barbearia;
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void run() { 
        barbearia.addCliente(this);
    }
}