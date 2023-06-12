package TicTacToe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettings extends JFrame {
    //Settings window
    public GameSettings() {

        setTitle("TicTacToe");
        setBounds(450, 450, 240, 190);
        setResizable(false);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel jLabelMode = new JLabel("Choose game mod:");

        JRadioButton radioButtonModeTwoPlayers = new JRadioButton("Player vs Player");
        radioButtonModeTwoPlayers.setSelected(true);
        radioButtonModeTwoPlayers.setFocusable(false);
        JRadioButton radioButtonModeAgainstAI = new JRadioButton("Player vs AI");
        radioButtonModeAgainstAI.setFocusable(false);


        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonModeTwoPlayers);
        buttonGroup.add(radioButtonModeAgainstAI);


        JButton jButtonSetSettings = new JButton("Start game!");
        jButtonSetSettings.setFocusable(false);

        //add buttons on the panel
        add(jLabelMode);
        add(radioButtonModeTwoPlayers);
        add(radioButtonModeAgainstAI);


        add(jButtonSetSettings);
        setVisible(true);
        GameFunction game = new GameFunction();


        //if choose  gameMode with AI - show jslider for choose difficult
        radioButtonModeAgainstAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonModeAgainstAI.isSelected()) {
                    GameFunction.gameMode = 2;
                    getContentPane().revalidate();
                }
            }
        });

        radioButtonModeTwoPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonModeTwoPlayers.isSelected()) {
                    GameFunction.gameMode = 1;
                    getContentPane().revalidate();
                }
            }
        });

        //by clicking in the start button - will be start MainGame TicTacToe
        jButtonSetSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jButtonSetSettings) {
                    MainFormForGame game = new MainFormForGame();
                    dispose();
                }
            }
        });
    }
}