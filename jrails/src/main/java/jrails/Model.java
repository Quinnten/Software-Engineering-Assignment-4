package jrails;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import java.lang.annotation.*;

import java.lang.Exception;
import java.util.List;
import java.util.Scanner;

import java.io.PrintWriter;
import java.io.FileWriter; 
import java.io.FileReader; 
import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.File; 
import java.io.IOException;


public class Model {
    private int id = 0;
    private boolean saved = false;

    private static int GlobalID = 1;
    private static File diskFile;

    public void save() { 
        /* this is an instance of the current model */
        try {
            diskFile = new File("databaseSE.txt");
            //Create a new file to disk if not already
            diskFile.createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
            
        //Get all fields of instance
        Class<?> clazz = this.getClass();
        Field[] fieldz = clazz.getFields();
        ArrayList<Field> annoFields = new ArrayList<>();
        
        //Get fields annotated with the Column class and that they all have a valid return type
        for (Field f : fieldz) {
            //Check annotation
            if (f.getAnnotation(Column.class) != null) {
                //Validate field type
                if (!typeCheck(f)) {
                    throw new RuntimeException(f.getName() + " is an invalid type");
                }
                annoFields.add(f);
            }
        }

        //Insert a new ID into database
        if (id == 0) {
            id = GlobalID;
            GlobalID++;

            try {
                FileWriter writer = new FileWriter("databaseSE.txt", true);
                

                writer.write("#ID " + this.id() + "\n");
                writer.write(clazz.getName());

                for (Field f : annoFields) {
                    writer.write(f.getName() + "\n");
                    //Get the values of each one of the fields
                    Object obj = f.get(this);
                    writer.write(obj + "\n");
                }
                writer.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            //TODO 
            //Replace previous ID
        }        
        saved = true;
    }

    public int id() {
        return id;
    }

    public static <T> T find(Class<T> c, int id) {

        try {
            Constructor<?> cons = c.getConstructor();
            Object instance = cons.newInstance();

            Scanner reader = new Scanner(diskFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                if (data.equals("#ID " + id)) {
                    reader.nextLine();
                    while (reader.hasNextLine() && data.charAt(0) != '#') {
                        data = reader.nextLine();

                    }
                }
            }
        } catch(Exception e){
            throw new RuntimeException("Some error occured in destroy");
        }
        throw new UnsupportedOperationException();
    }

    public static <T> List<T> all(Class<T> c) {
        // Returns a List<element type>
        throw new UnsupportedOperationException();
    }







    public void destroy() {
        if (diskFile == null) {
            throw new UnsupportedOperationException("Can't delete from a file that doesn't exist");
        }

        ArrayList<String> lines = new ArrayList<>();

        //Throw an error if the instance is not saved.
        if (!saved) {
            throw new RuntimeException("Can not destory an unsaved file");
        } 

        //Copy all the lines in the old file into the new array of lines 
        try {
            Scanner reader = new Scanner(diskFile);
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
        } catch(Exception e){
            throw new RuntimeException("Some error occured in destroy");
        }

        //Delete the old file and create a new one
        reset();

        //Write data that's not part of the instance we want to remove to the new file
        try {
            FileWriter writer = new FileWriter("databaseSE.txt", true);
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals("#ID " + id)){
                    lines.remove(i);
                    while (lines.get(i).charAt(0) != '#' && lines.size() > i) {
                        lines.remove(i);
                    }
                }

                writer.write(lines.get(i) + "\n");
            }
            writer.close();
        } catch(Exception e) {
            throw new RuntimeException("Something happened here");
        }
     // Set value of "saved" to false
        saved = false;
    }

       
    







    public static void reset() {
        try {
            if (diskFile == null) {
                diskFile = new File("databaseSE.txt");
                diskFile.delete();
                diskFile = new File("databaseSE.txt");
                diskFile.createNewFile();
            } else {
                diskFile.delete();
                diskFile = new File("databaseSE.txt");
                diskFile.createNewFile();
            }
        } catch (Exception e) {
            throw new RuntimeException("Something happened when you tried to delete and create again");
        }
    }

    // Used to check that the return type for a particular field is a String, int, or boolean
    private static boolean typeCheck(Field f) {
        String type = f.getType().toString();
        if (type.equals("int") || type.equals("boolean") || type.equals("class java.lang.String")) {
            return true;
        } else {
            return false;
        }
    }

}
