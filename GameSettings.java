import java.io.*;
import java.util.Properties;

public class GameSettings {

    private static final String SETTINGS_FILE = "game.settings";
    private Properties properties;

    public GameSettings() {
        properties = new Properties();
        loadSettings();
    }

    public void setSetting(String key, String value) {
        properties.setProperty(key, value);
        saveSettings();
    }

    public String getSetting(String key) {
        return properties.getProperty(key);
    }

    private void loadSettings() {
        try (InputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            // Файл не существует - создадим при первом сохранении
        }
    }

    private void saveSettings() {
        try (OutputStream output = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(output, "Game Settings");
        } catch (IOException e) {
            System.err.println("Ошибка сохранения настроек: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GameSettings settings = new GameSettings();

        // Установка настроек
        settings.setSetting("resolution", "1920x1080");
        settings.setSetting("volume", "80");
        settings.setSetting("fullscreen", "true");

        // Получение настроек
        System.out.println("Resolution: " + settings.getSetting("resolution"));
        System.out.println("Volume: " + settings.getSetting("volume"));
        System.out.println("Fullscreen: " + settings.getSetting("fullscreen"));
    }
}