package Elements;

public class SmallCaps {
	public static String smallCaps(String text){
		StringBuilder result = new StringBuilder();
		for(char letter : text.toCharArray()){
			switch (letter) {
				case 'a': case 'A': result.append('ᴀ'); break;
				case 'b': case 'B': result.append('ʙ'); break;
				case 'c': case 'C': result.append('ᴄ'); break;
				case 'd': case 'D': result.append('ᴅ'); break;
				case 'e': case 'E': result.append('ᴇ'); break;
				case 'f': case 'F': result.append('ғ'); break;
				case 'g': case 'G': result.append('ɢ'); break;
				case 'h': case 'H': result.append('ʜ'); break;
				case 'i': case 'I': result.append('ɪ'); break;
				case 'j': case 'J': result.append('ᴊ'); break;
				case 'k': case 'K': result.append('ᴋ'); break;
				case 'l': case 'L': result.append('ʟ'); break;
				case 'm': case 'M': result.append('ᴍ'); break;
				case 'n': case 'N': result.append('ɴ'); break;
				case 'o': case 'O': result.append('ᴏ'); break;
				case 'p': case 'P': result.append('ᴘ'); break;
				case 'q': case 'Q': result.append('ǫ'); break;
				case 'r': case 'R': result.append('ʀ'); break;
				case 's': case 'S': result.append('s'); break;
				case 't': case 'T': result.append('ᴛ'); break;
				case 'u': case 'U': result.append('ᴜ'); break;
				case 'v': case 'V': result.append('ᴠ'); break;
				case 'w': case 'W': result.append('ᴡ'); break;
				case 'x': case 'X': result.append('x'); break;
				case 'y': case 'Y': result.append('ʏ'); break;
				case 'z': case 'Z': result.append('ᴢ'); break;
				case '0': result.append('₀'); break;
				case '1': result.append('₁'); break;
				case '2': result.append('₂'); break;
				case '3': result.append('₃'); break;
				case '4': result.append('₄'); break;
				case '5': result.append('₅'); break;
				case '6': result.append('₆'); break;
				case '7': result.append('₇'); break;
				case '8': result.append('₈'); break;
				case '9': result.append('₉'); break;
				default: result.append(letter); break;
			}
		}
		return result.toString();
	}
}