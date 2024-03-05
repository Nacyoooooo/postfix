/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package comchenzhihao;

import org.junit.jupiter.api.Test;

import static comchenzhihao.Info.VARIABLE;


public class TestTT {
    @Test
    public  void testMyLinkedList(){
        MyLinkedList<String> myLinkedList=new MyLinkedList();
        myLinkedList.put("1");
        myLinkedList.put("2");
        myLinkedList.put("3");
        myLinkedList.put("4");
        myLinkedList.put("5");
        myLinkedList.display();
        System.out.println(myLinkedList.get(90));
    }
    @Test
    public  void testExpressionConveter(){
        ExpressionConverter.castToPostfix("2+3*(7-4)+8/4").display();
        ExpressionConverter.castToPostfix("(2+(3-4/5))+5").display();
        ExpressionConverter.castToPostfix("(2+5)*3+1").display();
        ExpressionConverter.castToPostfix("X/Y").display();
        ExpressionConverter.castToPostfix("(P+Q)*(ATR-S)").display();
    }
    @Test
    public  void testBinaryTree(){
        MyLinkedList list = ExpressionConverter.castToPostfix("(P+Q)*(R-S)");
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.PostConstructor(list);
        System.out.println(binaryTree.getDepth());
        System.out.println(binaryTree.levelTraversal());
    }
    @Test
    public  void testCalculate(){
        MyLinkedList list = ExpressionConverter.castToPostfix("10-9");
        String calculate = ExpressionConverter.Calculate(list);
        System.out.println(calculate);
        MyLinkedList<Info> infoMyLinkedList = ExpressionConverter.castToInfix("X/(YA-B)");
        System.out.println(infoMyLinkedList);
    }
    @Test
    public  void testIsLegal(){
        System.out.println(ExpressionConverter.isLegal("1+1"));
    }
    @Test
    public  void testMap(){
        MyMap<String,String> myMap=new MyMap<>();
        myMap.put("a","aaa");
        myMap.put("b","bbb");
        myMap.put("abc","ccc");
        myMap.put("a","test");
        System.out.println(myMap.get("a"));
        System.out.println(ExpressionConverter.getPriority("/"));
        myMap.remove("a");
        System.out.println(myMap.get("a"));
    }
    @Test
    public  void testAssignment(){
        MyLinkedList postfix = ExpressionConverter.castToPostfix("X+Y");
        MyMap<String,Variable>variableMyMap=new MyMap<>();
        variableMyMap.put("X",new Variable("X","1"));
        variableMyMap.put("Y",new Variable("Y","19"));
        MyLinkedList variable = ExpressionConverter.getVariable(postfix);
        variable.display();
    }
    @Test
    public  void testView(){
        View view = new View("后缀表达式计算器");
    }
    @Test
    public  void testCalcuationMethod(){
        MyLinkedList postfix = ExpressionConverter.castToPostfix("(2*5)*sin(1)");
        System.out.println(ExpressionConverter.Calculate(postfix));
    }
    @Test
    public  void testMergeConstantTerms(){
        MyLinkedList postfix = ExpressionConverter.castToPostfix("sin(a)+3+2");
        MyLinkedList postfix1 = ExpressionConverter.MergeConstantTerms(postfix);
        postfix1.display();
    }
    @Test
    public  void testPD(){
        Info[]args=
                new Info[]{new Info("a",VARIABLE),
                        new Info("b",VARIABLE)};
        MyLinkedList<Info> pd = PartialDerivative.PLUS.PD(args);
        pd.display();
        MyLinkedList<Info> pd1 = PartialDerivative.SUBTRACTION.PD(args);
        pd1.display();
    }
    @Test
    public void testBinartPostFix(){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.PostConstructor(
                ExpressionConverter.castToPostfix("9+1/3*(0/sin(3-2)+1*(7-6))"));
        System.out.println(binaryTree.levelTraversal());
        System.out.println(ExpressionConverter.Calculate(binaryTree));
    }
    @Test
    public void testPD_(){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.PostConstructor(ExpressionConverter.castToPostfix("4/a"));
        BinaryTree a = ExpressionConverter.PD(binaryTree, "a");
        System.out.println(binaryTree.levelTraversal());
        ExpressionConverter.merge(a.head);
        System.out.println(a.levelTraversal());
        MyLinkedList<Info> infoMyLinkedList = ExpressionConverter.BinaryTreeToLinkedList(a);
        infoMyLinkedList.display();
    }
    @Test
    public void testMerge(){
        BinaryTree binaryTree=new BinaryTree();
        binaryTree.PostConstructor(ExpressionConverter.castToPostfix("1+2+a"));
        ExpressionConverter.merge(binaryTree.head);
        System.out.println(binaryTree.levelTraversal());
    }
    @Test
    public void testBTTLL(){
        BinaryTree binaryTree=new BinaryTree();
        binaryTree.PostConstructor(
                ExpressionConverter.castToPostfix("3*a-1"));
        MyLinkedList<Info> infoMyLinkedList = ExpressionConverter.BinaryTreeToLinkedList(binaryTree);
        infoMyLinkedList.display();
    }
    @Test
    public void testC(){
        MyLinkedList postfix = ExpressionConverter.castToPostfix("1P2*3");
        System.out.println(ExpressionConverter.Calculate(postfix));
    }
}