package com.luwen.applicationContentTest.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 注册bean
     * @param importingClassMetadata 当前类的信息
     * @param registry Bean注册器
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        /**
         * BeanDefinition可以设置组件的多个信息
         * 例如：
         * setBeanClassName 设置bean的全类名
         * setScope 设置bean的作用域
         */
/*        BeanDefinition beanDefinition =new RootBeanDefinition();
        beanDefinition.setBeanClassName("com.applicationContentTest.entity.Person");
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        if(registry.containsBeanDefinition("com.applicationContentTest.entity.Person")){
            //beanName为在容器中的id
            registry.registerBeanDefinition("person",beanDefinition);
        }*/
    }
}
