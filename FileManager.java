package OOP_final_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class FileManager {
	private String file_name;
	public FileManager(String caller) {
        switch (caller) {
            case "addGUI":
            case "browseGUI":
            case "modifyGUI":
            case "searchGUI":
            case "ShoppingCart":
            case "ShoppingGUI":
                this.file_name = "storage.txt";
                break;
            case "revenueGUI":
                this.file_name = "revenue.txt";
                break;
            case "reviewGUI":
                this.file_name = "review.txt";
                break;
            default:
                throw new IllegalArgumentException("Unknown caller: " + caller);
        }
    }
    public Vector<String>read() {
    	Vector <String> data = new Vector<String>();
        try {
            Scanner myS = new Scanner(new File(file_name));
            while (myS.hasNextLine()) {
                data.add(myS.nextLine());
            }
            
            myS.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file_name);
            e.printStackTrace();
        }
        return data;
    }
    public void save(Vector<String>list_of_items) {
    	try {
    		FileWriter fw = new FileWriter(new File(file_name));
    		for (int i = 0; i <list_of_items.size();i++) {
    			 fw.write(list_of_items.get(i));
    			 fw.write("\n"); 
    		}
    		fw.close();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
}
