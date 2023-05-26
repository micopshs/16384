package _16384.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import _16384.views.Settings;
import _16384.views.UI;

/**
  * Acts as the event handler for Settings page
  *
  * @author SECatral, OMJavier, EAMamaril
  */
public class SettingsController implements ActionListener, ItemListener, KeyListener, WindowListener {
    /**
      * The Settings page to act upon
      */
    private Settings settingsPage;
    /**
      * The difficulty of the game
      */
    private static int savedDifficulty = 3;
    /**
      * The number of perks to be given after a victory
      */
    private static int savedPerksNum = 3;
    /**
      * Determines whether MulTiles can spawn
      */
    private static boolean savedMTiles = true;
    /**
      * Determines whether DivTiles can spawn
      */
    private static boolean savedDTiles = true;
    /**
      * Determines whether BombTiles can spawn
      */
    private static boolean savedBTiles = true;

    /**
      * Constructs an instance of a SettingsController
      *
      * @param s the desired Settings page to act upon
      */
    public SettingsController(Settings s) {
        settingsPage = s;
    }

    /**
      * Accesses the difficulty of the game
      *
      * @return difficulty of the game
      */
    public static int getSavedDifficulty() {
        return savedDifficulty;
    }

    /**
      * Accesses the number of perks to be given after a victory
      *
      * @return the number of perks to be given after a victory
      */
    public static int getSavedPerksNum() {
        return savedPerksNum;
    }

    /**
      * Determines whether or not MulTiles can be spawned
      *
      * @return boolean representing whether or not MulTiles can be spawned
      */
    public static boolean getSavedMTiles() {
        return savedMTiles;

    }

    /**
      * Determines whether or not DivTiles can be spawned
      *
      * @return boolean representing whether or not DivTiles can be spawned
      */
    public static boolean getSavedDTiles() {
        return savedDTiles;
    }

    /**
      * Determines whether or not BombTiles can be spawned
      *
      * @return boolean representing whether or not BombTiles can be spawned
      */
    public static boolean getSavedBTiles() {
        return savedBTiles;
    }

    /**
      * Exits the Settings page; if any changes were made, game resets; otherwise, saved game loads
      *
      * @param hasChanged boolean representing whether or not any changes have been made
      */
    private void exit(boolean hasChanged) {
        if (hasChanged) UIController.reset();
        settingsPage.dispose();
        UI ui = new UI();
        ui.setVisible(true);
    }

    /**
      * Saves any changes made to the Settings page
      *
      * @return boolean representing whether or not any changes have been made
      */
    private boolean save() {
        boolean flag = false;

        if (savedMTiles == (settingsPage.getMTiles().isSelected()) ||
          savedDTiles == (settingsPage.getDTiles().isSelected()) ||
          savedBTiles == (settingsPage.getBTiles().isSelected()) ||
          savedDifficulty != settingsPage.getDifficulty().getValue() ||
          savedPerksNum != settingsPage.getPerksNum().getValue())
            flag = true;

        savedDifficulty = settingsPage.getDifficulty().getValue();
        savedPerksNum = settingsPage.getPerksNum().getValue();
        savedMTiles = !(settingsPage.getMTiles().isSelected());
        savedDTiles = !(settingsPage.getDTiles().isSelected());
        savedBTiles = !(settingsPage.getBTiles().isSelected());

        return flag;
    }

    /**
      * Sends the user back to the UI when the return JButton is clicked
      *
      * @param e the detected ActionEvent
      */
    @Override public void actionPerformed(ActionEvent e) {
        this.exit(this.save());
    }

    /**
      * Changes the text of a JToggleButton component when clicked
      *
      * @param e the detected ItemEvent
      */
    @Override public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        if (state == ItemEvent.SELECTED) {
            ((JToggleButton)e.getSource()).setText("Disabled");
        } else {
            ((JToggleButton)e.getSource()).setText("Enabled");
        }
    }

    /**
      * Sends the user back to the UI when key "[P]" is clicked
      *
      * @param e the detected KeyEvent
      */
    @Override public void	keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 80) { //P
            this.exit(this.save());
        }
    }

    /**
      * Asks the user whether or not they will save settings
      *
      * @param e the detected WindowEvent
      */
    @Override public void	windowClosing(WindowEvent e) {
        int exit = JOptionPane.showConfirmDialog(null, "Do you want to save settings?", "Leaving?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (exit == JOptionPane.YES_OPTION) this.exit(this.save());
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void windowActivated(WindowEvent e) {}
    public void	windowClosed(WindowEvent e) {}
    public void	windowDeactivated(WindowEvent e) {}
    public void	windowDeiconified(WindowEvent e) {}
    public void	windowIconified(WindowEvent e) {}
    public void	windowOpened(WindowEvent e) {}
}
