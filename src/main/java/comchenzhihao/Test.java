package comchenzhihao;



import static comchenzhihao.Info.NUMBER;
import static comchenzhihao.Info.VARIABLE;
public class Test {

    public static void testMyLinkedList(){
        MyLinkedList<String> myLinkedList=new MyLinkedList();
        myLinkedList.put("1");
        myLinkedList.put("2");
        myLinkedList.put("3");
        myLinkedList.put("4");
        myLinkedList.put("5");
        myLinkedList.display();
        System.out.println(myLinkedList.get(90));
    }
    public static void testExpressionConveter(){
        ExpressionConverter.castToPostfix("2+3*(7-4)+8/4").display();
        ExpressionConverter.castToPostfix("(2+(3-4/5))+5").display();
        ExpressionConverter.castToPostfix("(2+5)*3+1").display();
        ExpressionConverter.castToPostfix("X/Y").display();
        ExpressionConverter.castToPostfix("(P+Q)*(ATR-S)").display();
    }
    public static void testBinaryTree(){
        MyLinkedList list = ExpressionConverter.castToPostfix("(P+Q)*(R-S)");
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.PostConstructor(list);
        System.out.println(binaryTree.getDepth());
        System.out.println(binaryTree.levelTraversal());
    }
    public static void testCalculate(){
        MyLinkedList list = ExpressionConverter.castToPostfix("10-9");
        String calculate = ExpressionConverter.Calculate(list);
        System.out.println(calculate);
        MyLinkedList<Info> infoMyLinkedList = ExpressionConverter.castToInfix("X/(YA-B)");
        System.out.println(infoMyLinkedList);
    }
    public static void testIsLegal(){
        System.out.println(ExpressionConverter.isLegal("1+1"));
    }
    public static void testMap(){
        MyMap<String,String>myMap=new MyMap<>();
        myMap.put("a","aaa");
        myMap.put("b","bbb");
        myMap.put("abc","ccc");
        myMap.put("a","test");
        System.out.println(myMap.get("a"));
        System.out.println(ExpressionConverter.getPriority("/"));
        myMap.remove("a");
        System.out.println(myMap.get("a"));
    }
    public static void testAssignment(){
        MyLinkedList postfix = ExpressionConverter.castToPostfix("X+Y");
        MyMap<String,Variable>variableMyMap=new MyMap<>();
        variableMyMap.put("X",new Variable("X","1"));
        variableMyMap.put("Y",new Variable("Y","19"));
        MyLinkedList variable = ExpressionConverter.getVariable(postfix);
        variable.display();
    }
    public static void testView(){
        View view = new View("后缀表达式计算器");
    }
    public static void testCalcuationMethod(){
        MyLinkedList postfix = ExpressionConverter.castToPostfix("(2*5)*sin(1)");
        System.out.println(ExpressionConverter.Calculate(postfix));
    }
    public static void testMergeConstantTerms(){
        MyLinkedList postfix = ExpressionConverter.castToPostfix("sin(a)+3+2");
        MyLinkedList postfix1 = ExpressionConverter.MergeConstantTerms(postfix);
        postfix1.display();
    }

    public static void testPD(){
        Info[]args=
                new Info[]{new Info("a",VARIABLE),
                        new Info("b",VARIABLE)};
        MyLinkedList<Info> pd = PartialDerivative.PLUS.PD(args);
        pd.display();
        MyLinkedList<Info> pd1 = PartialDerivative.SUBTRACTION.PD(args);
        pd1.display();
    }

}
