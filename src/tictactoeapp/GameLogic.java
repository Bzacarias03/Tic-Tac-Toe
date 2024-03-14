package tictactoeapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameLogic implements ActionListener {
    
    JPanel resetPanel = new JPanel();
    JTextField titleField = new JTextField();
    JTextArea currentScore = new JTextArea();
    JButton[] buttons;
    JButton resetAll = new JButton();
    JButton resetBoard = new JButton();
    int xWinCount = 0;
    int oWinCount = 0;
    int tileCountForTie = 0;
    boolean xTurn = true;
    
    GameLogic(JButton[] buttons, JPanel menuPanel, Font font) {
        
        this.buttons = buttons;
        resetBoard.addActionListener(this);
        resetAll.addActionListener(this);
        for (JButton button : this.buttons) {
            button.setFocusable(false);
            button.addActionListener(this);
        }
        
        titleField.setFont(font);
        titleField.setText("Tic-Tac-Toe");
        titleField.setHorizontalAlignment(JTextField.CENTER);
        titleField.setPreferredSize(new Dimension(300, 60));
        titleField.setCaretColor(Color.WHITE);
        titleField.setEditable(false);
        
        currentScore = new JTextArea();
        currentScore.setFont(font);
        currentScore.setBounds(600, 440, 240, 300);
        currentScore.setText(String.format("\n X Score: %d\n\n O Score: %d"
                , xWinCount, oWinCount));
        currentScore.setCaretColor(Color.WHITE);
        currentScore.setEditable(false);
        
        resetBoard.setText(String.format("Reset Board"));
        resetBoard.setPreferredSize(new Dimension(150, 100));
        resetAll.setText("Reset All");
        resetAll.setPreferredSize(new Dimension(150, 100));
        
        resetPanel.setLayout(new FlowLayout());
        resetPanel.add(resetBoard);
        resetPanel.add(resetAll);
        
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setOpaque(true);
        menuPanel.setBounds(600, 0, 200, 500);
        menuPanel.add(titleField, BorderLayout.NORTH);
        menuPanel.add(currentScore, BorderLayout.CENTER);
        menuPanel.add(resetPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Checks and updates the titles that are selected
        for (int i = 0; i < buttons.length; i++) {
            if (xTurn) {
                if (e.getSource() == buttons[i]) {
                    buttons[i].setText("X");
                    buttons[i].setEnabled(false);
                    check(tileCountForTie);
                    xTurn = false;
                }
            }
            else {
                if (e.getSource() == buttons[i]) {
                    buttons[i].setText("O");
                    buttons[i].setEnabled(false);
                    check(tileCountForTie);
                    xTurn = true;
                }
            }
        }
        
        //Checks for when the resetAll button is clicked to clear the game
        if (e.getSource() == resetAll) {
            resetAll();
        }
        
        //Check for when the resetBoard button is clicked to clear the board
        if (e.getSource() == resetBoard) {
            resetBoard();
        }
    }
    
    public void tie() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(Color.ORANGE);
            titleField.setText("Tie");
            buttons[i].setEnabled(false);
        }
    }
    
    public void xWins(int a, int b, int c) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isEnabled()) {
                buttons[i].setEnabled(false);
            }
        }
        titleField.setText("X Wins");
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        xWinCount++;
        currentScore.setText(String.format("\nX Score: %d\n\nO Score: %d"
                , xWinCount, oWinCount));
    }
    
    public void oWins(int a, int b, int c) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isEnabled()) {
                buttons[i].setEnabled(false);
            }
        }
        titleField.setText("O Wins");
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        oWinCount++;
        currentScore.setText(String.format("\nX Score: %d\n\nO Score: %d"
                , xWinCount, oWinCount));
    }
    
    public void resetBoard() {
        titleField.setText("Tic-Tac-Toe");
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setEnabled(true);
            xTurn = true;
        }
    }
    
    public void resetAll() {
        titleField.setText("Tic-Tac-Toe");
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setEnabled(true);
            xTurn = true;
        }
        xWinCount = 0;
        oWinCount = 0;
        currentScore.setText(String.format("\nX Score: %d\n\nO Score: %d"
                , xWinCount, oWinCount));
    }
    
    public boolean check(int tileCount) {
        
        //These check if X wins
        //Check the first column
        if ((buttons[0].getText() == "X") &&
            (buttons[1].getText() == "X") &&
            (buttons[2].getText() == "X")) {
            xWins(0, 1, 2);
            return true;
        }
        //Check the second column
        if ((buttons[3].getText() == "X") &&
            (buttons[4].getText() == "X") &&
            (buttons[5].getText() == "X")) {
            xWins(3, 4, 5);
            return true;
        }
        //Check the third column
        if ((buttons[6].getText() == "X") &&
            (buttons[7].getText() == "X") &&
            (buttons[8].getText() == "X")) {
            xWins(6, 7, 8);
            return true;
        }
        //Check the first row
        if ((buttons[0].getText() == "X") &&
            (buttons[3].getText() == "X") &&
            (buttons[6].getText() == "X")) {
            xWins(0, 3, 6);
            return true;
        }
        //Check the second row
        if ((buttons[1].getText() == "X") &&
            (buttons[4].getText() == "X") &&
            (buttons[7].getText() == "X")) {
            xWins(1, 4, 7);
            return true;
        }
        //Check the third row
        if ((buttons[2].getText() == "X") &&
            (buttons[5].getText() == "X") &&
            (buttons[8].getText() == "X")) {
            xWins(2, 5, 8);
            return true;
        }
        //Check left diagonal
        if ((buttons[0].getText() == "X") &&
            (buttons[4].getText() == "X") &&
            (buttons[8].getText() == "X")) {
            xWins(0, 4, 8);
            return true;
        }
        //Check right diagonal
        if ((buttons[2].getText() == "X") &&
            (buttons[4].getText() == "X") &&
            (buttons[6].getText() == "X")) {
            xWins(2, 4, 6);
            return true;
        }
        
        //These check if O wins
        //Check the first column
        if ((buttons[0].getText() == "O") &&
            (buttons[1].getText() == "O") &&
            (buttons[2].getText() == "O")) {
            oWins(0, 1, 2);
            return true;
        }
        //Check the second column
        if ((buttons[3].getText() == "O") &&
            (buttons[4].getText() == "O") &&
            (buttons[5].getText() == "O")) {
            oWins(3, 4, 5);
            return true;
        }
        //Check the third column
        if ((buttons[6].getText() == "O") &&
            (buttons[7].getText() == "O") &&
            (buttons[8].getText() == "O")) {
            oWins(6, 7, 8);
            return true;
        }
        //Check the first row
        if ((buttons[0].getText() == "O") &&
            (buttons[3].getText() == "O") &&
            (buttons[6].getText() == "O")) {
            oWins(0, 3, 6);
            return true;
        }
        //Check the second row
        if ((buttons[1].getText() == "O") &&
            (buttons[4].getText() == "O") &&
            (buttons[7].getText() == "O")) {
            oWins(1, 4, 7);
            return true;
        }
        //Check the third row
        if ((buttons[2].getText() == "O") &&
            (buttons[5].getText() == "O") &&
            (buttons[8].getText() == "O")) {
            oWins(2, 5, 8);
            return true;
        }
        //Check left diagonal
        if ((buttons[0].getText() == "O") &&
            (buttons[4].getText() == "O") &&
            (buttons[8].getText() == "O")) {
            oWins(0, 4, 8);
            return true;
        }
        //Check right diagonal
        if ((buttons[2].getText() == "O") &&
            (buttons[4].getText() == "O") &&
            (buttons[6].getText() == "O")) {
            oWins(2, 4, 6);
            return true;
        }
        
        //Check if all tiles are disabled for a tie
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().equals("X")
            || buttons[i].getText().equals("O")) {
                if (buttons[i].isEnabled() == false) {
                    if (buttons[i].getBackground().equals(Color.WHITE)) {
                        tileCount++;
                    }
                }
            }
        }
        if (tileCount == 9) {
            tie();
            return true;
        }
        return false;
    }
}
