package Classes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {

    private int dimW=640; // ширина в пиксилях (условно установил, ибо не пропускало)
    public void seiDimW(int dimW){this.dimW = dimW;}

    private int dimH = 640;  // высота в пиксилях (условно установил, ибо не пропускало)
    public void setDimH(int dimH){this.dimH = dimH;}

    public GameField() {
        loadImage();
        initGame();
        addKeyListener(new FiledKeyListtener());
        setFocusable(true);
    }

    private final int DOT_SIZE = 64; // размер одного звена или яблока

    private int[] x = new int[dimW/DOT_SIZE]; //количество точек по x
    private int[] y = new int[dimH/DOT_SIZE]; //количество точек по y

    private Image dot; // картинка звена змейки
    private Image apple; // картинка яблока



    private int appleX;//координаты яблока
    private int appleY;

    private int dots; //количество звеньев в змейке
    private Timer timer; //таймер, который определяет частоту обновления

    private boolean left = false; //булевые переменные, которые показывают куда движется змейка
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    private boolean inGame = true; //переменная которая показывает что мы еще не проиграли



    //Подгружает картинки
    private void loadImage() { //подгружаем наши картинки
        ImageIcon iia = new ImageIcon("apple.jpg");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.jpg");
        dot = iid.getImage();
    }

    private void createApple() { //определяем координаты яблока
//        boolean set = false;
//        while (set) {
            appleX = new Random().nextInt(dimW / DOT_SIZE) * DOT_SIZE;
            appleY = new Random().nextInt(dimH / DOT_SIZE) * DOT_SIZE;
//            for (int i = 0; i < dots; i++)
//                if (appleX == )
//        }
    }

    private void move() {//определяем логику движения змейки
        for (int i = dots; i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if (left) x[0] -= DOT_SIZE;
        if (right) x[0] += DOT_SIZE;
        if (up) y[0] -= DOT_SIZE;
        if (down) y[0] += DOT_SIZE;

    }

    private void checkApple() { //проверяем съела ли наша змейка яблоко
        if (x[0]/DOT_SIZE == appleX/DOT_SIZE && y[0]/DOT_SIZE == appleY/DOT_SIZE) {
            dots++;
            createApple();
        }
    }

    private void checkCollision() {//проверяем ситуации, когда наша змейка в проигрыше
        for (int i = dots; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
        }
        if (x[0] > dimW-1) inGame = false;
        if (x[0] < 0) inGame = false;
        if (y[0] > dimH-1) inGame = false;
        if (y[0] < 0) inGame = false;
    }

    private void initGame() { //инициализируем нашу игру(только при старте)
        dots = 3;
        for (int i = 0; i < dots; i++) {
            y[i] = DOT_SIZE;
            x[i] = (DOT_SIZE*2) - i * DOT_SIZE;
        }
        timer = new Timer(500, this);
        timer.start();
        createApple();
    }

    @Override
    protected void paintComponent(Graphics g) { //отрисовываем яблоки и змейку
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("backGround.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inGame) {
            // станавливаем картинку по центру -(ширина картинки - штрина экрана)/2
            g.drawImage(image, -(1600-dimW)/2, -(1600-dimH)/2, this);
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        } else {
            setBackground(Color.BLACK);
            String str = "Game Ower";
            g.setColor(Color.WHITE);
            g.drawString(str, dimW /2, dimH/2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { //метод который реагирует на наши изминения
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    class FiledKeyListtener extends KeyAdapter { //метод который слушает наши кнопки
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_UP && !down) {
                right = false;
                up = true;
                left = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                down = true;
                left = false;
            }
        }

    }
}


