package Model.evrimata;

public abstract class Evrimata {

    int pontoi;

    public int getPontoi() {
        return pontoi;
    }
    public void setPontoi(int pontoi) {
        this.pontoi = pontoi;
    }

    public Evrimata(int pontoi) {
        setPontoi(pontoi);
    }
}
