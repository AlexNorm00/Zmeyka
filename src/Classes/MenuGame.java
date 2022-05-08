package Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//создаём меню (отдельную панель) и подключаем слушателя кнопок
public class MenuGame extends JPanel implements ActionListener {

    public MenuGame() {
        setBackground(Color.BLACK);

        addKeyListener(new FiledKeyListener());
        setFocusable(true);
    }

    Color color1 = Color.YELLOW; // цвет тсрок меню
    Color color2 = Color.WHITE; // цвет тсрок меню
    Color color3 = Color.WHITE; // цвет тсрок меню

    @Override
    protected void paintComponent(Graphics g) { //отрисовываем меню
        super.paintComponent(g);
        g.setColor(color1);
        g.drawString("Start game", 140, 160);
        g.setColor(color2);
        g.drawString("Setting", 149, 180);
        g.setColor(color3);
        g.drawString("Exit", 156, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {//метод который реагирует на наши изминения
        repaint();
    }

    class FiledKeyListener extends KeyAdapter { //метод который слушает наши кнопки
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_UP) {
                if (color1 != Color.YELLOW) {
                    if (color2 != Color.YELLOW) {
                        color2 = Color.YELLOW;
                        color3 = Color.WHITE;
                    } else {
                        color1 = Color.YELLOW;
                        color2 = Color.WHITE;
                    }
                }
            }

            if (key == KeyEvent.VK_DOWN) {
                if (color3 != Color.YELLOW) {
                    if (color2 != Color.YELLOW) {
                        color2 = Color.YELLOW;
                        color1 = Color.WHITE;
                    } else {
                        color3 = Color.YELLOW;
                        color2 = Color.WHITE;
                    }
                }
            }
        }
    }

}

