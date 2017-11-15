package com.end.compiler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    public int startLine;
    public int finishLine;
    public int startIndexInLine;
    public int finishPositionInLine;

}
