package com.end.compiler;

import io.bretty.console.tree.PrintableTreeNode;
import org.antlr.v4.runtime.ParserRuleContext;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Utils {
    public static int index = 0;

    public static void setChildrensParent(Node node) {
        List<? extends PrintableTreeNode> b=node.children();
        if(node!=null && node.children()!=null && node.children().size()>0) {
            //((Node) node.children().get(0)).setParent(node);
            node.children().forEach(x ->{if (x!=null)
                    ((Node) x).setParent(node);});
        }
    }

    public static Node getClosestParent(Node node) {
        return node.getParent();
    }

    public static void setPosition(Node node, ParserRuleContext ruleContext) {
        node.setPosition(definePosition(ruleContext));
    }

    private static Position definePosition(ParserRuleContext parserRuleContext) {
        return new Position(
                parserRuleContext.start.getLine(),
                parserRuleContext.stop.getLine(),
                parserRuleContext.start.getCharPositionInLine(),
                parserRuleContext.stop.getCharPositionInLine());
    }

    /**
     * этот метод пройдется по всему дереву вниз и вернет узлы заданного типа
     *
     * @param node
     * @param clazz
     * @param <T>
     * @return
     */
    //обход дерева
    public static <T extends Node> List<T> getAllTargetClassChildren(Node node, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        Stack<Node> childrenStack = new Stack<>();
        node.children().forEach(it -> childrenStack.push((Node) it));
        while (!childrenStack.empty()) {
            Node child = childrenStack.pop();
            if (clazz.isInstance(child)) result.add(clazz.cast(child));
            child.children().forEach(it -> {if (it!=null) childrenStack.push((Node) it);});
        }
        Collections.reverse(result);
        return result;
    }

    // это все, что выше или на том же уровне
    //все, что на этом уровне или выше можно по другому назвать видимые узлы. ты же в проге видишь переменные, если они в этой же функции или выше
    //map умеет преобразовывать поток одних типов в поток других типов. здесь мы кастим все узлы в нужный класс
    // лист из T, а мы ему Node суем...почему-то
    public static <T extends Node> List<T> getAllVisibleTagertClassNodes(Node node, Class<T> targetNodesClass) {
        List<T> result = new ArrayList<>();
        Node parent = node.getParent();
        while (parent != null) {
            result.addAll(parent.children().stream()
                    .filter(targetNodesClass::isInstance).map(targetNodesClass::cast).collect(Collectors.toList()));
            parent = parent.getParent();
        }
        return result;
    }
    public static  List<Node> getAllChildren(Node node) {
        List<Node> result = new ArrayList<>();
        Stack<Node> childrenStack = new Stack<>();
        if(node!=null && node.children()!=null) {
            node.children().forEach(it ->{ if (it!=null)childrenStack.push((Node) it);});
            while (!childrenStack.empty()) {
                Node child = childrenStack.pop();
                result.add(child);
                child.children().forEach(it -> {if (it!=null) childrenStack.push((Node) it);});
            }
            Collections.reverse(result);
        }
        return result;
    }


    public static String stackToString(Stack<Integer> stack1) {
        String targetStr = "";
        if (stack1!= null) {
        Stack<Integer> stack=new Stack<>();
        stack.addAll(stack1);
            while (!stack.empty()) {
                targetStr += stack.pop().toString()+".";
            }
        }

        if (targetStr.length()>1) targetStr=targetStr.substring(0, targetStr.length()-1);
        return targetStr;
    }
}
