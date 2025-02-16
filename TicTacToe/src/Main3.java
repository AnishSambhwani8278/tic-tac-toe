import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class TicTacToe3{
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("TicTacToe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    Random random = new Random();

    JButton[][] tileBoard = new JButton[3][3];
    JButton restartButton = new JButton();

    String currentPlayer = "X";
    boolean gameOver = false;
    int turns = 0;

    TicTacToe3(){
        frame.setSize(boardWidth,boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon(getClass().getResource("/tictactoe.png")).getImage());

        textLabel.setFont(new Font("Arial",Font.BOLD,40));
        textLabel.setText("TicTacToe");
        textLabel.setForeground(Color.white);
        textLabel.setBackground(new Color(29, 30, 31));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        tileBoard[i][j].setText("");
                        tileBoard[i][j].setForeground(Color.yellow);
                    }
                }
                currentPlayer = "X";
                textLabel.setText("TicTacToe");
                gameOver = false;
                turns = 0;
            }
        });
        restartButton.setFont(new Font("Arial",Font.PLAIN,18));
        restartButton.setFocusable(false);
        restartButton.setText("Restart");
        restartButton.setBackground(new Color(29, 30, 31));
        restartButton.setForeground(Color.white);
        textPanel.add(restartButton, BorderLayout.SOUTH);

        gamePanel.setLayout(new GridLayout(3,3));
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                JButton tile = new JButton();
                tileBoard[i][j] = tile;
                tile.setFocusable(false);
                tile.setFont(new Font("Arial",Font.BOLD,90));
                tile.setBackground(new Color(29, 30, 31));
                tile.setForeground(Color.yellow);
                gamePanel.add(tile);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver){
                            return;
                        }
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText().isEmpty()){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = "O";
                                while(true){
                                    int a = random.nextInt(3);
                                    int b = random.nextInt(3);
                                    JButton nexto = tileBoard[a][b];

                                    if(nexto.getText().equals("X") || nexto.getText().equals("O")){
                                        continue;
                                    }

                                    nexto.setText("O");
                                    turns++;
                                    break;
                                }
                                checkWinner();
                                if(!gameOver){
                                    currentPlayer = "X";
                                    textLabel.setText(currentPlayer + "'s turn");
                                }
                            }
                        }
                    }
                });
            }
        }
        frame.add(gamePanel);
        frame.setVisible(true);
    }

    void checkWinner(){
        for(int i=0;i<3;i++){
            if(tileBoard[i][0].getText().isEmpty()){
                continue;
            }

            if(tileBoard[i][0].getText().equals(tileBoard[i][1].getText()) && tileBoard[i][1].getText().equals(tileBoard[i][2].getText())){
                gameOver = true;
                for(int j=0;j<3;j++){
                    setWinner(tileBoard[i][j]);
                }
                return;
            }
        }

        for(int i=0;i<3;i++){
            if(tileBoard[0][i].getText().isEmpty()){
                continue;
            }

            if(tileBoard[0][i].getText().equals(tileBoard[1][i].getText()) && tileBoard[1][i].getText().equals(tileBoard[2][i].getText())){
                gameOver = true;
                for(int j=0;j<3;j++){
                    setWinner(tileBoard[j][i]);
                }
                return;
            }
        }

        if(tileBoard[0][0].getText().equals(tileBoard[1][1].getText()) && tileBoard[1][1].getText().equals(tileBoard[2][2].getText())){
            if(tileBoard[0][0].getText().isEmpty()){
                return;
            }
            gameOver = true;
            for(int i=0;i<3;i++){
                setWinner(tileBoard[i][i]);
            }
            return;
        }

        if(tileBoard[0][2].getText().equals(tileBoard[1][1].getText()) && tileBoard[1][1].getText().equals(tileBoard[2][0].getText())){
            if(tileBoard[0][2].getText().isEmpty()){
                return;
            }
            gameOver = true;
            setWinner(tileBoard[0][2]);
            setWinner(tileBoard[1][1]);
            setWinner(tileBoard[2][0]);
            return;
        }

        if(turns == 9 && !gameOver){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    JButton tile = tileBoard[i][j];
                    tile.setForeground(Color.orange);
                    textLabel.setText("Tie!");
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        textLabel.setText(currentPlayer + " has won!");
    }
}

public class Main3 {
    public static void main(String[] args) {
        TicTacToe3 t = new TicTacToe3();
    }
}