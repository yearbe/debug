package app.c_singletion;

/**
 * 懒汉式
 * 懒加载，线程不安全
 * SingletonLazy
 */
public class SingletonLazy {
    private static SingletonLazy instance;
    private SingletonLazy() {}
    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}