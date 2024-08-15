
import java.security.MessageDigest;

public class StringUtil {
	
	// uses a Cryprographic algorithm called SHA256
	// Applies the algorithm to a string and returns the result,.
	
	public static String applySha256(String input) {
		try	{
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// Applies sha256 to the output.
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer(); // The hash will be contained here
			
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				
				if (hex.length() == 1) hexString.append('0');
					hexString.append(hex);
			
			}
			return hexString.toString();// Return the generated string as a String
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
