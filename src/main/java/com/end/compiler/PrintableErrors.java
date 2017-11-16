package com.end.compiler;

import java.util.List;

public class PrintableErrors {
    public static void printDublicatesError(String name, Position position){
        System.out.println("Dublicate '"+name+"' at line:"
                +position.startLine+": "+position.startIndexInLine+", ");

    }
}
