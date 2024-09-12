package com.hua.ruleengine.tree;

import java.util.List;

public class Node {

    //表达式
    private String expression;

    //子节点
    private List<Node> childNodes;

    //结果
    private Object result;

    //节点类型 判断
    private Boolean type;//true: 规则节点 false:结果节点

    //获取表达式
    public String getExpression() {
        return expression;
    }

    //表达式赋值
    public void setExpression(String expression) {
        this.expression = expression;
    }


    //字符串规则格式化
    @Override
    public String toString() {
        return "Node {" +
                "expression='" + expression + '\'' +
                ",childNodes=" + childNodes +
                ", result=" + result +
                ", type=" + type +
                '}';
    }

    //获取子节点
    public List<Node> getChildNodes(){
        return childNodes;
    }

    //设置子节点值
    public void setChildNodes(List<Node> childNodes){
        this.childNodes = childNodes;
    }

    public Object getResult(){
        return result;
    }

    public void setResult(Object result){
        this.result = result;
    }

    public Boolean getType(){
        return type;
    }

    public void setType(Boolean type){
        this.type = type;
    }

}
