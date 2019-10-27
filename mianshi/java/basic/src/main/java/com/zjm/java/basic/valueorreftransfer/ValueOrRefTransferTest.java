package com.zjm.java.basic.valueorreftransfer;

public class ValueOrRefTransferTest {

    static class Test0{
        public static void main(String[] args) {
            int i = 1;
            mymethod(i);
            // 输出1
            System.out.println(i);
        }
        private static void mymethod(int i){
            i++;
        }
    }


    static class Test1{
        public static void main(String[] args) {
            Integer i = 1;
            mymethod(i);
            // 输出1
            System.out.println(i);
        }
        private static void mymethod(Integer i){
            i++;
        }
    }

    static class Test1_2{
        public static void main(String[] args) {
            Integer i = 128;
            mymethod(i);
            // 输出1
            System.out.println(i);
        }
        private static void mymethod(Integer i){
            i++;
        }
    }

    static class Test2{
        public static void main(String[] args) {
            Integer i = new Integer(1);
            mymethod(i);
            // 输出1
            System.out.println(i);
        }
        private static void mymethod(Integer i){
            i++;
        }
    }

    static class Test3{
        public static void main(String[] args) {
            String str = "hello";
            mymethod(str);
            // 输出hello
            System.out.println(str);
        }
        private static void mymethod(String str){
            str = "world";
        }
    }

    static class Test4{
        public static void main(String[] args) {
            String str = new String("hellow");
            mymethod(str);
            // 输出hello
            System.out.println(str);
        }
        private static void mymethod(String str){
            str = "world";
        }
    }

    static class Test5{
        public static void main(String[] args) {
            StringBuilder sb = new StringBuilder("hellow");
            mymethod(sb);
            // 输出hello world
            System.out.println(sb.toString());
        }
        private static void mymethod(StringBuilder sb){
            sb.append(" world");
        }
    }



    static class TestN{

        public static void main(String[] args) {

            int a = 1;
            Integer integer = 1;
            long l = 1l;
            Long l2 = 1L;
            StringBuilder sb = new StringBuilder("hello");
            String str = "hello";

            changeIntValue(a);
            System.out.println(a);

            changeIntegerValue(integer);
            System.out.println(integer);

            changelongValue(l);
            System.out.println(l);

            changLongValue(l2);
            System.out.println(l2);

            changeRefenceValue(sb);
            System.out.println(sb.toString());

            changeStringValue(str);
            System.out.println(str);

        }

        static void changeIntValue(int a) {
            a++;
        }
        static void changeIntegerValue(Integer integer) {
            integer++;
        }
        static void changelongValue(long a) {
            a++;
        }
        static void changLongValue(Long l2) {
            l2++;
        }
        static void changeRefenceValue(StringBuilder sb) {
            sb.append("world");
        }
        static void changeStringValue(String str) {
            str = "world";
        }

    }
}
