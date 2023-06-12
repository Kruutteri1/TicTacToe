package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class GameFunction extends JComponent {

    public static final int FIELD_EMPTY = 0;
    public static final int FIELD_X = 10;
    public static final int FIELD_O = 200;
    public static int gameMode = 1;
    public static int gameOver; // 1 - stop game

    int[][] fields;
    int[][] board = new int[3][3];
    public static boolean isXTurn = true; // true - X, false - O

    public static int[] bestMove = new int[2];


    public GameFunction() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        fields = new int[3][3]; // game zone
        newGame();
    }

    void newGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = FIELD_EMPTY;
                board[i][j] = FIELD_EMPTY;
                gameOver = 0;
                repaint();
            }
        }
        isXTurn = true;
    }


    int checkWin() {
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            diag += fields[i][i];
            diag2 += fields[i][2 - i];
        }
        if (diag == FIELD_O * 3 || diag == FIELD_X * 3) return diag;
        if (diag2 == FIELD_O * 3 || diag2 == FIELD_X * 3) return diag2;

        int check_i, check_j;
        boolean hasEmpty = false;

        for (int i = 0; i < 3; i++) {
            check_i = 0;
            check_j = 0;
            for (int j = 0; j < 3; j++) {
                if (fields[i][j] == 0) {
                    hasEmpty = true;
                }
                check_i += fields[i][j];
                check_j += fields[j][i];
            }
            if (check_i == FIELD_O * 3 || check_i == FIELD_X * 3) {
                return check_i;
            }
            if (check_j == FIELD_O * 3 || check_j == FIELD_X * 3) {
                return check_j;
            }
        }
        if (hasEmpty) {
            return 0; // game not over
        } else {
            return -1; // Tie
        }
    }

    public boolean isBoardFull(int[][] board) {
        // Проходим по всем ячейкам доски
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // Если находим пустую ячейку, значит доска не заполнена
                if (board[i][j] == FIELD_EMPTY) {
                    return false;
                }
            }
        }
        // Если пустых ячеек не нашли, значит доска заполнена
        return true;
    }


    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            // get positions on click
            int i1 = (int) ((float) x / getWidth() * 3);
            int j1 = (int) ((float) y / getHeight() * 3);

            int res = checkWin();
            if (res != 0) {
                if (res == FIELD_O * 3) {
                    JOptionPane.showMessageDialog(this, "The zeros have won!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                    gameOver = 1;
                } else if (res == FIELD_X * 3) {
                    JOptionPane.showMessageDialog(this, "The crosses have won!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                    gameOver = 1;
                } else if (isBoardFull(fields)){
                    JOptionPane.showMessageDialog(this, "draw!", "Draw!", JOptionPane.INFORMATION_MESSAGE);
                    gameOver = 1;
                }
            }

            if (gameOver != 1) {
                if (gameMode == 1) {
                    if (fields[i1][j1] == FIELD_EMPTY) {

                        fields[i1][j1] = isXTurn ? FIELD_X : FIELD_O;
                        isXTurn = !isXTurn;
                        repaint();


                    }
                } else if (gameMode == 2) {
                    if (isXTurn) {
                        if (fields[i1][j1] == FIELD_EMPTY) {
                            fields[i1][j1] = FIELD_X;
                            isXTurn = false;
                            repaint();
                        }
                    }
                    if (!isXTurn) {
                        if (board[i1][j1] == FIELD_EMPTY) {
                            
                        }

                    }

                }
            }
        }
    }

    void drawGrid(Graphics graphics) {
        int w = getWidth();
        int h = getHeight();
        int dw = w / 3;
        int dh = h / 3;
        graphics.setColor(Color.BLACK);
        for (int i = 1; i < 3; i++) {
            graphics.drawLine(0,dh * i, w, dh * i);
            graphics.drawLine(dw * i, 0, dw * i, h);
        }

    }

    void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.RED);
        int dw = getWidth() / 3;
        int dh = getWidth() / 3;

        int x = i * dw;
        int y = j * dh;

        graphics.drawLine(x, y, x + dw, y + dh);
        graphics.drawLine(x, y + dh,x + dw, y);
    }

    void drawO(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLUE);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;

        graphics.drawOval(x + 5 * dw / 100,y,dw * 9 / 10,dh);
    }

    void drawXO(Graphics graphics) {
        if (gameOver != 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (fields[i][j] == FIELD_X) {
                        drawX(i, j, graphics);
                    } else if (fields[i][j] == FIELD_O) {
                        drawO(i, j, graphics);
                    }
                }
            }
        }
    }



    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawGrid(graphics);

        drawXO(graphics);
    }
}