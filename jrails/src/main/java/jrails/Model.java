package jrails;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import java.lang.annotation.*;

import java.util.Scanner;
import java.io.FileWriter; 
import java.io.File; 
import java.io.IOException;
import java.lang.Exception;
import java.util.List;

public class Model {
    private int id = 0;
    private boolean saved = false;

    private static int GlobalID = 1;
    private static Scanner reader;
    private static File diskFile;

    public void save() { 
        /* this is an instance of the current model */
        try {
            diskFile = new File("databaseSE.txt");
            //Create a new file to disk if not already
            if (diskFile.createNewFile()) {
                System.out.println("File created: " + diskFile.getName());
            } else {
                System.out.println("File already exists.");
            }
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
                    System.out.println("Before write");

        //Insert a new ID into database
        if (id == 0) {
            id = GlobalID;
            GlobalID++;

            try {
                FileWriter writer = new FileWriter("databaseSE.txt", true);
                

                writer.write("#ID\n");
                writer.write(this.id() + "\n");


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
        throw new UnsupportedOperationException();
    }

    public static <T> List<T> all(Class<T> c) {
        // Returns a List<element type>
        throw new UnsupportedOperationException();
    }

    public void destroy() {
        if (saved) {
            //Do some work
            throw new RuntimeException("Can not destory an unsaved file");
        } else {
            throw new RuntimeException("Can not destory an unsaved file");
        }
    }

    public static void reset() {
        try {
            diskFile.delete();
            diskFile = new File("databaseSE.txt");
            diskFile.createNewFile();
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
