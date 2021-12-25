//Eric
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

public class KeyGenerator {
    int bitlength = 100;
    private BigInteger thep;
    private BigInteger theq;
    private RSAKey publickey;
    private RSAKey privatekey;






    public BigInteger Make_n(int bitlength){
        Random rand = new Random();
        BigInteger p;
        BigInteger q;
        BigInteger n ;

        do  {
            p=  BigInteger.probablePrime(50, rand);
            q = BigInteger.probablePrime(50, rand);

            n = p.multiply(q);
        }while(n.bitLength()!=bitlength);
        this.thep=p;
        this.theq=q;


        return n;
        }




    public BigInteger findphi(BigInteger p, BigInteger q, BigInteger n){
        BigInteger bigone= BigInteger.valueOf(1);
        BigInteger phi= n.subtract(p).subtract(q).add(bigone);
        return phi;

    }

    public String crack(String formattedkey){
        String[] splitkey=formattedkey.split(";");
        BigInteger n = new BigInteger(splitkey[1]);
        BigInteger b = new BigInteger(splitkey[0]);
        BigInteger e = new BigInteger(splitkey[2]);
        int bitlength = b.intValue();


        BigInteger x = n.sqrt().add(BigInteger.ONE);
        BigInteger y = BigInteger.ZERO;
        if((x.pow(2).subtract(y.pow(2))).compareTo(n)==0){

        }
        else{
            for(int i=0;(x.pow(2).subtract(y.pow(2))).compareTo(n)!=0; i++ ){
                if((x.pow(2).subtract(y.pow(2))).compareTo(n)==-1){
                    x=x.add(BigInteger.ONE);
                }
                if((x.pow(2).subtract(y.pow(2))).compareTo(n)==1){
                    y=y.add(BigInteger.ONE);

                }

            }


        }
        BigInteger q=x.subtract(y);
        BigInteger p=x.add(y);
        BigInteger phi = findphi(p, q, n);
        BigInteger d = e.modInverse(phi);
        this.publickey = new RSAKey(n, d, e, bitlength, true);
        this.privatekey = new RSAKey(n, d, e, bitlength, false);
        System.out.println("this is the derived public key, it should be the same as the public key you used to find the private key "+ publickey.toString());
        System.out.println("this is the cracked private key "+privatekey.toString());
        return this.privatekey.toString();
    }




    public BigInteger Make_e (BigInteger phi, int bitlength){
        Random rand = new Random();
        BigInteger e;
        do{
            e = BigInteger.probablePrime(bitlength, rand);

        }while (!(e.gcd(phi).equals(BigInteger.ONE)) || e.compareTo(BigInteger.ONE)<=0 || e.compareTo(phi)>=0);


        return e;

    }

    public BigInteger find_d(BigInteger phi, BigInteger e){
        BigInteger d= e.modInverse(phi);

        return d;

    }

    public void createthestuff (int bitlength){
        BigInteger n= Make_n(bitlength);

        BigInteger phi = findphi(thep, theq, n);
        BigInteger e = Make_e(phi, bitlength);
        BigInteger d = find_d(phi, e);
        this.publickey = new RSAKey(n, d, e, bitlength, true);
        this.privatekey = new RSAKey(n, d, e, bitlength, false);
        System.out.println("this is the public key you just created "+ publickey.toString());
        System.out.println("this is the private key you just created "+privatekey.toString());


    }



    public BigInteger encrypt (BigInteger S, RSAKey pubkey){  //for testing only
        BigInteger encS = S.modPow(pubkey.returne(), pubkey.returnn());
        return encS;

    }

    public BigInteger decrypt (BigInteger M, RSAKey privkey){//for testing only
        BigInteger decM = M.modPow(privkey.returnd(), privkey.returnn());
        return decM;

    }

    public String fullencrypt(String message, RSAKey pubkey){
        byte[] s1 = message.getBytes();

        BitSet s2=  BitSet.valueOf(s1);

        long[] s3 = s2.toLongArray();


        int i;
        BigInteger[]s4= new BigInteger[s3.length];
        for(i=0; i<s3.length; i++){
            long x = s3[i];
            BigInteger y=BigInteger.valueOf(x);
            s4[i]=y;
        }

        String[]s5=new String[s4.length];
        int q;
        String finalmessage = "";
        for(q=0; q<s4.length; q++){
            s5[q]= s4[q].modPow(pubkey.returne(), pubkey.returnn()).toString();
            finalmessage = finalmessage+s5[q]+",";
        }
        return finalmessage;

    }

    public String fulldecrypt(String encmessage, RSAKey privkey){
        String[] m1=encmessage.split(",");
        int i;
        long [] m2 = new long[m1.length];


        for(i=0; i<m1.length;i++){
            String x =m1[i];
            BigInteger y = new BigInteger(x);
            BigInteger z=y.modPow(privkey.returnd(), privkey.returnn());
            long w = z.longValueExact();

            m2[i]=w;
        }

        BitSet m3=BitSet.valueOf(m2);
        byte[] m4 = m3.toByteArray();
        String m5=  new String(m4);
        return m5;

    }

    public RSAKey returnpub(){
        return this.publickey;
    }
    public RSAKey returnpriv(){
        return this.privatekey;
    }






}
