/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package comchenzhihao;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MyArrayList <T>{
    private int size;
    private int head;
    private int tail;
    private T[]data;
    private int maxSize=1<<4;//aka 16
    public MyArrayList(){
        this.head=0;
        this.tail=0;
        this.data=(T[]) new Object[maxSize];
    }
    //判断索引是否合法
    private boolean indexLegal(int i){
        if(i<0||i>size-1||i>maxSize-1){
            return false;
        }
        return true;
    }
    //数组扩容
    private void reSize(){
        this.maxSize<<=1;
        T[]oldTab=this.data;
        T[]newTab=(T[])new Object[this.maxSize];
        for (int i = 0; i < oldTab.length; i++) {
            newTab[i]=oldTab[i];
        }
        this.data=newTab;
    }
    //在数组尾部新增元素
    private boolean insertLast(T data){
        if(!canEnter())reSize();
        T[] tab = this.data;
        int tail=this.tail;
        if(tab[tail]!=null){
            tail++;
        }
        tab[tail]=data;
        if(tab[tail]!=data){
            tab[tail]=null;
            return false;
        }
        if(this.size>0){
            this.tail++;
        }
        this.size++;
        return true;
    }
    //在数组尾部删除元素
    private T removeLast(){
        int tail=this.tail;
        T[] tab = this.data;
        T t = tab[tail];
        tab[tail]=null;
        tail--;
        tail=tail>=0?tail:0;
        this.tail=tail;
        return t;
    }
    //在数组头部删除元素
    private T removeFirst(){
        int head=this.head;
        T[]tab=this.data;
        T t = tab[head];
        head++;
        head=head<=this.tail?head:this.tail;
        this.head=head;
        return t;
    }
    private boolean canEnter(){
        if(this.tail>=maxSize)return false;
        return true;
    }
    private boolean canEnter(int index){
        if(index<0||index>=maxSize)return false;
        T[]tab=this.data;
        if(tab[index]!=null)return false;
        return true;
    }
    //获取指定元素对象
    private T get(int i){
        if(!indexLegal(i))return null;
        T[]d=this.data;
        return d[i];
    }
    //获取数组队头元素
    public T getFirst(){
        return this.data[this.head];
    }
    //获取数组数组尾部元素
    public T getLast(){
        return this.data[this.tail];
    }
    //获取栈顶元素
    public T getStackHead(){
        return getLast();
    }
    //入栈
    public boolean push(T data){
        return insertLast(data);
    }
    //出栈
    public T pop(){
        return removeLast();
    }
    //入队
    public boolean EnQueue(T data){
        return insertLast(data);
    }
    //出队
    public T DeQueue(){
        return removeFirst();
    }
    //获取队头元素
    public T getQueueHead(){
        return getFirst();
    }
    //获取队尾元素
    public T getQueueTail(){
        return getLast();
    }
    //清空
    public T[] clear(){
        this.head=0;
        this.tail=0;
        this.size=0;
        this.maxSize=1<<4;
        T[]tab=this.data;
        this.data=(T[])new Object[maxSize];
        return (T[])tab;
    }
    //清空栈
    public T[]clearStack(){
        return clear();
    }
    //清空队列
    public T[]clearQueue(){
        return clear();
    }
}
