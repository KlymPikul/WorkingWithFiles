import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;



public class FileHandler {
    private String path;

    public FileHandler(String path) {
        this.path = path;
        createIfNotExists();
    }
    public static FileHandler createInstance(String path) {
        return new FileHandler(path);
    }

    public String read() {
        try (Scanner scanner = new Scanner(new File(this.path))) {
            String result = "";
            while (scanner.hasNextLine()){
                String newLine = scanner.nextLine();
                result += newLine + '\n';
            }
            scanner.close();
            return result;
            //System.out.println("Прочитали файл");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
        return "";
    }


    public  void  createIfNotExists() {
        File file = new File(this.path);
        try {
            boolean result = file.createNewFile();
            if (result) {
                System.out.println("Файл успешно создан.");
            } else {
                System.out.printf("Файл [%s] уже существует.%n", this.path);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

     // Возвращает строку с размером файла и корректным постфиксом (KB, MB, GB, TB)
    public  String getLength() {
        File file = new File(this.path);
        long length = file.length();

        if (length < 1024) {
            return length + " bytes";
        } else if (length < 1024 * 1024) {
            return String.format("%.2f KB", (double) length / 1024);
        } else if (length < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", (double) length / (1024 * 1024));
        } else {
            return String.format("%.2f GB", (double) length / (1024 * 1024 * 1024));
        }
    }

    //Переименование файла по новому пути.
    public void rename(String newPath) {
        File file = new File(this.path);
        File newFile = new File(newPath);
        if (file.renameTo(newFile)) {
            System.out.println("Файл успешно переименован.");
        } else {
            System.out.println("Не удалось переименовать файл.");
        }
    }

    // Создает файлы на основе списка путей.
    public static void createFiles(ArrayList<String> paths) {
        for (String path : paths) {
            FileHandler fileHandler = new FileHandler(path);
            System.out.println(fileHandler);
        }
    }

    @Override
    public String toString() {
        File file = new File(this.path);
        return String.format(
                "File: %s (length: %d bytes, path: %s)%n",
                file.getName(),
                file.length(),
                file.getAbsolutePath()
        );
    }

}
