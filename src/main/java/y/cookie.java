package y;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cookie {
    ArrayList<String> cookies = new ArrayList<>();
    //private static final File File = null;
    //List<String> cookies = null;
    // constructor is automatically added to class unless u have smth to do with constructor
    // 
    public void readCookiesFile(String fileName) throws IOException{

        //instantiate the cookies collection object
        
        // cookies = new ArrayList<String>()



        // use a File object to pass the fileName
        File cookieFile = new File(fileName);


        // use FileReader and BufferReader for reading from cookie file
        // instantiate a File Reader follow by a BufferedReader
        FileReader fr = new FileReader(cookieFile);
        BufferedReader br = new BufferedReader(fr);
        


        // while loop to loop through the file
        // read each line and add into the cookies collection object
        String idiom ="";
        while ((idiom = br.readLine())!=null){
            cookies.add(idiom);
        }

        // close the BufferReader and FileReader in reverse order
        fr.close();
        br.close();
    }

    
    public String getRandomCookie(){
        // use Math random function to get random item from cookies collection object
        Random random = new Random();


        //check if cookies collection are loaded
        //if cookies collection loaded, then generate and return random cookie
        // TO RUN AND CHECK THE COOKIES java y/App data\cookie.txt 3000
        if (cookies == null){
            return "There is no cookie.";
        } else{
            String randomCookie = cookies.get(random.nextInt(cookies.size()));
            return randomCookie;
        }
    }
    }

   

