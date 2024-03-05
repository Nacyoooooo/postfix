/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package comchenzhihao;

import org.junit.jupiter.api.Test;

public class TestQueue {
    @Test
    public void ArrayConstruct(){
        MyArrayList<Integer> array=new MyArrayList();
        System.out.println(array);
    }
    @Test
    public void ArrayInsert(){
        MyArrayList<Integer> array=new MyArrayList();
        array.EnQueue(1);
        array.EnQueue(2);
        array.EnQueue(3);
        array.EnQueue(4);
        array.EnQueue(5);
        System.out.println(array);
    }
    @Test
    public void ArrayGetQueueHead(){
        MyArrayList<Integer> array=new MyArrayList();
        array.EnQueue(1);
        array.EnQueue(2);
        array.EnQueue(3);
        array.EnQueue(4);
        array.EnQueue(5);
        System.out.println(array);
        Integer queueHead = array.getQueueHead();
        System.out.println(queueHead);
    }
    @Test
    public void ArrayGetQueueTail(){
        MyArrayList<Integer> array=new MyArrayList();
        array.EnQueue(1);
        array.EnQueue(2);
        array.EnQueue(3);
        array.EnQueue(4);
        array.EnQueue(5);
        System.out.println(array);
        Integer queueTail = array.getQueueTail();
        System.out.println(queueTail);
    }
    @Test
    public void ArrayDeQueue(){
        MyArrayList<Integer> array=new MyArrayList();
        array.EnQueue(1);
        array.EnQueue(2);
        array.EnQueue(3);
        array.EnQueue(4);
        array.EnQueue(5);
        System.out.println(array);
        Integer deQueue = array.DeQueue();
        System.out.println(deQueue);
        System.out.println(array);
    }
    @Test
    public void ArrayClear(){
        MyArrayList<Integer> array=new MyArrayList();
        array.EnQueue(1);
        array.EnQueue(2);
        array.EnQueue(3);
        array.EnQueue(4);
        array.EnQueue(5);
        System.out.println(array);
        array.clear();
        System.out.println(array);
    }
    @Test
    public void ListConstruct(){
        MyLinkedList<Integer> list=new MyLinkedList();
        list.display();
    }
    @Test
    public void ListInsert(){
        MyLinkedList<Integer> list=new MyLinkedList();
        list.EnQueue(1);
        list.EnQueue(2);
        list.EnQueue(3);
        list.EnQueue(4);
        list.EnQueue(5);
        list.display();
    }
    @Test
    public void ListGetQueueHead(){
        MyLinkedList<Integer> list=new MyLinkedList();
        list.EnQueue(1);
        list.EnQueue(2);
        list.EnQueue(3);
        list.EnQueue(4);
        list.EnQueue(5);
        list.display();
        Integer queueHead = list.getQueueHead();
        System.out.println(queueHead);
    }
    @Test
    public void ListGetQueueTail(){
        MyLinkedList<Integer> list=new MyLinkedList();
        list.EnQueue(1);
        list.EnQueue(2);
        list.EnQueue(3);
        list.EnQueue(4);
        list.EnQueue(5);
        list.display();
        Integer queueTail = list.getQueueTail();
        System.out.println(queueTail);
    }
    @Test
    public void ListDeQueue(){
        MyLinkedList<Integer> list=new MyLinkedList();
        list.EnQueue(1);
        list.EnQueue(2);
        list.EnQueue(3);
        list.EnQueue(4);
        list.EnQueue(5);
        list.display();
        Integer queueTail = list.DeQueue();
        System.out.println(queueTail);
        list.display();
    }
    @Test
    public void ListClear(){
        MyLinkedList<Integer> list=new MyLinkedList();
        list.EnQueue(1);
        list.EnQueue(2);
        list.EnQueue(3);
        list.EnQueue(4);
        list.EnQueue(5);
        list.display();
        list.clear();
        list.display();
    }
}
