package Model.pionia;

public abstract class Pionia {

    private int monopati;
    private int thesi;
    private boolean isThiseas;

    public void setMonopati(int monopati) {
        this.monopati = monopati;
    }
    public int getMonopati() {
        return monopati;
    }
    public void setThesi(int thesi) {
        this.thesi = thesi;
    }
    public int getThesi() {
        return thesi;
    }

    public boolean isThiseas() {
        return isThiseas;
    }
    public void setIsThiseas(boolean isThiseas) {
        this.isThiseas = isThiseas;
    }

    public void movesForward(int moves) {
        thesi += moves;
        if (thesi > 8) thesi = 8;
    }
    public void movesBackward() {
        if (thesi == -1) {
            // do nothing
        } else if(thesi < 2){
            thesi = 0;
        } else if (thesi < 6) {
            thesi = thesi -2;
        }
    }

    public Pionia(boolean isThiseas){
        setIsThiseas(isThiseas);
        setMonopati(-1);
        setThesi(-1);
    }

}





