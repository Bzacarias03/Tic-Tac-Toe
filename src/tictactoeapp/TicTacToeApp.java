package tictactoeapp;

import java.awt.*;
import javax.swing.*;

public class TicTacToeApp {
    
    GameLogic gameLogic;
    JFrame frame = new JFrame();
    JPanel menuPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton[] buttons = new JButton[9];
    Font font = new Font("Bahnschrift", Font.PLAIN, 50);
    
    public TicTacToeApp() {
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Tic-Tac-Toe");
        frame.setSize(800, 500);
        frame.setResizable(false);
        
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(font);
            buttons[i].setBackground(Color.WHITE);
            buttonPanel.add(buttons[i]);
        }
        buttonPanel.setLayout(new GridLayout(3, 3, 8, 8));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBounds(0, 0, 600, 500);
        
        gameLogic = new GameLogic
            (buttons, menuPanel, font);
        
        frame.setLocationRelativeTo(null);
        frame.add(menuPanel, BorderLayout.EAST);
        frame.add(buttonPanel);
    }
    
    public static void main(String[] args) {
        
        TicTacToeApp game = new TicTacToeApp();
        game.setVisible();
        
    }
    
    public void setVisible() {
        frame.setVisible(true);
    }
}
