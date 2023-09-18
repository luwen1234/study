package com.luwen.AnnotationTest;

import com.test.AnnotationTest.entity.TestConfig;
import com.test.AnnotationTest.service.ClassWriter;
import org.springframework.asm.ClassReader;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.core.type.AnnotationMetadata;

import java.io.IOException;
import java.util.Set;

/**
 * AnnotationMetadata 存在两个实现类分别为
 * StandardAnnotationMetadata。主要使用 Java 反射原理获取元数据
 * AnnotationMetadataReadingVisitor。使用 ASM 框架获取元数据。
 */
public class Index {

    public static void main(String[] args) throws IOException {
        /*
            asmTest();
            annotationMetadataTest();
        */
        mergedAnnotationsTest();
    }

    /**
     * asm api操作字节码
     * AnnotationMetadataReadingVisitor通过ClassWriter->ClassVisitor来获取类结构
     */
    public static void asmTest() throws IOException {
        ClassWriter classWriter = new ClassWriter();
        ClassReader classReader = new ClassReader(TestConfig.class.getName());
        classReader.accept(classWriter, 0);
    }

    /**
     * 接口，定义对特定类的注释的抽象访问，其形式不需要加载该类。
     * AnnotationMetadata风格：
     *  1. 公共的行为都定义再接口中
     *  2. 接口中创建默认的对象实例
     * AnnotationMetadata 存在两个实现类分别为 StandardAnnotationMetadata与 AnnotationMetadataReadingVisitor。
     * StandardAnnotationMetadata主要使用 Java 反射原理获取元数据，
     * AnnotationMetadataReadingVisitor 使用 ASM 框架获取元数据。
     */
    public static void annotationMetadataTest(){
        Set<String> stringSet = AnnotationMetadata.introspect(TestConfig.class).getAnnotationTypes();
        System.out.println(stringSet);
        Set<String> values = AnnotationMetadata.introspect(TestConfig.class)
                .getMetaAnnotationTypes("org.springframework.context.annotation.Configuration");
        System.out.println(values);
        boolean hasAnnotation = AnnotationMetadata.introspect(TestConfig.class)
                .hasAnnotation("org.springframework.context.annotation.Configuration");
        System.out.println("hasAnnotation : " + hasAnnotation);
    }

    public static void mergedAnnotationsTest(){
        MergedAnnotations mergedAnnotations = MergedAnnotations.from(TestConfig.class,
                MergedAnnotations.SearchStrategy.INHERITED_ANNOTATIONS,
                RepeatableContainers.none());
        mergedAnnotations.stream().forEach(item -> {
            System.out.println("item : " + item.getType());
        });
    }
}


