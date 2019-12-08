package com.example.phase1;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;

/** A file system used to save and load all data. */
public class FileSystem {

    /** This is used to make sure the save location to be app\data\files. */
    private Context context;

    /** Constructs a file system with given context. */
    public FileSystem(Context appContext){this.context = appContext;}

    /** Save object to fileName. */
    public <T> void save(T object, String fileName){
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            objectOutputStream.writeObject(object);
            objectOutputStream.close();

        }
        catch (IOException e){
            Log.e("Exception", "Cannot save " + fileName);}
    }

    /** Load object from fileName. */
    public <T> T load(String fileName){
        T ret = null;
        try{
            InputStream inputStream = context.openFileInput(fileName);
            if(inputStream != null){
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                ret = (T) objectInputStream.readObject();
                inputStream.close();
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("File not found.");
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return ret;
    }
}
