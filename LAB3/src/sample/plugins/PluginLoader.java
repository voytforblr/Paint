package sample.plugins;

import java.lang.reflect.*;
import java.io.*;
import java.util.ArrayList;

public class PluginLoader {
    private static PluginLoader instance;
    private Method resolve;
    private Method define;
    public static PluginLoader getInstance() throws NoSuchMethodException {
        if(instance == null){
            instance = new PluginLoader();
        }
        return instance;
    }
    private PluginLoader() throws NoSuchMethodException, SecurityException{
        Class<ClassLoader> c=ClassLoader.class;
        define = c.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
        resolve = c.getDeclaredMethod("resolveClass", Class.class);
        define.setAccessible(true);//методы - protected
        resolve.setAccessible(true);
    }


    public void PrintErorInfo(Exception e){
        System.out.print("FAIL; file skipped");
        e.printStackTrace();
    }
    //Загружаем плагины из списка
    public ArrayList<IPlugin> loadPlugins(File[] list){
        ArrayList<IPlugin> res = new ArrayList<IPlugin>();//Результат
        FileInputStream reader;//Поток для чтения
        ClassLoader c = ClassLoader.getSystemClassLoader();//Системный ClassLoader
        Class<?> cl;   //Загруженный класс
        Object instance;  //Его экземпляр
        for (File f : list) {

            try {
                reader = new FileInputStream(f);//Инициализируем
                byte[] b = new byte[reader.available()];//Читаём всё.
                reader.read(b);
                reader.close();

                cl = (Class<?>) define.invoke(c, null, b, 0, b.length);//Вызываем названный метод defineClass. От лица
                //Системного класслоадера, имя мы не знаем, читаем полностью от 0 до b.length
                resolve.invoke(c, cl);/// c.resolveClass(cl)
                instance = cl.newInstance();//Вызываем конструктор без аргументов
                //Если это плагин, то добавляем в результат
                if (instance instanceof IPlugin){
                    res.add((IPlugin)instance);
                }
            } catch (Exception e) {
                PrintErorInfo(e);//Отправляем ошибку в лог и идём дальше
            }
        }
        return res;//Возвращаем всё что загрузили
    }
}