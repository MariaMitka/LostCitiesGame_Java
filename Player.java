package Model;

import Model.cards.Card;
import Model.evrimata.Agalmatakia;
import Model.evrimata.Evrimata;
import Model.pionia.Arxaiologos;
import Model.pionia.Pionia;

import java.util.ArrayList;
import java.util.List;

public class Player {


    private int PlayerID;
    private int TotalPoints;
    private Pionia[] pionia = new Pionia[4];
    private ArrayList<Evrimata> evrimata = new ArrayList<>();
    private List<Card> cards;
    private int[] valueLastCardPlayed;  // simvolizei thn timh ths teleutaias kartas pou paixthke apo ton paikth


    private void setPlayerID(int PlayerID) {
        this.PlayerID = PlayerID;
    }
    public int getPlayerID() {
        return PlayerID;
    }
    private void setTotalPoints(int TotalPoints) {
        this.TotalPoints = TotalPoints;
    }
    public int getTotalPoints() {
        return TotalPoints;
    }

    public Pionia getPioni(int monopati) {
        return pionia[monopati];
    }
    public void setPioni(int monopati, Pionia pioni) {
        pionia[monopati] = pioni;
    }
    public ArrayList<Evrimata> getEvrimata() {
        return evrimata;
    }
    public List<Card> getCards() {
        return cards;
    }

    public void addPoints(int pointsToAdd){
        this.setTotalPoints(this.getTotalPoints() + pointsToAdd);
    }
    public void addEvrimata(Evrimata evrimata){
        this.evrimata.add(evrimata);
    }
    public void addCard(Card card){
        this.cards.add(card);
    }
    public void removeCard(int index){
        this.cards.remove(index);
    }

    public int getValueLastCardPlayed(int monopati) {
        return valueLastCardPlayed[monopati];
    }
    public void setValueLastCardPlayed(int monopati, int timiKartas) {
        valueLastCardPlayed[monopati] = timiKartas;
    }

    //mhdenizei thn max timi gia tis kartes pou mporoun na paixtoun
    private void ZeroValueLastCardPlayed(){
        valueLastCardPlayed[0] = 0;
        valueLastCardPlayed[1] = 0;
        valueLastCardPlayed[2] = 0;
        valueLastCardPlayed[3] = 0;
    }


    public Player(int PlayerID) {
        System.out.println("Player create ID: " + PlayerID);
        this.PlayerID = PlayerID;
        this.TotalPoints = 0;
        this.evrimata = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.valueLastCardPlayed = new int[4];

        for (int i = 0; i < 4; i++) {
            this.pionia[i] = new Arxaiologos();
        }

        ZeroValueLastCardPlayed();
    }

    public int calculatePoints() {
        int points = 0;
        int agalmatakiaCounter = 0;
        for (Evrimata evrima : evrimata) {
            points += evrima.getPontoi();
            if(evrima instanceof Agalmatakia){
                agalmatakiaCounter++;
            }
        }

        switch (agalmatakiaCounter) {
            case 1:
                points = points - 20;
                break;
            case 2:
                points = points - 15;
                break;
            case 3:
                points += 10;
                break;
            case 4:
                points += 15;
                break;
            case 5:
                points += 30;
                break;
            case 6:
                points += 50;
                break;
            default:
                break;
        }

        for(Pionia pioni : pionia){
            int thesi = pioni.getThesi();
            int pointsThesis = 0;
            switch (thesi){
                case 0:
                    pointsThesis = -20;
                    break;
                case 1:
                    pointsThesis = -15;
                    break;
                case 2:
                    pointsThesis = -10;
                    break;
                case 3:
                    pointsThesis = 5;
                    break;
                case 4:
                    pointsThesis = 10;
                    break;
                case 5:
                    pointsThesis = 15;
                    break;
                case 6:
                    pointsThesis = 30;
                    break;
                case 7:
                    pointsThesis = 35;
                    break;
                case 8:
                    pointsThesis = 50;
                    break;
                default:
                    break;
            }

            if(pioni.isThiseas())
                points += 2 * pointsThesis;
            else
                points += pointsThesis;
        }
        setTotalPoints(points);
        return points;
    }

}

