
import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    public static void main(String[] args)throws IOException{
        boolean game=true;
        Scanner scan=new Scanner(System.in);
        Socket socket=null;
        int x,y;
        try{
            ServerSocket server=new ServerSocket(8031);
            Pole pole=new Pole();
            socket=server.accept();
            while(game==true){
                System.out.println("The game is started");
                PrintStream ps=new PrintStream(socket.getOutputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String s=new String();
                while(pole.isKrestikiWin()==false && pole.isNolikikiWin()==false && pole.isFull()==false){
                    pole.move();
                    x=pole.getLastX();
                    y=pole.getLastY();
                    pole.display();
                    s=String.valueOf(x);
                    ps.println(s);
                    s=String.valueOf(y);
                    ps.println(s);
                    ps.flush();
                    if(pole.isKrestikiWin()==false){
                        System.out.println("Wait while your opponent is moving");
                        s=br.readLine();
                        x=Integer.valueOf(s);
                        s=br.readLine();
                        y=Integer.valueOf(s);
                        pole.setNolik(x, y);
                        pole.display();
                    }
                }
                if(pole.isKrestikiWin()==true)
                    System.out.println("You are win");
                if(pole.isNolikikiWin()==true)
                    System.out.println("You are loose");
                if(pole.isFull()==true)
                    System.out.println("Draw!");
                System.out.println("Do you want try again?");
                System.out.println("Yes-any integer");
                System.out.println("No-0");
                int choise=scan.nextInt();
                ps.close();
                if(choise==0)
                    game=false;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(socket!=null)
                socket.close();
        }
    }
}

