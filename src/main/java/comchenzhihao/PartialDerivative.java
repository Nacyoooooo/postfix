/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package comchenzhihao;

import lombok.Data;

import static comchenzhihao.Info.NUMBER;
import static comchenzhihao.Info.VARIABLE;

//求偏导，返回变量表达式的结果集
@Data
@Deprecated
public class PartialDerivative {
    private Operator operator;
    private Method method;
    public PartialDerivative(Operator operator,Method method){
        this.operator=operator;
        this.method=method;
    }
    public int getArgsNumber(){
        if(this.operator==null)return 0;
        return this.operator.getArgsNumber();
    }
    public MyLinkedList<Info>PD(Info...Args){
        if(this.method==null)return new MyLinkedList<Info>();
        return this.method.PD(Args);
    }
    @FunctionalInterface
    static interface Method{
        MyLinkedList<Info> PD(Info...Args);
    }
    //加号的偏导法则
    public static PartialDerivative PLUS=
            new PartialDerivative(Operator.PLUS,(Args)->{
                if(Args==null
                        ||Args.length!=Operator.PLUS.getArgsNumber())return new MyLinkedList<>();
                MyLinkedList<Info> results = new MyLinkedList<>();
                if(Args[0]==null||Args[1]==null||Args[0].isEmpty()||Args[1].isEmpty())return results;
                if(Args[0].type==NUMBER&&Args[1].type==NUMBER){
                    results.push( new Info("0",NUMBER));
                } else if (Args[0].type == VARIABLE && Args[1].type == VARIABLE) {
                    results.push(Args[0]);
                    results.push(Args[1]);
                    results.push(new Info("+",VARIABLE));
                }else {
                    if(Args[0].type==VARIABLE){
                        results.push(Args[0]);
                    }
                    if(Args[1].type==VARIABLE){
                        results.push(Args[1]);
                    }
                }
                return results;
            });
    //减号的偏导法则
    public static PartialDerivative SUBTRACTION=
            new PartialDerivative(Operator.PLUS,(Args)->{
                if(Args==null
                        ||Args.length!=Operator.PLUS.getArgsNumber())return new MyLinkedList<>();
                MyLinkedList<Info> results = new MyLinkedList<>();
                if(Args[0]==null||Args[1]==null||Args[0].isEmpty()||Args[1].isEmpty())return results;
                if(Args[0].type==NUMBER&&Args[1].type==NUMBER){
                    results.push( new Info("0",NUMBER));
                } else if (Args[0].type == VARIABLE && Args[1].type == VARIABLE) {
                    results.push(Args[0]);
                    results.push(Args[1]);
                    results.push(new Info("-",VARIABLE));
                }else {
                    if(Args[0].type==VARIABLE){
                        results.push(Args[0]);
                    }
                    if(Args[1].type==VARIABLE){
                        results.push(Args[1]);
                    }
                }
                return results;
            });
    public static void PD_TEST(String varName,MyLinkedList<Info>postfix){
        if(varName==null||varName.isEmpty()||postfix==null||postfix.isEmpty());

    }
}
