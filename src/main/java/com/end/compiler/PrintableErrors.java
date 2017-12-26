package com.end.compiler;

import java.util.stream.Collectors;

public class PrintableErrors {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static boolean isErrorOccurred = false;



    public static boolean isErrorOccurred() {
        return isErrorOccurred;
    }

    public static void setIsErrorOccurred(boolean errorOccurred) {
        PrintableErrors.isErrorOccurred = errorOccurred;
    }

    private static void printError(String message) {
        isErrorOccurred = true;
        System.out.print(ANSI_RED + message + ANSI_RESET);
    }

    private static void printlnError(String message) {
        isErrorOccurred = true;
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    public static void printDublicatesError(String name, Position position) {
        printlnError("Dublicate '" + name + "' at: "
                + position.startLine + ": " + position.startIndexInLine + ", ");
    }

    public static void printTypeMismatchError(Type expectedType, Type foundType, Position position) {
        String str = "Type mismatch at: " + position.startLine + ":" + position.startIndexInLine
                + ". Expected: " + expectedType.name() + " but found: ";
        if (foundType == null) str += "null";
        else str += foundType.name();
        printlnError(str);
    }

    public static void printNoSuchFunctionError(FunCall funCall, Position position) {
        printError("Cannot find function " + funCall.getName()+"(");
        if (funCall.getParameters().size()>0)
            printError(funCall.getParameters().stream()
                    .map(x->{if(Analysis.getType(x)!=null) return Analysis.getType(x).name(); else return "";})
                    .collect(Collectors.joining(",")));
        printlnError(") at: " + position.startLine + ": " + position.startIndexInLine);
    }

    public static void printUnresolvedReferenceError(String referenceName, Position position) {
        printlnError("Can't resolve reference " + referenceName + " at: "
                + position.startLine + ": " + position.startIndexInLine);
    }

    public static void printIncompatibleTypesError(Type type1, Type type2, Position position) {
        printlnError("Incompatible types at: " + position.startLine + ": " + position.startIndexInLine
                + ": " + type1.name() + " and " + type2.name());
    }

    public static void printOperationDoesNotSupportError(String operation, Type unsupportedType, Position position) {
        printlnError("Operation '" + operation + "' does not support " + unsupportedType.name() +
                "at: " + position.startLine + ": " + position.startIndexInLine);
    }

    public static void printIsNotIterableError(Position position) {
        printlnError("Expected iterable at: " + position.startLine + ": " + position.startIndexInLine);
    }

    public static void printNoReturnStatement(Position funDeclPosition) {
        printlnError("No return statement at function at:" + funDeclPosition.startLine +
                ": " + funDeclPosition.startIndexInLine);

    }

    public static void printConflict(Position position, Node node1, Node node2) {
        printlnError("Conflict: " + node1.toString() + ", " + node2.toString() +
                " at: " + position.startLine + ": " + position.startIndexInLine);
    }

    public static void printExternalFunctionBodyError(Position position){
        printlnError("External function must not have a body" +
                " at: " + position.startLine + ": " + position.startIndexInLine);
    }

    public static void printNegativeNumberError(Position position){
        printlnError("Negative number is not allowed" +
                " at: " + position.startLine + ": " + position.startIndexInLine);
    }

    public static void printNegativeOrZeroNumberError(Position position){
        printlnError("Negative number or 0 is not allowed" +
                " at: " + position.startLine + ": " + position.startIndexInLine);
    }

}
