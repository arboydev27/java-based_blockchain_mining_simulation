
import java.util.Date;

public class Block {
	
	public String hash;
	public String previousHash;
	private String data; // The data itself, can be any simple message.
	private long timeStamp; //As no.of milliseconds since 1/1/1970 from the Date class
	private int nonce;
	
	// Block Constructor
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); // Making sure we do this after we set the other values
	}
	
	// Calculate new hash based on block contents
	public String calculateHash() {
		String calculatedhash =  StringUtil.applySha256(
				previousHash +
				Long.toString(timeStamp) + 
				Integer.toString(nonce) +
				data
				);
		return calculatedhash;
	}
	
	// mineBlock takes in a parameter int difficulty, which is the number of 0's miners must solve
	// the higher the 0's like 42,234 or 256,231 the more complex and time consuming it takes to mine a block
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); // Create a string with difficulty * "0"
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash + "\n");
	}
	
}
