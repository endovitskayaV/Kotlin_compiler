package com.end.compiler;

import com.end.compiler.KLexer;
import com.end.compiler.KParser;
import io.bretty.console.tree.TreePrinter;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.Tree;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.*;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.Tree;
import java.io.File;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        CharStream stream = null;
        try {
            stream = CharStreams.fromFileName("code.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        KLexer kLexer = new KLexer(stream);
        TokenStream tokenStream = new CommonTokenStream(kLexer);
        KParser kParser = new KParser(tokenStream);
        Tree tree = kParser.expr();



        showSyntaxTree(tree,kParser);

       System.out.println(TreePrinter.toString(ToAst.toAst((KParser.ExprContext) tree)));

        //root
        //((KParser.MultiplyContext)tree).left


    }

    private static  void showSyntaxTree(Tree tree, KParser kParser){
        JFrame frame = new JFrame("Syntax tree");
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(kParser.getRuleNames()), tree);
        treeViewer.setScale(1.5);
        frame.add(treeViewer);
        frame.setSize(640, 480);
        frame.setVisible(true);
}
}
