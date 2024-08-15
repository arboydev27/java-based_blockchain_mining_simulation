import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 6;
	
	public static void main(String[] args) {
		
		
		// Add the blocks to the blockchain ArrayList
		blockchain.add(new Block("Hiii Im the first block", "0"));
		System.out.println("Trying to Mine block 1....");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Yooo Im the second block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 2....");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Heyyyy Im the third block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 3....");
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
		
		
	}
	
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String( new char[difficulty]).replace('\0','0');
		
		// Loop through the blockchain to check the integrity of the hashes
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			// Comparing the registered hash and the calculated hash
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}
			
			//Compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			
			// Checking if the hash is solved
			if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}
		return true;
	}
	
}
