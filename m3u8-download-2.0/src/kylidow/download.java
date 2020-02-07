package kylidow;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class download {
    private DecimalFormat DC = new DecimalFormat("00.0%");
    public download(String projectPath, String MP4Name) {
        File TS = new File(projectPath + File.separator + "TS");              //make TS folder to store all the .ts file
        TS.mkdir();
        
        for (int i = 0; i < TXT.lineNum; i++) {
            String thisURL = TXT.URLArrayList.get(i);
            String thisName = TXT.nameArrayList.get(i);
            System.out.println(thisURL);
            System.out.println(thisName);
            if (thisURL.startsWith("**download**")){                      //look whether the URL has be downloaded
                System.out.println(thisName + " has downloaded" + "      < " + DC.format(((double)(i + 1) / TXT.lineNum)) + " >");
            }else{
                try {                                                      //■ begin download
                    URL TSURL = new URL(thisURL);
                    InputStream in = TSURL.openStream();
                    BufferedInputStream bi = new BufferedInputStream(in);
    
                    FileOutputStream out = new FileOutputStream(projectPath + File.separator + "TS" + File.separator + thisName);
                    BufferedOutputStream bo = new BufferedOutputStream(out);           //this will cover original URL.txt, so we shoule write again following
    
                    int len = -1;
                    byte[] b = new byte[1024*1024];
    
                    while((len = bi.read(b)) != -1){
                        bo.write(b, 0, len);
                        bo.flush();
                    }
    
                    bo.close();
                    bi.close();
                    
                    TXT.URLArrayList.set(i, "**download**" + thisURL);                                       //□ creat a temp file URL.temp,
                                                                                                             // after a .ts file downloaded,
                    FileWriter fr = new FileWriter((projectPath + File.separator + "URL.temp"),false);       // sign it in URL.temp.
                    BufferedWriter bw = new BufferedWriter(fr);                                              // when stop progress before every .ts file be downloaded 
                                                                                                             // or some .ts files download failed,
                    bw.write(TXT.lineNum + "\n");                                                            //we can restart progress without redownloading of .ts file which has been downloaded
                    for(String afterURL : TXT.URLArrayList){
                        bw.write(afterURL + "\n");
                        bw.flush();
                    }
                    bw.close();                                                                              //□
                    System.out.println(thisName + " download successfully" + "      < " + DC.format(((double)(i + 1) / TXT.lineNum)) + " >");
                } catch (MalformedURLException e) {
                    e.getMessage();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.out.println("download failed (can't open target resource)");
                }                                                               //■
            }
        }
        //downloading finished before, than begin merger .ts file to a .mp4 file
        for (String partName : TXT.nameArrayList){
            File ts = new File(projectPath + File.separator + "TS" + File.separator +partName);
            if (ts.exists()){
                try {
                    FileInputStream fi = new FileInputStream(projectPath + File.separator + "TS" + File.separator + partName);
                    BufferedInputStream bi = new BufferedInputStream(fi);
    
                    FileOutputStream fo = new FileOutputStream((projectPath + File.separator + MP4Name), true);
                    BufferedOutputStream bo = new BufferedOutputStream(fo);
    
                    int len = -1;
                    byte[] b = new byte[1024*100];
    
                    while((len = bi.read(b)) != -1 ){
                        bo.write(b, 0, len);
                        bo.flush();
                    }
    
                    bo.close();
                    bi.close();
    
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                System.out.println(partName + "is not downloaded");
            }
        }
    }
}