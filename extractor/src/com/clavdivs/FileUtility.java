package com.clavdivs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtility
{
  static public String loadFile(String filename)
  {
    StringBuilder sb = new StringBuilder();
    try
    {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
      for (;;)
      {
        String line = br.readLine();
        if (line == null) break;
        sb.append(line);
        sb.append("\n");
      }
      br.close();
    }
    catch (Exception ex)
    {
      
    }
    finally
    {
      
    }
    
    return sb.toString();
  }
  
}
