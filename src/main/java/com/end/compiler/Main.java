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
import java.util.Arrays;
import java.util.List;


public class Main {

    static List<FunDeclaration> cSharpFunDeclarationList;

    public static void main(String[] args) {

        Program cSharpFunDeclProgram=parse("CSharpFunDeclarations.vl");
        cSharpFunDeclarationList= cSharpFunDeclProgram.getFunDeclarationList();
        printAstTree(cSharpFunDeclProgram, "astCsharpFunDecl.txt");

        Program program=parse("try.vl");

        printAstTree(program,"astTree.txt");

        //if no errors -> generate code
        if (!PrintableErrors.isErrorOccurred()) {
            String byteCode = CodeGenerator.generateCode(program);
            String byteCodeFileName = "bytecode.il";
            printInFile(byteCodeFileName, byteCode);
            System.out.println("Printed in " + byteCodeFileName + ":\n" + byteCode);

        } else System.out.println("\nCannot generate code. Error(s) occurred\n");


    }

    private static void printInFile(String fileName, String outputStr) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            printWriter.write(outputStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printAstTree(Program program, String fileName){
        String astTreeStr = TreePrinter.toString(program);
        String astTreeFileName = fileName;
        printInFile(astTreeFileName, astTreeStr);
        System.out.println("Printed in " + astTreeFileName + ":\n" + astTreeStr);
    }

    private static Program parse(String fileName){

        //read code from file
        CharStream charStream = null;
        try {
            charStream = CharStreams.fromFileName(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        KLexer kLexer = new KLexer(charStream);
        TokenStream tokenStream = new CommonTokenStream(kLexer);
        KParser kParser = new KParser(tokenStream);
        Tree tree = kParser.program();

        //build astTree (root=program)
        Program program = ToAst.toAst((KParser.ProgramContext) tree);


        //analyze tree(recursively, start from root)
        Analysis.analyze(program);
        return program;

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