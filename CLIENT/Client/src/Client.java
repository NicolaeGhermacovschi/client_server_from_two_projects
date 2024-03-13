import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        String recmsg;
        String sendmsg;
        Socket client;

        Scanner input = new Scanner(System.in);
        try {

            //Making conenction with server
            client = new Socket("localhost", 2225);
            System.out.println("Request sent successfully");
            boolean b = true;

            while (true) {

                // Server msg receive!
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                recmsg = br.readLine();
                System.out.println("Server response:" + recmsg);

                // Check if the received message indicates the server is done sending
                if (recmsg.equals("Server is done sending"))
                    break;

                // Client msg sending to server!
                System.out.print("Client sending to server: ");
                sendmsg = input.nextLine();
                PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
                pw.println(sendmsg);
            }
            // Close the client socket when done
            client.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}