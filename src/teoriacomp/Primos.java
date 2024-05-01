/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoriacomp;

import java.io.*;
import java.util.*;

/**
 *
 * @author alberto
 */
public class Primos {    
    
    static int linea=0;
    
    static void generar(int n) throws IOException{
    int i = 2;
    boolean flag;
    while(i < n){
        flag = true;
        for(int j = 2; j <= (double)Math.sqrt(i); j++){
            if (i%j == 0){
                flag = false;
                break;
            }
        }
        if(flag){
            fichero(", "+Integer.toBinaryString(i),"Primos.txt",false);
            System.out.print(", " + Integer.toBinaryString(i));
            coordenadas(Integer.toBinaryString(i));
            
            fichero(", "+String.valueOf(i),"PrimosDec.txt",false);
        }
        i++;
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
    
    static void coordenadas(String cur) throws FileNotFoundException, IOException{
    //    File myObj = new File("Primos.txt");
    //  Scanner myReader = new Scanner(myObj);
    //  int linea=1;
    //  while (myReader.hasNextLine()) {
    //    String data = myReader.nextLine();
    //    fichero(datosCor(linea,data),"CoordenadasPrim.txt");
    //    linea++;
    //  }
    //  myReader.close();
    
    fichero(datosCor(linea,cur),"CoordenadasPrim.txt",true);
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
    
//    static long binar(long decimal_number){
//    if (decimal_number == 0)
//        return 0;
//    else
//        return (decimal_number % 2 + 10 *
//                binar(decimal_number / 2));
//}
    
    static void limpiarFile(){
        File arc=new File("Primos.txt");
        arc.delete();
        File cor=new File("CoordenadasPrim.txt");
        cor.delete();
        File corD=new File("PrimosDec.txt");
        corD.delete();
    }
    
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        Random rand = new Random();

    
    
    int m=0,i=0;
    int n=0;
    
    while (m!=3){
    
    
    System.out.println("Do you want to intruduce the limit for numbers to check if prime? \n");
    System.out.println(" 1-Manual \n 2-Automatically \n 3-Exit \n");
    m=sc.nextInt();

    
    
    switch(m){
        case 1:
            System.out.println("Manual then... \n");
            System.out.println("Insert the greatest number to check: ");
            n=sc.nextInt();
            
        break;
        
        case 2:
            System.out.println("Auto then... \n");
            n=rand.nextInt(10000000-2) + 2;
            
        break;
        
        case 3:
            System.out.println("Fin del programa");
        break;
        
        default: 
            System.out.println("Introduzca una opción válida");
        break;
        
        
    }
    
    
     if(m==1 || m==2){
        if(n>10000000){
            System.out.println("Fuera de rango");
            System.exit(0);
        }
    
        limpiarFile();
    
        System.out.println("The greatest number to be checked is: "+ String.valueOf(n));
     
    
     
       System.out.print("{ ε");
        fichero("{ ε","Primos.txt",false);
        fichero("{ ε","PrimosDec.txt",false);
        generar(n+1);
     
        fichero("}","Primos.txt",false);   
        fichero("}","PrimosDec.txt",false);   
        System.out.print("}"+"\n");


     } 
    
    
    
    }
    
    }
    
}
