package Controller;

import Model.Player;
import Model.Tablo;
import Model.cards.*;
import Model.evrimata.*;
import Model.pionia.Pionia;
import Model.pionia.Thiseas;
import View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Controller implements Game {
    View view = new View();

    private List<Card> deck;
    int turnPlayer;
    Player[] players = new Player[2];

    boolean olakala;

    public void PaizeiPrwtos(){
        Random random = new Random();
        turnPlayer = random.nextInt(2);
        if (turnPlayer == 0){
            System.out.println("PLayer 1 will play first");
        }else{
            System.out.println("PLayer 2 will play first");

        }
    }
    public void poiosPaizei(){
        if (turnPlayer == 0){
            JOptionPane.showMessageDialog(null,"Top Player's turn",
                    "Game", JOptionPane.PLAIN_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null,"Bottom Player's turn",
                "Game", JOptionPane.PLAIN_MESSAGE);
    }
    public Player getCurrentPlayer(){
        return players[turnPlayer];
    }
    public Player getNotCurrentPlayer(){
        return players[getNotTurnPlayer()];
    }
    public void changeTurn(){
        if (turnPlayer == 1){
            turnPlayer = 0;
            }
        else{ turnPlayer=1;
        }
    }
    public int getTurnPlayer() {
        return turnPlayer;
    }
    public int getNotTurnPlayer() {
        if(turnPlayer == 0){
            return 1;
        }
        else{
            return 0;
        }
    }

    public void createDeck(){
        deck = new ArrayList<Card>();
        Card karta = null;

        //Kartes Anaktoro
        for (int i = 0; i < 20; i++) {
            karta = new CardAnaktoro((i+2)/2, cardMonopati.knossos);
            deck.add(karta);
            karta = new CardAnaktoro((i+2)/2, cardMonopati.malia);
            deck.add(karta);
            karta = new CardAnaktoro((i+2)/2, cardMonopati.phaistos);
            deck.add(karta);
            karta = new CardAnaktoro((i+2)/2, cardMonopati.zakros);
            deck.add(karta);
        }

        //kartes Ariadni
        for (int i = 0; i < 3; i++) {
            karta = new CardAriadni(cardMonopati.knossos);
            deck.add(karta);
            karta = new CardAriadni(cardMonopati.malia);
            deck.add(karta);
            karta = new CardAriadni(cardMonopati.phaistos);
            deck.add(karta);
            karta = new CardAriadni(cardMonopati.zakros);
            deck.add(karta);
        }

        //kartes Minot
        for (int i = 0; i < 2; i++) {
            karta = new CardMinot(cardMonopati.knossos);
            deck.add(karta);
            karta = new CardMinot(cardMonopati.malia);
            deck.add(karta);
            karta = new CardMinot(cardMonopati.phaistos);
            deck.add(karta);
            karta = new CardMinot(cardMonopati.zakros);
            deck.add(karta);
        }
        Collections.shuffle(deck, new Random());
    }
    public void giveCards(){
        while(players[turnPlayer].getCards().size() < 8) {
            players[turnPlayer].addCard(deck.removeFirst());
        }
        while(players[getNotTurnPlayer()].getCards().size() < 8) {
            players[getNotTurnPlayer()].addCard(deck.removeFirst());
        }
    }

    public boolean playCard(int thesiKartas){
        Card card = players[turnPlayer].getCards().get(thesiKartas);

        switch (card.getName()) {
            case Anaktoro:
                return playAnaktoro((CardAnaktoro) card);
            case Ariadni:
                return playAriadni((CardAriadni) card);
            case Minot:
                return playMinot((CardMinot) card);
            default:
                System.out.println("Invalid card name");
                return false;
        }
    }
    public boolean playAnaktoro(CardAnaktoro card){
        if(card.getTimh() < players[turnPlayer].getValueLastCardPlayed(card.getMonopatiInt())){
            JOptionPane.showMessageDialog(null,"Card cannot be played:\nCard's Power: " + card.getTimh() + "\nLast Played: " + players[turnPlayer].getValueLastCardPlayed(card.getMonopatiInt()),
                    "Game", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
        else{
            //ean den yparxei thiseas
            if(!players[turnPlayer].getPioni(0).isThiseas()
                    && !players[turnPlayer].getPioni(1).isThiseas()
                    && !players[turnPlayer].getPioni(2).isThiseas()
                    && !players[turnPlayer].getPioni(3).isThiseas()) {
                // kai ean prwth fora paizei auto to pioni
                if (players[turnPlayer].getPioni(card.getMonopatiInt()).getThesi() == -1) {
                    JDialog dialogPioni = new JDialog((Frame) null, "Thiseus/Arch", true);
                    dialogPioni.setSize(300, 150);
                    dialogPioni.setLayout(new FlowLayout());

                    // Add a JLabel and a JTextField
                    JLabel label = new JLabel("Do you want this Pawn to be Theseus or an Arch?");
                    dialogPioni.add(label);
                    // Add buttons
                    JButton theseusButton = new JButton("Theseus");
                    JButton archButton = new JButton("Arch");
                    dialogPioni.add(theseusButton);
                    dialogPioni.add(archButton);

                    // Add action listener to the Theseus button
                    theseusButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Pionia thiseas = new Thiseas();
                            players[turnPlayer].setPioni(card.getMonopatiInt(), thiseas);
                            dialogPioni.dispose(); // Close the dialog
                        }
                    });

                    archButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialogPioni.dispose(); // Close the dialog
                        }
                    });

                    // Set dialog properties and make it visible
                    dialogPioni.setLocationRelativeTo(null); // Center the dialog
                    dialogPioni.setVisible(true);
                }
            }
            players[turnPlayer].getPioni(card.getMonopatiInt()).movesForward(1);
            players[turnPlayer].setValueLastCardPlayed(card.getMonopatiInt() ,card.getTimh());
            return true;
        }
    }
    public boolean playAriadni(CardAriadni card){
        int monopatiKartas = card.getMonopatiInt();
        players[turnPlayer].getPioni(monopatiKartas).movesForward(2);
        return true;
    }
    public boolean playMinot(CardMinot card){
        int monopatiKartas = card.getMonopatiInt();
        players[turnPlayer].getPioni(monopatiKartas).movesBackward();

        return true;
    }

    public void discard(int thesiKartas){
        players[turnPlayer].removeCard(thesiKartas);
    }

    public boolean drawCard(){
        if(players[turnPlayer].getCards().size() < 8) {
            players[turnPlayer].addCard(deck.removeFirst());
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null,"Player has too many cards to draw!",
                    "Game", JOptionPane.PLAIN_MESSAGE);
            return false;
        }

    }

    public void checkIfgameEnd(){
        int pawnsCP = 0;
        for (int playerIndex = 0; playerIndex < players.length; playerIndex++) {
            for(int pawnIndex = 0; pawnIndex < 4; pawnIndex++){
                if(players[playerIndex].getPioni(pawnIndex).getThesi() >= 6)
                    pawnsCP++;
            }
        }
        if(pawnsCP == 4 || deck.isEmpty()){
            // END OF GAME
            // calculate winner
            int pointsTop = players[0].calculatePoints();
            int pointsBottom = players[1].calculatePoints();
            if(pointsTop > pointsBottom){
                JOptionPane.showMessageDialog(null,"Top Player Won\nTop Points: " + pointsTop + "\nBottom Points: " + pointsBottom,
                        "Game End", JOptionPane.PLAIN_MESSAGE);
            } else if (pointsTop == pointsBottom) {
                JOptionPane.showMessageDialog(null,"It was a Draw\nTop Points: " + pointsTop + "\nBottom Points: " + pointsBottom,
                        "Game End", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,"Bottom Player Won\nTop Points: " + pointsTop + "\nBottom Points: " + pointsBottom,
                        "Game End", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public Controller(){
        // Start of the game
        Tablo tablo = new Tablo();
        players = new Player[2];
        players[0] = new Player(0);
        players[1] = new Player(1);
        deck = new LinkedList<Card>();
        olakala = false;

        createDeck();
        updateDeck();

        PaizeiPrwtos();
        poiosPaizei();

        giveCards();
        updateView();


        // Add ActionListener
        view.drawCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("drawButton clicked");
                if( drawCard() ) {
                    allButtonsDisabled();
                    view.endTurnButton.setEnabled(true);
                }
                updateView();
                checkIfgameEnd();

            }
        });

        view.endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("endTurnButton clicked");
                changeTurn();
                poiosPaizei();
                updateView();
                checkIfgameEnd();
                allButtonsEnabled();
                view.endTurnButton.setEnabled(false);
            }
        });

        view.playCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("playCardButton clicked");
                JDialog dialog = new JDialog((Frame) null, "Which card you want to Play", true);
                dialog.setSize(300, 150);
                dialog.setLayout(new FlowLayout());

                // Add a JLabel and a JTextField
                JLabel label = new JLabel("Enter a number 1-8:");
                JTextField textField = new JTextField(10);
                dialog.add(label);
                dialog.add(textField);
                // Add OK button
                JButton okButton = new JButton("OK");
                dialog.add(okButton);

                int userNumber = 0;
                olakala = false;

                // Add action listener to the OK button
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            // Parse the number entered by the user
                            int userNumber = Integer.parseInt(textField.getText());
                            if(userNumber < 1 || userNumber > 8){
                                throw new IllegalArgumentException("Invalid user number, must be between 1 and 8");

                            }
                            System.out.println("User entered number: " + userNumber);
                            dialog.dispose(); // Close the dialog
                            int sumOfCards = players[turnPlayer].getCards().size();
                            if(userNumber <= sumOfCards){
                                Card card = players[turnPlayer].getCards().get(userNumber - 1);
                                if (card.isAnaktoro()){
                                    if(playAnaktoro((CardAnaktoro) card)){
                                        olakala = true;
                                        discard(userNumber - 1);
                                        anaskafi(tablo, card.getMonopatiInt());

                                    }
                                    else{
                                        System.out.println("PlayAnaktoro False???");
                                    }

                                }
                                else if(card.getName().equals(cardName.Ariadni)){
                                    if(players[turnPlayer].getPioni(card.getMonopatiInt()).getThesi() == -1){
                                        JOptionPane.showMessageDialog(null,"Δε μπορεί να ξεκινήσει μονοπάτι με κάρτα Μίτου Αριάδνης",
                                                "Game", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    else{
                                        players[turnPlayer].getPioni(card.getMonopatiInt()).movesForward(2);

                                        anaskafi(tablo, card.getMonopatiInt());
                                        anaskafiAriadnis(tablo, card.getMonopatiInt());

                                        olakala = true;
                                        discard(userNumber - 1);
                                    }
                                }
                                else if(card.getName().equals(cardName.Minot)){
                                    if (players[getNotTurnPlayer()].getPioni(card.getMonopatiInt()).isThiseas()){
                                        JOptionPane.showMessageDialog(null,"You played Minotaur on enemy Theseus xD",
                                                "Game", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    else {
                                        players[getNotTurnPlayer()].getPioni(card.getMonopatiInt()).movesBackward();
                                    }
                                    olakala = true;
                                    discard(userNumber - 1);
                                }
                            }
                            System.out.println("Card Played successful");
                            updateView();
                        } catch (NumberFormatException ex) {
                            // Show error message if input is not a valid number
                            JOptionPane.showMessageDialog(dialog, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (IllegalArgumentException ex) {
                            // Show error message if number is out of range
                            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        checkIfgameEnd();
                    }
                });

                // Set dialog properties and make it visible
                dialog.setLocationRelativeTo(null); // Center the dialog
                dialog.setVisible(true);

                if(olakala){
                    allButtonsDisabled();
                    view.drawCardButton.setEnabled(true);
                }
            }
        });

        view.discardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("discardButton clicked");

                olakala = false;

                JDialog dialog = new JDialog((Frame) null, "Which card you want to discard", true);
                dialog.setSize(300, 150);
                dialog.setLayout(new FlowLayout());

                // Add a JLabel and a JTextField
                JLabel label = new JLabel("Enter a number 1-8:");
                JTextField textField = new JTextField(10);
                dialog.add(label);
                dialog.add(textField);
                // Add OK button
                JButton okButton = new JButton("OK");
                dialog.add(okButton);

                int userNumber = 0;

                // Add action listener to the OK button
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            // Parse the number entered by the user
                            int userNumber = Integer.parseInt(textField.getText());
                            System.out.println("User entered number: " + userNumber);
                            dialog.dispose(); // Close the dialog
                            int sumOfCards = players[turnPlayer].getCards().size();
                            if(userNumber > 0 && userNumber <= sumOfCards){
                                discard(userNumber-1);
                                System.out.println("Discard successful");
                                olakala = true;
                            }
                            else{
                                throw new IllegalArgumentException("Invalid user number, must be between 1 and 8");
                            }
                            updateView();
                        } catch (NumberFormatException ex) {
                            // Show error message if input is not a valid number
                            JOptionPane.showMessageDialog(dialog, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        catch (IllegalArgumentException ex) {
                            // Show error message if number is out of range
                            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                // Set dialog properties and make it visible
                dialog.setLocationRelativeTo(null); // Center the dialog
                dialog.setVisible(true);
                if(olakala){
                    allButtonsDisabled();
                    view.drawCardButton.setEnabled(true);
                }
            }
        });

        view.topShowTreasure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("topShowTreasure clicked");
                StringBuilder stringBuilder = new StringBuilder();
                for(Evrimata evrima : players[0].getEvrimata()){
                    if(evrima instanceof Diskos){
                        stringBuilder.append("<br>Diskos: 35 pts");
                    } else if (evrima instanceof otherSpania) {
                        switch (((otherSpania) evrima).getMonopati()){
                            case 0:
                                stringBuilder.append("<br>Δαχτυλίδι του Μίνωα: 25 pts");
                                break;
                            case 1:
                                stringBuilder.append("<br>Κόσμημα Μαλίων: 25 pts");
                                break;
                            case 3:
                                stringBuilder.append("<br>Ρυτό Ζάκρου: 25 pts");
                                break;
                            default:
                                stringBuilder.append("<br>:O wow");
                                break;
                        }
                    } else if (evrima instanceof Agalmatakia) {
                        stringBuilder.append("<br>Agalmataki: 0 pts");
                    } else if (evrima instanceof ToixografiesA) {
                        stringBuilder.append("<br>Toixografia A: 20 pts");
                    } else if (evrima instanceof ToixografiesB) {
                        stringBuilder.append("<br>Toixografia B: 15 pts");
                    }
                }
                JOptionPane.showMessageDialog(null,"<html>" + stringBuilder.toString() + "<html>", "Treasures", JOptionPane.PLAIN_MESSAGE);
            }
        });
        view.bottomShowTreasure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("topShowTreasure clicked");
                StringBuilder stringBuilder = new StringBuilder();
                for(Evrimata evrima : players[1].getEvrimata()){
                    if(evrima instanceof Diskos){
                        stringBuilder.append("<br>Diskos: 35 pts");
                    } else if (evrima instanceof otherSpania) {
                        stringBuilder.append("<br>Spanio: 25 pts");
                    } else if (evrima instanceof Agalmatakia) {
                        stringBuilder.append("<br>Agalmataki: 0 pts");
                    } else if (evrima instanceof ToixografiesA) {
                        stringBuilder.append("<br>Toixografia A: 20 pts");
                    } else if (evrima instanceof ToixografiesB) {
                        stringBuilder.append("<br>Toixografia B: 15 pts");
                    }
                }
                JOptionPane.showMessageDialog(null,"<html>" + stringBuilder.toString() + "<html>", "Treasures", JOptionPane.PLAIN_MESSAGE);
            }
        });
        view.topShowToixografies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("topShowTreasure clicked");
                // Create a panel to hold multiple images
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout()); // Arrange images in a row

                ImageIcon[] imageArray = new ImageIcon[6];
                imageArray[0] = new ImageIcon("images/findings/fresco1_20.jpg");
                imageArray[1] = new ImageIcon("images/findings/fresco2_20.jpg");
                imageArray[2] = new ImageIcon("images/findings/fresco3_15.jpg");
                imageArray[3] = new ImageIcon("images/findings/fresco4_20.jpg");
                imageArray[4] = new ImageIcon("images/findings/fresco5_15.jpg");
                imageArray[5] = new ImageIcon("images/findings/fresco6_15.jpg");
                JLabel[] labelArray = new JLabel[6];
                labelArray[0] = new JLabel(imageArray[0]);
                labelArray[1] = new JLabel(imageArray[1]);
                labelArray[2] = new JLabel(imageArray[2]);
                labelArray[3] = new JLabel(imageArray[3]);
                labelArray[4] = new JLabel(imageArray[4]);
                labelArray[5] = new JLabel(imageArray[5]);

                int counter = 0;
                for(Evrimata evrima : players[0].getEvrimata()){
                    if (evrima instanceof Toixografies) {
                        panel.add(labelArray[counter]);
                        counter++;
                    }
                }
                // Display the dialog with the panel
                JOptionPane.showMessageDialog(null, panel, "Toixografies", JOptionPane.PLAIN_MESSAGE);
            }
        });
        view.bottomShowToixografies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("topShowTreasure clicked");
                // Create a panel to hold multiple images
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout()); // Arrange images in a row

                ImageIcon[] imageArray = new ImageIcon[6];
                imageArray[0] = new ImageIcon("images/findings/fresco1_20.jpg");
                imageArray[1] = new ImageIcon("images/findings/fresco2_20.jpg");
                imageArray[2] = new ImageIcon("images/findings/fresco3_15.jpg");
                imageArray[3] = new ImageIcon("images/findings/fresco4_20.jpg");
                imageArray[4] = new ImageIcon("images/findings/fresco5_15.jpg");
                imageArray[5] = new ImageIcon("images/findings/fresco6_15.jpg");
                JLabel[] labelArray = new JLabel[6];
                labelArray[0] = new JLabel(imageArray[0]);
                labelArray[1] = new JLabel(imageArray[1]);
                labelArray[2] = new JLabel(imageArray[2]);
                labelArray[3] = new JLabel(imageArray[3]);
                labelArray[4] = new JLabel(imageArray[4]);
                labelArray[5] = new JLabel(imageArray[5]);

                int counter = 0;
                for(Evrimata evrima : players[1].getEvrimata()){
                    if (evrima instanceof Toixografies) {
                        panel.add(labelArray[counter]);
                        counter++;
                    }
                }
                // Display the dialog with the panel
                JOptionPane.showMessageDialog(null, panel, "Toixografies", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public void updateView(){
        updateDeck();
        updateCardImg();
        updatePalace();
        updateInfobox();

    }
    public void updateInfobox(){
        String info = "";
        info += "<br>Top-Knossos:"      + (1 + players[0].getPioni(0).getThesi());
        info += "<br>Top-Malia:"        + (1 + players[0].getPioni(1).getThesi());
        info += "<br>Top-Phaistos:"     + (1 + players[0].getPioni(2).getThesi());
        info += "<br>Top-Zakros:"       + (1 + players[0].getPioni(3).getThesi());

        info += "<br>Bottom-Knossos: "  + (1 + players[1].getPioni(0).getThesi());
        info += "<br>Bottom-Malia: "    + (1 + players[1].getPioni(1).getThesi());
        info += "<br>Bottom-Phaistos: " + (1 + players[1].getPioni(2).getThesi());
        info += "<br>Bottom-Zakros: "   + (1 + players[1].getPioni(3).getThesi());

        info += "<br>Top Points: " + players[0].calculatePoints();
        info += "<br>Bottom Points: " + players[1].calculatePoints();

        view.centerSouthLabel.setText("<html>"+info+"</html>");
    }
    public void updatePlayedCards(){
        for(int i = 0; i < 4; i++) {
            view.topPalace[i].setText("Power: " + players[0].getValueLastCardPlayed(i));
            view.bottomPalace[i].setText("Power: " + players[1].getValueLastCardPlayed(i));
        }
    }
    public void updatePalace(){

        //ksossos
        int power = players[0].getValueLastCardPlayed(0);
        if(power != 0){
            ImageIcon imageIcon = new ImageIcon("images/cards/knossos" + power + ".jpg");
            Image image = imageIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
            view.topPalace[0].setIcon(new ImageIcon(image));
        }
        power = players[1].getValueLastCardPlayed(0);
        if(power != 0){
            ImageIcon imageIcon = new ImageIcon("images/cards/knossos" + power + ".jpg");
            Image image = imageIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
            view.bottomPalace[0].setIcon(new ImageIcon(image));
        }
        //malia
        power = players[0].getValueLastCardPlayed(1);
        if(power != 0){
            ImageIcon imageIcon = new ImageIcon("images/cards/malia" + power + ".jpg");
            Image image = imageIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
            view.topPalace[1].setIcon(new ImageIcon(image));
        }
        power = players[1].getValueLastCardPlayed(1);
        if(power != 0){
            ImageIcon imageIcon = new ImageIcon("images/cards/malia" + power + ".jpg");
            Image image = imageIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
            view.bottomPalace[1].setIcon(new ImageIcon(image));
        }
        //phaistos
        power = players[0].getValueLastCardPlayed(2);
        if(power != 0){
            ImageIcon imageIcon = new ImageIcon("images/cards/phaistos" + power + ".jpg");
            Image image = imageIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
            view.topPalace[2].setIcon(new ImageIcon(image));
        }
        power = players[1].getValueLastCardPlayed(2);
        if(power != 0){
            ImageIcon imageIcon = new ImageIcon("images/cards/phaistos" + power + ".jpg");
            Image image = imageIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
            view.bottomPalace[2].setIcon(new ImageIcon(image));
        }
        //zakros
        power = players[0].getValueLastCardPlayed(3);
        if(power != 0){
            ImageIcon imageIcon = new ImageIcon("images/cards/zakros" + power + ".jpg");
            Image image = imageIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
            view.topPalace[3].setIcon(new ImageIcon(image));
        }
        power = players[1].getValueLastCardPlayed(3);
        if(power != 0){
            ImageIcon imageIcon = new ImageIcon("images/cards/zakros" + power + ".jpg");
            Image image = imageIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
            view.bottomPalace[3].setIcon(new ImageIcon(image));
        }
    }
    public void updateCards(){

        // Top player's cards
        int sizeOfHand =  players[0].getCards().size();
        List<Card> cards = players[0].getCards();
        int i = 0;

        while(i < sizeOfHand && i < 8){
            if(cards.get(i).isAnaktoro()){
                view.cardLabelsTop[i].setText("<html>Type: " + cards.get(i).getName() + "\n Monopati: " + (1+cards.get(i).getMonopatiInt()) + "\nDynami: " + cards.get(i).getTimh() + "\n Vimata: " + cards.get(i).getVimata() + "<html>");
            }
            else
                view.cardLabelsTop[i].setText("<html>Type: " + cards.get(i).getName() + "\n Monopati: " + (1+cards.get(i).getMonopatiInt()) + "\n Vimata: " + cards.get(i).getVimata() + "<html>");
            i++;
        }
        while (sizeOfHand < 8){
            view.cardLabelsTop[i].setText("<html>Card " + (i+1)  + "<html>");
            sizeOfHand++;
        }

        // Bottom player's cards
        cards = players[1].getCards();
        sizeOfHand = players[1].getCards().size();
        i = 0;
        while(i < sizeOfHand && i < 8){
            if(cards.get(i).isAnaktoro()){
                view.cardLabelsBottom[i].setText("<html>Type: " + cards.get(i).getName() + "\n Monopati: " + (1+cards.get(i).getMonopatiInt()) + "\nDynami: " + cards.get(i).getTimh() + "\n Vimata: " + cards.get(i).getVimata() + "<html>");
            }
            else
                view.cardLabelsBottom[i].setText("<html>Type: " + cards.get(i).getName() + "\n Monopati: " + (1+cards.get(i).getMonopatiInt()) + "\n Vimata: " + cards.get(i).getVimata()+"<html>");
            i++;
        }
        while (sizeOfHand < 8){
            view.cardLabelsBottom[i].setText("<html>Card " + (i+1) + "<html>");
            sizeOfHand++;
        }
    }
    public void updateCardImg() {
        for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
            List<Card> cards = players[playerIndex].getCards();
            int monopati;
            int index = 0;
            StringBuilder fileName = new StringBuilder();
            for (Card card : cards) {
                monopati = card.getMonopatiInt();
                switch (monopati) {
                    case 0:
                        fileName = new StringBuilder("knossos");
                        break;
                    case 1:
                        fileName = new StringBuilder("malia");
                        break;
                    case 2:
                        fileName = new StringBuilder("phaistos");
                        break;
                    case 3:
                        fileName = new StringBuilder("zakros");
                        break;
                }
                if (card.isAnaktoro()) {
                    fileName.append(card.getTimh());
                } else if (card.getName().equals(cardName.Ariadni)) {
                    fileName.append("Ari");
                } else {
                    fileName.append("Min");
                }

                ImageIcon imageIcon = new ImageIcon("images/cards/" + fileName.toString() + ".jpg");
                Image image = imageIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
                if(playerIndex == 0){
                    view.cardLabelsTop[index].setIcon(new ImageIcon(image));
                }
                else {
                    view.cardLabelsBottom[index].setIcon(new ImageIcon(image));
                }
                index++;
            }
            while(index < 8){
                if(playerIndex == 0){
                    view.cardLabelsTop[index].setIcon(null);
                    view.cardLabelsTop[index].setText("Card " + (index+1));
                }
                else {
                    view.cardLabelsBottom[index].setIcon(null);
                    view.cardLabelsBottom[index].setText("Card " + (index+1));
                }
                index++;
            }
        }
    }

    public void allButtonsEnabled() {
        view.drawCardButton.setEnabled(true);
        view.playCardButton.setEnabled(true);
        view.discardButton.setEnabled(true);
        view.endTurnButton.setEnabled(false);
    }
    public void allButtonsDisabled() {
        view.drawCardButton.setEnabled(false);
        view.playCardButton.setEnabled(false);
        view.discardButton.setEnabled(false);
        view.endTurnButton.setEnabled(false);
    }

    public void anaskafi(Tablo tablo, int monopati){
        int thesi = players[turnPlayer].getPioni(monopati).getThesi();
        if(tablo.getHasEvrima(monopati, thesi)){
            if(players[turnPlayer].getPioni(monopati).isThiseas()){
                JDialog dialogDestroy = new JDialog((Frame) null, "Destroy Treasure?", true);
                dialogDestroy.setSize(300, 150);
                dialogDestroy.setLayout(new FlowLayout());

                // Add a JLabel and a JTextField
                JLabel label = new JLabel("Do you want to destroy this Treasure?");
                dialogDestroy.add(label);
                // Add buttons
                JButton yesButton = new JButton("Yes");
                JButton noButton = new JButton("No");
                dialogDestroy.add(yesButton);
                dialogDestroy.add(noButton);

                // Add action listener to the Theseus button
                yesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        tablo.setHasEvrima(monopati, thesi, false);

                        dialogDestroy.dispose(); // Close the dialog
                    }
                });

                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialogDestroy.dispose(); // Close the dialog
                    }
                });

                // Set dialog properties and make it visible
                dialogDestroy.setLocationRelativeTo(null); // Center the dialog
                dialogDestroy.setVisible(true);
            }
            else{
                JDialog dialogDestroy = new JDialog((Frame) null, "Treasure Excavation", true);
                dialogDestroy.setSize(300, 150);
                dialogDestroy.setLayout(new FlowLayout());

                // Add a JLabel and a JTextField
                JLabel label = new JLabel("Do you want to take this Treasure?");
                dialogDestroy.add(label);
                // Add buttons
                JButton yesButton = new JButton("Yes");
                JButton noButton = new JButton("No");
                dialogDestroy.add(yesButton);
                dialogDestroy.add(noButton);

                // Add action listener to the Theseus button
                yesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        Evrimata treasure = tablo.getPosition(monopati, thesi).getEvrimata();
                        players[turnPlayer].addEvrimata(treasure);
                        if(treasure instanceof Toixografies){
                            //do nothing
                        }
                        else{
                            tablo.setHasEvrima(monopati, thesi, false);
                        }

                        dialogDestroy.dispose(); // Close the dialog
                    }
                });

                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialogDestroy.dispose(); // Close the dialog
                    }
                });

                // Set dialog properties and make it visible
                dialogDestroy.setLocationRelativeTo(null); // Center the dialog
                dialogDestroy.setVisible(true);
            }
        }
    }
    public void anaskafiAriadnis(Tablo tablo, int monopati){
        int thesi = players[turnPlayer].getPioni(monopati).getThesi() - 1; // To -1 einai h monh allagh apo to parapanw
        if(tablo.getHasEvrima(monopati, thesi)){
            if(players[turnPlayer].getPioni(monopati).isThiseas()){
                JDialog dialogDestroy = new JDialog((Frame) null, "Destroy Treasure?", true);
                dialogDestroy.setSize(300, 150);
                dialogDestroy.setLayout(new FlowLayout());

                // Add a JLabel and a JTextField
                JLabel label = new JLabel("Do you want to destroy this Treasure?");
                dialogDestroy.add(label);
                // Add buttons
                JButton yesButton = new JButton("Yes");
                JButton noButton = new JButton("No");
                dialogDestroy.add(yesButton);
                dialogDestroy.add(noButton);

                // Add action listener to the Theseus button
                yesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        tablo.setHasEvrima(monopati, thesi, false);

                        dialogDestroy.dispose(); // Close the dialog
                    }
                });

                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialogDestroy.dispose(); // Close the dialog
                    }
                });

                // Set dialog properties and make it visible
                dialogDestroy.setLocationRelativeTo(null); // Center the dialog
                dialogDestroy.setVisible(true);
            }
            else{
                JDialog dialogDestroy = new JDialog((Frame) null, "Treasure Excavation", true);
                dialogDestroy.setSize(300, 150);
                dialogDestroy.setLayout(new FlowLayout());

                // Add a JLabel and a JTextField
                JLabel label = new JLabel("Do you want to take this Treasure?");
                dialogDestroy.add(label);
                // Add buttons
                JButton yesButton = new JButton("Yes");
                JButton noButton = new JButton("No");
                dialogDestroy.add(yesButton);
                dialogDestroy.add(noButton);

                // Add action listener to the Theseus button
                yesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        tablo.setHasEvrima(monopati, thesi, false);
                        Evrimata treasure = tablo.getPosition(monopati, thesi).getEvrimata();
                        players[turnPlayer].addEvrimata(treasure);
                        if(treasure instanceof Toixografies){
                            //do nothing
                        }
                        else{
                            tablo.setHasEvrima(monopati, thesi, false);
                        }

                        dialogDestroy.dispose(); // Close the dialog
                    }
                });

                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialogDestroy.dispose(); // Close the dialog
                    }
                });

                // Set dialog properties and make it visible
                dialogDestroy.setLocationRelativeTo(null); // Center the dialog
                dialogDestroy.setVisible(true);
            }
        }
    }


    public void updateDeck(){
        int counter = 0;
        for (Card card : deck) {
            counter++;
        }
        String topBot = "Bottom";
        if(turnPlayer == 0){
            topBot = "Top";
        }
        view.centerWestLabel.setText("<html>Available Cards: " + counter + "\nTurn: Player " + topBot + "<html>");

    }
}


