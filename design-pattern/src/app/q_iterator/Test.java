package app.q_iterator;

import app.q_iterator.iterator.Iterator;
import app.q_iterator.iterator.NameRepository;

/**
 * 迭代器模式（Iterator Pattern）是 Java 和 .Net 编程环境中非常常用的设计模式。
 * 这种模式用于顺序访问集合对象的元素，不需要知道集合对象的底层表示。
 * 迭代器模式属于行为型模式。
 */
public class Test {
    public static void main(String[] args) {
        NameRepository repository = new NameRepository();
        for (Iterator iter = repository.getIterator(); iter.hasNext();) {
            String name = (String) iter.next();
            System.out.println("Name: " + name);
        }
    }
}