package Bonus.diffesocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Alice {
    static ServerSocket serverSocket;
    public static Scanner cin = new Scanner(System.in);
    private static int a, prime, Alpha;

    public static void prime() {
        boolean flag = false, flag2;
        do {
            System.out.println("Enter the prime num ");
            prime = cin.nextInt();

            for (int i = 2; i <= prime / 2; ++i) {
                // condition for nonprime number
                if (prime % i == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                System.out.println(prime + " is a prime number.");
            else {
                System.out.println(prime + " is not a prime number.");
            }
            flag2 = flag;
            flag = false;
        } while (flag2 == true);

    }

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

    private static int Alice() {
        int A = 0;
        A = power(Alpha, a) % prime;

        return A;
    }

    public static void main(String[] args) {
        try {
            // Initialize Server Socket with port number as parameter.
            serverSocket = new ServerSocket(4000);
            System.out.println("Server is booted up and is waiting for clients to connect.");

            while (true) {
                // Accept any Client wants to connect to the server.
                Socket clientSocket = serverSocket.accept();

                System.out.println("A new client is connected to the server.");

                // Takes input from the client socket.
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());

                // Print output to the client socket.
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                // Take input from console.
                Scanner scanner = new Scanner(System.in);

                // Start communication with client.

                // Send to the client that it is now connected.
                output.writeUTF("connected.");

                // input prime
                prime();
                output.writeInt(prime);

                // input Alpha
                System.out.println("Enter Alpha : ");
                Alpha = cin.nextInt();
                output.writeInt(Alpha);

                // input a
                System.out.println("Enter a : ");
                a = cin.nextInt();
                output.writeInt(a);

                // read b
                int b = input.readInt();
                System.out.println("Clint:. b is : " + b);

                // Calc and Send A
                System.out.println("A = " + Alice());
                output.writeInt(Alice());

                // reseve B
                int B = input.readInt();
                System.out.println("Clint:. B is : " + B);

                // calculate Ka
                int foralice = power(B, a) % prime;
                System.out.println("Ka is : " + foralice);
                output.writeInt(foralice);

                // reseve Ka
                int Kb = input.readInt();
                System.out.println("Server:. Ka is : " + Kb);

                // Close the objects.
                scanner.close();
                input.close();
                output.close();
            }
        } catch (IOException e) {
            System.out.println("Problem with Server Socket.");
        }
    }

}
