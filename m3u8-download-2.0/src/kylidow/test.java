package kylidow;

import java.io.File;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        String m3u8Path;
        String ProjectLocation;
        String ProjectName;
        String pathName;
        String mp4Name;
        String Switchh;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("new download project or restart a project ? \"N\" for new , \"R\" for restart");
        Switchh = input.nextLine();
        
        if (Switchh.equals("N")){
            System.out.println("m3u8 file path :");
            m3u8Path = input.nextLine();

            System.out.println("work space :");
            ProjectLocation = input.nextLine();

            System.out.println("Project name :");
            ProjectName = input.nextLine();
        
            System.out.println("mp4 name :");
            mp4Name = input.nextLine();

            input.close();

            pathName = ProjectLocation + File.separator + ProjectName;

            new M3U8(m3u8Path, ProjectLocation, ProjectName);
            new TXT(pathName);
            new download(pathName, mp4Name);
            TXT.TEMP2TXT(pathName);
        }else if(Switchh.equals("R")){
            System.out.println("project path :");
            pathName = input.nextLine();

            System.out.println("mp4 name :");
            mp4Name = input.nextLine();
            
            if(new File(pathName + File.separator + "URL.temp").exists()){
                TXT.TEMP2TXT(pathName);
            }
            new TXT(pathName);
            new download(pathName, mp4Name);
            TXT.TEMP2TXT(pathName);
        }else{
            System.out.println("Error input");
        }
        
        /* new M3U8("C:\Users\17795\Desktop\test.m3u8", "C:\Users\17795\Desktop", "TEST");
        new TXT("C:\Users\17795\Desktop\TEST");
        new download(("C:\Users\17795\Desktop\TEST"),"1234.mp4"); */

        
    }
}