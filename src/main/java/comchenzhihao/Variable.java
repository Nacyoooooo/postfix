package comchenzhihao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//变量
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Variable {
    //变量名
    String name;
    //变量值
    String value;
    public boolean isEmpty(){
        if(this.name==null||this.value==null||this.name.isEmpty()||this.value.isEmpty()){
            return true;
        }
        return false;
    }

}
