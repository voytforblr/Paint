package sample.plugins;

import java.io.IOException;

public interface IPlugin {
     String getName();
     String convertTo(String name,String string) throws IOException;
     String convertFrom(String name, String string) throws IOException;
}
