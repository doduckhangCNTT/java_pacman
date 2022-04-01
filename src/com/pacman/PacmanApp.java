package com.pacman;

import com.pacman.view.GameTitleUI;

import java.awt.*;

public class PacmanApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GameTitleUI gameTitleUI= new GameTitleUI();
        });
    }
}
