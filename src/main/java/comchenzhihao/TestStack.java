/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package comchenzhihao;

import org.junit.jupiter.api.Test;

public class TestStack {
    @Test
    public void constructNullStack(){
        MyArrayList<Integer>myArrayList=new MyArrayList<>();
        System.out.println(myArrayList);
    }
    @Test
    public void insertElementStack(){
        MyArrayList<Integer>myArrayList=new MyArrayList<>();
        myArrayList.push(1);
        myArrayList.push(2);
        myArrayList.push(3);
        myArrayList.push(4);
        myArrayList.push(5);
        System.out.println(myArrayList);
    }
    @Test
    public void getStackHeadElement(){
        MyArrayList<Integer>myArrayList=new MyArrayList<>();
        myArrayList.push(1);
        myArrayList.push(2);
        myArrayList.push(3);
        myArrayList.push(4);
        myArrayList.push(5);
        System.out.println(myArrayList);
        Integer pop = myArrayList.getStackHead();
        System.out.println(pop);
        System.out.println(myArrayList);
    }
    @Test
    public void popStackHeadElement(){
        MyArrayList<Integer>myArrayList=new MyArrayList<>();
        myArrayList.push(1);
        myArrayList.push(2);
        myArrayList.push(3);
        myArrayList.push(4);
        myArrayList.push(5);
        System.out.println(myArrayList);
        Integer pop = myArrayList.pop();
        System.out.println(pop);
        System.out.println(myArrayList);
    }
    @Test
    public void clear(){
        MyArrayList<Integer>myArrayList=new MyArrayList<>();
        myArrayList.push(1);
        myArrayList.push(2);
        myArrayList.push(3);
        myArrayList.push(4);
        myArrayList.push(5);
        System.out.println(myArrayList);
        myArrayList.clearStack();
        System.out.println(myArrayList);
    }
    @Test
    public void ListConstruct(){
        MyLinkedList<Integer>list=new MyLinkedList<>();
        list.display();
    }
    @Test
    public void ListInsert(){
        MyLinkedList<Integer>list=new MyLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        list.display();
    }
    @Test
    public void ListGetStackHead(){
        MyLinkedList<Integer>list=new MyLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        list.display();
        Integer stackHead = list.getStackHead();
        System.out.println(stackHead);
    }
    @Test
    public void ListPop(){
        MyLinkedList<Integer>list=new MyLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        list.display();
        Integer pop = list.pop();
        System.out.println(pop);
        list.display();
    }

    @Test
    public void ListClear(){
        MyLinkedList<Integer>list=new MyLinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        list.display();
        list.clear();
        list.display();
    }
}
