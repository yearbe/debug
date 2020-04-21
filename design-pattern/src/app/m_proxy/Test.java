package app.m_proxy;

import app.m_proxy.image.Image;
import app.m_proxy.image.ProxyImage;

/**
 * 在代理模式（Proxy Pattern）中，一个类代表另一个类的功能。这种类型的设计模式属于结构型模式。
 * 在代理模式中，我们创建具有现有对象的对象，以便向外界提供功能接口。
 */
public class Test {
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("======================");
        // 图像不需要从磁盘加载
        image.display();
    }
}