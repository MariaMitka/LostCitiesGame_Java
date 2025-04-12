package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

    public JPanel topPanel = new JPanel();
    public JPanel centerPanel = new JPanel();
    public JPanel bottomPanel = new JPanel();

    public JPanel topCardArrayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    public JLabel[] cardLabelsTop = new JLabel[8];

    public JPanel bottomCardArrayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    public JLabel[] cardLabelsBottom = new JLabel[8];

    public JPanel topPalaceArrayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    public JLabel[] topPalace = new JLabel[4];

    public JPanel bottomPalaceArrayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    public JLabel[] bottomPalace = new JLabel[4];

    public JPanel eastGridPanel = new JPanel(new GridLayout(4, 9, 5, 5));

    public JLabel[][] gridSquares = new JLabel[4][9];

    public JLabel centerWestLabel = new JLabel("AVAILABLE CARDS", JLabel.CENTER);
    public JLabel centerSouthLabel = new JLabel("INFOBOX", JLabel.CENTER);

    public JButton topShowTreasure = new JButton("Show Treasures");
    public JButton bottomShowTreasure = new JButton("Show Treasures");
    public JButton topShowToixografies = new JButton("Show Toxografies");
    public JButton bottomShowToixografies = new JButton("Show Toxografies");

    public JButton playCardButton = new JButton("Play Card");
    public JButton drawCardButton = new JButton("Draw Card");
    public JButton discardButton = new JButton("Discard");
    public JButton endTurnButton = new JButton("End Turn");

    ImageIcon archImg = new ImageIcon("images/pionia/arch.png");
    ImageIcon thiseasImg = new ImageIcon("images/pionia/theseus.png");
    JLabel archPawn = new JLabel(archImg);
    JLabel thiseasPawn = new JLabel(thiseasImg);

    public View(){
        super("Lost Cities");

        ImageIcon imageIcon = null;
        // Set up the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600, 1200);
        this.setLayout(new BorderLayout()); // Use BorderLayout for top, center, bottom panels

        // Set specific dimensions for panels
        topPanel.setPreferredSize(new Dimension(800, 150)); // Top panel: 800x100
        centerPanel.setPreferredSize(new Dimension(800, 400)); // Center panel: 800x400
        bottomPanel.setPreferredSize(new Dimension(800, 150)); // Bottom panel: 800x100

        // Add borders to the panels
        topPanel.setBorder(new LineBorder(Color.RED, 3)); // Black border with thickness 2
        centerPanel.setBorder(new LineBorder(Color.GRAY, 3)); // Gray border with thickness 2
        bottomPanel.setBorder(new LineBorder(Color.GREEN, 3)); // Dark gray border with thickness 2

        // Add some content to the panels
        topPanel.add(new JLabel("Top Panel"));
        centerPanel.add(new JLabel("Center Panel"));
        bottomPanel.add(new JLabel("Bottom Panel"));

        // Add an array of 8 labels ("cards") to the top panel
        for (int i = 0; i < cardLabelsTop.length; i++) {
            cardLabelsTop[i] = new JLabel("Card " + (i + 1), JLabel.CENTER);
            cardLabelsTop[i].setPreferredSize(new Dimension(80, 100)); // Set card dimensions
            cardLabelsTop[i].setOpaque(true);
            cardLabelsTop[i].setBackground(Color.WHITE); // Card background color
            cardLabelsTop[i].setBorder(new LineBorder(Color.BLACK, 1)); // Add border to the card
            topCardArrayPanel.add(cardLabelsTop[i], BorderLayout.WEST);
        }

        // Add an array of 8 labels ("cards") to the bottom panel
        for (int i = 0; i < cardLabelsBottom.length; i++) {
            cardLabelsBottom[i] = new JLabel("Card " + (i + 1), JLabel.CENTER);
            cardLabelsBottom[i].setPreferredSize(new Dimension(80, 100)); // Set card dimensions
            cardLabelsBottom[i].setOpaque(true);
            cardLabelsBottom[i].setBackground(Color.WHITE); // Card background color
            cardLabelsBottom[i].setBorder(new LineBorder(Color.BLACK, 1)); // Add border to the card
            bottomCardArrayPanel.add(cardLabelsBottom[i], BorderLayout.WEST);
        }

        // Create the second card array (4 palace) for the top panel
        for (int i = 0; i < topPalace.length; i++) {
            topPalace[i] = new JLabel("Palace " + (i + 1), JLabel.CENTER);
            topPalace[i].setPreferredSize(new Dimension(80, 100)); // Set card dimensions
            topPalace[i].setOpaque(true);
            topPalace[i].setBackground(Color.LIGHT_GRAY); // Card background color
            topPalace[i].setBorder(new LineBorder(Color.BLACK, 1)); // Add border to the card
            topPalaceArrayPanel.add(topPalace[i]);
        }
        topPalace[0].setBorder(new LineBorder(Color.RED, 2));
        topPalace[1].setBorder(new LineBorder(Color.ORANGE, 2));
        topPalace[2].setBorder(new LineBorder(Color.WHITE, 2));
        topPalace[3].setBorder(new LineBorder(Color.BLUE, 2));

        // Create the second card array (4 palace) for the bottom panel
        for (int i = 0; i < bottomPalace.length; i++) {
            bottomPalace[i] = new JLabel("Palace " + (i + 1), JLabel.CENTER);
            bottomPalace[i].setPreferredSize(new Dimension(80, 100)); // Set card dimensions
            bottomPalace[i].setOpaque(true);
            bottomPalace[i].setBackground(Color.LIGHT_GRAY); // Card background color
            bottomPalace[i].setBorder(new LineBorder(Color.BLACK, 1)); // Add border to the card
            bottomPalaceArrayPanel.add(bottomPalace[i]);
        }
        bottomPalace[0].setBorder(new LineBorder(Color.RED, 2));
        bottomPalace[1].setBorder(new LineBorder(Color.ORANGE, 2));
        bottomPalace[2].setBorder(new LineBorder(Color.WHITE, 2));
        bottomPalace[3].setBorder(new LineBorder(Color.BLUE, 2));

        // Add the two card arrays to the top panel with space between them
        topPanel.add(topCardArrayPanel, BorderLayout.EAST);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(topPalaceArrayPanel, BorderLayout.WEST);

        // Add the two card arrays to the bottom panel with space between them
        bottomPanel.add(bottomCardArrayPanel, BorderLayout.EAST);
        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(bottomPalaceArrayPanel, BorderLayout.WEST);

        topPanel.add(topShowTreasure, BorderLayout.EAST);
        topPanel.add(topShowToixografies, BorderLayout.EAST);
        bottomPanel.add(bottomShowTreasure, BorderLayout.EAST);
        bottomPanel.add(bottomShowToixografies, BorderLayout.EAST);


        // Create a panel for the 4x9 grid (on the east side of the center panel)
        eastGridPanel.setPreferredSize(new Dimension(900, 400)); // Set panel size
        eastGridPanel.setBorder(new LineBorder(Color.BLACK, 2)); // Border around the grid panel

        // Add squares to the grid panel
        for (int j = 0; j < 9; j++) {

            imageIcon = new ImageIcon("images/pathsOriginal/knossos.jpg");
            if(j == 8){
                imageIcon = new ImageIcon("images/pathsOriginal/knossosPalace.jpg");
            } else if(j == 1 || j == 3 || j == 5 || j == 7){
                imageIcon = new ImageIcon("images/pathsOriginal/knossos2.jpg");
            }
            gridSquares[0][j] = new JLabel(imageIcon);
            gridSquares[0][j].setPreferredSize(new Dimension(50, 30)); // Set square size
            gridSquares[0][j].setBorder(new LineBorder(Color.RED, 2)); // Black border for each square
            eastGridPanel.add(gridSquares[0][j]); // Add square to the grid panel
        }
        for (int j = 0; j < 9; j++) {

            imageIcon = new ImageIcon("images/pathsOriginal/malia.jpg");
            if(j == 8){
                imageIcon = new ImageIcon("images/pathsOriginal/maliaPalace.jpg");
            } else if(j == 1 || j == 3 || j == 5 || j == 7){
                imageIcon = new ImageIcon("images/pathsOriginal/malia2.jpg");
            }
            gridSquares[1][j] = new JLabel(imageIcon);
            gridSquares[1][j].setPreferredSize(new Dimension(50, 30)); // Set square size
            gridSquares[1][j].setBorder(new LineBorder(Color.ORANGE, 2)); // Black border for each square
            eastGridPanel.add(gridSquares[1][j]); // Add square to the grid panel
        }
        for (int j = 0; j < 9; j++) {

            imageIcon = new ImageIcon("images/pathsOriginal/phaistos.jpg");
            if(j == 8){
                imageIcon = new ImageIcon("images/pathsOriginal/phaistosPalace.jpg");
            } else if(j == 1 || j == 3 || j == 5 || j == 7){
                imageIcon = new ImageIcon("images/pathsOriginal/phaistos2.jpg");
            }

            gridSquares[2][j] = new JLabel(imageIcon);
            gridSquares[2][j].setPreferredSize(new Dimension(50, 30)); // Set square size
            gridSquares[2][j].setBorder(new LineBorder(Color.WHITE, 2)); // Black border for each square
            eastGridPanel.add(gridSquares[2][j]); // Add square to the grid panel
        }
        for (int j = 0; j < 9; j++) {

            imageIcon = new ImageIcon("images/pathsOriginal/zakros.jpg");
            if(j == 8){
                imageIcon = new ImageIcon("images/pathsOriginal/zakrosPalace.jpg");
            } else if(j == 1 || j == 3 || j == 5 || j == 7){
                imageIcon = new ImageIcon("images/pathsOriginal/zakros2.jpg");
            }
            gridSquares[3][j] = new JLabel(imageIcon);
            gridSquares[3][j].setPreferredSize(new Dimension(50, 30)); // Set square size
            gridSquares[3][j].setBorder(new LineBorder(Color.BLUE, 2)); // Black border for each square
            eastGridPanel.add(gridSquares[3][j]); // Add square to the grid panel
        }

        // Add the grid panel to the east side of the center panel
        centerPanel.add(eastGridPanel, BorderLayout.EAST);

        // Create a label for the west side of the center panel
        centerWestLabel.setPreferredSize(new Dimension(140, 150)); // Set label dimensions
        centerWestLabel.setOpaque(true);
        centerWestLabel.setBackground(Color.YELLOW); // Set background color
        centerWestLabel.setBorder(new LineBorder(Color.BLACK, 2)); // Add border to the label
        centerWestLabel.setVerticalAlignment(SwingConstants.CENTER);   // Align text to the center
        centerWestLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        centerPanel.add(centerWestLabel, BorderLayout.WEST);

        centerSouthLabel.setPreferredSize(new Dimension(400, 250)); // Set label dimensions
        centerSouthLabel.setOpaque(true);
        centerSouthLabel.setBackground(Color.YELLOW); // Set background color
        centerSouthLabel.setBorder(new LineBorder(Color.BLACK, 2)); // Add border to the label
        centerSouthLabel.setVerticalAlignment(SwingConstants.CENTER);   // Align text to the center
        centerSouthLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        centerPanel.add(centerSouthLabel, BorderLayout.SOUTH);


        // Add buttons panel
        centerPanel.add(playCardButton);
        centerPanel.add(drawCardButton);
        centerPanel.add(discardButton);
        centerPanel.add(endTurnButton);

        archPawn.setBounds(100,100,100,100);
        archPawn.setOpaque(true);
        archPawn.setBackground(Color.YELLOW);
        archPawn.setBorder(new LineBorder(Color.BLACK, 2));
        archPawn.setVerticalAlignment(SwingConstants.CENTER);
        archPawn.setHorizontalAlignment(SwingConstants.CENTER);

        centerPanel.add(archPawn);
        centerPanel.add(thiseasPawn);

        // Add panels to the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Make frame visible
        this.setVisible(true);

    }

    public void setText(String text, JLabel label){
        label.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
