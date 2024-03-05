package comchenzhihao;

import jdk.nashorn.internal.scripts.JD;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
public class View extends JFrame {
    /**************************Fields**************************/
    // 中缀表达式输入框
    JTextField infixField = new JTextField("A+B", 20);
    // 确定按钮
    JButton enSure = new JButton("转为后缀表达式");
    JButton castToBinaryTree = new JButton("写出二叉树");
    JButton PD=new JButton("求偏导");
    JButton merge=new JButton("合并常数项");

    MyLinkedList<assignTextField> assignTextFields = new MyLinkedList<>();
    public static final ExecutorService CACULATOR_POOL= Executors.newSingleThreadExecutor();
    /**************************Classes**************************/
    @Data
    static class assignTextField extends JTextField {
        //变量名
        String name;
    }
    @Data
    static class caculateTask implements Runnable{
        MyMap<String,Variable>variables;
        MyLinkedList<Info> postfix;
        public caculateTask(MyLinkedList<Info> postfix,MyMap<String,Variable>variables){
            this.postfix=postfix;
            this.variables=variables;
        }
        @Override
        public void run() {
            ExpressionConverter.assignment(postfix,variables);
            String calculate = ExpressionConverter.Calculate(postfix);
            System.out.println(calculate);
            showTips(calculate);
        }
    }
    /**************************Methods**************************/
    public View(String title) {
        this.setTitle(title);
        init();
    }

    public View() {
    }

    public void init() {
        this.setDefaultCloseOperation(View.DISPOSE_ON_CLOSE);
        this.setSize(400, 600);
        this.setLayout(new FlowLayout());
        this.enSure.addActionListener(enSureEvent());
        this.castToBinaryTree.addActionListener(castToBinaryTreeEvent());
        this.PD.addActionListener(PDEvent());
        this.merge.addActionListener(mergeEvent());
        this.add(infixField);
        this.add(enSure);
        this.add(castToBinaryTree);
        this.add(PD);
        this.add(merge);
        this.setVisible(true);
    }
    public static void showTips(String tips){
        JDialog jDialog=new JDialog();
        jDialog.add(new JLabel(tips));
        jDialog.setSize(200,150);
        jDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jDialog.setVisible(true);
    }

    public void mountAssignTextFields(MyLinkedList postfix) {
        if (assignTextFields.isEmpty()) return;
        int count = assignTextFields.size();
        JDialog jDialog = new JDialog();
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jDialog.setLayout(new GridBagLayout());
        jDialog.setSize(400, 600);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // 列索引为0
        gbc.gridy = 0; // 行索引为0
        gbc.anchor = GridBagConstraints.WEST; // 左对齐
        jDialog.add(new JLabel(postfix.postDisplay()));
        gbc.gridy++;
        for (int i = 0; i < count; i++) {
            assignTextField assignTextField = assignTextFields.get(i);
            JLabel label = new JLabel(assignTextField.getName());
            jDialog.add(label, gbc);

            gbc.gridx = 1; // 列索引为1
            jDialog.add(assignTextField, gbc);

            gbc.gridy++; // 行索引递增
            gbc.gridx = 0; // 重置列索引为0
        }

        JButton tmpEnsure = new JButton("确认输入");
        gbc.gridwidth = 2; // 跨越两列
        gbc.gridy++;
        jDialog.add(tmpEnsure, gbc);
        tmpEnsure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count=assignTextFields.size();
                if(count<=0)jDialog.dispose();
                MyMap<String,Variable> variables=new MyMap<>();
                for (int i = 0; i < count; i++) {
                    assignTextField assignTextField = assignTextFields.get(i);
                    if(assignTextField==null
                            ||assignTextField.getText()==null
                            ||assignTextField.getText().isEmpty()){
                        showTips("您还有未输入数据的变量");
                        break;
                    }
                    variables.put(assignTextField.name,
                            new Variable(assignTextField.name,assignTextField.getText()));
                }
                //开启线程任务
                CACULATOR_POOL.submit(new caculateTask(postfix,variables));
                jDialog.dispose();
            }
        });

        jDialog.setVisible(true);
    }
    public void clearAssignTextFields() {
        assignTextFields.clear();
        this.repaint();
    }
    public ActionListener enSureEvent() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAssignTextFields();
                String expression = infixField.getText();
                if (expression == null || expression.isEmpty()) return;
                MyLinkedList postfix = ExpressionConverter.castToPostfix(expression);
                postfix.display();
                MyLinkedList<Info> variable = ExpressionConverter.getVariable(postfix);
                if (variable.size() <= 0) {
                    String calculate = ExpressionConverter.Calculate(postfix);
                    System.out.println(calculate);
                    showTips(calculate);
                } else {
                    int count = variable.size();
                    for (int i = 0; i < count; i++) {
                        assignTextField assignTextField = new assignTextField();
                        Info v = variable.get(i);
                        assignTextField.setName(v.data);
                        assignTextField.setColumns(20);
                        assignTextFields.push(assignTextField);
                    }
                    mountAssignTextFields(postfix);
                }
            }
        };
    }
    public ActionListener castToBinaryTreeEvent(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(infixField==null||infixField.getText()==null||infixField.getText().isEmpty())return;
                BinaryTree binaryTree=new BinaryTree();
                MyLinkedList postfix = ExpressionConverter.castToPostfix(infixField.getText());
                binaryTree.PostConstructor(postfix);
                String s = binaryTree.levelTraversal();
                showTips(s);
                System.out.println(s);
            }
        };
    }
    public ActionListener PDEvent(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(infixField==null||infixField.getText()==null||infixField.getText().isEmpty())return;

                MyLinkedList<Info> postfix = ExpressionConverter.castToPostfix(infixField.getText());
                MyLinkedList<Info> variables = ExpressionConverter.getVariable(postfix);
                MyMap<String,MyLinkedList<Info>>map=new MyMap<>();
                int size= variables.size();

                for (int i = 0; i < size; i++) {
                    Info info = variables.get(i);
                    BinaryTree binaryTree = new BinaryTree();
                    binaryTree.PostConstructor(ExpressionConverter.castToPostfix(infixField.getText()));
                    BinaryTree pd = ExpressionConverter.PD(binaryTree, info.data);
                    MyLinkedList<Info> infoMyLinkedList = ExpressionConverter.BinaryTreeToLinkedList(pd);
                    BinaryTree b = new BinaryTree();
                    b.PostConstructor(infoMyLinkedList);
                    ExpressionConverter.merge(b.head);
                    map.put(info.data,ExpressionConverter.BinaryTreeToLinkedList(b));
                    System.out.println(b.levelTraversal());
                }

            }
        };
    }
    public ActionListener mergeEvent(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(infixField==null||infixField.getText()==null||infixField.getText().isEmpty())return;
                MyLinkedList postfix = ExpressionConverter.castToPostfix(infixField.getText());
                BinaryTree binaryTree = new BinaryTree();
                binaryTree.PostConstructor(postfix);
                ExpressionConverter.merge(binaryTree.head);
                System.out.println(binaryTree.levelTraversal());
            }
        };
    }
}
