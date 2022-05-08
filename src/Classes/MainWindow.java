package Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowStateListener;

public class MainWindow extends JFrame {

    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//текущий размер экрана

    public MainWindow(){
        setTitle("Змейка Overone"); // текст в заголовке окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setSize(dim.width, dim.height); //Устрановить размер окна
        setLocationRelativeTo(null); //фрейм по центру экрана
        setResizable(false); // отключить возможность изменение размера окна
        //add(new MenuGame())
        GameField GF = new GameField();
        GF.seiDimW(dim.width);
        GF.setDimH(dim.height);
        add(GF);
        setVisible(true);
        //add(new GameField());
        //setVisible(true);
    }


    public static void main (String[] args){
        MainWindow mainWindow = new MainWindow();
    }
}
