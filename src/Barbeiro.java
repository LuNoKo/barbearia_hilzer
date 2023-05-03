public class Barbeiro implements Runnable { // initializing the barber

    private int idBarbeiro;
    Barbearia barbearia;
    

    public Barbeiro(Barbearia barbearia, int idBarbeiro) {
        this.barbearia = barbearia;
        this.idBarbeiro = idBarbeiro;
    }

    public int getIdBarbeiro() {
        return idBarbeiro;
    }

    public void run() {
        while (true) {
            barbearia.trabalhar(this);
        }
    }
}