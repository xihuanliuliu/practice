package com.jd.edi.hive;

import com.alibaba.fastjson.JSON;

public class EtlUtils {


    /**
     * 切割视频类别种类
     *
     * c1 & c2 & c3
     */

    public static String combatCategory(String ori) {
        // ori= c1\tc2\tc3
        StringBuilder etlString = new StringBuilder();
        String[] splits = ori.split("\\t");
        if(splits.length < 9) return null;
        splits[3] = splits[3].replace(" ", "");
        for(int i = 0; i < splits.length; i++){
            if(i < 9){
                if(i == splits.length - 1){
                    etlString.append(splits[i]);
                }else{
                    etlString.append(splits[i] + "\t");
                }
            }else{
                if(i == splits.length - 1){
                    etlString.append(splits[i]);
                }else{
                    etlString.append(splits[i] + "&");
                }
            }
        }
        return etlString.toString();
    }


    public static void main(String[] args) {
        String ori = "c1\tc2\tc3";
        String ori1 = "";
        String ori2 = "c1";
        String ori3 = "c1\tc2";
        System.out.println(combatCategory(ori));
        System.out.println(combatCategory(ori1));
        System.out.println(combatCategory(ori2));
        System.out.println(combatCategory(ori3));

    }
}
