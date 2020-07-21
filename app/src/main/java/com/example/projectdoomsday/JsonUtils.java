package com.example.projectdoomsday;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class JsonUtils {

    Context mainContext;
    private HashMap<String, String> itemValues;

    public JsonUtils(Context mainContext)
    {
        this.mainContext = mainContext;
    }


    //Read json file and return a json array
    public String readJsonFile(String fileName)
    {
        FileInputStream fileInputStream = null;
        String text;
        StringBuilder stringBuilder = new StringBuilder();

        try
        {
            fileInputStream = mainContext.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while((text = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(text).append("\n");
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return stringBuilder.toString();
    }

    public void writeItemToJson(Item item, String fileName, int mode)
    {
        try
        {
            JSONObject newItem = new JSONObject();
            newItem.put("ID", item.getID());
            newItem.put("Title",item.getTitle());

            writeStringToJson(newItem.toString(), fileName, mode);
        }
        catch (JSONException e)
        {
            System.out.println("Could not write object to json file: " + e.getMessage());
        }

    }

    public void writeStringToJson(String toWrite, String fileName, int mode)
    {
        FileOutputStream fileOutputStream = null;

        try
        {
            fileOutputStream = mainContext.openFileOutput(fileName, mode);
            fileOutputStream.write(toWrite.getBytes());
        }
        catch(IOException e)
        {
            System.out.println("Could not write object to json file: " + e.getMessage());
        }

        finally
        {
            if(!(fileOutputStream == null))
            {
                try
                {
                    fileOutputStream.close();
                }
                catch (IOException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    public HashMap<String, String> getItemValues() {
        return itemValues;
    }

    public void setItemValues(HashMap<String, String> itemValues) {
        this.itemValues = itemValues;
    }


}
