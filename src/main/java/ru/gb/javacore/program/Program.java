package ru.gb.javacore.program;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Program {

    public static void main(String[] args) throws IOException {
        creatFiles();
        backupFile(new File("."), "backup");
    }

    /**
     * Метод создания файло для проверки копирования
     *
     * @throws IOException
     */
    static void creatFiles() throws IOException {
        String[] fileNames = new String[10];
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = "file_" + (i + 1) + ".txt";
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileNames[i])) {
                fileOutputStream.write(i + 49);
                System.out.printf("Файл %s был создан.\n", fileNames[i]);
            }
        }
    }

    /**
     * метод копирования файлов в другую директорию (если директория отсутствует. то буде создана)
     * так же идет проверка на повторяющиеся файлы(если есть повторы,
     * то в папке backup файл удаляетя и записывается заново)
     * @param file   от куда копируем
     * @param backup название директории куда копируем
     * @throws IOException
     */
    static void backupFile(File file, String backup) throws IOException {
        if (!Files.exists(Path.of(String.format("./" + backup)))) {
            Path dir = Files.createDirectory(Paths.get(String.format("./" + backup)));
        }
        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                if (!Files.exists(Paths.get(String.format("./" + backup + "/" + files[i])))) {
                    Path path;
                    path = Files.copy(files[i].toPath(), new File(String.format(backup + "/" + files[i])).toPath());
                    System.out.printf("Файл %s был скопирован.\n", files[i].getName());
                } else {
                    Files.delete(Paths.get(String.format(backup + "/" + files[i])));
                    Path path;
                    path = Files.copy(files[i].toPath(), new File(String.format(backup + "/" + files[i])).toPath());
                    System.out.printf("Файл %s был скопирован.\n", files[i].getName());
                }
            }
        }
    }
}
