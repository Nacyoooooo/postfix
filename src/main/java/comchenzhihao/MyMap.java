package comchenzhihao;

//手动实现map集合
//K是key的类型，V是value的类型
public class MyMap <K,V>{

    /**************************Fields**************************/
    int capacity=0;//map集合容量
    int MAX_CAPACITY=1<<43;//aka 16 当前可容纳最大容量
    int DEFAULT_MAX_CAPACITY=1<<32;//aka 2^32 整体最大容量，不能超过该值
    float loadFactor=0.75f;//负载因子
    Entry<K,V>[]table;//存储数据的键值对数组
    /*************************Classes************************/
    //键值对对象
    static final class Entry<K,V> {
        K key;//键
        V value;//值
        Entry next;
        public Entry(){

        }
        public Entry(K key,V value){
            this.key=key;
            this.value=value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
    public <K,V>MyMap(){
        this.capacity=0;
        this.table=new Entry[MAX_CAPACITY];
    }
    /**************************Methods**************************/
    //实现put接口
    public V put(K key,V value){
        return putVal(hash(key),key,value);
    }
    public V putVal(int hash,K key,V value){
        //创建新的键值对对象
        Entry<K, V> entry = new Entry<>(key,value);
        //判断map集合中是否存在数据，没有则进行初始化
        Entry<K,V> []tab=this.table;
        int index=(tab.length-1)&hash;
        //先判断是否要扩容
        //如果需要则先扩容
        //不需要则直接开始插入数据
        if(isExpanded()) reSize();
        //计算哈希值，并计算在该数组中的索引
        //如果该处的索引为空，则可以直接插入
        if (tab[index]==null){
            tab[index]=entry;
            this.capacity++;
        }
        //如果发生哈希冲突，则以链表的形式存入
        else {
            Entry<K, V> e = tab[index];
            while (e!=null){
                K k = e.getKey();
                //如果二者的哈希值相同且equal方法也相同，则认为是同一个key，则直接覆盖这个key对应的value
                if(k.hashCode()==key.hashCode()&&k.equals(key)){
                    V v = e.getValue();
                    e.setValue(value);
                    return v;
                }
                //如果e之后没有元素，且循环没被打断，则直接插入
                if(e.next==null){
                    e.next=entry;
                    this.capacity++;
                }
                e=e.next;
            }
        }
        return null;

        //如果哈希值相同，则调用equal方法比较，以equal方法为主
    }
    //实现get接口
    public V get(K key){
        return getVal(hash(key),key);
    }
    public V getVal(int hash,K key){
        Entry<K,V>[]tab=this.table;
        int index=(tab.length-1)&hash;
        Entry<K, V> entry = tab[index];
        if(entry==null){
            return null;
        }else {
            while (entry!=null){
                K k = entry.getKey();
                if(k.equals(key)){
                    return entry.getValue();
                }
                entry=entry.next;
            }
            return null;
        }
    }
    //判断是否扩容
    public boolean isExpanded(){
        //计算当前容量是否达到扩容的标准  公式 为    当前容量    >= 允许最大容量 * 负载因子
        //如果达到了扩容条件,则扩容，返回true
        if(this.capacity>=MAX_CAPACITY*loadFactor){
            return true;
        }
        //否则不需要扩容
        else return false;
    }
    //扩容接口
    public void reSize(){
        //扩容需要先扩大当前最大容量
        //如果小于默认最大容量，则允许扩大，否则不允许
        if(MAX_CAPACITY<DEFAULT_MAX_CAPACITY) MAX_CAPACITY<<=1;
        //创建一个新的数组，并将数据全部转移到新的数组中
        Entry<K,V>[]newTables=new Entry[MAX_CAPACITY];
        Entry<K,V>[]oldTables=this.table;
        for (Entry<K, V> entry : oldTables) {
            //如果entry对象不为空，则将数据插入到新的数组中
            if(entry!=null){
                K key = entry.getKey();
                newTables[(key.hashCode()-1)&MAX_CAPACITY]=entry;
            }
        }
        this.table=newTables;
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    public boolean isEmpty(){
        return this.capacity<=0;
    }
    //移除key
    public V remove(K key){
        return removeKey(hash(key),key);
    }
    public V removeKey(int hash,K key){
        Entry<K,V>[]tab=this.table;
        int index=(tab.length-1)&hash;
        Entry<K, V> e = tab[index];
        Entry<K,V> top=e;
        while (e!=null){
            K k = e.getKey();
            if(k.hashCode()==hash&&k.equals(key)){
                if(top.equals(e)){
                    if(e.next==null){
                        tab[index]=null;
                    }
                    else {
                        tab[index]=e.next;
                    }
                }
                else {
                    if(e.next==null){
                        top.next=null;
                    }
                    else {
                        top.next=e.next;
                    }
                }
                return e.getValue();
            }
        }
        return null;
    }
    public void clear(){
        this.capacity=0;//map集合容量
        this.MAX_CAPACITY=1<<4;//aka 16 当前可容纳最大容量
        this.DEFAULT_MAX_CAPACITY=1<<32;//aka 2^32 整体最大容量，不能超过该值
        this.loadFactor=0.75f;//负载因子
        this.table=null;//存储数据的键值对数组
    }
}