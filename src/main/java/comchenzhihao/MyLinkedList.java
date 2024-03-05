package comchenzhihao;
//链表
public  class  MyLinkedList<T> {


    static class ListNode<T>{
        T data;
        ListNode next;//下一节点
        ListNode pre;//上一节点
        public ListNode(){

        }
        public ListNode(T data){
            this.data=data;
        }
    }
    //头节点存在，但无数据
    private String string;

    private ListNode head;
    private ListNode tail;
    private int size;
    public MyLinkedList(){
        head=new ListNode();
        tail=null;
        size=0;
    }
    public String postDisplay(){
        this.string="";
        ListNode h = this.head.next;
        while (h!=null){
            this.string+=h.data.toString()+" ";
            h=h.next;
        }
        return this.string;
    }
    @Override
    public String toString() {
        postDisplay();
        return this.string;
    }
    //添加数据到链表尾
    public boolean append(T data){
        ListNode newNode=new ListNode(data);
        if(tail==null){
            tail=newNode;
            head.next=tail;
            tail.pre=head;
            size++;
        }else {
            newNode.pre=tail;
            tail.next=newNode;
            tail=tail.next;
            size++;
        }
        postDisplay();
        return true;
    }
    //插在该位置的节点后面
    public boolean insert(int position,T data){
        //不能超过长度和不能插在头节点以及负数
        if(position>size||position<0){
            return false;
        }
        //尾插
        if(position==size){
            this.append(data);
        } else if (position==0) {
            ListNode newNode=new ListNode(data);
            ListNode next = head.next;
            head.next=newNode;
            if(next!=null){
                next.pre=newNode;
            }
            newNode.pre=head;
            newNode.next=next;
            size++;
        } else {
            ListNode cur=head.next;
            for (int i = 1; i <=position ; i++) {
                if(i!=position){
                    if(cur==null){
                    return false;
                }
                    cur=cur.next;
                }
                else {
                    if(cur==null){
                        return false;
                    }
                    ListNode newNode=new ListNode(data);
                    ListNode next = cur.next;
                    cur.next=newNode;
                    if(next!=null){
                        next.pre=newNode;
                    }
                    newNode.pre=cur;
                    newNode.next=next;
                    size++;
                }

            }
        }postDisplay();
        return true;
    }
    public void display(){
        ListNode cur=head.next;
        while (cur!=null){
            System.out.print(cur.data+" ");
            cur=cur.next;
        }
        System.out.println();
    }
    //移除链表首个元素
    public T removeFirst(){
        //如果链表没有元素，不用返回
        if(size==0){
            return null;
        }
        //获取链表的第一个有效元素
        ListNode next = head.next;
        //如果链表的第一个有效元素为空，则不需要移除
        if(next==null){
            return null;
        }

        //如果链表只有一个元素，则链表第一个元素和链尾失去引用
        if(this.size==1){
            this.head.next=null;
            this.tail=null;
        }
        else {
            //获取第一个有效元素的下一个元素，即第二个元素
            ListNode next1 = next.next;
            if(next1==null){
                head.next=null;
            }
            else {
                head.next=next1;
            }
        }

        this.size--;
        postDisplay();
        return (T) next.data;
    }
    //删除链表最后一个元素
    public T removeLast(){
        if(size==0) {
            return null;
        }
        ListNode pre = tail.pre;
        if(pre==null){
            return null;
        }
        ListNode tail1 = tail;
        tail=pre;
        tail.next=null;
        size--;
        postDisplay();
        return (T) tail1.data;
    }
    //删除指定位置的元素
    public T removeAt(int position){
        if(position<=0||position>size){
            return null;
        }
        if(position==1){
            return removeFirst();
        } else if (position==size) {
            return removeLast();
        }
        ListNode cur = head.next;
        for (int i = 1; i <= position; i++) {
            if(i!=position){
                if(cur==null){
                    return null;
                }
                cur=cur.next;
            }
            else {
                if(cur==null){
                    return null;
                }
                ListNode next = cur.next;
                ListNode pre = cur.pre;
                if(pre==null){
                    return null;
                }
                pre.next=next;
                if(next==null){
                    return null;
                }
                next.pre=pre;
                size--;
                return (T) cur.data;
            }
        }
        postDisplay();
        return null;
    }
    public boolean isEmpty(){
        return size<=0||head.next==null||tail==null;
    }
    //弹栈
    public T pop(){
        return removeLast();
    }
    //入栈
    public boolean push(T data){
        return append(data);
    }
    //栈是否为空
    public boolean StackEmpty(){
        return isEmpty();
    }
    //取栈顶元素
    public T getStackHead(){
        if(isEmpty()){
            return null;
        }
        return (T) tail.data;
    }
    //入队
    public boolean EnQueue(T data){
        return append(data);
    }
    //出队
    public T DeQueue(){
        return removeFirst();
    }
    //队列是否为空
    public boolean QueueEmpty(){
        return isEmpty();
    }
    //取队头
    public T getQueueHead(){
        if(isEmpty()){
            return null;
        }
        ListNode next = head.next;
        return (T) next.data;
    }
    //取队尾元素
    public T getQueueTail(){
        if(isEmpty()){
            return null;
        }
        if(tail==null){
            return null;
        }
        return (T) tail.data;
    }
    public int size(){
        return size;
    }
    public boolean put(T data){
        return append(data);
    }
    //根据索引获取元素，从0开始
    public T get(int index){
        if(index>=size()||index<0)return null;
        ListNode<T>listNode=this.head.next;
        for (int i = 0; i < index; i++) {
            if(listNode==null)return null;
            listNode=listNode.next;
        }
        if(listNode==null)return null;
        return listNode.data;
    }
    public void clear(){
        this.head=new ListNode();
        this.tail=null;
        this.size=0;
        this.string="";

    }
}
