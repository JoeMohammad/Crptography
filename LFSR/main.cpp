#include <iostream>
#include <string>
#include <cstring>
#include <bitset>
#include <sstream>


                                                                                // Eslam Ahmed abdelfatah elsayed
                                                                                //20195002
                                                                                //Omar Hassan Saad Abdelhamid Dawood
                                                                                //20195003


using namespace std;


void  binConvertXOR(string ConvertMe,int key[]){

    int ArraySize = ConvertMe.length();
    int* asciiArray = new int [ArraySize];
    int binValues[8];
    int decValues[8] = {128,64,32,16,8,4,2,1};
    // Convert each char of ConvertMe into its ascii value
    for(int i = 0; i < ArraySize; i++){
        asciiArray[i] = ConvertMe[i];
    }

// Convert asciiArray values from decimal to binary and output to screen
    for(int i = 0; i < ArraySize; i++){
            int XORS[8]={0,0,0,0,0,0,0,0};
        // convert each ascii value into its binary 8 bit equivalent
        for (int a = 0; a < 8; a++){
            if (asciiArray[i] >= decValues[a]){
                binValues[a] = 1;
            }
            else if (asciiArray[i] < decValues[a]){
                binValues[a] = 0;
            }
            if (binValues[a] == 1){
                asciiArray[i] = asciiArray[i] - decValues[a];
            }
        }
        for(int i=0;i<8;i++)
        {
            XORS[i]=key[i]^binValues[i];
            cout<<XORS[i];
        }
        cout<<" ";
    }
    cout<<endl;
}

void bin_to_char( string char_out ) // Convert binary to ASCII.
{

	// Allows the sequence of strings to be accessed directly as a string.
	istringstream in( char_out );

	// Divides the bits into bytes.
	bitset<8> bs;

	//cout << endl << "Your input in ASCII is: ";

	// B-A Conversion
	// Loop extracts the bytes in the string and prints them as there ascii values.
	while ( in >> bs )
	{
		cout << char( bs.to_ulong() );
	}

}



int main()
{
    cout<<"1-ENCRYPTION "<<endl;
    cout<<"2-DECRYPTION "<<endl;
    int answer;
    cin>>answer;
    const int nOfRegisters=9;
    if(answer==1){
        string originalText;
        cout<<"Enter original Text: "<<endl;
        cin.ignore();
        getline(cin,originalText);

       string initialState;
       cout<<"Enter initial state : "<<endl;
       cin>>initialState;
       string PoX;
       cout<<"Enter P(X) : "<<endl;
       cin>>PoX;


        int arr[initialState.length()];
        for(int i=0; i<initialState.length(); i++)
        {arr[i]=initialState[i]-'0';}

        int pX[PoX.length()];
        for(int i=0; i<PoX.length(); i++)
        {pX[i]=PoX[i]-'0';}
        // if rule = (2^n.of register)-11  so rows = 511
        int rows=511, cols=9;

        int length=0;
        for(int i=0;i<PoX.length();i++)
        {
            if(pX[i]==1)
                length++;
        }


        int k=0,v=1;
        int Arr[cols];
        for(int z=0;z<PoX.length();z++)
        {
            if(pX[z]==1)
            {
                Arr[k]=cols-v;
                k++;
                v++;
            }
            else
            {
                v++;
            }
        }


        int Matrix[rows][cols];
        for(int i=0;i<cols;i++)
        {
               Matrix[0][i]=arr[i];
        }

        for(int i=1;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(j!=cols-1)
                {
                   Matrix[i][j]=Matrix[i-1][j+1];
                }
                else
                {
                     int sum=0;
                     for(int o=0;o<length;o++)
                     {
                         sum^=Matrix[i-1][Arr[o]];
                     }
                     Matrix[i][j]=sum;
                }
            }
        }
    int key[rows];
    //KEY GENERATED :
        for(int i=0;i<rows;i++)
        {
            key[i]=Matrix[i][0];
        }

        cout<<endl;


    // ORIGINAL NAME IN BINARIES XORS
    cout<<"YOUR CYPHER TEXT IS : ";
    binConvertXOR(originalText,key);
    cout<<endl;
    }
else if(answer==2){
    string cipher;
    cout<<"Enter cipher Text: "<<endl;
    cin.ignore();
    getline(cin,cipher);


    string initialState;
       cout<<"Enter initial state : "<<endl;
       cin>>initialState;
       string PoX;
       cout<<"Enter P(X) : "<<endl;
       cin>>PoX;


        int arr[initialState.length()];
        for(int i=0; i<initialState.length(); i++)
        {arr[i]=initialState[i]-'0';}

        int pX[PoX.length()];
        for(int i=0; i<PoX.length(); i++)
        {pX[i]=PoX[i]-'0';}
       // if rule = (2^n.of register)-11  so rows = 511
        int rows=511, cols=9;

        int length=0;
        for(int i=0;i<PoX.length();i++)
        {
            if(pX[i]==1)
                length++;
        }


        int k=0,v=1;
        int Arr[cols];
        for(int z=0;z<PoX.length();z++)
        {
            if(pX[z]==1)
            {
                Arr[k]=cols-v;
                k++;
                v++;
            }
            else
            {
                v++;
            }
        }


        int Matrix[rows][cols];
        for(int i=0;i<cols;i++)
        {
               Matrix[0][i]=arr[i];
        }

        for(int i=1;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(j!=cols-1)
                {
                   Matrix[i][j]=Matrix[i-1][j+1];
                }
                else
                {
                     int sum=0;
                     for(int o=0;o<length;o++)
                     {
                         sum^=Matrix[i-1][Arr[o]];
                     }
                     Matrix[i][j]=sum;
                }
            }
        }
    int key[rows];
    //KEY GENERATED :
        for(int i=0;i<rows;i++)
        {
            key[i]=Matrix[i][0];
        }

        cout<<endl;


    int E=0;
    int arr2[cipher.length()];
        for(int i=0; i<cipher.length(); i++)
        {arr2[i]=cipher[i]-'0';}
    while(E<cipher.length())
    {
        int cXor[8]={0,0,0,0,0,0,0,0};

        for(int i=0;i<8;i++)
        {
            cXor[i]=arr2[E]^key[i];
            E++;
        }
        string XXor="";
        for(int i=0;i<8;i++)
        {
            if(cXor[i]==0)
                XXor+='0';
            else
                XXor+='1';
        }

        bin_to_char(XXor);


        E++;
    }
    cout<<endl;
}



    return 0;
}



