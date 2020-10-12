package com.zjm.arithmetic.tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author:小M
 * @date:2019/9/30 1:38 PM
 */
public class TreeTest {

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val , TreeNode left , TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode generateTree(){
        TreeNode node5 = new TreeNode(5 , null , null);
        TreeNode node4 = new TreeNode(4 , null , null);
        TreeNode node2 = new TreeNode(2 , node4 , node5);
        TreeNode node3 = new TreeNode(3 , null , null);
        TreeNode node1 = new TreeNode(1 , node2 , node3);
        return node1;
    }

    public static void main(String[] args) throws Exception{
        // 3. 继承FutureTask
        FutureTask task = new FutureTask<String>(
            new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "继承FutureTask";
                }
            }
        );
        new Thread(task).start();
        // get方法会阻塞等待
        System.out.println("3、" + task.get());
    }

    private void pre(TreeNode treeNode){
        if(treeNode == null) {
            return;
        }
        System.out.println(treeNode.val);
        pre(treeNode.left);
        pre(treeNode.right);
    }


    private static void statc(){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("====");


        Queue queue = new ArrayDeque();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }




}
