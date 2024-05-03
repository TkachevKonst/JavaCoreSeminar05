package ru.gb.javacore.program;

import java.io.File;

public class Tree {
    public static void main(String[] args) {
        printTree(new File("."), "", true);
    }

    static void printTree(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());
        File[] files = file.listFiles();



        //Добавил проверку на пустой массив (если будет файл, то массив будет пустой)
        if (files != null) {
            int sumDirTotal = 0;
            int sumFileTotal = 0;
            int sumDirCount = 0;
            int sumFileCount = 0;
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    sumDirTotal++;
                } else if (files[i].isFile()) {
                    sumFileTotal++;
                }
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    printTree(files[i], indent, sumDirTotal == ++sumDirCount);
                } else if (files[i].isFile()) {
                    printTree(files[i], indent, sumFileTotal == ++sumFileCount);
                }
            }
        }
    }

}
