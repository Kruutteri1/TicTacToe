package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFormForGame extends JFrame {
    public MainFormForGame() {
        // name of window
        setTitle("Tic Tac Toe");
        setBounds(300, 300, 455, 500);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GameFunction gameField = new GameFunction();

        JPanel buttonPanel = new JPanel(new GridLayout());
        add(gameField, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnStart = new JButton("Start new game");
        btnStart.setFocusable(false);
        JButton btnEnd = new JButton("Exit");
        btnEnd.setFocusable(false);
        JButton btnToSettings = new JButton("Settings");
        btnToSettings.setFocusable(false);
        buttonPanel.add(btnEnd);
        buttonPanel.add(btnToSettings);
        buttonPanel.add(btnStart);

        setVisible(true);


        //stop program
        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnEnd) {
                    System.exit(0);
                }
            }
        });

        // return to setting window
        btnToSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnToSettings) {
                    GameSettings gameSettings = new GameSettings();
                    GameFunction.gameMode = 1;
                    dispose();
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnStart) {
                    gameField.newGame();
                }
            }
        });


    }
}
