package com.company;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static boolean checkForPrime(double P) {
        boolean isItPrime = true ;

        if (P <= 1) {
            isItPrime = false;

            return isItPrime;
        } else {
            for (int i = 2; i <= P / 2; i++) {
                if ((P % i) == 0) {
                    isItPrime = false;

                    break;
                }
            }

            return isItPrime;
        }
    }


    static double modInverse(double KE, double P)
    {

        for (int x = 1; x < P; x++)
            if (((KE%P) * (x%P)) % P == 1)
                return x;
        return 1;
    }
    static double Calc(double down, double up, double P) {
        double calc = ((double) Math.pow(down, up));
        double Calc=calc% P;
        return Calc;

    }
    static double gcd(double a, double b)
    {

        if (a == 0)
            return b;
        if (b == 0)
            return a;


        if (a == b)
            return a;


        if (a > b)
            return gcd(a-b, b);
        return gcd(a, b-a);
    }
    static double Random_d(double high){


        Random r = new Random();
        high=high-2;
        double low = 2;
        double result = r.nextInt((int) (high-low)) + low;
        return result;
    }
    static double Random_KE(double high){


        Random r = new Random();
        high=high-2;
        double low = 0;
        double result = r.nextInt((int) (high-low)) + low;
        return result;
    }
    public static void main(String[] args) {
        double P,Alpha,d,B,X,KE,r,keInv,S,t,Alphax;

        System.out.println("Enter your Prime number");
        Scanner sc = new Scanner(System.in);
        P=sc.nextDouble();
        boolean A=checkForPrime(P);
        if(A==false)
            System.out.println("Please enter a Prime number");
        while(A==true)
        {
            System.out.println("Enter Alpha");
            Alpha=sc.nextDouble();
            System.out.println("Enter d");
            d=sc.nextDouble();
            B=Calc(Alpha,d,P);
            System.out.println("Enter X");
            X=sc.nextDouble();
            System.out.println("Enter KE");
            KE=sc.nextDouble();
            while(gcd(KE,P-1)!=1)
            {
                System.out.println("the greatest common divisor of KE and (P-1) not = 1 ");
                System.out.println("Enter KE");
                KE=sc.nextDouble();

            }
            r=Calc(Alpha,KE,P);
            keInv=modInverse(KE,P-1);
            double cl,q=P-1;
            cl=(X-(d*r))*keInv;
            while(cl<0)
            {
                cl=cl+(P-1);
            }
            S=cl%q;
           // if(S<0)
            //    S=S*(-1);
          //  S=S*1;
            t=(Calc(B,r,P)*Calc(r,S,P))%P;
            Alphax=Calc(Alpha,X,P);
            System.out.println("B is equal to "+B);
            System.out.println("r is equal to "+r);
            System.out.println("s is equal to "+S);
            System.out.println("t is equal to "+t);
            System.out.println("Alpha power X is equal to "+Alphax);
            if(t==Alphax)
            {
                System.out.println("Valid signature");
            }

            else
                System.out.println("Invalid signature");

            break;
        }
    }

}
