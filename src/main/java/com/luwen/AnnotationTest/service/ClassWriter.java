package com.luwen.AnnotationTest.service;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Opcodes;

/**
 * asm访问方法重写
 */
public class ClassWriter extends ClassVisitor {

    public ClassWriter() {
        super(Opcodes.ASM4);
    }

    /**
     * 重写注解方法，打印参数
     * @param descriptor
     * @param visible
     * @return
     */
    @Override
    public AnnotationVisitor visitAnnotation(final String descriptor, final boolean visible) {
        System.out.println("annotation : " + descriptor);
        return super.visitAnnotation(descriptor, visible);
    }
}
