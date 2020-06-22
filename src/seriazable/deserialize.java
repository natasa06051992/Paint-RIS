package seriazable;

import geometry.*;

import java.io.*;
import java.util.ArrayList;

public class deserialize {

    Shape shape= null;
    ArrayList<Shape> shapes = null;
    public ArrayList<Shape> deserialize(String location){
        ObjectInputStream in=null;
        FileInputStream input=null;
        try {
            shapes = new ArrayList<Shape>();
             input = new FileInputStream(location);
             in = new ObjectInputStream(input);

            while ((shape = (Shape) in.readObject()) != null) {

                if (shape instanceof Point) {
                    shapes.add((Point) shape);
                } else if (shape instanceof Line) {
                    shapes.add((Line) shape);
                } else if (shape instanceof Circle) {
                    shapes.add((Circle) shape);
                } else if (shape instanceof Square) {
                    shapes.add((Square) shape);
                } else if (shape instanceof Rectangle) {
                    shapes.add((Rectangle) shape);
                } else if (shape instanceof HexagonAdapter) {
                    shapes.add((HexagonAdapter) shape);
                }
            }
        }catch (EOFException ex) {
                System.out.println("File is opend.");
        }catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        finally {
            try {
                // Close the input stream.
                in.close();
                input.close();
                return shapes;
            }
            catch(IOException ex) {
                System.err.println("An IOException was caught: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return shapes;
    }
}
