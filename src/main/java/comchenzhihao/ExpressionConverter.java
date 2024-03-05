package comchenzhihao;

import static comchenzhihao.Info.*;
import static comchenzhihao.Operator.*;

//变量必须以大小写字母开头
//表达式转换器，用于生成对应的后缀表达式
public class ExpressionConverter {
    /**************************Fields**************************/
    //保存运算符的map集合
    public static final MyMap<String,Operator> OPERATORS_MAP_META=new MyMap();
    static {
        OPERATORS_MAP_META.put("+",Operator.PLUS);
        OPERATORS_MAP_META.put("-",Operator.SUBTRACTION);
        OPERATORS_MAP_META.put("*",Operator.MULTIPLICATION);
        OPERATORS_MAP_META.put("/",Operator.DIVISION);
        OPERATORS_MAP_META.put("^",Operator.EXPONENTIATION);
        OPERATORS_MAP_META.put("sin",Operator.SINE);
        OPERATORS_MAP_META.put("cos",Operator.COSINE);
        OPERATORS_MAP_META.put("tan",Operator.TANGENT);
        OPERATORS_MAP_META.put("(",Operator.LEFT_BRACKET);
        OPERATORS_MAP_META.put(")",Operator.RIGHT_BRACKET);
    }
    //自定义运算符
    public static  MyMap<String,Operator> OPERATORS_MAP=new MyMap();
    static {
        OPERATORS_MAP.put("+",new Operator(1,"+","+",2));
        OPERATORS_MAP.put("-",new Operator(1,"-","-",2));
        OPERATORS_MAP.put("*",new Operator(2,"*","*",2));
        OPERATORS_MAP.put("/",new Operator(2,"/","/",2));
        OPERATORS_MAP.put("^",new Operator(3,"^","^",2));
        OPERATORS_MAP.put("(",new Operator(100,"(","(",0));
        OPERATORS_MAP.put(")",new Operator(100,")",")",0));
        OPERATORS_MAP.put("P",new Operator(10,"P","+",2));
        OPERATORS_MAP.put("sin",new Operator(4,"sin","sin",1));
        OPERATORS_MAP.put("sin",new Operator(4,"cos","cos",1));
        OPERATORS_MAP.put("sin",new Operator(4,"tan","tan",1));
    }
    //元运算符map
    /**************************Methods**************************/
    //判断表达式是否合法
    public static boolean isLegal(String expression){
        MyLinkedList<Info> infix = castToInfix(expression);
        //判断对象是否有足够的元素
        if(infix==null||infix.isEmpty()){
            return false;
        }
        //从对象中观察运算符和数字是否单独出现，括号等对称运算符单独计算
        boolean legal=true;
        /*队尾为空，则首个可以是运算符，但是仅限于+-，也可以是数字,或者左括号,但是不能是右括号
         *队尾不为空，则运算符和数字必须交替出现
         *括号内必须符合以上要求
         *如果插入左括号，则队尾要么为空，要么必须是运算符
         * 如果插入右括号，则队尾必须是变量或者数字
         * 遍历完之后，左括号和右括号的数量必须一致
         */
        MyLinkedList<Info> queue=new MyLinkedList<>();
        int left_bracket=0;//左括号数
        int right_bracket=0;//右括号数
        int TEMP_NUMBER=0;
        int TEMP_OPERATOR=0;
        while (!infix.isEmpty()){
            //获取队头元素
            Info info = infix.getQueueHead();
            //如果为空，说明没有元素，直接结束
            if(info==null||info.isEmpty()){
                break;
            }

            //如果临时队列的队尾为空
            //队尾为空，则首个可以是运算符，但是仅限于+-，也可以是数字,或者左括号,但是不能是右括号
            if(queue.isEmpty()){
                if(
                    //首个不可以是右括号
                        info.data.equals(")")
                    //也可以 是运算符，但是只能是加号或者减号
                ){
                    legal=false;
                    break;
                }
                if(info.type==OPERATOR){
                    if(!info.data.equals("+")||!info.data.equals("-")){
                        legal=false;
                        break;
                    }
                }
            }
            //如果临时队列的队尾不为空
            else {
                //运算符和数字必须交替出现
                /*如果插入左括号，则队尾要么为空，要么必须是运算符
                 * 如果插入右括号，则队尾必须是变量或者数字
                 * 遍历完之后，左括号和右括号的数量必须一致
                 * */
                //左括号，队尾必须为空或者为运算符,否则非法
                if(info.data.equals("(")){
                    left_bracket++;
                    if(queue.isEmpty()||queue.getQueueTail().type==OPERATOR){

                    }else {
                        legal=false;
                        break;
                    }
                }
                //如果是右括号，则队尾必须是数字或者变量
                else if (info.data.equals(")")) {
                    right_bracket++;
                    if(queue.getQueueTail().type==OPERATOR){
                        legal=false;
                        break;
                    }
                }
                //如果是变量或者数字，则必须交替出现，否则非法
                else if (info.type==NUMBER||info.type==VARIABLE) {
                    //如果队尾是左括号，则重新计算两者
                    //则info必须是数字或变量，如果是运算符则非法
                    if(!queue.isEmpty()&&queue.getQueueTail().data.equals("(")){
                        if(info.type==OPERATOR){
                            legal=false;
                            break;
                        }
                    }
                    TEMP_NUMBER=1;
                    if(TEMP_OPERATOR==0){
                        legal=false;
                        break;
                    }
                    TEMP_NUMBER=0;
                }
                //如果是运算符,则队尾必须是数字或者变量
                //如果队尾不是数字或变量，是左括号，则只允许左括号
                else if (info.type==OPERATOR) {
                    Info queueTail = queue.getQueueTail();
                    if(queueTail!=null&&!queueTail.isEmpty()){
                        //可以是运算符
                        if(queueTail.type==NUMBER||queueTail.type==VARIABLE){

                        } else if (queueTail.type==OPERATOR) {
                            if(!queueTail.data.equals("(")){
                                legal=false;
                                break;
                            }
                        }else {
                            legal=false;
                            break;
                        }
                    }
                    TEMP_OPERATOR=1;
                    if(TEMP_NUMBER==0){
                        legal=false;
                        break;
                    }
                    TEMP_OPERATOR=0;
                }
                //无类型
                else if (info.type==NOT) {
                    legal=false;
                    break;
                }
            }
            queue.EnQueue(info);
            infix.DeQueue();
        }
        //如果左括号和右括号不一样多，说明不匹配
        if(left_bracket!=right_bracket){
            legal=false;
        }
        return legal;
    }
    //中缀表达式格式化
    public static MyLinkedList<Info> castToInfix(String expression){
        char[] charArray = expression.toCharArray();
        if(charArray.length<=0){
            return null;
        }
        //中缀
        MyLinkedList<Info> infix=new MyLinkedList();
        for (int i=0;i<charArray.length;i++){
            char c = charArray[i];
            int type=Info.NOT;
            if((c>='0'&&c<='9')){
                type= NUMBER;
            }
            else if ((c>='a'&&c<='z')||(c >= 'A' && c <= 'Z')){
                type=Info.VARIABLE;
            } else if (c == '+'
                    ||c=='-'
                    ||c=='*'
                    ||c=='/'
                    ||c=='^'
                    ||c=='('
                    ||c==')') {
                type=OPERATOR;
            }
            //常数或变量直接入栈
            if((c>='0'&&c<='9')||(c>='a'&&c<='z')||(c>='A'&&c<='Z')){
                String number=String.valueOf(c);
                for (int j = i+1; j < charArray.length; j++) {
                    char c1 = charArray[j];
                    if((c>='0'&&c<='9')){
                        if((c1>='0'&&c1<='9')){
                            number+=String.valueOf(c1);
                            i=j;
                        }else break;
                    } else if ((c>='a'&&c<='z')||(c >= 'A' && c <= 'Z')) {
                        if((c1>='a'&&c1<='z')||(c1 >= 'A' && c1 <= 'Z')){
                            number+=String.valueOf(c1);
                            i=j;
                        }else break;
                    }  else break;
                }
                //数字和变量直接入队
                infix.EnQueue(new Info(number,type));
            }else if (c == '+'
                    ||c=='-'
                    ||c=='*'
                    ||c=='/'
                    ||c=='^'
                    ||c=='('
                    ||c==')'
            ) {
                String number=String.valueOf(c);
                infix.EnQueue(new Info(number,type));
            }
        }
        return infix;
    }
    //中缀表达式转化为后缀表达式
    public static MyLinkedList castToPostfix(String expression){
        if(expression==null||expression.isEmpty())return new MyLinkedList();
        expression=expression.replaceAll("\\s","");
        char[] charArray = expression.toCharArray();
        if(charArray.length<=0){
            return new MyLinkedList();
        }
        //后缀
        MyLinkedList<Info> postfix=new MyLinkedList();
        //运算符
        MyLinkedList<Info>  operator=new MyLinkedList();
        /**
         *                                       null 零级
         *                                       +,-一级
         *                                          * / 二级
         *                                          ^ 幂运算三级
         */
        int bracketNumber=0;
        Bracket bracket=Bracket.NOT;
        for (int i=0;i<charArray.length;i++) {
            char c = charArray[i];
            //常数或变量直接入栈
            String token=String.valueOf(c);
            for (int j = i+1; j < charArray.length; j++) {
                if(isOperator(token))break;
                char c1 = charArray[j];
                if(isNumber(c)){
                    if(isNumber(c1)){
                        token+=String.valueOf(c1);
                        i=j;
                    }else break;
                } else if (isAlphabet(c)) {
                    if(isAlphabet(c1)){
                        token+=String.valueOf(c1);
                        i=j;
                    }else break;
                }
                else break;
            }
            //运算符
            int tokenType=tokenType(token);
            if(tokenType==OPERATOR){
                //取栈顶元素进行比较
                Info stackHead = operator.getStackHead();
                //大于等于栈顶则入栈
                //栈顶为空则直接入栈
                if(stackHead==null||stackHead.isEmpty()){
                    operator.push(new Info(token,Info.OPERATOR));
                }
                else {
                    //如果是括号则进行标识、
                    if(token.equals("(")){
                        bracketNumber++;
                        bracket=Bracket.LEFT;
                    } else if (token.equals(")")) {
                        bracket=Bracket.RIGHT;
                    }
                    //如果是处于左括号直接比较入栈
                    if(bracket==Bracket.NOT){
                        //左括号或者优先级比较当前的低或等于，则入栈，高则先取出来，将高的存入队列入栈，再入栈
                        if(stackHead.data.equals("(")||InOrOut(stackHead,token)){
                            operator.push(new Info(token,Info.OPERATOR));
                        }
                        else {
                            MyLinkedList<Info>queue=new MyLinkedList<>();
                            //当无法进入栈时开启循环
                            while (true){
                                stackHead=operator.getStackHead();
                                if(InOrOut(stackHead,token)){
                                    operator.push(new Info(token,OPERATOR));
                                    while (!queue.isEmpty()){
                                        postfix.push(queue.DeQueue());
                                    }
                                    break;
                                }
                                else {
                                    queue.EnQueue(operator.pop());
                                }
                            }
                        }
                    }
                    else if(bracket==Bracket.LEFT) {
                        //左括号或者优先级比较当前的低或等于，则入栈，高则先取出来，入栈，再入栈
                        //左括号则直接入栈
                        //如果栈顶运算符<=当前运算符，则直接入栈
                        //如果大于，则把栈顶弹出，并与下一个做对比，依然大则继续弹出，直到符合由小到大的顺序为止
                        if(stackHead.data.equals("(")||InOrOut(stackHead,token)){
                            operator.push(new Info(token,Info.OPERATOR));
                        }
                        else {
                            MyLinkedList<Info>queue=new MyLinkedList<>();
                            //当无法进入栈时开启循环
                            while (true){
                                stackHead=operator.getStackHead();
                                if(InOrOut(stackHead,token)){
                                    operator.push(new Info(token,OPERATOR));
                                    while (!queue.isEmpty()){
                                        postfix.push(queue.DeQueue());
                                    }
                                    break;
                                }
                                else {
                                    queue.EnQueue(operator.pop());
                                }
                            }
                        }
                    }
                    //如果是右括号直接按顺序出栈，直到遇到左括号为止
                    else if (bracket==Bracket.RIGHT) {
                        //当标记还是右括号时循环下去
                        while (bracket==Bracket.RIGHT){
                            Info headElement = operator.getStackHead();
                            if(headElement==null||headElement.isEmpty()){
                                //运算符栈弹出
                                operator.pop();
                            }
                            //遇到了左括号，终止
                            else if (headElement.equals("(")) {
                                bracket=Bracket.NOT;
                                bracketNumber--;
                                //如果括号数大于0，说明还有左括号
                                if(bracketNumber>0){
                                    bracket=Bracket.LEFT;
                                }
                                operator.pop();
                            }else {
                                //进入后缀的队列
                                postfix.EnQueue(headElement);
                                //运算符栈弹出
                                operator.pop();
                            }
                        }
                    }
                }
                //小于栈顶则出栈
            }
            else if(tokenType==VARIABLE||tokenType==NUMBER){
                //数字和变量直接入队
                postfix.EnQueue(new Info(token,tokenType));
            }
        }
        if(!operator.StackEmpty()){
            while (true){
                if(operator.StackEmpty()){
                    break;
                }
                Info pop = operator.pop();
                postfix.EnQueue(pop);
            }
        }
        return postfix;
    }
    //区分有无括号
    static enum Bracket{
        NOT,//无括号
        LEFT,//左括号
        RIGHT//右括号
        ;
    }
    //获取运算符的优先级
    public static int getPriority(String operator){
        return OPERATORS_MAP.get(operator)==null?0:OPERATORS_MAP.get(operator).getPriority();
    }
    //是否入栈，优先级高则直接入栈
    //返回true就是入栈
    //false就是不入栈
    public static boolean InOrOut(Info head,String cur){
        if(head==null||head.isEmpty()){
            return true;
        }
        if(cur==null||cur.isEmpty())return false;
        //如果栈顶是左括号，则非右括号全部进去
        if(head.data.equals("(")){
            if(!cur.equals(")"))return true;
        }
        int h=getPriority(head.data);
        int c=getPriority(cur);
        return h<c;
    }
    public static String Calculate(MyLinkedList<Info> infos){
        if(infos==null||infos.isEmpty()){
            return "无数据";
        }
        Integer res=0;
        MyLinkedList<Info> results=new MyLinkedList<>();
        while (!infos.isEmpty()){
            Info queueHead = infos.getQueueHead();
            if(queueHead==null||queueHead.isEmpty()){
                break;
            }
            //如果是数字，则直接入栈
            if(queueHead.type==NUMBER){
                results.push(queueHead);
            }
            //如果是变量,直接入栈
            else if (queueHead.type==VARIABLE) {
                results.push(queueHead);
                return "表达式还有变量，无法计算!";
            }
            //如果是运算符
            else if (queueHead.type==OPERATOR) {
                //从自定义运算符中寻找对应的运算符
                Operator o = OPERATORS_MAP.get(queueHead.getData());
                if(o==null)return "运算符为空";
                //通过自定义运算符名字，从元运算符集中找到运算符
                Operator o_meta = OPERATORS_MAP_META.get(o.getRule());
                if(o_meta==null)return "无对应的元运算符"+o.getRule();
                int argsNumber = o_meta.getArgsNumber();
                String[]args=new String[argsNumber];
                for (int i = 0; i < argsNumber; i++) {
                    Info pop = results.pop();
                    if(pop==null)return "运算数不足";
                    args[argsNumber-1-i]=pop.data;
                }
                //再从计算规则集中找到运算规则
                Double calcuate = o_meta.method.calcuate(args);
                results.push(new Info(calcuate.toString(),NUMBER));
            }
            infos.DeQueue();
        }
        Info pop = results.pop();
        if(pop==null){
            return null;
        }
        if(pop.type==VARIABLE_EXPERSSION){
            String data = pop.data;
            String[] split = data.split("@+");

        }
        String data = pop.data;
        return data;
    }
    //相加
    public static void plus(String a,String b,MyLinkedList<Info> results){
        Integer a1 = Integer.valueOf(a);
        Integer b1 = Integer.valueOf(b);
        Integer sum = a1 + b1;
        results.push(new Info(sum.toString(),NUMBER));
    }
    public static void subtraction(String a,String b,MyLinkedList<Info> results){
        Integer a1 = Integer.valueOf(a);
        Integer b1 = Integer.valueOf(b);
        Integer sum = a1 - b1;
        results.push(new Info(sum.toString(),NUMBER));
    }
    public static void multiplication(String a,String b,MyLinkedList<Info> results){
        Integer a1 = Integer.valueOf(a);
        Integer b1 = Integer.valueOf(b);
        Integer sum = a1 * b1;
        results.push(new Info(sum.toString(),NUMBER));
    }
    public static void division(String a,String b,MyLinkedList<Info> results){
        Integer a1 = Integer.valueOf(a);
        Integer b1 = Integer.valueOf(b);
        Integer sum = a1 / b1;
        results.push(new Info(sum.toString(),NUMBER));
    }
    public static void exponentiation(String a,String b,MyLinkedList<Info> results){
        Integer a1 = Integer.valueOf(a);
        Integer b1 = Integer.valueOf(b);
        double pow = Math.pow(a1.doubleValue(), b1.doubleValue());
        results.push(new Info(Double.valueOf(pow).toString(),NUMBER));
    }
    //变量赋值
    public static void assignment(MyLinkedList<Info> infos,MyMap<String,Variable> variables){
        if(infos==null||infos.isEmpty()||variables==null||variables.isEmpty())return;
        int count=infos.size();
        for (int i = 0; i < count; i++) {
            Info info = infos.get(i);
            if(info!=null&&!info.isEmpty()){
                //检测到变量，则从变量集中寻找同名的变量并赋值
                if(info.type==VARIABLE){
                    Variable variable = variables.get(info.data);
                    if(variable!=null){
                        info.assign(variable);
                    }
                }
            }
        }
    }
    //获取变量集
    public static MyLinkedList<Info> getVariable(MyLinkedList<Info> infos){
        MyLinkedList<Info>variables=new MyLinkedList<>();
        if(infos==null||infos.isEmpty())return variables;
        int count=infos.size();
        for (int i = 0; i < count; i++) {
            Info info = infos.get(i);
            if(info!=null&&!info.isEmpty()){
                if(info.type==VARIABLE){
                    variables.push(info);
                }
            }
        }
        return variables;
    }
    //判断是否为运算符
    public static boolean isOperator(char c){
        return OPERATORS_MAP.get(String.valueOf(c))!=null?true:false;
    }
    public static boolean isOperator(String token){
        Operator operator = OPERATORS_MAP.get(token);
        return OPERATORS_MAP.get(token)!=null?true:false;
    }
    //判断是否为变量
    public static boolean isAlphabet(char c){
        return (c>='a'&&c<='z')||(c >= 'A' && c <= 'Z')||c=='_';
    }
    public static boolean isVariable(String token){
        try {
            Double.parseDouble(token);
            return false;
        } catch (NumberFormatException e) {
            return OPERATORS_MAP.get(token)!=null?false:true;
        }
    }
    //判断是否为数字
    public static boolean isNumber(char c){
        return (c>='0'&&c<='9')||c=='.';
    }
    public static boolean isNumber(String token){
        if(token==null||token.isEmpty())return false;
        boolean isNumber = false;
        try {
            double number = Double.parseDouble(token);
            isNumber = true;
        } catch (NumberFormatException e) {
            // 解析失败，不是数字
        }
        return isNumber;
    }
    public static int tokenType(String token){
        if(token==null||token.isEmpty())return NOT;
        if(isOperator(token))return OPERATOR;
        if(isVariable(token))return VARIABLE;
        if (isNumber(token))return NUMBER;
        return NOT;
    }
    //合并同类项
    public static MyLinkedList<Info> MergeConstantTerms(MyLinkedList<Info> postfix){
        MyLinkedList<Info>MCT=new MyLinkedList<>();
        if(postfix==null||postfix.isEmpty())return MCT;
        MyLinkedList<Info>stack=new MyLinkedList<>();
        while (!postfix.isEmpty()){
            Info queueHead = postfix.getQueueHead();
            if(queueHead==null||queueHead.isEmpty())break;
            if(queueHead.type==NUMBER||queueHead.type==VARIABLE){
                stack.push(queueHead);
            } else if (queueHead.type==OPERATOR) {
                Operator operator = OPERATORS_MAP_META.get(queueHead.data);
                if(operator==null)break;
                int argsNumber = operator.getArgsNumber();
                String[]args=new String[argsNumber];
                boolean legal=true;
                for (int i = 0; i < argsNumber; i++) {
                    Info pop = stack.pop();
                    args[argsNumber-1-i]=pop.data;
                    if(pop==null||pop.isEmpty()||pop.type!=NUMBER) {
                        legal = false;
                        break;
                    }

                }
                if(!legal){
                    for (int i = 0; i < argsNumber; i++) {
                        if(args[i]!=null&&!args[i].isEmpty()){
                            stack.push(new Info(args[i],NUMBER));
                        }
                    }
                    stack.push(queueHead);
                }
                else {
                    Double calcuate = operator.calcuate(args);
                    stack.push(new Info(calcuate.toString(),NUMBER));
                }

            }
            postfix.DeQueue();
        }
        return stack;
    }
    //求偏导
    public static MyLinkedList<Info> partialDerivative(MyLinkedList<Info> postfix){
        MyLinkedList<Info> pD=new MyLinkedList<>();
        if(postfix==null||postfix.isEmpty())return pD;
        int size = postfix.size();
        MyMap<String,MyLinkedList<Info>> pDs=new MyMap<>();
        for (int i = 0; i < size; i++) {
            Info info = postfix.get(i);
            if(info==null||info.isEmpty());
            else {
                if(info.type==VARIABLE){
                    pDs.put(info.data,new MyLinkedList<Info>());
                }
            }
        }

        return pD;
    }
    //二叉树后序迭代器
    public static void postIteration(BinaryTree.leave leave){
        if(leave==null)return;
        postIteration(leave.lchild);
        postIteration(leave.rchild);
        if(leave.data.type==OPERATOR){
            Operator o = OPERATORS_MAP.get(leave.data.data);
            Operator o_meta = OPERATORS_MAP_META.get(o.getRule());
            int argsNumber = o_meta.getArgsNumber();
            String[]Args=new String[argsNumber];
            for (int i = 0; i < argsNumber; i++) {
                if(i==0)Args[i]=leave.lchild.data.data;
                if(i==1)Args[i]=leave.rchild.data.data;
            }
            Double calcuate = o_meta.calcuate(Args);
            leave.data=new Info(calcuate.toString(),NUMBER);
            leave.lchild=null;
            leave.rchild=null;
        }
    }
    public static String Calculate(BinaryTree binaryTree){
        if(binaryTree==null|| binaryTree.isEmpty())return "树为空";
        postIteration(binaryTree.head);
        return binaryTree.head.data.data;
    }
    public static BinaryTree PD(BinaryTree binaryTree, String variable) {
        return new BinaryTree(derive(binaryTree.head, variable));
    }

    public static BinaryTree.leave derive(BinaryTree.leave root, String variable) {
        if (root == null || variable == null) return null;

        BinaryTree.leave derivedNode = new BinaryTree.leave();
        String data = root.data.data;

        switch (data) {
            case "+":
            case "-":
                // For + and -, the derivative is the derivative of the left and right child
                derivedNode.data = new Info(data,OPERATOR);
                derivedNode.lchild = derive(root.lchild, variable);
                derivedNode.rchild = derive(root.rchild, variable);
                break;

            case "*":
                // For *, apply the product rule: (u'v + uv')
                derivedNode.data = new Info("+",OPERATOR);
                BinaryTree.leave left = new BinaryTree.leave();
                left.data = new Info("*",OPERATOR);
                left.lchild = derive(root.lchild, variable);
                left.rchild = root.rchild;

                BinaryTree.leave right = new BinaryTree.leave();
                right.data = new Info("*",OPERATOR);
                right.lchild = root.lchild;
                right.rchild = derive(root.rchild, variable);

                derivedNode.lchild = left;
                derivedNode.rchild = right;
                break;

            case "/":
                // For /, apply the quotient rule: (u'v - uv') / v^2
                derivedNode.data = new Info("/",OPERATOR);
                BinaryTree.leave numerator = new BinaryTree.leave();
                numerator.data = new Info("-",OPERATOR);

                BinaryTree.leave leftMul = new BinaryTree.leave();
                leftMul.data = new Info("*",OPERATOR);
                leftMul.lchild = derive(root.lchild, variable);
                leftMul.rchild = root.rchild;

                BinaryTree.leave rightMul = new BinaryTree.leave();
                rightMul.data = new Info("*",OPERATOR);
                rightMul.lchild = root.lchild;
                rightMul.rchild = derive(root.rchild, variable);

                numerator.lchild = leftMul;
                numerator.rchild = rightMul;

                BinaryTree.leave denominator = new BinaryTree.leave();
                denominator.data = new Info("*",OPERATOR);
                denominator.lchild = root.rchild;
                denominator.rchild = root.rchild; // Squaring the denominator

                derivedNode.lchild = numerator;
                derivedNode.rchild = denominator;
                break;

            default:
                // Handle variable and constant cases
                if (data.equals(variable)) {
                    derivedNode.data = new Info("1",NUMBER);
                } else {
                    derivedNode.data = new Info("0",NUMBER);
                }
                break;
        }

        return derivedNode;
    }

    //二叉树合并常数项
    public static BinaryTree.leave merge(BinaryTree.leave root){
        if(root==null)return null;
        merge(root.lchild);
        merge(root.rchild);
        if(root.data.type==OPERATOR){
            Operator operator = OPERATORS_MAP_META.get(root.data.data);
            int argsNumber = operator.getArgsNumber();
            String result="";
            if(argsNumber==1){
                if(root.lchild!=null&&root.lchild.data.type==NUMBER){
                    String data = root.lchild.data.data;
                    Double calcuate = operator.calcuate(data);
                    result=calcuate.toString();
                }
            } else if (argsNumber==2) {
                if(root.lchild!=null && root.rchild!=null){
                    if(root.lchild.data.type==NUMBER&&root.rchild.data.type==NUMBER){
                        String[]Args=new String[]{root.lchild.data.data,root.rchild.data.data};
                        Double calcuate = operator.calcuate(Args);
                        result=calcuate.toString();
                    } else if (root.lchild.data.type==NUMBER||root.rchild.data.type==NUMBER) {
                        if(root.lchild.data.type==NUMBER){
                            if(Double.valueOf(root.lchild.data.data)==0)result="0";
                        }
                        if(root.rchild.data.type==NUMBER){
                            if(Double.valueOf(root.rchild.data.data)==0)result="0";
                        }
                    }

                }
            }
            if(!result.equals("")){
                root.data=new Info(result,NUMBER);
                root.lchild=null;
                root.rchild=null;
            }
        }
        return null;
    }
    public static MyLinkedList<Info>BinaryTreeToLinkedList(BinaryTree binaryTree){
        if(binaryTree==null)return null;
        MyLinkedList<Info> infoMyLinkedList = new MyLinkedList<>();
        BTTLL(binaryTree.head,infoMyLinkedList);
        return infoMyLinkedList;
    }
    public static void BTTLL(BinaryTree.leave leave,MyLinkedList<Info> infoMyLinkedList){
        if(leave==null)return ;
        BTTLL(leave.lchild,infoMyLinkedList);
        BTTLL(leave.rchild,infoMyLinkedList);
        infoMyLinkedList.push(leave.data);
    }
}
