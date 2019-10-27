package com.zjm.java.design.observe;

import java.util.ArrayList;
import java.util.List;

public class ObserverDesign {

    public static void main(String[] args) {
        // 被观察者
        SubjectA subject = new SubjectA();
        // 观察者
        Observe observeA = new ObserveA();
        // 注册观察者
        subject.register(observeA);
        // 被观察者执行方法，观察者观察到
        subject.update();
    }

    /**
     * 被观察者父类
     */
    public static class Subject{

        private List<Observe> observeList = new ArrayList<>();

        public void register(Observe observe) {
            observeList.add(observe);
        }

        public void remove(Observe observe) {
            observeList.remove(observe);
        }

        protected void notifyEveryObserve(String args){
            for(Observe observe : observeList) {
                observe.execute(args);
            }
        }
    }

    /**
     * 具体被观察的对象
     */
    public static class SubjectA extends Subject{
        public void update(){
            System.out.println("SubjectA#update");
            notifyEveryObserve("args");
        }
    }

    /**
     * 观察者接口
     */
    public interface Observe {
        void execute(String args);
    }

    /**
     * 具体的观察者
     */
    public static class ObserveA implements Observe{

        @Override
        public void execute(String args) {
            System.out.println("ObserveA#execute:"+args);
        }
    }
}
