package comchenzhihao;

import lombok.Data;

@Data
public class Info{
    //无
    public static int NOT=0;
    //数字
    public static int NUMBER=1;
    //变量（元变量）
    public static int VARIABLE=2;
    //操作符
    public static int OPERATOR=3;
    //变量表达式，用于计算时操作栈弹出变量和数字，以及变量和变量这两种情况的时候
    public static int VARIABLE_EXPERSSION=4;
    String data;
    //数据的类型
    int type;

    @Override
    public String toString() {
        return this.data;
    }

    public Info(){}

    @Override
    public boolean equals(Object o) {
        return this.data.equals(o);
    }

    public Info(String data, int type) {
        this.data = data;
        this.type = type;
    }
    public boolean isEmpty(){
        if(this.data==null||this.data.isEmpty()){
            return true;
        }
        else return false;
    }
    public void assign(Variable variable){
        if(variable==null||variable.isEmpty())return;
        this.data=variable.value;
        this.type=NUMBER;
    }
}
