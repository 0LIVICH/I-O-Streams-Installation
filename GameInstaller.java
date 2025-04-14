import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameInstaller {

    public static void main(String[] args) {
        // Укажите ваш путь к папке Games
        String basePath = "C:/Games/";  // Для Windows
        // String basePath = "/Users/username/Games/";  // Для MacOS/Linux

        StringBuilder log = new StringBuilder();

        // 1. Создаем основные директории
        File src = new File(basePath + "src");
        File res = new File(basePath + "res");
        File savegames = new File(basePath + "savegames");
        File temp = new File(basePath + "temp");

        createDir(src, log);
        createDir(res, log);
        createDir(savegames, log);
        createDir(temp, log);

        // 2. Создаем поддиректории в src
        File main = new File(src, "main");
        File test = new File(src, "test");

        createDir(main, log);
        createDir(test, log);

        // 3. Создаем файлы в main
        File mainJava = new File(main, "Main.java");
        File utilsJava = new File(main, "Utils.java");

        createFile(mainJava, log);
        createFile(utilsJava, log);

        // 4. Создаем поддиректории в res
        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");

        createDir(drawables, log);
        createDir(vectors, log);
        createDir(icons, log);

        // 5. Создаем temp.txt
        File tempFile = new File(temp, "temp.txt");
        createFile(tempFile, log);

        // Записываем лог в файл
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(log.toString());
            log.append("Лог успешно записан в temp.txt\n");
        } catch (IOException e) {
            log.append("Ошибка при записи лога: ").append(e.getMessage()).append("\n");
        }

        System.out.println(log);
    }

    private static void createDir(File dir, StringBuilder log) {
        if (dir.mkdir()) {
            log.append("Директория создана: ").append(dir.getAbsolutePath()).append("\n");
        } else {
            log.append("Ошибка создания директории: ").append(dir.getAbsolutePath()).append("\n");
        }
    }

    private static void createFile(File file, StringBuilder log) {
        try {
            if (file.createNewFile()) {
                log.append("Файл создан: ").append(file.getAbsolutePath()).append("\n");
            } else {
                log.append("Ошибка создания файла: ").append(file.getAbsolutePath()).append("\n");
            }
        } catch (IOException e) {
            log.append("Ошибка ввода-вывода: ").append(e.getMessage()).append("\n");
        }
    }
}