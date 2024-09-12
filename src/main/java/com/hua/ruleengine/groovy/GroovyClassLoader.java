package com.hua.ruleengine.groovy;

import com.hua.ruleengine.tree.Node;

import java.util.List;

public class GroovyClassLoader implements IGroovyClassLoader {
    @Override
    public Class gengerateClass(List<Node> nodes) {
        String s = "";
        for (Node node : nodes){
            String expression = parseExpressionWithMap(node.getExpression());
            if(node.getType()){
                s += "if(" + expression + "){\n";
                final List<Node> childNodes = node.getChildNodes();
                for(Node childNode : childNodes){
                    if(childNode.getType()){
                        s += gengerateClass(childNode.getChildNodes());
                    } else {
                        expression = parseExpressionWithMap(childNode.getExpression());
                        if(childNode.getResult().getClass().equals(String.class)){
                            childNode.setResult("\"" + childNode.getResult() + "\"");
                        }
                        s += "if(" + expression + "){\n" +
                                "return " + childNode.getResult() + ";" +
                                "\n}\n";
                    }
                }
                s += "}\n";
            } else {
                if(node.getResult().getClass().equals(String.class)){
                    node.setResult("\"" + node.getResult() + ";");
                }
                s += "if(" + expression + "){\n" +
                        "return " + node.getResult() + ";" +
                        "\n}\n";
            }
        }
        final String javaSrc = getJavaSrc(s);
        groovy.lang.GroovyClassLoader groovyClassLoader = new groovy.lang.GroovyClassLoader();
        Class<?> clazz = groovyClassLoader.parseClass(javaSrc);
        return clazz;
    }

    private String getJavaSrc(String expression) {
        String s = "package com.ruleengine.sample;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "import com.hua.ruleengine.services.RuleEngine;" +
                "import com.hua.ruleengine.utils.BeanUtils;" +
                "\n" +
                "\n" +
                "public class Groovy implements RuleEngine{\n" +
                "public Object exec(Object object){\n" +
                "Map map = BeanUtils.convertToMap(Object);" +
                "        \n" + expression +
                "         return null;\n" +
                "   }\n" +
                "}";
        return s;
    }


    private String parseExpressionWithMap(String expression){
        if(expression.indexOf("{") == -1){
            return expression;
        }
        //模板开始的位置
        int start = 0;
        //模板结束的位置
        int end = 0;
        char[] chars = expression.toCharArray();
        boolean flag = false;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '{'){
                start = i;
            }
            if(chars[i] == '}'){
                end = i;
                flag = true;
            }
            //起始位置 和 终止位置 都找到了
            if(flag) {
                String subString = expression.substring(start + 1 , end);
                String a = "{" + subString + '}';
                Object value = String.format("map.get(\"%s\")",subString);
                expression = expression.replace(a,value.toString());
                return parseExpressionWithMap(expression);
            }
        }
        return expression;
    }
}
