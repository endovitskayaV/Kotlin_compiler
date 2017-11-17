package com.end.compiler;

public class PrintableErrors {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static boolean isErrorOccurred =false;

    public static boolean isErrorOccurred() {
        return isErrorOccurred;
    }

    private static void setIsErrorOccurred(boolean errorOccurred) {
        PrintableErrors.isErrorOccurred = errorOccurred;
    }

    private static  void printError(String message){
        isErrorOccurred =true;
        System.out.println(message);
    }
    public static void printDublicatesError(String name, Position position){
        printError("Dublicate '"+name+"' at: "
                +position.startLine+": "+position.startIndexInLine+", ");
    }

    public static void printTypeMismatchError(Type expectedType, Type foundType,Position position){
        printError("type mismatch at: "+ position.startLine+":"+ position.startIndexInLine
                 +". Expected: "+expectedType.name()+" but found: "+foundType);
    }

    public static void printNoSuchFunctionError(FunCall funCall,Position position){
        printError("Cannot find function "+ funCall.getName()+"(");
        funCall.getParameters().forEach(x-> printError(x.getType().getClass().getSimpleName()+", "));
        printError("at: " +position.startLine+": "+position.startIndexInLine);
    }


    public static void printUnresolvedReferenceError(String referenceName, Position position){
        printError("Can't resolve reference "+referenceName+" at: "
                +position.startLine+": "+position.startIndexInLine);
    }

    public  static  void printIncompatibleTypesError(Type type1, Type type2, Position position){
        printError("Incompatible types at: "+position.startLine+": "+position.startIndexInLine
                +": "+ type1.name()+" and "+type2.name());
    }

    public static void printOperationDoesNotSupportError(String operation, Type unsupportedType,Position position){
        printError("Operation '"+operation+"' does not support "+unsupportedType.name()+
                "at: "+position.startLine+": "+position.startIndexInLine);
    }
}
