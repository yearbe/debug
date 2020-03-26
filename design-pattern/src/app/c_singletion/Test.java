package app.c_singletion;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        SingletonHunger singleObject = SingletonHunger.getInstance();
        singleObject.showMessage();
    }
}