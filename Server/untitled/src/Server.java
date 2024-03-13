import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String args[]) {
        ServerSocket client;
        Socket socket;
        // CommThread ct;
        Scanner input = new Scanner(System.in);
        String sendmsg;
        String recmsg;
        int requests = 0;
        try {

            //Waiting for client
            System.out.println("Waiting for client request");
            client = new ServerSocket(2225);
            socket = client.accept();
            //accept and Now connected with client!
            System.out.println("New client is pop up!");
            PrintStream ps1 = new PrintStream(socket.getOutputStream());
            ps1.println("Server: ");
            //True bcz server always remain open for clients
            while (true) {


                // client msg receive that he/she send!
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                recmsg = br.readLine();

                PrintStream ps = new PrintStream(socket.getOutputStream());
                if(recmsg.equals("1")){
                    sendmsg =	method1();
                } else if (recmsg.equals("2")) {
                    sendmsg =method2();
                } else if (recmsg.equals("3")) {
                    sendmsg =	method3();
                } else{
                    sendmsg = "Unknown command";
                }
                ps.println(sendmsg);

                // System.out.println("Accepted Request# "+requests);
                // ct=new CommThread(client);
                // ct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static String method1(){
        return "method 1";
    }
    static String method2(){
        return "method 2";
    }
    static String method3(){
        return "method 3";
    }
}