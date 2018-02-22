package com.bdurdu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen extends JFrame implements ActionListener {
    private JMenuItem imageMenuItem;
    private JMenu open;
    private JLabel imageBox;
    private Image image;

    public Screen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1280, 720));

        JMenuBar menuBar = onCreateMenu();
        this.setJMenuBar(menuBar);

        imageBox = new JLabel();
        add(imageBox, BorderLayout.WEST);

        setVisible(true);
    }

    private JMenuBar onCreateMenu() {
        JMenuBar menuBar = new JMenuBar();
        open = new JMenu("Open");

        imageMenuItem = new JMenuItem("Image");
        imageMenuItem.addActionListener(this);

        menuBar.add(open);
        open.add(imageMenuItem);

        return menuBar;
    }

    private void openFile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                imageBox.setIcon(new ImageIcon(ImageIO.read(file)));
                BufferedImage image = ImageIO.read(file);
                getImageAnalysis(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getImageAnalysis(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println("width, height: " + w + ", " + h);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.println("x,y: " + j + ", " + i);
                int pixel = image.getRGB(j, i);
                printPixelARGB(pixel);
                System.out.println("");
            }
        }
    }

    public void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(imageMenuItem)) {
            openFile();
        }
    }
}
