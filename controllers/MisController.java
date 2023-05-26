package _16384.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

import _16384.views.Miscellaneous;
import _16384.views.miscellaneous.HowToPlay;
import _16384.views.miscellaneous.WinLose;
import _16384.views.UI;

/**
  * Acts as the event handler for Miscellaneous pages
  *
  * @author SECatral, OMJavier, EAMamaril
  */
public class MisController implements ActionListener, KeyListener, WindowListener {
    /**
      * The Miscellaneous page to act upon
      */
    private Miscellaneous page;

    /**
      * Constructs an instance of a MisController
      *
      * @param p the desired Miscellaneous page to act upon
      */
    public MisController(Miscellaneous p) {
        page = p;
    }

    /**
      * Sends the user back to the UI when button is clicked
      *
      * @param e the detected ActionEvent
      */
    @Override public void actionPerformed(ActionEvent e) {
        if (page instanceof WinLose) UIController.reset();

        page.dispose();
        UI ui = new UI();
        ui.setVisible(true);
    }

    /**
      * Sends the user back to the UI when key "[P]" is clicked
      *
      * @param e the detected KeyEvent
      */
    @Override public void	keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 80) { //"P"
            page.dispose();
            UI ui = new UI();
            ui.setVisible(true);
        }
    }

    /**
      * Warns the user about exiting the page and asks the user to confirm their decision
      *
      * @param e the detected WindowEvent
      */
    @Override public void windowClosing(WindowEvent e) {
        int exit = JOptionPane.showConfirmDialog(null, page.getCloseMsg(), "Are you sure you want to exit?", JOptionPane.YES_NO_CANCEL_OPTION);
        if (exit == JOptionPane.YES_OPTION) {
            if (page instanceof WinLose) System.exit(0);
            else if (page instanceof HowToPlay) {
                page.dispose();
                UI ui = new UI();
                ui.setVisible(true);
            }
        } else {
            if (page instanceof WinLose) {
                UIController.reset();
                page.dispose();
                UI ui = new UI();
                ui.setVisible(true);
            }
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
