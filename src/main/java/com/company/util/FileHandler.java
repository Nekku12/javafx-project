
/** This FileHandler class is for opening and saving files.
 *
 * @author: Aikio
 * @version: 1.0
 * @since: 2020.04.29
 * */

/** package */
package com.company.util;

/** import */
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/** class FileHandler*/
public class FileHandler {
    /** string filepath */
    private String filePath;

    /** constructors */
    public FileHandler(String filePath) {
         setFilePath(filePath);
    }

    public FileHandler() {}

    /** getter and setter for filePath! */
    public void setFilePath(String path) {
        this.filePath = path;
    }

    public String getFilePath() {
        return filePath;
    }

    /** method which open the given file and returns the content, uses filePath */
    public String open() {
        /* Path mypath = Paths.get(filePath);
        List<String> lines = Files.readAllLines(mypath, Charset.defaultCharset()); */
        // Read
        String readcontent = "";
        try {
            // read or write here
            readcontent = Files.readString(Paths.get(filePath));
        } catch(IOException e) {
            System.out.println("problem with IO");
            e.printStackTrace();
        }
        return readcontent;
    }

    /** method which Save the content to given file path */
    public void save(String content) {    
        // Write
        try {
            Files.writeString(Paths.get(filePath), content);
        } catch(IOException e) {
            System.out.println("problem with IO");
            e.printStackTrace();
        }
    }

}