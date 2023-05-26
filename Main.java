import _16384.controllers.SettingsController;
import _16384.views.Settings;
import _16384.views.UI;

public class Main {
    public static void main(String[] args) {
        new SettingsController(new Settings());
        UI ui = new UI();
        ui.setVisible(true);
    }
}
