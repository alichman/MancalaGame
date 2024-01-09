package ui;

import mancala.Saver;
import mancala.MancalaGame;
import mancala.InvalidMoveException;

import java.io.IOError;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.w3c.dom.css.RGBColor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import mancala.MancalaGame;

public class Interface extends JFrame{
    private PositionAwareButton[][] ButtonList = new PositionAwareButton[2][6];
    private int Win_Height = 600;
    private int Win_Width = 900;
    private JPanel currentMainPanel;

    private Saver saver = new Saver();
    private MancalaGame game;
    private String[] pPaths = new String[2];
    private String gamePath;


    
    public Interface(){
        super();

        for(int i=0; i<2; i++){
            pPaths[i] = null;
        }

        Saver.mkdir();
        gamePath = "SampleName";

        setTitle("Greatest Mancala Game You'll Ever Play");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Win_Width, Win_Height);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        
        currentMainPanel = new JPanel();
        currentMainPanel.setLayout(new BoxLayout(currentMainPanel, BoxLayout.Y_AXIS));

        currentMainPanel.add(makeMenuPanel());

        add(currentMainPanel, BorderLayout.CENTER);
        
        /*
        game = new MancalaGame();
        currentMainPanel.add(makeGamePanel());
        ; */

    }

    public void showWindow(){
        setVisible(true);
    }

    private JPanel makeMenuPanel(){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));

        // Title spacer
        newPanel.add(Box.createRigidArea(new Dimension(0, Win_Height/20)));

        // Title Text Panel
        newPanel.add(makeCenteredTextPanel("Mancala", new Font("Arial", Font.BOLD, Win_Height/8)
        ));


        // Game selection spacer
        newPanel.add(Box.createRigidArea(new Dimension(0, Win_Height/15)));
        
        // Game Selection buttons
        newPanel.add(makeCenteredTextPanel("Start game / Select Gamemode", new Font("Arial", Font.BOLD, Win_Height/20)));
        newPanel.add(Box.createRigidArea(new Dimension(0, Win_Height/15)));
        
        JPanel gameButtons = new JPanel();
        gameButtons.setLayout(new BoxLayout(gameButtons, BoxLayout.X_AXIS));

        // define buttons
        JButton nkButton = new JButton("New Kalah");
        nkButton.addActionListener(e -> {
            game = new MancalaGame(0);
            currentMainPanel.removeAll();
            currentMainPanel.add(makeGamePanel());
            revalidate();
            repaint();
        });

        // JButton naButton = new JButton("New Ayoayo");
        // nkButton.addActionListener(e -> {
        //     game = new MancalaGame(1);
        //     currentMainPanel.removeAll();
        //     currentMainPanel.add(makeGamePanel());
        //     revalidate();
        //     repaint();
        // });

        
        // JButton lgButton = new JButton("Load Game");
        // nkButton.addActionListener(e -> {
        //     gamePath = JOptionPane.showInputDialog("Enter Save File:");
        //     game = loadGame(gamePath);

        //     currentMainPanel.removeAll();
        //     currentMainPanel.add(makeGamePanel());
        //     revalidate();
        //     repaint();
        // });
        

        gameButtons.add(Box.createHorizontalGlue());
        gameButtons.add(nkButton);
        gameButtons.add(Box.createHorizontalGlue());
        //gameButtons.add(naButton);
        //gameButtons.add(Box.createHorizontalGlue());
        //gameButtons.add(lgButton);
        //gameButtons.add(Box.createHorizontalGlue());

        newPanel.add(gameButtons);

        newPanel.setBackground(Color.BLUE);
        return newPanel;
    }

    private JPanel makeNewPlayerPanel(int playerNum){
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setMaximumSize(new Dimension(Win_Width/3, Win_Height/4));

        String playerText = "Player " + playerNum;
        // Header
        playerPanel.add(makeCenteredTextPanel(playerText, new Font("Arial", Font.BOLD, Win_Height/20)));
        // First prompt
        playerPanel.add(makePromptPanel("Create new player", playerText, getFont(), Color.GREEN));
        // Spacer between prompts
        playerPanel.add(Box.createRigidArea(new Dimension(0, Win_Height/30)));
        // Second prompt
        playerPanel.add(makePromptPanel("Load saved player", "", getFont(), Color.GREEN));

        return playerPanel;
    }


    private JPanel makeGamePanel(){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));

        

        // Current Player
        newPanel.add(Box.createRigidArea(new Dimension(0, Win_Height/30)));
        newPanel.add(makeCenteredTextPanel(game.getCurrentName() + "'s turn", new Font("Arial", Font.BOLD, Win_Height/23)));

        // Player 2 header
        newPanel.add(Box.createRigidArea(new Dimension(0, Win_Height/25)));
        newPanel.add(makeCenteredTextPanel(game.getPlayerName(2), new Font("Arial", Font.BOLD, Win_Height/23)));

        // Board
        newPanel.add(Box.createRigidArea(new Dimension(0, Win_Height/30)));
        newPanel.add(makeBoardPanel());

        // Player 1 Header
        newPanel.add(Box.createRigidArea(new Dimension(0, Win_Height/30)));
        newPanel.add(makeCenteredTextPanel(game.getPlayerName(1), new Font("Arial", Font.BOLD, Win_Height/23)));



        return newPanel;
    }

    private void refreshGamePanel(){
        currentMainPanel.removeAll();
        currentMainPanel.add(makeGamePanel());
    }


    private JPanel makeBoardPanel(){
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 8));

        // First row - second player
        int offset = 13;

        //Skip first and last square.
        boardPanel.add(new JPanel());
        for(int i=0; i<6; i++){
            ButtonList[0][i] = new PositionAwareButton(offset-i, game.getNumStones(offset-i));
            ButtonList[0][i].addActionListener(e -> {
                try{
                    game.move(convertInput(e));
                    
                    if(true){
                        currentMainPanel.removeAll();
                        currentMainPanel.add(makeGameOverPanel());
                    } else{
                        refreshGamePanel();
                    }
                    revalidate();
                    repaint();
                } catch(InvalidMoveException exception){
                    System.out.println("There seems to be an error: " + convertInput(e));
                } 
            });
            boardPanel.add(ButtonList[0][i]);
        }
        boardPanel.add(new JPanel());

        // Second row Stores on edges and menu in the middle
        boardPanel.add(makeCenteredTextPanel(""+game.getNumStones(14), new Font("Arial", Font.BOLD, Win_Height/23)));
        // 6 empty spots
        boardPanel.add(new JPanel());
        boardPanel.add(new JPanel());
        boardPanel.add(new JPanel());
        
        // Menu
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(e->{
            gamePath = JOptionPane.showInputDialog("Enter save name:");
            saveGame();
            refreshGamePanel();
        });

        JMenu exitmenu = new JMenu("Exit");
        JMenuItem exitM = new JMenuItem("Exit to menu");
        exitM.addActionListener(e -> {
            currentMainPanel.removeAll();
            currentMainPanel.add(makeMenuPanel());
        });

        fileMenu.add(saveGame);
        menuBar.add(fileMenu);

        boardPanel.add(menuBar);

        boardPanel.add(new JPanel());
        boardPanel.add(new JPanel());
        
        boardPanel.add(makeCenteredTextPanel(""+game.getNumStones(7), new Font("Arial", Font.BOLD, Win_Height/23)));

        // Third row - first player
        offset =1;

        //Skip first and last square.
        boardPanel.add(new JPanel());
        for(int i=0; i<6; i++){
            ButtonList[0][i] = new PositionAwareButton(offset+i, game.getNumStones(offset + i));
            ButtonList[0][i].addActionListener(e -> {
                try{
                    System.out.println("Button Value: " + convertInput(e));
                    game.move(convertInput(e));                    

                    
                    if(game.isGameOver()){
                        currentMainPanel = makeGameOverPanel();
                        add(currentMainPanel, BorderLayout.CENTER);
                    } else{
                        refreshGamePanel();
                    }
                    revalidate();
                    repaint();
                    
                } catch(InvalidMoveException exception){
                    System.out.println("There seems to be an error: " + convertInput(e));
                    exception.printStackTrace();
                } 
            });
            boardPanel.add(ButtonList[0][i]);
        }
        boardPanel.add(new JPanel());
        

        return boardPanel;
    }

    private int convertInput(ActionEvent e){
        PositionAwareButton clicked = (PositionAwareButton)e.getSource();
        return clicked.getPitNum();
    }


    private JPanel makeGameOverPanel(){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));

        // Title spacer
        newPanel.add(Box.createRigidArea(new Dimension(0, Win_Height/20)));

        //Tie logic
        String winner = game.getWinningName();

        if(winner == null){
            winner = "Nobody";
        }
        // Title Text Panel
        newPanel.add(makeCenteredTextPanel(winner + "Wins!", new Font("Arial", Font.BOLD, Win_Height/8)));

        // Spacer
        newPanel.add(Box.createRigidArea(new Dimension(0, Win_Height/20)));
        
        // Player Save Panel
        JPanel pPanel = new JPanel();
        pPanel.setLayout(new BoxLayout(pPanel, BoxLayout.X_AXIS));

        pPanel.add(Box.createHorizontalGlue());
        pPanel.add(makeSavePlayerPanel(1));
        pPanel.add(Box.createHorizontalGlue());
        pPanel.add(makeSavePlayerPanel(2));
        pPanel.add(Box.createHorizontalGlue());

        return newPanel;
    }

    private JPanel makeSavePlayerPanel(int playerNum){

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        playerPanel.add(makeCenteredTextPanel(game.getPlayerName(playerNum), new Font("Arial", Font.BOLD, Win_Height/8)));

        // If player is already saved
        JLabel updateLabel = new JLabel(game.getPlayerName(playerNum) + "Is already a saved profile, and has been updated/");
        updateLabel.setFont(new Font("Arial", Font.BOLD, Win_Height/28));
        

        if(pPaths[playerNum-1] != null){
            savePlayer((Serializable)game.getPlayers().get(playerNum-1), pPaths[playerNum-1]);
            playerPanel.add(updateLabel);
        } else{
            playerPanel.add(makeCenteredTextPanel(("Save this profile?"), new Font("Arial", Font.BOLD, Win_Height/28)));

            JTextField field = new JTextField("defaultFilePath");
            field.setPreferredSize(new Dimension(Win_Width/4, Win_Height/20));
            field.setColumns(20);
            playerPanel.add(field);

            JButton submit = new JButton("Submit");
            submit.addActionListener(e ->{
                savePlayer((Serializable)game.getPlayers().get(playerNum-1), field.getText());
            });
            playerPanel.add(submit);

        }



        return playerPanel;
    }




    private JPanel makeCenteredTextPanel(final String text, final Font font){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.X_AXIS));

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(font);
        newPanel.add(Box.createHorizontalGlue());
        newPanel.add(textLabel);
        newPanel.add(Box.createHorizontalGlue());

        return newPanel;
    }

    private JPanel makePromptPanel(String prompt, String dValue, Font font, Color color){
        JPanel promptPanel = new JPanel();
        promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.Y_AXIS));
        JLabel Prompt = new JLabel(prompt);
        Prompt.setFont(font);
        promptPanel.add(Prompt);

        JTextField field = new JTextField(dValue);
        field.setPreferredSize(new Dimension(Win_Width/4, Win_Height/20));
        field.setColumns(20);

        promptPanel.add(field);

        return promptPanel;
    }
    

    private void saveGame(){
        Saver.saveObject(game, gamePath);
    }
    private MancalaGame loadGame(String filename){
        try{
            Serializable object = Saver.loadObject("assets/" + filename);
            if(object instanceof MancalaGame){
                return (MancalaGame)object;
            }
            throw new RuntimeException("File name doesn't belong to class");
        } catch(IOException e){
            throw new RuntimeException("File with given name doesn't exist.");
        }
    }
    
    private void savePlayer(Serializable player, String filename){
        Saver.saveObject(player, filename);
    }
    private Serializable loadPlayer(String filePath){
        if(filePath == null){
            return null;
        }

        try{
            Serializable object = Saver.loadObject("assets/"+filePath);
            return object;
        } catch(IOException e){
            throw new RuntimeException("File with given name doesn't exist");
        }
    }
}
