package com.cy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**cn.sunyard.encrypt.Base64.<init>(Base64.java:3)  Class "Base64" has 0 references Base64  org.ucdetector.analyzeMarkerReference 
 * cn.sunyard.model.AppModel.<init>(AppModel.java:66) Interface "AppModel.Observer" has 0 references  AppModel.Observer org.ucdetector.analyzeMarkerReference 
 * com.chinaums.pospassport.POSPassportPayActivity.<init>(POSPassportPayActivity.java:314)  Member class "POSPassportPayActivity.POSPassportBindCardItemInfo" has 0 references  POSPassportPayActivity.POSPassportBindCardItemInfo  org.ucdetector.analyzeMarkerReference Xuechao Wang <br/>
 * com.sunyard.chinaums.common.cons.UmsConstant.<init>(UmsConstant.java:210)  Enum "UmsConstant.OrderFunctionTagTable" has 0 references UmsConstant.OrderFunctionTagTable org.ucdetector.analyzeMarkerReference 
 *com.sunyard.chinaums.common.cons.UmsConstant.<init>(UmsConstant.java:49)  Interface "UmsConstant.SaleType" has 0 references UmsConstant.SaleType  org.ucdetector.analyzeMarkerReference 
 * @author wangxc <br/>
 */
public class ResourceRemover {
    private static final String TAG = ResourceRemover.class.getName();
    
    static final String pathResult="F:/projects/projects-ecilpse_android/ucdetector_reports/UCDetectorReport_003.txt";

    
    public static void main(String[] args){
        String pathProject = "F:/projects/android2/umsqmf2g-2.2.0_develop";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathResult));
            String line;
            int count = 0;
            int count_innerClass=0;
            int count_class=0;
            while((line = reader.readLine()) != null) {
                count++;
                if(isInnerClass(line)) {
                    count_innerClass++;
                    System.out.println("inner class:"+line);
                    continue;
                }
                
                if (line.contains("has 0 references")) {
                    count_class++;
                    int end = line.indexOf("<");
                    if (end != -1){
                        line = line.substring(0, end-1);
                        line=line.replace(".", "/");
                        String f = pathProject +"/src/"+line+".java";
                        System.out.println(f);
                        new File(f).delete();
                    }
                }
            }
            reader.close();
            System.out.println("count:"+count+"  count_innerClass:"+count_innerClass+"  count_class:"+count_class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    /**
     */
    private static boolean isInnerClass(String line){
        Pattern pattern = Pattern.compile("\"[a-zA-Z_0-9]+\\.[a-zA-Z_0-9]+\" has 0 references");// \\.→\.字符串内\代表转义
        Matcher matcher = pattern.matcher(line);
        return matcher.find();//部分匹配，matcher.matches()为全匹配
    }
}

