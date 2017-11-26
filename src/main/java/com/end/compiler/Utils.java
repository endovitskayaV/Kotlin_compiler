package com.end.compiler;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Utils {

    public  static void setChildrensParent(Node node){
        node.children().forEach(x -> ((Node)x).setParent(node));
    }
    public static Node getClosestParent(Node node) {
        return node.getParent();
    }

    public  static void  setPosition(Node node, ParserRuleContext ruleContext){
        node.setPosition(definePosition(ruleContext));
    }
    private static Position definePosition(ParserRuleContext parserRuleContext){
        return new Position(
                parserRuleContext.start.getLine(),
                parserRuleContext.stop.getLine(),
                parserRuleContext.start.getCharPositionInLine(),
                parserRuleContext.stop.getCharPositionInLine());
    }

    /**
     * этот метод пройдется по всему дереву вниз и вернет узлы заданного типа
     * @param node
     * @param clazz
     * @param <T>
     * @return
     */
    //обход дерева
    public static <T extends Node> List<T> getAllChildren(Node node, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        Stack<Node> childrenStack = new Stack<>();
        node.children().forEach(it ->  childrenStack.push((Node)it));
        while (!childrenStack.empty()) {
            Node child = childrenStack.pop();
            if (clazz.isInstance(child)) result.add(clazz.cast(child));
            child.children().forEach(it -> childrenStack.push((Node)it));
        }
         Collections.reverse(result);
        return result;
    }

    //это не дети()/ это не все. это все, что выше или на том же уровне
    //все, что на этом уровне или выше можно по другому назвать видимые узлы. ты же в проге видишь переменные, если они в этой же функции или выше
    //map умеет преобразовывать поток одних типов в поток других типов. здесь мы кастим все узлы в нужный класс
    //зачем. ну он опять ругается, что лист из T, а мы ему Node суем...почему-то
    public  static <T extends Node> List<T> getAllVisibleNodes(Node node, Class<T> targetNodesClass){
        List<T> result = new ArrayList<>();
        Node parent=node.getParent();
        while (parent!=null){
            result.addAll(parent.children().stream()
                    .filter(targetNodesClass::isInstance).map(targetNodesClass::cast).collect(Collectors.toList()));
            parent=parent.getParent();
        }
        return  result;
    }

}
