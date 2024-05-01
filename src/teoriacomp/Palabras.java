/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriacomp;

import java.io.*;
import java.util.*;

/**
 *
 * @author alberto
 */
public class Palabras {
    static char[] alphabet = "01".toCharArray();
    static int linea=0;
    
    
    static void generar(StringBuilder sb, int n) throws IOException {
        if (n == sb.length()) {
            System.out.print(", "+sb.toString());
            fichero(", "+sb.toString(),"Palabras.txt",false);
            coordenadas(sb.toString());

            return;
        }
        for (char letter : alphabet) {
            sb.setCharAt(n, letter);
            generar(sb, n + 1);
        }
        
    }
    
    static void fichero(String cur, String name,boolean a) throws IOException{
        
        FileWriter fw = new FileWriter(name, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(cur);
        if(a)
            bw.newLine();
        
        bw.close();
        
    }
    
    static void coordenadas(String data) throws FileNotFoundException, IOException{
    //    File myObj = new File("Palabras.txt");
    //  Scanner myReader = new Scanner(myObj);
    //  int linea=1;
    //  while (myReader.hasNextLine()) {
    //    String data = myReader.nextLine();
    //    fichero(datosCor(linea,data),"Coordenadas.txt");
    //    linea++;
    //  }
    //  myReader.close();
    
    fichero(datosCor(linea,data),"Coordenadas.txt",true);
        linea++;
    
    }
    
    static String datosCor(int lin, String dat){
        String current;
        int unos=0;
        
        for(int i=0; i < dat.length(); i++){
            if(dat.charAt(i) == '1')
            unos++;
    }
        
        current=String.valueOf(lin)+"\t"+unos;
        
        return current;
    }
    
    static void limpiarFile(){
        File arc=new File("Palabras.txt");
        arc.delete();
        File cor=new File("Coordenadas.txt");
        cor.delete();
    }
    
    public static void main(String args[]) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        Random rand = new Random();


    
    int m=0, n=0,i=0;
    System.out.println("Do you want to intruduce the lenght of the words or generate them automatically? \n");
    System.out.println(" 1-Manual \n 2-Automatically \n");
    m=sc.nextInt();

     limpiarFile();   
    
    switch(m){
        case 1:
            System.out.println("Manual then... \n");
            System.out.println("Insert the lenght of the words: ");
            n=sc.nextInt();
            if(n>1000 || n<0){
                System.out.println("El número se debe encontrar en un rango de 0 a 1000");
                System.exit(0);
            }
            
        break;
        
        case 2:
            System.out.println("Auto then... \n");
            n=rand.nextInt(100);
            
        break;
        
    }
    
     System.out.println("The lenght of the words is:"+ String.valueOf(n)+"\n");
     
        System.out.print("{ ε");
        fichero("{ ε","Palabras.txt",false);
        for (int length = 1; length <= n; length++) {
            sb.setLength(length);
            generar(sb, 0);
        }
     
     fichero("}","Palabras.txt",false);   
     System.out.print("}"+"\n");
      
    
    }
    
    
    
}
