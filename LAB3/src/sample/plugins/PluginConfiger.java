package sample.plugins;

import java.io.File;
import java.util.ArrayList;


public class PluginConfiger implements Configer{
    private String path;
    private PluginLoader pluginLoader;
    private File dir;
    private File[] list;
    private ArrayList<IPlugin> plugins;

    public PluginConfiger(String path)  {
        this.path = path;
    }

    @Override
    public ArrayList<IPlugin> config() throws NoSuchMethodException {
        pluginLoader=PluginLoader.getInstance();
        dir=new File(path);
        list=dir.listFiles();
        plugins=pluginLoader.loadPlugins(list);
        return plugins;
    }
}
