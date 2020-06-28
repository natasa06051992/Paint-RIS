package seriazable;

import geometry.Shape;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serialize {
    private static final long serialversionUID = 129348938L;
    public void serialize(String location, ArrayList<Shape> shapes){
        // Serialization
        try
        {
            String fileLocation = location.endsWith(".ser")? location: location+".ser";
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(fileLocation);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            shapes.forEach(sh-> {
                try {
                    out.writeObject(sh);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            out.close();
            file.close();

        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
}
