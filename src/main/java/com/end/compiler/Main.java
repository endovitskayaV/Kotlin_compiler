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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {

    //TODO: enable multi lib function (so that compiler can process any number of user`s libs)
    static List<FunDeclaration> cSharpFunDeclarationList;
    static List<FunDeclaration> userFunDeclList;
    static File userFunLib = null;
    private static List<File> codeFile = new ArrayList<>();
    private static CharStream funDeclLib;
    private static String resPath = ".\\res\\";

    public static void main(String[] args) {

        if (readCommand(args)) {
            Program cSharpFunDeclProgram = null;
            System.out.println("processing c sharp functions library...");
            cSharpFunDeclProgram = parse(funDeclLib);

            if (cSharpFunDeclProgram != null) {
                cSharpFunDeclarationList = cSharpFunDeclProgram.getFunDeclarationList();
                printAstTree(cSharpFunDeclProgram, "astCsharpFunDecl");


                if (userFunLib != null) {
                    Program userCSharpFunDeclProgram = null;
                    System.out.println("processing user c sharp functions library...");
                    userCSharpFunDeclProgram = parse(userFunLib);

                    if (userCSharpFunDeclProgram != null) {
                        userFunDeclList = userCSharpFunDeclProgram.getFunDeclarationList();
                        printAstTree(userCSharpFunDeclProgram, "astUserCsharpFunDecl");
                    }
                }

                for (File file:codeFile) {
                    Program program = parse(file);
                    if (program != null) {
                        printAstTree(program, "astTree");

                        //if no errors -> generate code
                        if (!PrintableErrors.isErrorOccurred()) {
                            String byteCode = CodeGenerator.generateCode(program);
                            String byteCodeFileName = file.getName()
                                    .substring(0, file.getName().length() - 3) + ".il";
                            System.out.println("creating " + byteCodeFileName + "...");
                            printInFile(byteCodeFileName, byteCode);
                            System.out.println("creating "
                                    + byteCodeFileName.substring(0, file.getName().length() - 3) + ".exe ...");
                            if (createExe(file.getName().substring(0, file.getName().length() - 3)))
                                System.out.println("\n***COMPILATION SUCCEEDED***\n");
                            else System.out.println("creating exe failed!\n" +
                                    " more information in \\res\\ilasmResult.txt " +
                                    "\n***COMPILATION FAILED***\n");
                        } else System.out.println("\n***COMPILATION FAILED***\n");

                    }
                }

            }
        } else System.out.println("wrong command");
        //new Scanner(System.in).nextLine();
    }

    private static boolean readCommand(String[] args) {
        List<String> splitList = Arrays.asList(args);
//        System.out.print(">");
//        String commandStr = new Scanner(System.in).useDelimiter("\n").nextLine();
//        List<String> splitList = Arrays.asList(commandStr.split("\\s+"));


        if ((splitList.size() >= 3) && (splitList.get(0).equals("kotlin-compiler"))
                && (splitList.get(1).equals("compile"))) {
            for (int i = 2; i < args.length; i++) {
                if (Pattern.compile(".*\\.vl$").matcher(splitList.get(i)).matches()) {

                    codeFile.add(new File(splitList.get(i)));
                } else break;
            }

                try {
                    InputStream i1 = Main.class.getResourceAsStream("/CSharpStandartFunctions.vl");
                    funDeclLib = CharStreams.fromStream(i1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (splitList.stream().anyMatch(x -> x.equals("-lib"))
                        && splitList.size() > splitList.lastIndexOf("-lib") + 1)
                    userFunLib = new File(splitList.get(splitList.lastIndexOf("-lib") + 1) + ".vl");



        } else return false;

        return true;
    }

    private static boolean createExe(String name) {
        String cSharpToolsEnvVarPath = System.getenv("CSHARP_TOOLS");

        String com = "cd " +
                cSharpToolsEnvVarPath + " && VsDevCmd.bat &&" +
                "ilasm /exe " +
                new File("").getAbsolutePath() + "\\res\\" + name + ".il" +
                " /output=" + new File("").getAbsolutePath() + "\\" + name + ".exe";

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", com);


        try {
            processBuilder.redirectOutput(new File(resPath + "ilasmResult.txt"));
            Process process = processBuilder.start();
            process.waitFor();
            return process.exitValue() == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void printInFile(String fileNameExten, String outputStr) {
        File file = new File(resPath + fileNameExten);
        file.getParentFile().mkdir();
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.write(outputStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printAstTree(Program program, String fileName) {
        printInFile(fileName + ".txt",
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
            String s = file.getCanonicalPath();
            System.out.println("processing " + s + "...");
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