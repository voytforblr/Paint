package sample.utils;

import sample.plugins.IPlugin;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Archiver implements IPlugin {

    @Override
    public String getName() {
        return "Архивация";
    }

    @Override
    public String convertTo(String name,String string) throws IOException {
        ZipOutputStream zipOutputStream=new ZipOutputStream(new FileOutputStream("arxive.zip"));
        ZipEntry zipEntry=new ZipEntry(name);
        zipOutputStream.putNextEntry(zipEntry);
        byte[] buffer=string.getBytes();
        zipOutputStream.write(buffer);
        zipOutputStream.close();
        return null;
    }

    @Override
    public String convertFrom(String name,String string) throws IOException {
        String answer="";
        ZipFile zipFile=new ZipFile("arxive.zip");
        Enumeration zipEnum=zipFile.entries();
        while (zipEnum.hasMoreElements()){
            ZipEntry zipEntry1=(ZipEntry) zipEnum.nextElement();
                BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(zipEntry1));
                byte[] buffer1=is.readAllBytes();
                answer= new String(buffer1,"UTF-8");
        }
        return answer;
    }
}
