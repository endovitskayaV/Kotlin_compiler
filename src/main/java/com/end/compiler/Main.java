package com.end.compiler;

import com.end.compiler.KLexer;
import com.end.compiler.KParser;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.Tree;

import javax.swing.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String str="class Main {fun main() {\n   var e:Array<Int>;\n" +
                "    e=Array<Int>(3,{1});\n" +
                "   var c: Int =3;\n\n" +
                "    var q: Array<Int> = Array<Int>(3, {c.inc()});" +
                "val a: Int=w[0];" +
                "val t:Double=3.684;" +
                "\n}}";
        CharStream stream = CharStreams.fromString(str);
        KLexer kLexer = new KLexer(stream);
        TokenStream tokenStream = new CommonTokenStream(kLexer);
        KParser kParser = new KParser(tokenStream);
        //root
        Tree tree = kParser.program();
        //((KParser.MultiplyContext)tree).left

        JFrame frame = new JFrame("Syntax tree");
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(kParser.getRuleNames()), tree);
        treeViewer.setScale(1.5);
        frame.add(treeViewer);
        frame.setSize(640, 480);
        frame.setVisible(true);

    }

}
