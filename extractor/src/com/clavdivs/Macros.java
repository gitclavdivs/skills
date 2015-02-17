package com.clavdivs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Macros
{
  protected HashMap <String,String> map = new HashMap<String,String>();

  public Map<String,String> get()
  {
    return map;  
  }
  
  public void load()
  {
    try
    {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("war/macros.txt")));
      for (;;)
      {
        String line = br.readLine();
        if (line == null) break;
        appendMacro(line);
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
    System.out.println("MACROS:");
    for (String k : map.keySet())
    {
      System.out.format("  {%s}: '%s'\n", k, map.get(k));
    }
    System.out.println();
  }
  
  protected void appendMacro(String s)
  {
    String[] a = s.split(":");
    if (a.length != 2) return;
    map.put(a[0].trim(), a[1].trim());
  }

}
