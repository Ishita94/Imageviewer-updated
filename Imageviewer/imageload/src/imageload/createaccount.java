/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageload;

/**
 *
 * @author Lt. Col Shams
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package imageload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
/**
 *
 * @author Lt. Col Shams
 */
public class createaccount {
   public String p;
   int flag=0;
    Socket sock;
    public createaccount() {
        p=null;
    }
   public  String [] view(String s) throws IOException
    {
          Socket sock = new Socket("localhost", 9999);
          OutputStream os = sock.getOutputStream();
         PrintStream pw= new PrintStream( sock.getOutputStream() );
         //pw.println("newuser");
         pw.println(s);
        pw.flush();
        int k,j;
        InputStream in = sock.getInputStream();
        BufferedReader b=new BufferedReader(new InputStreamReader(in));
        k=b.read()-48;
       System.out.println(k);
        String a[]=new String[k+1];
        j=0;
        
        while(j<=k)
        {
            a[j]=b.readLine();
            System.out.println(a[j]);
            j++;
        }
        sock.close();
        return a;
    
    
    }
     public static long bytesToLong(byte[] bytes) {
            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.put(bytes);
            buffer.flip();//need flip 
            return buffer.getLong();
        }
   public void download(String s,String m) throws IOException
   {
       Socket sock = new Socket("localhost", 9999);
         OutputStream os = sock.getOutputStream();
         PrintStream pw= new PrintStream( sock.getOutputStream() );
         //pw.println("newuser");
         pw.println(s);
        pw.flush();
       
            byte[] mybytearray = new byte[1000000];
            //this part gets all the stream
            InputStream is = sock.getInputStream();
             //System.out.println("total bytes "+is.available());
            File f = new File("folder",m);
            //FileOutputStream fos = new FileOutputStream("H:\\imageload\\src\\imageload\\imagesreceived\\".concat(m));
            FileOutputStream fos = new FileOutputStream(f);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
             
            // System.out.println("Sock input buffer: "+sock.getReceiveBufferSize());
            //this part gets the filesize from server
            byte [] tempbuffer=new byte[8];
            is.read(tempbuffer,0,8);
            long fileSize=bytesToLong(tempbuffer);
            //System.out.println(fileSize);
            
             //READ
             int bytesRead=0;
             int totalbytes=0;         
             while(totalbytes<fileSize)
             {
                  
                    bytesRead = is.read(mybytearray,0,mybytearray.length);
                    totalbytes+=bytesRead;
                    //System.out.println("Total bytesRead "+totalbytes);
                    bos.write(mybytearray,0, bytesRead);                   
                    bos.flush();
                 
             
             }
        
                  //return p;
        //os.flush();
        //sock.close();
                sock.close();
                
   }
    public  String send(String s) throws IOException
    {
       //p=null;
      // s="newuser".concat(s);
        
        
     Socket sock = new Socket("localhost", 9999);
        
     
         OutputStream os = sock.getOutputStream();
         PrintStream pw= new PrintStream( sock.getOutputStream() );
         //pw.println("newuser");
         pw.println(s);
        pw.flush();
        //pw.println(s);
        
           
         InputStream in = sock.getInputStream();
              BufferedReader b=new BufferedReader(new InputStreamReader(in));
             
                p=b.readLine();
                  //return p;
        //os.flush();
        sock.close();
                //sock.close();
        
         //return p;
         return p;
    
}
     
    public static byte[] longToBytes(long x) 
    {
    ByteBuffer buffer = ByteBuffer.allocate(8);
    buffer.putLong(x);
    return buffer.array();
   }
    public  String sendfile(File f,String n) throws IOException
    {
       //p=null;
      // s="newuser".concat(s);
       Socket sock = new Socket("localhost", 9999);
        //File myFile = new File("F:\\ami.jpg");
                  //OutputStream os = sock.getOutputStream();
         PrintStream pw= new PrintStream( sock.getOutputStream() );
         //pw.println("newuser");
         String h=f.getName();
         String g="sending".concat(n);
         g=g.concat("/");
         g=g.concat(h);
         pw.println(g);
         System.out.println(g);
        pw.flush();
                  System.out.println("file length: "+f.length());
                  BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
                //  System.out.println("Sock outpubuffer: "+sock.getSendBufferSize());
                  OutputStream os = sock.getOutputStream();
 //this part gets the filesiize and sends it to client
                  long filesize=f.length();
                  byte tempbuffer[];
              tempbuffer = longToBytes(filesize);
                  os.write(tempbuffer);
                  os.flush();                
 	        byte[] mybytearray = new byte[(int)sock.getSendBufferSize()];
                int bytesRead=0;
                int totalbytes=0;
                  while(totalbytes!=filesize)
                  {
                    
                      bytesRead=bis.read(mybytearray, 0, mybytearray.length);
                      totalbytes+=bytesRead;
                      System.out.println("Total bytesRead "+totalbytes);                     
                      os.write(mybytearray, 0, bytesRead);
                      os.flush();
                  
                  
                  }
                 InputStream in = sock.getInputStream();
              BufferedReader b=new BufferedReader(new InputStreamReader(in));
                p=b.readLine();
                  //return p;
        //os.flush();
        //sock.close();
                sock.close();
                return p;
               }

   
      
   }


