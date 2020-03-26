package app.c_singletion;

/**
 * 双检锁/双重校验锁（DCL，即 double-checked locking）
 * 懒加载，线程安全
 * SingleonDCL
 */
public class SingleonDCL {
    private volatile static SingleonDCL instance;

    private SingleonDCL() {}
    
    public static SingleonDCL getInstance() {
        if (instance == null) {
            synchronized (SingleonDCL.class) {
                if (instance == null) {
                    instance = new SingleonDCL();
                }
            }
        }
        return instance;
    }
}