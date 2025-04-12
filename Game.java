package Controller;

import Model.Player;
import Model.cards.Card;

import java.util.List;
import java.util.Random;

public interface Game {
    /**@invariants players there are two players in the game*/
    Player[] players = new Player[2];



    /**Method to execute a round of the game
     * Pre-condition:
     * Post-condition: a single round is completed*/
    public default void Round(){
    }
    /**
     * method that shows what happens when a player plays a card*/
    public default void PlayCard(){
    }


    /**
     * The decision-making process of opening or destroyng a find
     * Pre-condition: The player has to take a decision
     * Post-condition: the box is opened or is destroyed or nothing */
    public default void DecisionForEvrima(){
    }
    /** Announces the winner of the game */
    public default void AnnounceWinner(){
    }
    public default Player getPlayer(int playerId){
        return players[playerId];
    }
}
