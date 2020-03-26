package app.c_singletion;

/**
 * 懒汉式
 * 懒加载，线程安全
 * SingletonLazySafe
 */
public class SingletonLazySafe {

    private static SingletonLazySafe instance;

    private SingletonLazySafe() {
    }

    public static synchronized SingletonLazySafe getInstance() {
        if (instance == null) {
            instance = new SingletonLazySafe();
        }
        return instance;
    }
}