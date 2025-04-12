package Model;

import Model.evrimata.Evrimata;

public class Position {

    private int column;
    private int row;
    private int points;
    private Evrimata evrima;
    private boolean hasEvrima;

    public Position() {
        setEvrimata(null);
        setHasEvrima(false);
    }


    public void setColumn(int column) {
        this.column = column;
    }
    public int getColumn() {
        return column;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getRow() {
        return row;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getPoints() {
        return points;
    }
    public void setEvrimata(Evrimata evrimata) {
        this.evrima = evrimata;
        setHasEvrima(true);
    }
    public Evrimata getEvrimata() {
        return evrima;
    }
    public void setHasEvrima(boolean hasEvrima) {
        this.hasEvrima = hasEvrima;
    }
    public boolean getHasEvrima() {
        return hasEvrima;
    }



}

