/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceptaccount;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;
import java.net.InetAddress;
import java.nio.ByteBuffer;

/**
 *
 * @author Lt. Col Shams
 */
public class Acceptaccount {
   // public String n;
      public static long bytesToLong(byte[] bytes) {
            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.put(bytes);
            buffer.flip();//need flip 
            return buffer.getLong();
        }
       public static byte[] longToBytes(long x) {
    ByteBuffer buffer = ByteBuffer.allocate(8);
    buffer.putLong(x);
    return buffer.array();
}
    public static void main(String[] args) throws IOException {
        
    
        String accountname ;

                ServerSocket servsock = new ServerSocket(9999);
                while(true){
                     int i=0;               //ServerSocket servsock = new ServerSocket(9999);

                Socket sock = servsock.accept();
                
               InputStream in = sock.getInputStream();
              BufferedReader b=new BufferedReader(new InputStreamReader(in));
                  String s=b.readLine();
                  if(s.startsWith("newuser"))
                  {
                     StringBuffer s1=new StringBuffer(s);
                     int len="newuser".length();
                     s1.delete(0,len);
                  s=s1.toString();
                  System.out.println(s);
                  //s=b.readLine();
                   //else
                  //
                      //accountname="C:\\Users\\Lt. Col Shams\\Documents\\NetBeansProjects\\Acceptaccount\\images\\".concat(s);
                     System.out.println(s);
                  
                    File fileDirectory = new File("images",s);
                   if(!fileDirectory.exists())
                  {
                      if(fileDirectory.mkdir())
                      {
                          String  n="New Account Created!!!";
                           OutputStream out=sock.getOutputStream();
                           PrintStream pw= new PrintStream(out);
                          pw.println(n);
                            pw.flush();
                          System.out.println("Directory is created!");
                      }
                    
                       else System.out.println("Failed to create directory!");
                }
                }
                  else if(s.startsWith("login"))
                  {
                      
                     StringBuffer s1=new StringBuffer(s);
                     int len="login".length();
                     s1.delete(0,len);
                  s=s1.toString();
                  System.out.println(s);
                  //s=b.readLine();
                   //else
                  //
                     // accountname="C:\\Users\\Lt. Col Shams\\Documents\\NetBeansProjects\\Acceptaccount\\images\\".concat(s);
                     //System.out.println(s);
                  
                    File fileDirectory = new File("images",s);
                   if(!fileDirectory.exists())
                  {
                      String  n="Your Account does not exist,create new account!!!";
                           OutputStream out=sock.getOutputStream();
                           PrintStream pw= new PrintStream(out);
                          pw.println(n);
                            pw.flush();
                          //System.out.println("Directory is created!");
                }
                   else
                   {
                       String  n="Logged In Succesfully!!!";
                           OutputStream out=sock.getOutputStream();
                           PrintStream pw= new PrintStream(out);
                          pw.println(n);
                            pw.flush();
                   }
                   sock.close();
                }
           else if(s.startsWith("sending"))
            {
                    StringBuffer s1=new StringBuffer(s);
                     int len="sending".length();
                     s1.delete(0,len);
                  s=s1.toString();
                  int l=s.indexOf('/');
                  String e=s.substring(0, l);
                  e="images".concat("/").concat(e);
                  String f=s.substring(l+1,s.length());
                      byte[] mybytearray = new byte[1000000];
            //this part gets all the stream
            InputStream is = sock.getInputStream();
             //System.out.println("total bytes "+is.available());
           // String p="C:\\Users\\Lt. Col Shams\\Documents\\NetBeansProjects\\Acceptaccount\\images\\".concat(s);
            
            FileOutputStream fos = new FileOutputStream(new File(e,f));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
             
            // System.out.println("Sock input buffer: "+sock.getReceiveBufferSize());
            //this part gets the filesize from server
            byte [] tempbuffer=new byte[8];
            is.read(tempbuffer,0,8);
            long fileSize=bytesToLong(tempbuffer);
            System.out.println(fileSize);
            
             //READ
             int bytesRead=0;
             int totalbytes=0;         
             while(totalbytes<fileSize)
             {
                  
                    bytesRead = is.read(mybytearray,0,mybytearray.length);
                    totalbytes+=bytesRead;
                    System.out.println("Total bytesRead "+totalbytes);
                    bos.write(mybytearray,0, bytesRead);                   
                    bos.flush();
                 
             
             }
           
           // sock.close();

        OutputStream out=sock.getOutputStream();
                           PrintStream pw= new PrintStream(out);
                          pw.println("Received!");
                            pw.flush();
                            sock.close();
        
              }
            else if(s.startsWith("download"))
             {
                    StringBuffer s1=new StringBuffer(s);
                     int len="download".length();
                     s1.delete(0,len);
                     s=s1.toString();
                      //String p="C:\\Users\\Lt. Col Shams\\Documents\\NetBeansProjects\\Acceptaccount\\images\\".concat(s);
                    len=s.indexOf('/');
                    String e=s.substring(0, len);
                    String f=s.substring(len+1, s.length());
                     try{

                
                File myFile = new File("images/".concat(e),f);
                 
                  //System.out.println("file length: "+myFile.length());
                  BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
                //  System.out.println("Sock outpubuffer: "+sock.getSendBufferSize());
                  OutputStream os = sock.getOutputStream();
 //this part gets the filesiize and sends it to client
                  long filesize=myFile.length();
                  byte tempbuffer[]=longToBytes(filesize);
                  os.write(tempbuffer);
                  os.flush();
//write file in socket                  
 	        byte[] mybytearray = new byte[(int)sock.getSendBufferSize()];
                int bytesRead=0;
                int totalbytes=0;
                  while(totalbytes!=filesize)
                  {
                    
                      bytesRead=bis.read(mybytearray, 0, mybytearray.length);
                      totalbytes+=bytesRead;
                      //System.out.println("Total bytesRead "+totalbytes);                     
                      os.write(mybytearray, 0, bytesRead);
                      os.flush();
                  
                  
                  }
                
                   
                  
                 
                  sock.close();
                 
                

            
        }
        catch(Exception ex)
        {
        }
             
             
        }
             else if(s.startsWith("viewing"))
             {
                    StringBuffer s1=new StringBuffer(s);
                     int len="viewing".length();
                     s1.delete(0,len);
                  s=s1.toString();
                     
            //this part gets all the stream
            //InputStream is = sock.getInputStream();
             //System.out.println("total bytes "+is.available());
            //String p="C:\\Users\\Lt. Col Shams\\Documents\\NetBeansProjects\\Acceptaccount\\images\\".concat(s);
            //FileOutputStream fos = new FileOutputStream(p);
            //BufferedOutputStream bos = new BufferedOutputStream(fos);
            
            File f=new File("images",s);
            String [] store=f.list();
           
            //Scanner output=new Scanner(f);
            int j=0;
             OutputStream out=sock.getOutputStream();
                           PrintStream pw= new PrintStream(out);
                           pw.println(store.length);
                            pw.flush();
            while(j<store.length)
            {
                 //OutputStream out=sock.getOutputStream();
                          // PrintStream pw= new PrintStream(out);
                          pw.println(store[j]);
                            pw.flush();
                            j++;
            }
            //if(j==0)  pw.println("nothing in account");
            //else  pw.println("finished");
           // pw.flush();
            sock.close();
            }
                  
        }
                  
    }
}
       
    

