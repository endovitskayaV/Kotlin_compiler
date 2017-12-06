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

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import java.io.IOException;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Main {

    public static void main(String[] args) {

        //read code from file
        CharStream stream = null;
        try {
            stream = CharStreams.fromFileName("codenew.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        KLexer kLexer = new KLexer(stream);
        TokenStream tokenStream = new CommonTokenStream(kLexer);
        KParser kParser = new KParser(tokenStream);
        Tree tree = kParser.program();

        //showSyntaxTree(tree, kParser);

        //build astTree (root=program)
        Program program = ToAst.toAst((KParser.ProgramContext) tree);

        //analyze tree(recursively, start from root)
        Analysis.analyze(program);

        //if no errors -> generate code
        if (!PrintableErrors.isErrorOccurred()) {
            String byteCode = CodeGenerator.generateCode(program);
            String byteCodeFileName = "bytecode.txt";
            printInFile(byteCodeFileName, byteCode);
            System.out.println("Printed in " + byteCodeFileName + ":\n" + byteCode);
        } else System.out.println("\nCannot generate code. Error(s) occurred\n");

        String astTreeStr = TreePrinter.toString(program);
        String astTreeFileName = "astTree.txt";
        printInFile(astTreeFileName, astTreeStr);
        System.out.println("Printed in " + astTreeFileName + ":\n" + astTreeStr);
    }

    private static void printInFile(String fileName, String outputStr) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            printWriter.write(outputStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void showSyntaxTree(Tree tree, KParser kParser) {
        JFrame frame = new JFrame("Syntax tree");
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(kParser.getRuleNames()), tree);
        treeViewer.setScale(1.5);
        frame.add(treeViewer);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}