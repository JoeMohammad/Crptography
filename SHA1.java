import java.util.Scanner;

public class SHA1 {
    public static double[] ARRofHashed(String Hashed) {
        double[] IntArr = new double[10];
        int k = 0, R = 16;
        char[] chars = Hashed.toCharArray();
        for (int i = 0; i < 10; i++) {
            StringBuilder result = new StringBuilder();
            for (int j = k; j < R; j++) {
                result.append(chars[j]);
            }
            k = k + 16;
            R = R + 16;
            IntArr[i] = binaryTodecimal(String.valueOf(result));
        }
        return IntArr;
    }

    public static int binaryTodecimal(String s) {
        int i = -1;
        char[] str = s.toCharArray();
        int dec_val = 0;
        for (int j = str.length - 1; j >= 0; j--) {
            int k = Integer.valueOf(str[j]) - '0';
            i = i + 1;
            dec_val += k * (Math.pow(2, i));
        }
        return dec_val;
    }

    private static String hash(int[] msg) {
        int h1 = 0x67452301; // 01100111010001010010001100000001
        int h2 = 0xEFCDAB89; // 11101111110011011010101110001001
        int h3 = 0x98BADCFE; // 10011000101110101101110011111110
        int h4 = 0x10325476; // 00010000001100100101010001110110
        int h5 = 0xC3D2E1F0; // 11000011110100101110000111110000
        int k1 = 0x5A827999;
        int k2 = 0x6ED9EBA1;
        int k3 = 0x8F1BBCDC;
        int k4 = 0xCA62C1D6;
        int msglen = msg.length;
        int[] intArray = new int[80];
        int j = 0;
        // complete the rest of 80 groups
        for (int i = 0; i < msglen; i += 16) {
            for (j = 0; j <= 15; j++)
                intArray[j] = msg[j + i];
            for (j = 16; j <= 79; j++) {
                intArray[j] = leftrotate(intArray[j - 3] ^ intArray[j - 8] ^ intArray[j - 14] ^ intArray[j - 16], 1);
            }
            int A = h1;
            int B = h2;
            int C = h3;
            int D = h4;
            int E = h5;
            int temp = 0;
            for (int x = 0; x <= 19; x++) {
                temp = leftrotate(A, 5) + ((B & C) | ((~B) & D)) + E + intArray[x] + k1;
                E = D;
                D = C;
                C = leftrotate(B, 30);
                B = A;
                A = temp;
            }
            for (int b = 20; b <= 39; b++) {
                temp = leftrotate(A, 5) + (B ^ C ^ D) + E + intArray[b] + k2;
                E = D;
                D = C;
                C = leftrotate(B, 30);
                B = A;
                A = temp;
            }
            for (int c = 40; c <= 59; c++) {
                temp = leftrotate(A, 5) + ((B & C) | (B & D) | (C & D)) + E + intArray[c] + k3;
                E = D;
                D = C;
                C = leftrotate(B, 30);
                B = A;
                A = temp;
            }
            for (int d = 60; d <= 79; d++) {
                temp = leftrotate(A, 5) + (B ^ C ^ D) + E + intArray[d] + k4;
                E = D;
                D = C;
                C = leftrotate(B, 30);
                B = A;
                A = temp;
            }
            h1 += A;
            h2 += B;
            h3 += C;
            h4 += D;
            h5 += E;
        }
        String h11Length = Integer.toBinaryString(h1);
        String h22Length = Integer.toBinaryString(h2);
        String h33Length = Integer.toBinaryString(h3);
        String h44Length = Integer.toBinaryString(h4);
        String h55Length = Integer.toBinaryString(h5);

        String h1Length = Integer.toHexString(h1);
        String h2Length = Integer.toHexString(h2);
        String h3Length = Integer.toHexString(h3);
        String h4Length = Integer.toHexString(h4);
        String h5Length = Integer.toHexString(h5);
        String hh = h11Length + h22Length + h33Length + h44Length + h55Length;
        System.out.println("Result: " + hh);

        return hh;
    }

    static boolean checkForPrime(double P) {
        boolean isItPrime = true;

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

    static double Calc(double down, double up, double P) {
        double calc = ((double) Math.pow(down, up));
        double Calc = calc % P;
        return Calc;

    }

    static double modInverse(double KE, double P) {

        for (int x = 1; x < P; x++)
            if (((KE % P) * (x % P)) % P == 1)
                return x;
        return 1;
    }

    // Converting String to Binary
    public static String convertToBinary(String word) {
        byte[] bytes = word.getBytes();
        StringBuilder binary = new StringBuilder();

        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        return binary.toString();

    }

    private static int leftrotate(int x, int shift) {

        return ((x << shift) | (x >>> (32 - shift)));

    }

    public static String wordtotallength(int bitLength) {

        String tempBitsLength = Integer.toBinaryString(bitLength);
        StringBuilder sb = new StringBuilder(tempBitsLength);
        int temp = 64 - tempBitsLength.length();
        while (temp > 0) {
            sb.insert(0, 0);
            temp--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Enter Text to be encypted:");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        System.out.println("Plain Text: " + word);
        String binary = convertToBinary(word);
        int messLength = binary.length();
        int binaryMessageLength = word.length() * 8 - 8;
        String endBitLength = wordtotallength(binaryMessageLength + 8);
        int temp = (binaryMessageLength);
        temp = 432 - temp;
        int binaryZeros = temp;// count of zeros that will be added for padding
        String append1 = "10000000"; // append 1 then 7 zeros
        binary = binary.replaceAll("\\s+", "");
        StringBuilder messageBinary = new StringBuilder(binary);
        messageBinary.insert(messageBinary.toString().length(), append1);

        while (binaryZeros > 0) {// add padding
            messageBinary.insert(messageBinary.toString().length(), 0);
            binaryZeros--;
        }

        messageBinary.insert(messageBinary.toString().length(), endBitLength);
        String m = messageBinary.toString();
        m = m.replaceAll("\\s+", "");
        int[] mArray = new int[m.toString().length() / 32];
        for (int i = 0; i < m.toString().length(); i += 32) {
            mArray[i / 32] = Integer.valueOf(m.substring(i + 1, i + 32), 2);
        }
        hash(mArray);

        double[] dsasha = ARRofHashed(m);
        double P, q, Alpha, d, B, SHA, Ke, r, KeInv, v;
        double[] s = new double[10];
        double[] w = new double[10];
        double[] u1 = new double[10];
        double[] u2 = new double[10];
        double[] alphalu1 = new double[10];
        double[] Betau2 = new double[10];
        SHA = 26;
        System.out.println("Enter your Prime number");
        P = sc.nextDouble();
        boolean A = checkForPrime(P);
        if (A == false) {
            System.out.println("Please enter a Prime number");
            P = sc.nextDouble();
            A = checkForPrime(P);
        }

        System.out.println("Enter Q");
        q = sc.nextDouble();
        System.out.println("Enter Alpha");
        Alpha = sc.nextDouble();
        System.out.println("Enter Private Key=d");
        d = sc.nextDouble();
        B = Calc(Alpha, d, P);
        System.out.println("B equal");
        System.out.println(B);
        System.out.println("Enter KE");
        Ke = sc.nextDouble();
        KeInv = modInverse(Ke, q);
        r = (Calc(Alpha, Ke, P)) % q;
        System.out.println("r equal");
        System.out.println(r);
        s = ((SHA + (d * r)) * KeInv) % q;
        System.out.println("s equal");
        System.out.println(s);
        w = modInverse(s, q);
        System.out.println("w equal");
        System.out.println(w);
        u1 = (w * SHA) % q;
        System.out.println("u1 equal");
        System.out.println(u1);
        u2 = (w * r) % q;
        System.out.println("u2 equal");
        System.out.println(u2);
        Alu1 = Calc(Alpha, u1, P);

        Bu2 = Calc(B, u2, P);

        v = ((Alu1 * Bu2) % P) % q;
        System.out.println("v equal");
        System.out.println(v);
        if (v == r) {
            System.out.println("Valid");
        } else
            System.out.println("Invalid");

    }
}