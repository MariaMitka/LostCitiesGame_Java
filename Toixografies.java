package Model.evrimata;

public class Toixografies extends Evrimata {

    private int xaraktiristika;

    public Toixografies(int pontoi) {
        super(pontoi);
    }

    public Toixografies(int pontoi, int xaraktiristika) {
        super(pontoi);
        this.xaraktiristika = xaraktiristika;
    }

    public int getXaraktiristika() {
        return xaraktiristika;
    }
    public void setXaraktiristika(int xaraktiristika) {
        this.xaraktiristika = xaraktiristika;
    }
}
