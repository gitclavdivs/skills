package com.clavdivs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main
{
  public static void main(String[] args)
  {
    Macros macros = new Macros();
    macros.load();
    macros.print();
    
    Taxonomies taxonomies = new Taxonomies(macros.get());
    taxonomies.load();
    taxonomies.print();
  
    Map<String,ArrayList<Match>> matches = new HashMap<String,ArrayList<Match>>();

    String text;
    
  //text = FileUtility.loadFile("war/examples/Seeker, Thomas B");
  //text = FileUtility.loadFile("war/examples/Holding, Sarah R");
    text = FileUtility.loadFile("war/examples/Shaffer, Christina M");
  //System.out.println(text);

    for (String s : taxonomies.get())
    {
    //System.out.format("Pattern: '%s'\n", s);
      
      Pattern p = Pattern.compile(s + "\\W+", Pattern.CASE_INSENSITIVE);
      Matcher m = p.matcher(text);
      
      ArrayList<Match> array = new ArrayList<Match>();
      
      while (m.find())
      {
        Match match = new Match(m.start(), m.end(), m.group(0));
      //match.print();
        array.add(match);
      }
      if (array.size() > 0)
      {
        matches.put(s, array);
      }
    }
    
    for (String k : matches.keySet())
    {
      System.out.format("'%s' -> %d occurrences\n", k, matches.get(k).size());
    }
  }
  
}




