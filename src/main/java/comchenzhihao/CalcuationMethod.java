/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package comchenzhihao;

import java.util.Arrays;

public class CalcuationMethod {
    /**************************Fields**************************/
    private Method method;

    /**************************Classes**************************/
    @FunctionalInterface
    static interface Method{
        public  Double calcuate(String...Args);
    }
    /**************************Methods**************************/
    public Double calcuate(String...Args){
        return method.calcuate(Args);
    }
    public CalcuationMethod(Method method){
        this.method=method;
    }
    public static CalcuationMethod PLUS=new CalcuationMethod((Args)->{
        if(Args.length!=2){
            return 0d;
        }
        return Double.valueOf(Args[0])+Double.valueOf(Args[1]);
    });
    public static CalcuationMethod SUBTRACTION=new CalcuationMethod((Args)->{
        if(Args.length!=2){
            return 0d;
        }
        return Double.valueOf(Args[0])-Double.valueOf(Args[1]);
    });
    public static CalcuationMethod MULTIPLICATION=new CalcuationMethod((Args)->{
        if(Args.length!=2){
            return 0d;
        }
        return Double.valueOf(Args[0])*Double.valueOf(Args[1]);
    });
    public static CalcuationMethod DIVISION=new CalcuationMethod((Args)->{
        if(Args.length!=2||Double.valueOf(Args[1])==0){
            return 0d;
        }
        return Double.valueOf(Args[0])/Double.valueOf(Args[1]);
    });
    public static CalcuationMethod EXPONENTIATION=new CalcuationMethod((Args)->{
        if(Args.length!=2){
            return 0d;
        }
        return Math.pow(Double.valueOf(Double.valueOf(Args[0])), Double.valueOf(Args[1]));
    });
    public static CalcuationMethod SINE=new CalcuationMethod((Args)->{
        if(Args.length!=1)return 0d;
        return Math.sin(Double.valueOf(Args[0]));
    });
    public static CalcuationMethod COSINE=new CalcuationMethod((Args)->{
        if(Args.length!=1)return 0d;
        return Math.cos(Double.valueOf(Args[0]));
    });
    public static CalcuationMethod TANGENT=new CalcuationMethod((Args)->{
        if(Args.length!=1)return 0d;
        return Math.tan(Double.valueOf(Args[0]));
    });
}