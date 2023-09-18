package com.luwen.collectionTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ListTest {

    public static void main(String[] args) {
        vectorTest();
    }

    /**
     * ArrayList
     * 数组校验时要考虑第一个位置0和最大位置length-1是否越界的情况。
     */
    public static void arrayListTest() {
        /**
         * arrayList底层
         * Object[] elementData 容器， 默认初始内存长度为10
         * size elementData元素长度，
         * modCount修改次数，添加删除一次加1
         * 1. 初始化长度为1的elementData数组结构
         */
        List<Integer> list = new ArrayList<>(1);
        /**
         * 1. calculateCapacity方法。计算添加元素之后elementData size的长度，
         * 取max(size+1,elementData.length)（如果elementData为空minCapacity=10） -> minCapacity。
         *
         * 2. ensureExplicitCapacity方法。若minCapacity大于elementData内存长度，则扩容。否则直接添加
         *
         * 3. grow方法。扩容规则：
         *   3.1 如果elementData不为空，newCapacity = length + length>>>1 既1.5倍扩容
         *   3.2 如果newCapacity < minCapacity, newCapacity = minCapacity
         *   3.3 如果newCapacity大于Integer范围，则取Integer最大值
         *   3.4 底层使用System.arraycopy扩容
         */
        list.add(1);
        list.add(2);
        /**
         * 先查找再删除 null也可以删除
         * 1. 先找到元素index
         * 2. [1,2,3,4,5] index = 2
         *    numMoved = length([4,5])=2
         *    System.arraycopy(elementData, index+1, elementData, index, numMoved);
         */
        list.remove(Integer.valueOf(2));
        /**
         * iterator方法：初始化一个迭代器Itr
         * 迭代器 内部类Itr
         * cursor如果再elementData起始和结束元素之间,迭代。否则抛出异常。
         * expectedModCount = modCount 迭代过程中不能删除元素。
         */
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * Vector
     * 数组校验时要考虑第一个位置0和最大位置length-1是否越界的情况。
     */
    public static void vectorTest() {
        /**
         * vector底层
         * Object[] elementData 容器
         * elementCount：元素长度，默认为10
         * capacityIncrement：
         * modCount修改次数，添加删除一次加1
         * 1. 初始化长度为1的elementData数组结构
         */
        Vector<Integer> vector = new Vector<>();
        /**
         * synchronized方法，线程安全
         * 1. ensureCapacityHelper方法。计算添加元素之后elementData elementCount的长度，
         * minCapacity = elementCount + 1
         * minCapacity > length则扩容，否则直接添加
         *
         * 2. grow方法。扩容规则：
         *   3.1 如果elementData不为空，newCapacity = length + capacityIncrement > 0 ? capacityIncrement : length 默认2倍扩容
         *   3.2 如果newCapacity < minCapacity, newCapacity = minCapacity
         *   3.3 如果newCapacity大于Integer范围，则取Integer最大值
         *   3.4 底层使用System.arraycopy扩容
         */
        vector.add(1);
        vector.add(2);
        /**
         * 先查找再删除 null也可以删除
         * 1. 先找到元素index
         * 2. [1,2,3,4,5] index = 2
         *    numMoved = length([4,5])=2
         *    System.arraycopy(elementData, index+1, elementData, index, numMoved);
         */
        vector.remove(Integer.valueOf(2));
        /**
         * iterator方法：初始化一个迭代器Itr
         * 迭代器 内部类Itr
         * cursor如果再elementData起始和结束元素之间,迭代。否则抛出异常。
         * expectedModCount = modCount 迭代过程中不能删除元素。
         */
        Iterator iterator = vector.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
