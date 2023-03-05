import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Solution {
    public static final String ext = ".mp3";

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            sayAndWaitEnter("Нет параметров запуска! Ну совсем нет!");
            return;
        }
        File dir = new File(args[0]);
        if (dir.isDirectory()) {
            for (File item : dir.listFiles()) {
                if (item.isDirectory()) {
                    System.out.println("Обработка: " + item.getName() + " ...");
                    Path path = Paths.get(item.getName(), new String[0]);
                    joinFiles(item, item.getPath() + ".mp3");
                }
            }
        } else {
            sayAndWaitEnter("Каталог не обнаружен: " + args[0]);
            return;
        }
        sayAndWaitEnter("Объединение завершено ");
    }

    public static void joinFiles(File folder, String fileName) throws IOException {
        FileOutputStream outputStream = null;
        Path path = Paths.get(fileName, new String[0]);
        boolean isWritten = false;
        for (File item : folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"))) {
            System.out.println(item.getName());
            if (outputStream == null)
                outputStream = new FileOutputStream(fileName);
            Files.copy(Paths.get(item.getPath(), new String[0]), outputStream);
            isWritten = true;
        }
        if (isWritten)
            System.out.println("Результат в: " + fileName);
    }

    public static void sayAndWaitEnter(String str) {
        System.out.println(str);
        System.out.println("Press Enter to quit");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
