package Model;

import Model.evrimata.*;

import java.util.Random;

public class Tablo {

    Position[][] position = new Position[4][9];

    /**
     * Fills the path matrix with predefined values representing the points at each position
     * Pre-condition:
     * Post-condition: The paths are populated with predefined point values for each position
     */
    private void setPoints(){
        for (int i=0;i<4;i++){
            position[i][0].setPoints(-20);
            position[i][1].setPoints(-15);
            position[i][2].setPoints(-10);
            position[i][3].setPoints(5);
            position[i][4].setPoints(10);
            position[i][5].setPoints(15);
            position[i][6].setPoints(30);
            position[i][7].setPoints(35);
            position[i][8].setPoints(50);
        }
    }
    private void initializePositions() {
        // Initialize the position array with Position objects
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                position[i][j] = new Position();
            }
        }
    }

    /**
     * Fills the positions matrix for Evrima with predefined rules.
     * Randomly places rare and common Evrima positions, ensuring no overlap.
     * Pre-condition:
     * Post-condition: The Evrima positions matrix is filled with values representing rare, common, and empty positions.
     */
    private void filltheseisEvrimatwn() {
        Random random = new Random();
        int randomNumber;
        int thesi;

        //Topothetoume 4 Spania Evrimata
        for (int i = 0; i < 4; i++) {
            randomNumber = random.nextInt(5);
            thesi = converter(randomNumber);

            position[i][thesi].setHasEvrima(true);
            if(i == 2)
                position[i][thesi].setEvrimata(new Diskos());
            else
                position[i][thesi].setEvrimata(new otherSpania(i));

        }
        System.out.println("\t\tSpania OK");

        // Topothetoume 10 agalmatakia
        int sunolo = 0;  //metraei posa agalmata exoyme valei
        while (sunolo < 10) {
            int randomi = random.nextInt(4);
            int randomj = random.nextInt(5);
            randomj = converter(randomj);
            if (!position[randomi][randomj].getHasEvrima()) {
                sunolo++;
                position[randomi][randomj].setHasEvrima(true);
                position[randomi][randomj].setEvrimata(new Agalmatakia());
            }
        }
        System.out.println("\t\tAgalmata OK");

        // Topothetoume 6 toixografies
        sunolo = 0;
        while (sunolo < 6) {
            int randomi = random.nextInt(4);
            int randomj = random.nextInt(5);
            randomj = converter(randomj);
            if (position[randomi][randomj].getHasEvrima()) {
                continue;
            }
            sunolo++;
            position[randomi][randomj].setHasEvrima(true);
            if(sunolo < 3)
                position[randomi][randomj].setEvrimata(new ToixografiesA()); //20 pontoi
            else
                position[randomi][randomj].setEvrimata(new ToixografiesB()); //15 pontoi
        }
        System.out.println("\t\tToixografies OK");
    }

    /**Opens the box at the specified position and
     * resets the value at that position in the Evrima matrix.*/
    public int anoigwKouti(int i, int j){
        return 0;
    }
    /**Destroys the box at the specified position,
     *  resetting the position to a destroyed state*/
    public int destroyKouti(int i, int j){
        return 0;
    }

    public boolean getHasEvrima(int monopati, int thesi){
        return position[monopati][thesi].getHasEvrima();
    }
    public void setHasEvrima(int monopati, int thesi, boolean hasEvrima){
        position[monopati][thesi].setHasEvrima(hasEvrima);
    }
    public Position getPosition(int monopati, int thesi){
        return position[monopati][thesi];
    }

    /**Constructor
     * Pre-condition: The paths and finds are initialized
     * using fillMonopati() and filltheseisEvrimatwn() methods*/
    public Tablo(){
            System.out.println("Constructor Tablo ...");
        initializePositions();
        setPoints();
            System.out.println("\tfillTheseisEvrimatwn ...");
        filltheseisEvrimatwn();
            System.out.println("\tfillTheseisEvrimatwn OK");
            System.out.println("Constructor Tablo OK");
    }

    private int converter(int random){
        // metatrepei ton random arithmo apo 0 ews 4 sthn antistoixh thesh sto tablo pou periexei evrima
        int thesi;
        switch (random) {
            case 0:
                thesi = 1;
                break;
            case 1:
                thesi = 3;
                break;
            case 2:
                thesi = 5;
                break;
            case 3:
                thesi = 7;
                break;
            case 4:
                thesi = 8;
                break;
            default:
                thesi = -1;
                break;
        }
        return thesi;
    }
}
