package com.end.compiler;

import io.bretty.console.tree.TreePrinter;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.Tree;
import org.apache.tools.ant.taskdefs.optional.*;

import java.io.*;
import javax.swing.JFrame;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {

    static List<FunDeclaration> cSharpFunDeclarationList;
    static File userFunLib = null;
    private static File codeFile;
    private static CharStream funDeclLib;
    private static String resPath=".\\res\\";

    public static void main(String[] args) {

        if (readCommand()) {

            Program cSharpFunDeclProgram = null;
            System.out.println("processing c# funs library...");
            cSharpFunDeclProgram = parse(funDeclLib);


            if (cSharpFunDeclProgram != null) {
                cSharpFunDeclarationList = cSharpFunDeclProgram.getFunDeclarationList();
                printAstTree(cSharpFunDeclProgram, "astCsharpFunDecl.txt");

                if (userFunLib != null) {
                    Program userCSharpFunDeclProgram = null;
                    System.out.println("processing user c# funs library...");
                    userCSharpFunDeclProgram = parse(userFunLib);


                    if (userCSharpFunDeclProgram != null) {
                        cSharpFunDeclarationList = userCSharpFunDeclProgram.getFunDeclarationList();
                        printAstTree(userCSharpFunDeclProgram, "astUserCsharpFunDecl.txt");
                    }
                }

                        PrintableErrors.setIsErrorOccurred(false);


                        System.out.println("processing " + codeFile.getName() + "...");
                        Program program = parse(codeFile);

                        if (program != null) {
                            printAstTree(program, "astTree.txt");

                            //if no errors -> generate code
                            if (!PrintableErrors.isErrorOccurred()) {
                                String byteCode = CodeGenerator.generateCode(program);
                                String byteCodeFileName = codeFile.getName()
                                        .substring(0, codeFile.getName().length() - 3) + ".il";
                                System.out.println("creating " + byteCodeFileName + "...");
                                printInFile(byteCodeFileName, byteCode);
                                System.out.println("creating "
                                        + byteCodeFileName.substring(0, codeFile.getName().length() - 3) + ".exe ...");
                                createExe(codeFile.getName().substring(0, codeFile.getName().length() - 3));
                                System.out.println("\n***COMPILATION SUCCEEDED***\n");
                            } else System.out.println("\n***COMPILATION FAILED***\n");

                        }


            }
        } else System.out.println("wrong command");
        //new Scanner(System.in).nextLine();
    }

    private static boolean readCommand() {
        System.out.print(">");
        String commandStr = new Scanner(System.in).useDelimiter("\n").nextLine();
        List<String> splitList = Arrays.asList(commandStr.split("\\s+"));


        if ((splitList.size() >= 3) && (splitList.get(0).equals("kotlin-compiler"))
                && (splitList.get(1).equals("compile")) &&
                (Pattern.compile(".*\\.vl$").matcher(splitList.get(2)).matches())) {

            codeFile = new File(splitList.get(2));

            try {
                InputStream i = Main.class.getResourceAsStream("/CSharpStandartFuns.vl");
                funDeclLib = CharStreams.fromStream(i);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (splitList.stream().anyMatch(x -> x.equals("-lib"))
                    && splitList.size() > splitList.lastIndexOf("-lib") + 1
                    && (Pattern.compile(".*\\.vl").matcher
                    (splitList.get(splitList.lastIndexOf("-lib") + 1)).matches()))

                userFunLib = new File(splitList.get(splitList.lastIndexOf("-lib") + 1));


        } else return false;

        return true;
    }

    private static void createExe(String name) {
        String cSharpToolsEnvVarPath = System.getenv("CSHARP_TOOLS");

        String com = "cd " +
                cSharpToolsEnvVarPath + " && VsDevCmd.bat &&" +
                "ilasm /exe " +
                new File("").getAbsolutePath() + "\\res\\" + name + ".il" +
                " /output=" + new File("").getAbsolutePath() + "\\" + name + ".exe";

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", com);


        try {
            processBuilder.redirectOutput(new File(resPath+"ilasmResult.txt"));
            Process process = processBuilder.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printInFile(String fileNameExten, String outputStr) {
        File file=new File(resPath+fileNameExten);
        file.getParentFile().mkdir();
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.write(outputStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printAstTree(Program program, String fileName) {
        printInFile(fileName+".txt",
                TreePrinter.toString(program));
        //System.out.println("Printed in " + astTreeFileName + ":\n" + astTreeStr);
    }

    private static Program parse(CharStream fileContent) {
        com.end.compiler.KLexer kLexer = new com.end.compiler.KLexer(fileContent);
        TokenStream tokenStream = new CommonTokenStream(kLexer);
        com.end.compiler.KParser kParser = new com.end.compiler.KParser(tokenStream);
        Tree tree = kParser.program();

        //build astTree (root=program)
        Program program = ToAst.toAst((com.end.compiler.KParser.ProgramContext) tree);

        //analyze tree(recursively, start from root)
        Analysis.analyze(program);
        return program;
    }

    private static Program parse(File file) {

        //read code from file
        CharStream charStream = null;
        try {
            String s=file.getCanonicalPath();
            System.out.println(s);
            charStream = CharStreams.fromFileName(file.getCanonicalPath());
        } catch (IOException e) {
            //  e.printStackTrace();
            System.out.println("no such file\n***COMPILATION FAILED***\n");
            return null;
        }

        return parse(charStream);

    }

    private static void showSyntaxTree(Tree tree, com.end.compiler.KParser kParser) {
        JFrame frame = new JFrame("Syntax tree");
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(kParser.getRuleNames()), tree);
        treeViewer.setScale(1.5);
        frame.add(treeViewer);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}