//Eric
import java.math.BigInteger;

public class RSAKey {

    private BigInteger n;
    private BigInteger d;
    private BigInteger e;
    private int bitlength;
    private boolean pubornot;

    public RSAKey(BigInteger somen, BigInteger somed, BigInteger somee, int bitlength, boolean pubstatus) {
        this.n=somen;
        this.d=somed;
        this.e=somee;
        this.bitlength=bitlength;
        this.pubornot=pubstatus;

    }
    public RSAKey(String formattedkey, boolean pubornot) {
        String[] splitkey=formattedkey.split(";");
        this.n=new BigInteger(splitkey[1]);
        if (pubornot==true){
            this.e=new BigInteger(splitkey[2]);
        }
        if (pubornot==false){
            this.d=new BigInteger(splitkey[2]);
        }

        this.bitlength=new Integer(splitkey[0]);
        this.pubornot= pubornot;

    }
    public BigInteger returne(){
        return this.e;
    }
    public BigInteger returnn(){
        return this.n;
    }
    public BigInteger returnd(){
        return this.d;
    }
    public String toString() {
        if (pubornot==true){
            return bitlength+";"+n.toString()+";"+e.toString();
        }
        else; {
            return bitlength+";"+n.toString()+";"+d.toString();

        }
    }




}
