package Bonus.diffesocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Bob {

    public static Scanner cin = new Scanner(System.in);
    private static int b;

    public static int power(int base, int exponent) {
        int result = 0;

        if (exponent == 0) {
            result = 1;
        } else if (exponent == 1) {
            result = base;
        } else if (exponent > 1) {
            // result = 2147483647;
            result = base * power(base, exponent - 1);
        }

        int resultTemp = result;
        result = 0;
        return resultTemp;
    }

    private static int Bob(int Alpha2, int b, int prime) {
        int B = power(Alpha2, b) % prime;

        return B;
    }

    public static void main(String[] args) {
        try {
            // Get the ip address of the local host and send it the socket with the server
            // port number.
            InetAddress ip = InetAddress.getByName("localhost");
            Socket clientSocket = new Socket(ip, 4000);

            System.out.println("Connecting to the server....");

            // Take input from the server by using the client socket.
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());

            // Print output to the server by using the client socket.
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            // Take input from console.
            Scanner scanner = new Scanner(System.in);

            // Read from the server that the client is now connected and print it.
            String connectConfirm = input.readUTF();
            System.out.println("Server: " + connectConfirm);

            // Start communication with server.

            // Read String from Alice Massage for prime
            // reseve prime
            int prime2 = input.readInt();
            System.out.println("Server:. the prime is :" + prime2);

            // Read integer Alpha from Alice MAssage
            // resseve Alpha
            int Alpha2 = input.readInt();
            System.out.println("Server:. the Alpha is : " + Alpha2);

            // Send b
            System.out.println("Enter b : ");
            b = cin.nextInt();
            output.writeInt(b);

            // Reseve a
            int a = input.readInt();
            System.out.println("Server:. a is : " + a);

            // reseve A
            int A = input.readInt();
            System.out.println("Server:. A is : " + A);

            // Calc and Send B
            System.out.println("B = " + Bob(Alpha2, b, prime2));
            output.writeInt(Bob(Alpha2, b, prime2));

            // reseve Ka
            int Ka = input.readInt();
            System.out.println("Server:. Ka is : " + Ka);

            // calculate Kb
            int forBob = power(A, b) % prime2;
            System.out.println("Server:. Kb is : " + forBob);
            output.writeInt(forBob);

            // Close the connection with the server.
            System.out.println("Closing connection to server....");
            clientSocket.close();
            System.out.println("connection is closed.");

            // Close the objects.
            scanner.close();
            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println("Connection with server is terminated.");
        }
    }

}
