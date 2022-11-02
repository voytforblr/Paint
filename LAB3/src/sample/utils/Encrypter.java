package sample.utils;

import sample.plugins.IPlugin;

import java.io.IOException;

public class Encrypter implements IPlugin {
    @Override
    public String getName() {
        return "Шифрация";
    }

    @Override
    public String convertTo(String name, String string) throws IOException {
        int heigh=(int)Math.pow(string.length(),1.0/4);
        return encryptionHedge(string,heigh);
    }

    @Override
    public String convertFrom(String name, String string) throws IOException {
        int heigh=(int)Math.pow(string.length(),1.0/4);
        return decryptionHedge(string,heigh);
    }



    public static String encryptionHedge(String str, int height){
        StringBuilder answer=new StringBuilder();
        for (int i = 0; i <str.length(); i++) {
            answer.append((char)(str.charAt(i)+height));
        }
        return answer.toString();
    }

    public static String decryptionHedge(String str, int height){
        StringBuilder answer=new StringBuilder();
        for (int i = 0; i <str.length(); i++) {
            answer.append((char)(str.charAt(i)-height));
        }
        return answer.toString();
    }


}
