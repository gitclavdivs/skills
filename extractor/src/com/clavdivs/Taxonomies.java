package com.clavdivs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Taxonomies
{
  
  protected ArrayList<String> taxonomies = new ArrayList<String>();
  
  static Pattern p = Pattern.compile("\\{\\w+\\}");
  static ArrayList<Match> matches = new ArrayList<Match>();

  protected Map<String,String> rules = new HashMap<String,String>();
  protected Map<String,String> macros;
  
  public Taxonomies(Map<String,String> macros)
  {
    this.macros = macros;
  }
  
  public List<String> get()
  {
    return taxonomies;
  }
  
  public void load()
  {
    try
    {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("war/taxonomy.txt")));
      for (;;)
      {
        String line = br.readLine();
        if (line == null) break;
        appendTaxonomy(line);
      }
      br.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
  public void print()
  {
    System.out.println("TAXONOMY:");
    for (String t : taxonomies)
    {
      System.out.format("t: '%s'\n", t);
    }
    System.out.println();
  }
  
  public void appendTaxonomy(String s)
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
    
    String re = t.substring(0, t.indexOf(":"));
    re = re.trim();
    
    taxonomies.add(re);
  }

}
