package com.luwen.applicationContentTest.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

public class MyImportSelector implements ImportSelector {

    /**
     * @param importingClassMetadata 加@Import注解的类,所有注解信息
     * @return
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

/*        //当前类的所有注解
        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
        System.out.println("当前配置类的注解信息：" + annotationTypes);
        //注意不能返回null,不然会报NullPointException
        System.out.println("注册了Person");
        return new String[]{"com.applicationContentTest.entity.Person"};*/
        return new String[]{};
    }
}
