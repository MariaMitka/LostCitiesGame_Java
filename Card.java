package Model.cards;


public abstract class Card {

    private cardName name;
    private int vimata;
    private cardMonopati monopati;
    private int timh;

    public void setTimh(int timh){
        this.timh=timh;
    }
    public int getTimh(){
        return timh;
    }


    /**
     * Set the steps
     * @param vimata the number of steps
     * Pre-condition: the number of steps must be integer
     * Post-condition: */
    public void setVimata(int vimata){
        this.vimata=vimata;
    }
    /**
     * Gets the value of the steps
     * Pre-condition:
     * Post-condition: returns the steps a pawn takes*/
    public int getVimata(){
        return vimata;
    }
    /**
     * Set the name of the card
     * @param name
     * Pre-condition: name not null
     * Post-condition:
     */
    public void setName(cardName name) {
        this.name = name;
    }
    /**
     * return the name
     * @return the name of the card
     * Pre-condition:
     * Post-condition: returns the saved name of the card
     */
    public cardName getName() {
        return name;
    }
    public void setMonopati(cardMonopati monopati) {
        this.monopati = monopati;
    }
    public cardMonopati getMonopati() {
        return monopati;
    }
    public boolean isAnaktoro(){
        if(name.equals(cardName.Anaktoro)){
            return true;
        }
        else return false;
    }

    /**
     * Constructor
     * Pre-condition: number of steps, name of the card, the path that corresponds each card
     * @param vimata refers to steps
     * @param name refers to name of the card
     */
    public Card(int vimata, cardName name, cardMonopati monopati) {
        setVimata(vimata);
        setName(name);
        setMonopati(monopati);
    }

    public int getMonopatiInt() {
        switch (monopati) {
            case knossos: return 0;
            case malia: return 1;
            case phaistos: return 2;
            case zakros: return 3;
            default: return -1;
        }
    }

}
