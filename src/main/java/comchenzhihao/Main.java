/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package comchenzhihao;

import org.junit.jupiter.api.Test;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        List<List<List<Vector>>> testCase=new ArrayList<>();
        //共有几个测试用例
        int N= in.nextInt();
        for (int i = 0; i < N; i++) {
            //共有几帧
            int M=in.nextInt();
            List<List<Vector>>characteristic=new ArrayList<>();
            for (int j = 0; j < M; j++) {
                //每一帧有几个特征
                int line=in.nextInt();

                List<Vector> vectors=new ArrayList<>();
                for (int k = 0; k < line; k++) {
                    int x1=in.nextInt();
                    int x2=in.nextInt();
                    vectors.add(new Vector(x1,x2));
                }
                characteristic.add(vectors);
            }
            testCase.add(characteristic);
        }
        System.out.println(testCase);
        List<Integer>max=new ArrayList<>();
        //对每一个测试用例进行遍历
        for (int i = 0; i < N; i++) {
            //存储连续最大的集合
            Map<Vector,Integer>Continuous=new HashMap<>();
            //获取本次计算所用的测试用例
            List<List<Vector>> lists = testCase.get(i);
            max.add(0);
            //如果本次测试是空的，则不用计算，直接为0
            if (!lists.isEmpty()){
                //如果不是空的
                for (List<Vector> list : lists) {
                    //如果这个帧没有特征，则直接结束，有特征就继续
                    //如果连续最大是空的，则全部进入
                    if(Continuous.isEmpty()){
                        for (Vector vector : list) {
                            Continuous.put(vector,1);
                        }
                    }
                    //如果不是，则开始检查谁不连续,
                    else {
                        Set<Vector> vectors = new HashSet<>();
                        for (Vector vector : Continuous.keySet()) {
                            vectors.add(vector);
                        }
                        for (Vector vector : list) {
                            //如果存在这个key，则在其基础上加1
                            if(Continuous.containsKey(vector)){
                                Integer i1 = Continuous.get(vector);
                                i1++;
                                Continuous.put(vector,i1);
                                vectors.remove(vector);
                            }
                            //如果不存在，则置零
                            else {
                                Continuous.put(vector,1);
                                vectors.remove(vector);
                            }
                        }
                        //遍历之后清除连续
                        for (Vector vector : vectors) {
                            Continuous.remove(vector);
                        }
                    }
                    //开始计算本次连续谁最大
                    int finalI = i;
                    Continuous.forEach((key, value)->{
                        Integer i1 = max.get(finalI);
                        if(i1<value){
                            max.set(finalI,value);
                        }
                    });
                }
            }
        }
        for (Integer i : max) {
            System.out.println(i);
        }
    }

    static class Vector{
        int x_1;
        int x_2;
        @Override
        public String toString() {
            return "Vector{" +
                    "x_1=" + x_1 +
                    ", x_2=" + x_2 +
                    '}';
        }


        public Vector(int x_1,int x_2){
            this.x_1=x_1;
            this.x_2=x_2;
        }

        @Override
        public int hashCode() {
            return this.x_1+x_2;
        }

        @Override
        public boolean equals(Object o) {
            if(o==null)return false;
            if(this==o)return true;
            Vector o1 = (Vector) o;
            if(o1.x_1==this.x_1&&o1.x_2==this.x_2){
                return true;
            }
            return false;
        }
    }
    @Test
    public void testVector(){
        Map<Vector,Integer>map=new HashMap<>();

        Vector v1 = new Vector(1, 2);
        Vector v2 = new Vector(2, 1);
        map.put(v1,1);
        map.put(v2,2);
        Integer i = map.get(new Vector(2, 1));
        System.out.println(i);
    }
}