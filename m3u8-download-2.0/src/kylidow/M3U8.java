package kylidow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class M3U8 {                                                  //analysis m3u8 file and creat URL.txt
    public M3U8(String m3u8Path, String projectPath, String projectName) {
        
        File aM3U8File = new File(m3u8Path);                                          //the m3u8 file which we need
        File projectFile = new File(projectPath + File.separator + projectName);
        File URLlist = new File(projectPath + File.separator + projectName + File.separator + "URL.txt");      //the URL file(used to realize "pause" function)
        
        if(!projectFile.exists()){
            projectFile.mkdir();                                     //make project file
        }else{
            System.out.println("make project file failed");
        }
        
        if (!URLlist.exists()) {                                     //■ creat URL.txt to store every URL
            try {
                URLlist.createNewFile();
            } catch (IOException e) {
                e.getMessage();
                System.out.println("creat URL.tet failed");
            }
        }                                                            //■
        
        try {                                                        //■ analysis m3u8 file
            String temString = "";
            int lineNum = 0;                                         //use to calculate the number of lines
            FileReader fr = new FileReader(aM3U8File);               //  and generate URL.txt
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter(URLlist);
            BufferedWriter bw = new BufferedWriter(fw);

            while(true){
                String aLine = br.readLine();
                if(aLine.startsWith("#")){
                    if(aLine.equals("#EXT-X-ENDLIST")){
                        break;
                    }
                }else if(aLine.equals("")){

                }else if(aLine == "null"){

                }else{
                    lineNum += 1;
                    temString += (aLine + "\n");
                }
            }
            bw.write(lineNum + "\n" + temString);
            bw.flush();
            bw.close();
            br.close();
		} catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
		} catch (IOException e) {
            System.out.println(e.getMessage());
        }                                                            //■
    }
}
