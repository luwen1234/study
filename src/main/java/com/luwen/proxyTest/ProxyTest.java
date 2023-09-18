package com.luwen.proxyTest;

import com.test.proxyTest.model.Action;
import com.test.proxyTest.model.Person;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

public class ProxyTest {
    public static void main(String[] args) {
        System.out.println("===InvocationHandlerTest1===");
        InvocationHandlerTest1();
        System.out.println("===InvocationHandlerTest2===");
        InvocationHandlerTest2();
        System.out.println("===cglibTest===");
        cglibTest();
    }

    /**
     * JDK动态代理测试
     * JDK动态代理是接口代理
     * 利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理
     */
    public static void InvocationHandlerTest1() {

        // 1. 只代理接口，通过Action拿个action方法，在action方法调用前后做操作
        Action action = (Action) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                new Class[]{Action.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (Objects.equals("action", method.getName())) {
                            System.out.println("====action======");
                        }
                        return null;
                    }
                });
        // 2. 执行代理类
        action.action();
    }

    public static void InvocationHandlerTest2() {

        Person person = new Person();
        // 1. 代理Person对象
        Action action = (Action) Proxy.newProxyInstance(person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        method.invoke(person, null);
                        return null;
                    }
                });
        // 2. 执行代理类
        action.action();
    }

    /**
     * cglib动态代理是利用asm开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理
     */
    public static void cglibTest() {

        Person person = new Person();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(person.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("cglib proxy action ...");
                return null;
            }
        });
        // asm字节码生成也给person代理类
        Person personProxy = (Person) enhancer.create();
        personProxy.action();
    }
}
