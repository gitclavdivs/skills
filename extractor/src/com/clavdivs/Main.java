package com.clavdivs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
  
  static HashMap  <String,String> macros     = new HashMap  <String,String>();
  static ArrayList<String>        taxonomies = new ArrayList<String>();
  
  public static void main(String[] args)
  {
    
    try
    {
      /*
       * 
       */
      
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("war/macros.txt")));
      for (;;)
      {
        String line = br.readLine();
        if (line == null) break;
        appendMacro(line);
      }
      br.close();
      
      for (String k : macros.keySet())
      {
        System.out.format("{%s}: '%s'\n", k, macros.get(k));
      }
      
      /*
       * 
       */
      
      br = new BufferedReader(new InputStreamReader(new FileInputStream("war/taxonomy.txt")));
      for (;;)
      {
        String line = br.readLine();
        if (line == null) break;
        appendTaxonomy(line);
      }
      br.close();

      for (String t : taxonomies)
      {
        System.out.format("t: '%s'\n", t);
      }
      
      /*
       * 
       * 
       */
      Pattern p = Pattern.compile("aero(space|nautical)\\s*(\\w+)?\\s*engineer");
      Matcher m;
      
      m = p.matcher("aerospace engineer");
      if (m.matches()) { System.out.println("Matches! " + m.group(0)); }
      m = p.matcher("aeronautical engineer");
      if (m.matches()) { System.out.println("Matches! " + m.group(0)); }
      m = p.matcher("aeropostale engineer");
      if (m.matches()) { System.out.println("Matches! " + m.group(0)); }
        
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
  static public void appendMacro(String s)
  {
    String[] a = s.split(":");
    if (a.length != 2) return;
    macros.put(a[0].trim(), a[1].trim());
  }
  
  static Pattern p = Pattern.compile("\\{\\w+\\}");
  static ArrayList<Match> matches = new ArrayList<Match>();
  
  static public void appendTaxonomy(String s)
  {
    Matcher m = p.matcher(s);
    int p1, p2;

    if (s.trim().length() == 0) return;
    
    matches.clear();
    
    for (;;)
    {
      if (!m.find()) break;
      p1 = m.start();
      p2 = m.end();
      System.out.format("pos => (%d,%d) string => '%s'\n", p1, p2, s.substring(p1, p2));
      matches.add(new Match(p1, p2, s.substring(p1, p2)));
    }
    
    String t = s;
    for (int i = matches.size() - 1; i >= 0; i--)
    {
      String x = matches.get(i).text;
      t = t.replace(x, macros.get(x.substring(1, x.length() - 1)));
    }
    
    taxonomies.add(t);
  }
  
  static public class Match
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
    
  }
  
}




