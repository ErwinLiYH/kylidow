package kylidow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TXT {
    static ArrayList<String> nameArrayList = null; // to store the name of all .ts file which should be opreated by next steps(used to realize "pause" function)
    static ArrayList<String> URLArrayList = null;  // to store the URL of all .ts file which should be opreated by next steps
    static int lineNum;                     // the number of all URLs

    public TXT(String projectPath) {
        nameArrayList = new ArrayList<String>();     //initialize
        URLArrayList = new ArrayList<String>();      //initialize
        
        File txt = new File(projectPath + File.separator + "URL.txt");
        try {
            FileReader fr = new FileReader(txt);
            BufferedReader br = new BufferedReader(fr);
            TXT.lineNum = Integer.parseInt(br.readLine());    //get the number of URLs
            
            for(int i = 0; i < TXT.lineNum; i++){             //make the URLArrayList
                TXT.URLArrayList.add(br.readLine());
            }
            
            for(String partURL : URLArrayList){               //make the nameArrayList
                String[] partURLlist = partURL.split("/");
                nameArrayList.add(partURLlist[partURLlist.length - 1]);
            }
            
            br.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
            System.out.println("can't find URL.txt");
        } catch (NumberFormatException e) {
            e.getMessage();
            System.out.println("URL.txt file be damaged");
        } catch (IOException e) {
            e.getMessage();
        }
    }
    static void TEMP2TXT(String pathName){
        File URLtxt = new File(pathName + File.separator + "URL.txt");
        URLtxt.delete();
        File newURLtxt = new File(pathName + File.separator + "URL.txt");
        File URLtemp = new File(pathName + File.separator + "URL.temp");
        URLtemp.renameTo(newURLtxt);
    }
}