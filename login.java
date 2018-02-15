import java.security.*;
public class login {
    public static String hash(String input) throws Exception {
        String original = input;
	MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(original.getBytes());
	byte[] digest = md.digest();
	StringBuffer sb = new StringBuffer();
	for (byte b : digest) {
		sb.append(String.format("%02x", b & 0xff));
	}
	return sb.toString();
    }
}