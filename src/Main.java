import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/*
Доработайте класс FileHandler и добавьте приватный метод getLength, который возвращает строку размера файла с корректным постфиксом (kb, mb, gb, tb).

Добавьте метод rename, который получает в качестве аргумента новый путь к файлу и переименовывает файл.

Добавьте статический метод, который получает ArrayList с путями к файлам и создает их.
 */

public class Main {
    public static void main(String[] args) {
        FileHandler users = new FileHandler("src/users.txt");

        System.out.println(users.read());
        System.out.println(users.getLength());
        System.out.println(users.getLength2("bytes"));
        System.out.println( users.getLength2("kb"));
        System.out.println(users.getLength2("mb"));
        System.out.println( users.getLength2("gb"));
        users.rename("src/users_new.txt");


        ArrayList<String> fileList = new ArrayList<>();
        fileList.add("src/file1.txt");
        fileList.add("src/file2.txt");
        fileList.add("src/file3.txt");

        FileHandler.createFiles(fileList);

    }
}
