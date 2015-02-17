package com.clavdivs;


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
    
    String s = FileUtility.loadFile("war/example.txt");
    System.out.println(s);
    
    /*
    Pattern p = Pattern.compile("aero(space|nautical)\\s*(\\w+)?\\s*engineer");
    Matcher m;

    m = p.matcher("aerospace engineer");
    if (m.matches()) { System.out.println("Matches! " + m.group(0)); }
    m = p.matcher("aeronautical engineer");
    if (m.matches()) { System.out.println("Matches! " + m.group(0)); }
    m = p.matcher("aeropostale engineer");
    if (m.matches()) { System.out.println("Matches! " + m.group(0)); }
    */
  }
  
}




