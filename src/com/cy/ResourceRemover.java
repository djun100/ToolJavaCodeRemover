package com.cy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**F:\android\android-sdk\tools>lint --check "UnusedResources" F:\projects\android2\
 * umsqmf2g-2.2.0_develop > result.txt
 * @author wangxc <br/>
 */
public class ResourceRemover {
    private static final String TAG = ResourceRemover.class.getName();
    
    static final String pathResult="F:/android/android-sdk/tools/result.txt";

    
    public static void main(String[] args){
        String pathProject = "F:/projects/android2/umsqmf2g-2.2.0_develop";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathResult));
            String line;
            int count = 0;
            while((line = reader.readLine()) != null) {
                if (line.contains("UnusedResources") && !line.contains("res\\values") && !line.contains("appcompat")) {
                    count++;
                    int end = line.indexOf(":");
                    if (end != -1){
                        String file = line.substring(0, end);
                        String f = pathProject +"/"+file;
                        System.out.println(f);
                        new File(f).delete();
                    }
                }
            }
            reader.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

}

