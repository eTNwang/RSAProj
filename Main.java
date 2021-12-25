//Eric


public class Main {


    public static void main(String[] args) {

	    // generates a matching public key and private key pair
	    KeyGenerator somekeygenerator = new KeyGenerator();
		somekeygenerator.createthestuff(100);

		//encrypts with matching public key and intended message
	    RSAKey theirpubkey  = new RSAKey("100;658831982149199566051170872371;188359883405405763774713191187", true);
	   	String M = somekeygenerator.fullencrypt("Boston, New Hampshire and New York", theirpubkey);
	    System.out.println("this is the message you just encrypted "+M);

		//cracks the private key given a public key
		String otherkeystring = somekeygenerator.crack("50;834338849077271;155339224698193");

	    // decrypts with a matching private key and the encrypted message
		RSAKey theotherkey = new RSAKey(otherkeystring, false);
		String S = somekeygenerator.fulldecrypt("825380311422829", theotherkey);
	    System.out.println("this is the decoded message: " + S);










    }
}


//my privkey = 100;658831982149199566051170872371;188359883405405763774713191187
//peter pubkey = 80;780551972066976337073983;689008579032551531198265
//Caleb pubkey = 100;133710125440992683140983708607;71835494637365831469714202261
//Micah pubkey =