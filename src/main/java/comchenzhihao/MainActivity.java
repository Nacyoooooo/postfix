package comchenzhihao;

import org.junit.jupiter.api.Test;

public class MainActivity {
    //以字符串序列输入语法正确的后缀表达式，并利用二叉树存储
    @Test
    public void constructBinaryTree(){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.PostConstructor(ExpressionConverter.castToPostfix("(1+2)/3-4*5"));
        System.out.println(binaryTree.levelTraversal());
    }
    //用带括弧的普通表达式输出后缀表达式
    @Test
    public void outPostfix(){
        MyLinkedList postfix = ExpressionConverter.castToPostfix("(1+2)/3-4*5");
        postfix.display();
    }
    //实现后缀表达式中变量的赋值
    public static void main(String[] args) {
        new View("计算器赋值");
    }
    //对后缀表达式求值。可采用后根遍历的方式求值
    @Test
    public void calcaulate(){
        MyLinkedList postfix = ExpressionConverter.castToPostfix("(1+2)/3-4*5");
        System.out.println(ExpressionConverter.Calculate(postfix));
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.PostConstructor(ExpressionConverter.castToPostfix("(1+2)/3-4*5"));
        System.out.println(ExpressionConverter.Calculate(binaryTree));
    }
    //利用两个已经存在的后缀表达式E1和E2,使用运算P，构造一个新的复合表达式(E1)P(E2)
    @Test
    public void UserDefinedOperators(){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.PostConstructor(ExpressionConverter.castToPostfix("1P2*3"));
        System.out.println(ExpressionConverter.Calculate(binaryTree));
        System.out.println(ExpressionConverter.Calculate(ExpressionConverter.castToPostfix("1P2*3")));
    }
    //偏导数运算
    @Test
    public void PD(){
        String expression="1/e";
        MyLinkedList<Info> postfix = ExpressionConverter.castToPostfix(expression);
        MyLinkedList<Info> variables = ExpressionConverter.getVariable(postfix);
        MyMap<String,MyLinkedList<Info>>map=new MyMap<>();
        int size= variables.size();

        for (int i = 0; i < size; i++) {
            Info info = variables.get(i);
            BinaryTree binaryTree = new BinaryTree();
            binaryTree.PostConstructor(ExpressionConverter.castToPostfix(expression));
            BinaryTree pd = ExpressionConverter.PD(binaryTree, info.data);
            MyLinkedList<Info> infoMyLinkedList = ExpressionConverter.BinaryTreeToLinkedList(pd);
            BinaryTree b = new BinaryTree();
            b.PostConstructor(infoMyLinkedList);
            ExpressionConverter.merge(b.head);
            map.put(info.data,ExpressionConverter.BinaryTreeToLinkedList(b));
            System.out.println(b.levelTraversal());
        }
    }
    //三角函数等初等函数
    @Test
    public void elementaryFunction(){
        String expression="sin(1)*10+1";
        System.out.println(ExpressionConverter.Calculate(ExpressionConverter.castToPostfix(expression)));
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.PostConstructor(ExpressionConverter.castToPostfix(expression));
        System.out.println(ExpressionConverter.Calculate(binaryTree));
    }
    //常数合并
    @Test
    public void constantFolding(){
        String expression="3^a*(2*2+4)";
        MyLinkedList<Info> postfix = ExpressionConverter.castToPostfix(expression);
        postfix.display();
        ExpressionConverter.MergeConstantTerms(postfix).display();
    }

}