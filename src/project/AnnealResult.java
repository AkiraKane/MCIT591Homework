package project;

import java.util.*;

public class AnnealResult{
    public List<Integer> path;
    public List<Long> trace;
    
    public AnnealResult(List<Integer> path, List<Long> trace){
        this.path = path;
        this.trace = trace;
    }
}