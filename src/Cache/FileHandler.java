package Cache;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {

    public static boolean createFile(String fileName) {
        try {
            File newFile = new File(fileName);
            return newFile.createNewFile();

        } catch (IOException e) {
            System.out.println("Cache.Exception occurred: " + e);
            return false;
        }
    }

    public static boolean createFolder(String directoryName) {
        File newDirectory = new File(directoryName);
        boolean directoryCreated = newDirectory.mkdir();
        return directoryCreated;
    }


    public static void deleteFile(String FName) {
        try {
            File file = new File(FName);
            if (file.exists()) {
                System.out.println("File deleted: " + file.delete());
            } else {
                System.out.println("File not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public static void writeInFile(String fileName, String text) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(text);
        }
    }




    public static String readFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner fileReader = new Scanner(file);
        String text = fileReader.nextLine();
        fileReader.close();
        return text;
    }

    public static boolean existFile(String fileName){
        File file = new File(fileName);
        return file.exists();
    }

    public static String[] readFolder(String folderName)  {
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        String[] filesNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            filesNames[i] = files[i].getName().replace(".txt", "");
        }
        return filesNames;
    }

    public String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String fileContent = "";
        while (sc.hasNextLine()) {
            fileContent += sc.nextLine();
        }
        return fileContent;
    }


    public static void deleteFolder(String folderName) {
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        for (File file : files) {
            file.delete();
        }
        folder.delete();
    }

    public static void deleteFolderContent(String folderName) {
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        for (File file : files) {
            file.delete();
        }
    }

    public static void deleteFolderContent(String folderName, String extension) {
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(extension)) {
                file.delete();
            }
        }
    }


    public static void deleteFolderContent(String folderName, String[] extensions) {
        File folder = new File(folderName);
        File[] files = folder.listFiles();
        for (File file : files) {
            for (String extension : extensions) {
                if (file.getName().endsWith(extension)) {
                    file.delete();
                }
            }
        }
    }
}
