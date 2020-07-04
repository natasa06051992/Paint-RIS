package seriazable;

import geometry.Shape;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/* *
 * * The Serialize class that serialize all shapes
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class Serialize {

    /**
     * This method serialize all shapes on input location
     * @param location Location where file will be created
     * @param shapes All shapes that will be serialized
     */
    public void serialize(String location, ArrayList<Shape> shapes){

        try
        {
            String fileLocation = location.endsWith(".ser")? location: location+".ser";

            FileOutputStream file = new FileOutputStream(fileLocation);
            ObjectOutputStream out = new ObjectOutputStream(file);

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
