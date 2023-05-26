package _16384.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import java.util.Random;

import _16384.models.Direction;
import _16384.models.Grid;
import _16384.views.UI;
import _16384.views.Settings;
import _16384.views.miscellaneous.HowToPlay;
import _16384.views.miscellaneous.WinLose;
import _16384.views.uicomponents.PerkBtn;
import _16384.views.uicomponents.PerkView;

/**
  * Acts as the event handler for main UI page
  *
  * @author SECatral, OMJavier, EAMamaril
  */
public class UIController implements ActionListener, KeyListener, WindowListener {
    /**
      * Stores the saved Grid model.
      */
    public static Grid gridModel = new Grid();
    /**
      * Stores the best score achieved by the user.
      */
    public static int best = 0;
    /**
      * Stores the number of DoublePerks attained by the user.
      */
    public static int doublePerkCount = 2;
    /**
      * Stores the number of DeactivatePerks attained by the user.
      */
    public static int deactivatePerkCount = 2;
    /**
      * Stores the main UI to act upon
      */
    private UI playScreen;

    /**
      * Constructs an instance of a UIController
      *
      * @param s the desired UI page to act upon
      */
    public UIController(UI s) {
        playScreen = s;
    }

    /**
      * Replaces the Grid with a new instance
      *
      */
    public static void reset() {
        Grid g = new Grid();
        gridModel = g;
    }

    /**
      * Updates the current UI screen
      *
      */
    private void refresh() {
        playScreen.dispose();
        UI ui = new UI();
        ui.setVisible(true);
    }

    /**
      * Orders the Grid to move blocks in a specified direction & roll the chances of special Tiles. Also displays the WinLose screen after conditions are met.
      *
      * @param d Direction that the user moved
      */
    private void playerMove(Direction d) {
        if (gridModel.moveBlocks(d)) {
            gridModel.generateBlocks();
            gridModel.generateBlocks();
        }

        if (gridModel.getScore() > best)
            best = gridModel.getScore();

        if (gridModel.isWin()) {
            playScreen.dispose();
            Random r = new Random();
            int doubleIncrement = 0, deactivateIncrement = 0;
            for (int i = 0; i < SettingsController.getSavedPerksNum(); i++) {
                if (r.nextDouble() < 0.3) {
                    deactivateIncrement++;
                    deactivatePerkCount++;
                } else {
                    doubleIncrement++;
                    doublePerkCount++;
                }
            }
            WinLose win = new WinLose(true, doubleIncrement, deactivateIncrement);
            win.setVisible(true);

        } else if (gridModel.isLose()) {
            playScreen.dispose();
            WinLose lose = new WinLose(false, 0, 0);
            lose.setVisible(true);
        } else {
            gridModel.rollSpecial();
            refresh();
        }
    }

    /**
      * Displays HowToPlay and Settings screens whenever their respective buttons are pressed; also activates Double and Deactivate Perks whenever their respective buttons are pressed
      *
      * @param e the detected ActionEvent
      */
    @Override public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(playScreen.getHowToBtn())) {
            playScreen.dispose();
            HowToPlay howTo = new HowToPlay();
            howTo.setVisible(true);
        }
        else if (e.getSource().equals(playScreen.getSettingsBtn())) {
            playScreen.dispose();
            Settings settings = new Settings();
            settings.setVisible(true);
        }
        else if (e.getSource() instanceof PerkBtn) {
            ((PerkBtn) e.getSource()).activate();

            PerkView pView = ((PerkBtn) e.getSource()).getPerkView();
            if (pView.getCount() == -1) {
                JOptionPane.showMessageDialog(null, "You have no perks of this type");
                return;
            }

            pView.setDisplayTxt();
            pView.getPerk().perkSpecial(gridModel);
            refresh();
        }

    }

    /**
      * Moves blocks in specified direction if WASD or arrows keys are pressed; displays HowToPlay and Settings if "[H]" and "[G]" are pressed, respectively. activates Double and Deactivate Perks if "[Z]" and "[C]" are pressed, respectively
      *
      * @param e the detected KeyEvent
      */
    @Override public void	keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case 72: //H;
                playScreen.dispose();
                HowToPlay howTo = new HowToPlay();
                howTo.setVisible(true);
                break;
            case 71: //G
                playScreen.dispose();
                Settings settings = new Settings();
                settings.setVisible(true);
                break;
            case 90: //Z
                this.actionPerformed(new ActionEvent(playScreen.getDoubleBtn(), ActionEvent.ACTION_PERFORMED, null));
                break;
            case 67: //C
                this.actionPerformed(new ActionEvent(playScreen.getDeactivateBtn(), ActionEvent.ACTION_PERFORMED, null));
                break;

            case 87: //W
            case 38: //Up
                playerMove(Direction.UP);
                break;

            case 65: //A
            case 37: //Left
                playerMove(Direction.LEFT);
                break;

            case 83: //S
            case 40: //Down
                playerMove(Direction.DOWN);
                break;

            case 68: //D
            case 39: //Right
                playerMove(Direction.RIGHT);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Invalid Key");
        }
    }

    /**
      * Warns the user about exiting the page and asks the user to confirm their decision
      *
      * @param e the detected WindowEvent
      */
    @Override public void windowClosing(WindowEvent e) {
        int exit = JOptionPane.showConfirmDialog(null, "Leaving will lose all your progress", "Leaving?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (exit == JOptionPane.YES_OPTION) {
          playScreen.dispose();
        }
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
