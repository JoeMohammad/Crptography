package com.company;

import java.math.BigInteger;
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;

public class Main {
    static class Point{
        int x,y;

    }

    static boolean PCheck(double P) {
        boolean prime = true ;

        if (P <= 1) {
            prime = false;

            return prime;
        } else {
            for (int i = 2; i <= P / 2; i++) {
                if ((P % i) == 0) {
                    prime = false;

                    break;
                }
            }

            return prime;
        }
    }
    static double RandomK(double high){


        Random r = new Random();
        high=high-2;
        double low = 2;
        double result = r.nextInt((int) (high-low)) + low;
        return result;
    }
    static boolean checkPointonE(Point Pnt,double P){
        //Y^2=X^3+3X+1   mod P
        int X,Y;
        Y= (int) ((Math.pow(Pnt.y,2))%P);
        System.out.println("your Y = "+Y);
        X= (int) ((Math.pow(Pnt.x,3)+(3*Pnt.x)+1)%P);
        System.out.println("your X = "+X);
        if(Y==X)
        {
            return true;
        }
        else
        {
            System.out.println("WRONG WRONG WRONG");
            return false;
        }


    }

    public static void main(String[] args) {
        double P ,ALPHA,Secret_A,Beta,Zvalue,RandomK,Y1,Y2,MessageX,DmessageX;
        Point alpha = new Point();
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to ELgamal  By Elliptic Curve ");
        System.out.println("---------------------------------------------------- ");
        System.out.println("--------------------------------------");
        System.out.println("----------------------");
        System.out.println("------------");

        System.out.println("Enter message you want to Encrypt ");
        MessageX=scan.nextDouble();

        System.out.println("BOB is Generating His Public Keys (Alpha , Beta )");


        //BOB
        System.out.println("Enter Prime number");
        P = scan.nextDouble();
        boolean check =PCheck(P);
        while(check==false)
        {
            System.out.println("Wrong not a Prime number ");
            System.out.println("Please enter a large prime number!");
            P = scan.nextDouble();
            check =PCheck(P);

        }
        System.out.println("Enter point Alpha(x,y) (on Elliptic curve )");
        ALPHA=scan.nextInt();
       // alpha.x=scan.nextInt();
        //alpha.y=scan.nextInt();
       // System.out.println("checking point .......");
       /* while(checkPointonE(alpha,P)!=true)
        {
            System.out.println("Point is not correct :( ");
            System.out.println("Enter point Alpha(x,y) (on Elliptic curve )");
            alpha.x=scan.nextInt();
            alpha.y=scan.nextInt();
        }*/
        //System.out.println("Point is correct on EC :) ");

        System.out.println("choose Secret number A :");
        Secret_A=scan.nextDouble();
        Beta=(ALPHA*Secret_A)%P;
        System.out.println("ALICE is going to Generate Encrypted keys");

        //ALICE
      //  System.out.println("Enter Z Value to get Random K ");
        //Zvalue=scan.nextDouble();
        RandomK=RandomK(P);
        System.out.println("your Random K = "+RandomK);
        Y1=(RandomK*ALPHA)%P;
        Y2=(MessageX+(RandomK*Beta))%P;
        System.out.println("Encrypted message Y1= "+Y1+" Y2= "+Y2);

        //BOB
        System.out.println("BOB is DECRYPTING the keys& Message  ");
        System.out.println("....");
        System.out.println("...");
        System.out.println("..");
        System.out.println(".");
        double R=(Secret_A*Y1)%P;
        DmessageX=(Y2-R)%P;
        if(DmessageX<0)
        {
            DmessageX+=P;
        }
        System.out.println("Your Decrypted message = "+DmessageX);















    }
}

