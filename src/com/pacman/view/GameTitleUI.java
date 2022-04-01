package com.pacman.view;

import com.pacman.controller.GameController;
import com.pacman.utils.Constants;

import showScore.ShowScore;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

public class GameTitleUI {
    private static final String GAME_TITLE = "PACMAN";

    private JFrame window;
    private ImagePanel titleUI;
    private GameView gameUI;
    private GameController controller;
    private Container con;

    public GameTitleUI() {
        initFrame();
        initTileUI();
    }

    private void initFrame() {
        window = new JFrame();
        window.setTitle(GAME_TITLE);
        window.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private void initTileUI() {
        titleUI = new ImagePanel("src\\com\\pacman\\res\\title-background.jpg");
        con = window.getContentPane();

        // Title panel config
        titleUI.setLayout(new BoxLayout(titleUI, BoxLayout.Y_AXIS));

        // sub title panel
        JPanel menuPanel = new JPanel();
        JPanel logoPanel = new JPanel();

        // Logo panel config
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon("src\\com\\pacman\\res\\menu-logo.png"));
        logoPanel.add(logo);
        logoPanel.setBorder(new EmptyBorder(40,0,0,0));
        logoPanel.setOpaque(false);

        // Menu panel config
        menuPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        menuPanel.setPreferredSize(new Dimension(300,290));
        menuPanel.setMaximumSize(new Dimension(300, 290));

        // can le ben duoi
        menuPanel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setOpaque(false);

        MenuButton startBtn = new MenuButton("StartButton.png");
        MenuButton continueBtn = new MenuButton("ContinueButton.png");
        MenuButton scoreBtn = new MenuButton("ScoreButton.png");
        MenuButton optionBtn = new MenuButton("OptionsButton.png");
        MenuButton quitBtn = new MenuButton("QuitButton.png");

        //add button to menu panel
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0,0,5,0);
        c.gridy = 0;
        menuPanel.add(startBtn, c);
        c.gridy = 1;
        menuPanel.add(continueBtn, c);
        c.gridy = 2;
        menuPanel.add(scoreBtn, c);
        c.gridy = 3;
        menuPanel.add(optionBtn, c);
        c.gridy = 4;
        menuPanel.add(quitBtn, c);

        // add to title ui
        titleUI.add(logoPanel);
        titleUI.add(menuPanel);

        // add to frame
        con.add(titleUI);
    }


    // Image button // TODO update thanh sprite
    private class MenuButton extends JLabel implements MouseListener {
        private static final String iconFolPath = "src\\com\\pacman\\res\\MenuButton\\";
        String buttonName;
        String iconName;
        String activeIconName;

        ImageIcon norIcon;
        ImageIcon actIcon;

        public MenuButton(String iconName) {
            super();
            this.buttonName = iconName;
            this.iconName = iconFolPath + iconName;
            this.activeIconName = iconFolPath + "Active" + iconName;

            norIcon = new ImageIcon(this.iconName);
            actIcon = new ImageIcon(activeIconName);

            this.setIcon(norIcon);
            addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if (buttonName.equals("StartButton.png")) {
                titleUI.setVisible(false);

                try {
                    gameUI = new GameView();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                window.addKeyListener(gameUI);
                window.setFocusable(true); // for key listener

                con.add(gameUI);

                try {
                    controller = new GameController(gameUI);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                controller.startGameThread();
                return;
            }

            if (buttonName.equals("ContinueButton.png")) {
                // TODO lam menu continue
                return;
            }

            if (buttonName.equals("ScoreButton.png")) {
            	ShowScore frame;
				try {
					frame = new ShowScore();
					frame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }

            if (buttonName.equals("OptionsButton.png")) {

                return;
            }

            // quit button
            if (buttonName.equals("QuitButton.png")) {
                System.exit(0);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.setIcon(actIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            this.setIcon(norIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    // Background cho main menu
    private class ImagePanel extends JPanel {

        private Image img;

        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }
}
