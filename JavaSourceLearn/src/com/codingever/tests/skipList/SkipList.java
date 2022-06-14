package com.codingever.tests.skipList;

import java.util.Random;
import java.util.Stack;

public class SkipList <T>{

    SkipNode headNode;// 头结点
    int highLevel;// 当前跳表索引层
    Random random;// 用于投篩子
    final int Max_level = 32;// 最大的層數

    public SkipList(){
        random = new Random();
        headNode = new SkipNode(Integer.MIN_VALUE, null);
        highLevel = 0;
    }

    // 查詢操作
    public SkipNode search(int key){
        SkipNode temp = headNode;
        while(temp != null){
            if (temp.key == key){// 説明查找的当前节点即为要查找的节点
                return temp;
            }else if (temp.right == null){// 右侧没有了，只能下降
                temp = temp.down;
            }else if (temp.right.key > key){// 说明位于temp 和temp.right之间，向下查找
                temp = temp.down;
            }else {// 向右查找
                temp = temp.right;
            }
        }
        return null;
    }

    // 删除操作
    public void delete(int key){
        SkipNode temp = headNode;
        while(temp != null){
            if (temp.right == null){
                temp = temp.down;
            } else if (temp.right.key == key){
                temp.right = temp.right.right;
                temp = temp.down;
            } else if(temp.right.key > key){
                temp = temp.down;
            } else{
                temp = temp.right;
            }
        }
    }

    // 插入操作
    public void insert(SkipNode node){
        int key = node.key;
        SkipNode findNode = search(key);
        if(findNode != null){// 如果存在这个键值为key的节点
            findNode.value = node.value;// 更新value
            return;
        }

        Stack<SkipNode> stack = new Stack<>();// 存储向下的节点，这些节点可能在右侧插入节点
        SkipNode temp = headNode;// 查找待插入的节点
        while(temp != null){
            if (temp.right == null){
                stack.add(temp);// 将曾经向下的节点记录
                temp = temp.down;
            } else if (temp.right.key > key){
                stack.add(temp);// 将曾经向下的节点记录
                temp = temp.down;
            } else{
                temp = temp.right;
            }
        }

        // 查找结束，从最底层开始添加
        int level = 1;// 当前层数
        SkipNode downNode = null;// 保持前驱结点（即down的指针，初始化为null）
        while(!stack.isEmpty()){
            // 在该层插入node
            temp = stack.pop();//抛出带插入节点的左侧节点
            SkipNode nodeTeam = new SkipNode(node.key, node.value);// 节点需要重新创建
            nodeTeam.down = downNode;// 处理竖方向
            downNode = nodeTeam;// 标记下次使用
            if(temp.right == null){// 右侧为null，插入在末尾
                temp.right = nodeTeam;
            }else {
                node.right = temp.right;
                temp.right = node;
            }
            // 考虑是否需要向上
            if (level > Max_level){
                break;
            }
            double num = random.nextDouble();//[0-1]的随机数
            if (num > 0.5){
                break;
            }
            level++;
            if (level > highLevel){// 比当前最大高度要高，但是依然在允许范围内，需要改变head节点
                highLevel = level;
                // 需要创建一个新的节点
                SkipNode highHeadNode = new SkipNode(Integer.MIN_VALUE, null);
                highHeadNode.down = headNode;
                headNode = highHeadNode;// 改变head
                stack.add(headNode);// 下次抛出head
            }
        }
    }

    // 打印操作
    public void print(){
        SkipNode tempNode = headNode;
        int index = 1;
        SkipNode last = tempNode;
        while(last.down != null){
            last = last.down;
        }
        while(tempNode != null){
            SkipNode enumNode = tempNode.right;
            SkipNode enumLast = last.right;
            System.out.printf("%-8s", "head->");
            while (enumLast != null && enumNode != null){
                if (enumLast.key == enumNode.key){
                    System.out.printf("%-5s", enumLast.key + "->");
                    enumLast = enumLast.right;
                    enumNode = enumNode.right;
                } else{
                    enumLast = enumLast.right;
                    System.out.printf("%-5s", "");
                }
            }
            tempNode = tempNode.down;
            index++;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipList<Integer> list = new SkipList<>();
        for(int i = 1; i < 20; i++){
            list.insert(new SkipNode(i, 666));
        }
        System.out.println("删除前：");
        list.print();
        list.delete(4);
        list.delete(8);
        System.out.println("删除后：");
        list.print();
    }
}
