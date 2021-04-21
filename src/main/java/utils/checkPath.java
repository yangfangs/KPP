package utils;

import java.io.File;
import java.io.IOException;

public class checkPath {

    public static String checkInputFile(String inputPath){
        String checkPath;
        File file = new File(inputPath);
        File f= new File(".");
        String path = null;
        try {
            path = f.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(inputPath.startsWith("/")){
            checkPath = inputPath;
        }else {
            checkPath = path + "/" + inputPath;


        }
        return checkPath;


    }
    public static String checkOutputFile(String outputPath){
        String checkPath;
        String path = null;
        File f= new File(".");
        try {
            path = f.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(outputPath.startsWith("/")){
            checkPath = outputPath;
        }else {
            checkPath = path + "/" + outputPath;
        }
        return checkPath;
    }
    public static String checkOutputFileIndex(String outputPath){
        String checkPath;
        String path = null;
        File f= new File(".");
        try {
            path = f.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(outputPath.startsWith("/")){
            checkPath = outputPath;
        }else {
            checkPath = path + "/Index/" + outputPath;
        }
        return checkPath;
    }
    public static String checkOutputFileProfile(String outputPath){
        String checkPath;
        String path = null;
        File f= new File(".");
        try {
            path = f.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(outputPath.startsWith("/")){
            checkPath = outputPath;
        }else {
            checkPath = path + "/" + outputPath +"/";
        }
        return checkPath;
    }
}
