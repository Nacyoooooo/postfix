/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package comchenzhihao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//运算符
@Data
@NoArgsConstructor
public class Operator {

    int priority;//运算优先级
    String name;//运算符名称
    String rule;//运算规则
    int argsNumber;//所需要的参数的个数
    CalcuationMethod method;//计算接口
    //判断字符串是否回文
    //默认不需要回文
    Integer Palindrome=0;
    public static MyMap<Integer,Integer>PalindromeChoooses=new MyMap<>();
    static {
        PalindromeChoooses.put(0,0);
        PalindromeChoooses.put(1,1);
    }
    public static  Integer NO=0;
    public static  Integer YES=1;

    public Operator(int priority, String name, String rule,int argsNumber) {
        this.priority = priority;
        this.name = name;
        this.rule = rule;
        this.argsNumber=argsNumber;
    }
    public Operator(int priority, String name, int argsNumber,CalcuationMethod method) {
        this.priority = priority;
        this.name = name;
        this.method=method;
        this.argsNumber=argsNumber;
    }

    public Operator setPalindrome(Integer Palindrome){
        if(PalindromeChoooses.get(Palindrome)!=null)this.Palindrome=Palindrome;
        return this;
    }
    public Double calcuate(String ...Args){
        if(this.method==null)return 0d;
        return this.method.calcuate(Args);
    }
    //加号
    public static Operator PLUS=new Operator(1,"+",2,CalcuationMethod.PLUS);
    //减号
    public static Operator SUBTRACTION=new Operator(1,"-",2,CalcuationMethod.SUBTRACTION);
    //乘号
    public static Operator MULTIPLICATION=new Operator(2,"*",2,CalcuationMethod.MULTIPLICATION);
    ///除号
    public static Operator DIVISION=new Operator(2,"/",2,CalcuationMethod.DIVISION);
    //幂乘
    public static Operator EXPONENTIATION=new Operator(3,"^",2,CalcuationMethod.EXPONENTIATION);
    //正弦
    public static Operator SINE=new Operator(4,"sin",1,CalcuationMethod.SINE);
    //余弦
    public static Operator COSINE=new Operator(4,"cos",1,CalcuationMethod.COSINE);
    //正切
    public static Operator TANGENT=new Operator(4,"tan",1,CalcuationMethod.TANGENT);
    //余弦
    public static Operator LEFT_BRACKET=new Operator(100,"(",-1,null);
    //正切
    public static Operator RIGHT_BRACKET=new Operator(100,")",-1,null);
}
