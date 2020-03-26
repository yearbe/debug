package app.c_singletion;

/**
 * 饿汉式
 * 非懒加载，多线程安全
 * Singleton
 */
public class SingletonHunger {
    private static SingletonHunger instance = new SingletonHunger();

    private SingletonHunger() {
    }

    public static SingletonHunger getInstance() {
        return instance;
    }
    public void showMessage() {
        System.out.println("Single object.");
    }
}