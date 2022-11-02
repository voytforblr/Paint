package sample.plugins;

import java.util.ArrayList;

public interface Configer {
     ArrayList<IPlugin> config() throws NoSuchMethodException;
}
