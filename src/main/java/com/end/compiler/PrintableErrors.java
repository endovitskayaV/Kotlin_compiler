package com.end.compiler;

import java.util.List;

public class PrintableErrors {
    public static void printDublicatesError(String name, Position position){
        System.out.println("Dublicate '"+name+"' at: "
                +position.startLine+": "+position.startIndexInLine+", ");
    }
    public static void printTypeMismatchError(Position position, Type expectedType, Type foundType){
        System.out.println("type mismatch at: "+ position.startLine+":"+ position.startIndexInLine
                 +". Expected: "+expectedType.name()+" but found: "+foundType);
    }

    public static  void noSuchFunctionError(Position position, String funName){
        System.out.println();
    }

}
