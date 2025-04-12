package Model.evrimata;

public class otherSpania extends Spania {

    private int monopati;

    public void setMonopati(int monopati) {
        this.monopati = monopati;
    }
    public int getMonopati() {
        return monopati;
    }

    public otherSpania(int monopati) {
        super(25);
        setMonopati(monopati);
    }
}
