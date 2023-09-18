package com.luwen.applicationContentTest;

import com.test.applicationContentTest.config.ImportConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Index {

    public static void main(String[] args) {
/*        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ObjectConfig.class);
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person.getName());*/
        importTest();
    }

    /**
     * 流程：
     *
     * 1.拆分程序员自定义的BeanFactoryPostProcessor分别放入BeanFactoryPostProcessor和BeanDefinitionRegistryPostProcessor的相关list，
     * 并且如果是BeanDefinitionRegistryPostProcessor，则执行拓展方法postProcessBeanDefinitionRegistry（程序员手动添加的）
     * 2.通过getBeanNamesForType方法从工厂beanFactory获取BeanDefinitionRegistryPostProcessor类型的bean名字，并通过名字获取bean，
     * 放入存放BeanDefinitionRegistryPostProcessor的list
     * 3.排序spring内部的BeanDefinitionRegistryPostProcessor类型的list
     * 4.汇总spring内部和程序员自定义的BeanDefinitionRegistryPostProcessor类型的集合
     * 5.执行spring内部的BeanDefinitionRegistryPostProcessor中拓展postProcessBeanDefinitionRegistry方法（spring内部的）
     */


    /**
     *
     */
    public static void importTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportConfig.class);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
}

