package com.clavdivs;

import java.io.PrintStream;

public class Match
{
  int    start;
  int    end;
  String text;
  
  public Match(int start, int end, String text)
  {
    this.start = start;
    this.end   = end;
    this.text  = text;
  }

  public void print(PrintStream ps)
  {
    ps.format("%d %d '%s'\n", start, end, text);
  }
  
  public void print()
  {
    print(System.out);
  }
  
}
