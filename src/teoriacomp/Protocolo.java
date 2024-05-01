
package teoriacomp;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.*;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author alberto
 */
public class Protocolo extends JPanel{
    static Random rand=new Random();
    static List<String> list = new ArrayList<String> (); 
    //Boolean flag=false;
    
    
    
    enum Estados{
        q0,
        q1,
        q2,
        q3;
    }
    
    
    
    
    public static void main(String args[]) throws IOException, InterruptedException{
        boolean encendido=true;
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int runs=0;
        
        while(encendido){
                runs++;
                
            list=generar(10); //genera n cadenas de longitud 64, n es el parámetro de la función
            
            Thread.sleep(2000);
            
            verificar_cadenas();
            
            int cond=rand.nextInt(100);
            if( cond<=100 && cond>=40){ // 60% de probabilidad de apagarse
                encendido=false;
            }
            System.out.print("Number of runs: "+String.valueOf(runs));
            System.out.println("\t condition: "+String.valueOf(cond));
        }
        
        System.out.println("Deseas graficar el Automata finito determinista?");
        String reply=sc.nextLine();
        if(reply.equalsIgnoreCase("si") || reply.equalsIgnoreCase("yes") 
                || reply.equals("1") || reply.equalsIgnoreCase("y")){
          graphics();   
       }
        
        
    
    }
    
    static void graphics(){
        Protocolo autom=new Protocolo();
        JFrame fr=new JFrame("Automata de paridad");
        fr.add(autom,BorderLayout.CENTER);
        fr.setSize(900, 800);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
    }
    
    
    static List <String> generar(int s) throws IOException{
      List<String> lista=new ArrayList<String>();    
      
      
      /*
        for(int i=0;i<=s;i++){
        //int num= rand.nextInt(Integer.parseInt("11111111111111111111111111111111", 2));
        //111111111111111111111111111111111111111111111111111111111111111
        //System.out.println(String.valueOf(num));
        

        
        int num1=rand.nextInt(Integer.parseInt("1111111111111111111111111111111",2));
        int num2=rand.nextInt(Integer.parseInt("1111111111111111111111111111111",2));
        int num3=rand.nextInt(Integer.parseInt("11",2));
        
        String pre=Integer.toBinaryString(num1)+Integer.toBinaryString(num2)+Integer.toBinaryString(num3);
        lista.add(String.format("%64s", pre).replace(' ', '0'));
        }
     */
      
        for(int i=1;i<=s;i++){
            String a;
            a=binar64();
            
            lista.add(a);
            
            if(i==s){
            fichero(a+"\n","Cadenas64.txt",false);    
            }else{
            fichero(a+", ","Cadenas64.txt",false);    
            }
            
        }
    
    return lista;
    }
    
    static String binar64(){
        String res="";
        
        for(int j=1;j<=64;j++){
                res=res+String.valueOf(rand.nextInt(2));
            }
        
        
        return res;
    }
    
    static void fichero(String cur, String name,boolean a) throws IOException{
        
        FileWriter fw = new FileWriter(name, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(cur);
        if(a)
            bw.newLine();
        
        bw.close();
        
    }
    
    
    static void verificar_cadenas() throws IOException{
        
        for(int i=0;i<list.size();i++){
            String curStr =list.get(i);
            
            if(paridadInd(curStr)){
                fichero(curStr,"ParidadAceptadas.txt",true);
            }else{
                fichero(curStr,"ParidadRechazadas.txt",true);
            }
               
        }
        
    }
    
    
    static boolean paridadInd(String current){
        Estados est=Estados.q0; //set initial state
        
        //g2.setColor(Color.black);
        //g2.fillOval(210, 230, 120, 120);
        //g2.drawOval(205, 225, 130, 130);
        //g2.setColor(Color.white);
        //g2.drawString("q0",253,298);
        
        for(String u : current.split("")){
            
            switch(est){
                
                case q0:
                    
                    if(u.equals("0")){
                        est=Estados.q2;
                    }
                    if(u.equals("1")){
                        est=Estados.q1;
                    }
                    
                break;
                
                
                case q1:
                       
                    if(u.equals("0")){
                        est=Estados.q3;
                    }
                    if(u.equals("1")){
                        est=Estados.q0;
                    }
                break;
                
                
                case q2:
                    
                    if(u.equals("0")){
                        est=Estados.q0;
                    }
                    if(u.equals("1")){
                        est=Estados.q3;
                    }
                break;
                
                
                case q3:
                    
                    if(u.equals("0")){
                        est=Estados.q1;
                    }
                    if(u.equals("1")){
                        est=Estados.q2;
                    }
                break;
                
                
            }
            
            
            
            
        }
        
        
        return est==Estados.q0;
    }
    
    
    //Graphics
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
        g.setColor(new Color(204, 255, 204));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        
        
        paintProtocolAutomata(g2);
        
        paintParidadAutomata(g2);
        
        //try {
        //    if(flag)
        //    verificar_cadenas(g2);
        //} catch (IOException ex) {
        //    Logger.getLogger(Protocolo.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //flag=true;
   }
    
    
    
    public void paintProtocolAutomata(Graphics2D g2){
        
        g2.setColor(new Color(0, 102, 0));
        g2.setFont(new Font("Arial Black", Font.BOLD, 20));
        
        //Start
        g2.drawString("Start",90,90);
        
        g2.setColor(Color.BLACK);
        
        //Arrow start
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(160, 85, 190, 85);
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(195, 85, 180, 75);
        g2.drawLine(195, 85, 180, 95);
        
        //Ready
        g2.drawOval(210, 55, 120, 60);
        g2.drawString("Ready",235,92);
        
        //Arrow dataIn
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(340, 85, 425, 85);
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(428, 85, 413, 75);
        g2.drawLine(428, 85, 413, 95);
        
        //Data in
        g2.setFont(new Font("Arial Black", Font.BOLD, 15));
        g2.drawString("data in",345,65);
        
        //Sending
        g2.drawOval(450, 55, 120, 60);
        g2.drawString("Sending",478,92);
        
        //Arrow timeout
        g2.setStroke(new BasicStroke(2));
        g2.drawArc(575, 55, 110, 65, 200, 120+180);
        g2.drawLine(588, 67, 595, 55);
        g2.drawLine(588, 67, 598, 70);
        
        //Timeout 
        g2.setFont(new Font("Arial Black", Font.BOLD, 18));
        g2.drawString("timeout",700,90);
        
        //Arrow acknowledge drawArc(int x, int y, int width, int length, int startAngle, int arcAngle)
        g2.drawArc(295, 95, 230, 60, 195, 350-180);
        g2.drawLine(300, 133, 320, 135);
        g2.drawLine(300, 133, 303, 150);
        
        //Acknowledge
        g2.setFont(new Font("Arial Black", Font.BOLD, 14));
        g2.drawString("acknowledge",500,150);
        
    }
    
    public void paintParidadAutomata(Graphics2D g2){
                g2.setColor(new Color(0, 0, 210));
                g2.setFont(new Font("SansSerif Plain", Font.BOLD, 25));
                
                     
                
        //q0
        g2.drawOval(210, 230, 120, 120);
        g2.drawOval(205, 225, 130, 130);
        g2.drawString("q0",253,298);
        
        g2.setColor(new Color(153, 0, 255));

        //q2
        g2.drawOval(570, 230, 120, 120);
        g2.drawString("q2",613,298);
        
        //q1
        g2.drawOval(210, 480, 120, 120);
        g2.drawString("q1",253,545);
        
        //q3
        g2.drawOval(570, 480, 120, 120);
        g2.drawString("q3",613,545);
        
        g2.setColor(Color.BLACK);
        
        //Arrows drawArc(int x, int y, int width, int length, int startAngle, int arcAngle)
        
        //                  HORIZONTAL
        //q0 to q2
        g2.drawArc(270, 170, 360, 200,150 , -120);
        
        //q2 to q0      Inner
        g2.drawArc(320, 190, 270, 200,215 , 120);
        
        
        
        //q3 to q1
        g2.drawArc(270, 460, 360, 200,215 , 120);
        
        //q1 to 13      Inner
        g2.drawArc(310, 440, 270, 200,150 , -120);
        
        
        //                  VERTICAL
        
            //drawArc(int x, int y, int width, int length, int startAngle, int arcAngle)
        //q1 to q0
        g2.drawArc(200, 320, 90, 190, 240, -100);
        
        
        //q3 to q2
        g2.drawArc(550, 350, 180, 140, 240, -110);
        
        
        //q0 to q1
        g2.drawArc(170, 335, 180, 140, 55, -110);
        
        //q2 to q3
        g2.drawArc(610, 300, 90, 190, 50, -100);
        
        
        //Puntas de flecha
        
        
        g2.drawLine(213, 348, 197, 360);
        g2.drawLine(213, 348, 214, 368);
        
        
        g2.drawLine(593-4, 358, 574-4, 364);
        g2.drawLine(593-4, 358, 592-4, 378);
        
        
       
        g2.drawLine(613, 225, 617, 215);
        g2.drawLine(613, 225, 595, 222);
        
        
        
        g2.drawLine(675, 477, 679, 460);
        g2.drawLine(675, 477, 698, 462);
        
        
        
        
        g2.drawLine(298, 610, 300, 630);
        g2.drawLine(298, 610, 320, 620);
        
       
        
        g2.drawLine(299, 470, 308, 455);
        g2.drawLine(299, 470, 328, 460);
        
        
         
        g2.drawLine(573, 500, 545, 493);
        g2.drawLine(573, 500, 569, 488); 
        
        
        //g2.setColor(Color.red);
        
        g2.drawLine(335, 336, 338, 350);
        g2.drawLine(335, 336, 365, 350); 
        
        
        //Etiquetas 0 y 1
        
        g2.setFont(new Font("SansSerif Plain", Font.BOLD, 45));
        
        //0
        g2.drawString("0",440,208);
        g2.drawString("0",440,380);
        g2.drawString("0",440,478);
        g2.drawString("0",440,650);
        
        //1
        g2.drawString("1",175,430);
        g2.drawString("1",310,430);
        g2.drawString("1",560,430);
        g2.drawString("1",700,430);
        
    }
    
}
