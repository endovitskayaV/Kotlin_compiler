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
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {

    static List<FunDeclaration> cSharpFunDeclarationList;
    private static String codeFileName;
    private static String funDeclLibName = "CSharpFunDeclarations.vl";

    public static void main(String[] args) {

            if(readCommand()) {
            Program cSharpFunDeclProgram = parse(funDeclLibName);
            if (cSharpFunDeclProgram != null) {
                cSharpFunDeclarationList = cSharpFunDeclProgram.getFunDeclarationList();
                printAstTree(cSharpFunDeclProgram, "astCsharpFunDecl.txt");
                PrintableErrors.setIsErrorOccurred(false);

                Program program = parse(codeFileName);

                if (program != null) {
                    printAstTree(program, "astTree.txt");

                    //if no errors -> generate code
                    if (!PrintableErrors.isErrorOccurred()) {
                        String byteCode = CodeGenerator.generateCode(program);
                        String byteCodeFileName = "bytecode.il";
                        printInFile(byteCodeFileName, byteCode);

                    } else System.out.println("\n***COMPILATION FAILED***\n");

                }
            }
        } else System.out.println("wrong command");
        new Scanner(System.in).nextLine();
    }


    private static boolean readCommand() {
        System.out.print(">");
        String commandStr = new Scanner(System.in).useDelimiter("\n").nextLine();
        String[] splitArr = commandStr.split("\\s+");


        if ((splitArr.length >= 3) && (splitArr[0].equals("kotlin-compiler"))
                && (splitArr[1].equals("compile")) &&
                (Pattern.compile(".*\\.vl$").matcher(splitArr[2]).matches())) {

             codeFileName = splitArr[2];

            if (splitArr.length == 5) {
                if ((splitArr[3].equals("-lib"))
                        && (Pattern.compile(".*\\.vl").matcher(splitArr[4]).matches())) {
                    funDeclLibName = splitArr[4];
                } else return false;
            } else if (splitArr.length > 5)  return false;

        } else return false;

        return true;
    }
    private static void printInFile(String fileName, String outputStr) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            printWriter.write(outputStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printAstTree(Program program, String fileName) {
        String astTreeStr = TreePrinter.toString(program);
        String astTreeFileName = fileName;
        printInFile(astTreeFileName, astTreeStr);
        //System.out.println("Printed in " + astTreeFileName + ":\n" + astTreeStr);
    }

    private static Program parse(String fileName) {

        //read code from file
        CharStream charStream = null;
        try {
            charStream = CharStreams.fromFileName(fileName);
        } catch (IOException e) {
            //  e.printStackTrace();
            System.out.println("no such file");
            return null;
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