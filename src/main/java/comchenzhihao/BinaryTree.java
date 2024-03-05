package comchenzhihao;

import lombok.ToString;

//二叉树
public class BinaryTree implements Cloneable{
    int size;
    leave head;
    //树的叶子节点
    @ToString
    static class leave{
        Info data;
        //数据的类型
        leave lchild;
        leave rchild;
        public leave(){}
        public leave(Info data){
            this.data=data;
        }
    }
    public BinaryTree(leave leave){
        this.head=leave;
        LRD();
    }

    public BinaryTree(){
        this.size=0;
        head=null;
    }
    public BinaryTree(Info data){
        this.head=new leave(data);
        this.size++;
    }
    //根据后缀表达式构造二叉树
    public void PostConstructor(MyLinkedList<Info> postfix){
        BinaryTree binaryTree = new BinaryTree();
        if(postfix==null||postfix.isEmpty()){
            return;
        }
        //存储数字，变量
        MyLinkedList<leave> leaves=new MyLinkedList();
        while (!postfix.isEmpty()){
            Info queueHead = postfix.getQueueHead();
            if(queueHead==null||queueHead.isEmpty()){
                break;
            }
            else {
                //如果栈顶是数字或者变量，则直接入栈
                if(queueHead.type==Info.NUMBER||queueHead.type==Info.VARIABLE){
                    leaves.push(new leave(queueHead));
                    this.size++;
                }
                //如果栈顶是运算符，则从栈中取两个元素并构成二叉树
                else if (queueHead.type==Info.OPERATOR) {
                    Operator o = ExpressionConverter.OPERATORS_MAP.get(queueHead.data);
                    Operator operator = ExpressionConverter.OPERATORS_MAP_META.get(o.getRule());
                    int argsNumber = operator.getArgsNumber();
                    leave[]Args=new leave[2];
                    for (int i = 0; i < argsNumber; i++) {
                        Args[i]=leaves.pop();
                    }
                    leave top = new leave(queueHead);

                    if(argsNumber==1){
                        top.lchild=Args[0];
                    } else if (argsNumber==2) {
                        top.rchild=Args[0];
                        top.lchild=Args[1];
                    }

                    leaves.push(top);
                    this.size++;
                }

            }
            postfix.DeQueue();
        }
        leave head = leaves.pop();
        this.head=head;
    }

    public boolean isOperator(Info info){
        if(info==null||info.type!=Info.OPERATOR){
            return false;
        }
        return true;
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return this.size<=0||this.head==null;
    }
    public void constructLeave(MyLinkedList operators,MyLinkedList numbers){

    }
    String post="";
    //后序遍历的结果
    private void PostorderTraversal(leave t){
        if(t==null){
            return;
        }
        PostorderTraversal(t.lchild);
        PostorderTraversal(t.rchild);
    }
    //后序遍历接口
    public void LRD(){
        PostorderTraversal(this.head);
    }
    private int Depth(leave l){
        if(l==null){
            return 0;
        }
        int left=Depth(l.lchild);
        int right=Depth(l.rchild);
        return (left>right?left:right)+1;
    }
    public int getDepth(){
        return Depth(this.head);
    }

    //层序遍历
    public String levelTraversal(){
        int level=getDepth();
        int floor=0;
        String res="";
        MyLinkedList<leave> queue=new MyLinkedList<>();
        queue.EnQueue(this.head);
        //如果队列不为空，则继续，直到队列空了为止
        while (!queue.isEmpty()){
            int size= queue.size();
            for (int i = 0; i < size; i++) {
                leave leave = queue.DeQueue();
                for (int j = floor; j < level; j++) {
                    res+="\t";
                }
                res+=leave.data.toString();
                if(leave.lchild!=null){
                    queue.EnQueue(leave.lchild);
                }
                if(leave.rchild!=null){
                    queue.EnQueue(leave.rchild);
                }
                res+="\t";
            }
            floor++;
            res+="\n";
        }
        return res;
    }

}
