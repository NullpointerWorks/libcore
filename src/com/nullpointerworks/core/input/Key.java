/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.core.input;

/**
 * Contains static some integer ASCII members which are of common usage in 
 * games. This is not a comprehensive collection of all ASCII/UTF character 
 * codes, just the ones that are common such as letters (A-Z) and numbers 
 * (0-9), some control keys (CTRL, ALT, ESC, etc.), arrow keys and F1-10 keys.
 * 
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class Key 
{
	/**
	 * Returns true if the given key code is a letter in the alphabet. The 
	 * ASCII codes for letters is from 65 until and including 90 for upper case 
	 * letters, and from 97 until and including 122 for lower case letters.
	 * @param code - the integer key code
	 * @return true if the given code is a letter
	 * @since 1.0.0
	 */
	public static boolean isLetter(int code)
	{
		return (code >= 65 && code <= 90) || (code >= 97 && code <= 122);
	}
	
	/**
	 * Returns true if the given keycode is a number. The ASCII codes 
	 * for numbers is from 48 until and including 57.
	 * @param code - the integer keycode
	 * @return true if the given code is a number
	 * @since 1.0.0
	 */
	public static boolean isNumber(int code)
	{
		return (code >= 48 && code <= 57);
	}
	
	/**
	 * Returns true for the following non-shifted punctuation markers;<br>
	 * <pre>
	 * ` (= 96) 
	 * ] (= 93) 
	 * \ (= 92) 
	 * [ (= 91) 
	 * = (= 61) 
	 * ; (= 59) 
	 * / (= 47) 
	 * . (= 46) 
	 * - (= 45) 
	 * , (= 44) 
	 * * (= 42) 
	 * ' (= 39)</pre>
	 * @param code - the integer key code
	 * @return true for non-shifted punctuation markers
	 * @since 1.0.0
	 */
	public static boolean isPunctuation(int code)
	{
		switch(code)
		{
		case 96:// `
		case 93:// ]
		case 92:// \
		case 91:// [
		case 61:// =
		case 59:// ;
		case 47:// /
		case 46:// .
		case 45:// -
		case 44:// ,
		case 42:// *
		case 39:// '
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the string representation of the given key code.
	 * @return the string representation of the given key code
	 * @param code - the integer key code
	 * @since 1.0.0
	 */
	public static String toString(int code)
	{
		return ""+((char)code);
	}
	
	/**
	 * Returns the shifted key code for the given character code if it can be shifted.
	 * @param code - the integer key code
	 * @return the key code of the character when shifted
	 * @since 1.0.0
	 */
	public static int shift(int code)
	{
		if(isLetter(code))
			return code + 32;
		
		if(isNumber(code)) 
		{
			switch(code)
			{
			case 48: return 41; // )
			case 49: return 33; // !
			case 50: return 64; // @
			case 51: return 35; // #
			case 52: return 36; // $
			case 53: return 37; // %
			case 54: return 94; // ^
			case 55: return 38; // &
			case 56: return 42; // *
			case 57: return 40; // (
			}
		}
		
		switch(code)
		{
		case 96: return 126; // ~
		case 45: return 95; // _
		case 61: return 43; // +
		case 91: return 123; // {
		case 92: return 124; // |
		case 93: return 125; // }
		case 59: return 58; // :
		case 39: return 34; // "
		case 44: return 60; // <
		case 46: return 62; // >
		case 47: return 63; // ?
		}
		
		return ERROR;
	}
	
	// error code
	public final static int ERROR 	= -1;
	
	// modifiers
	public final static int ESC 	= 27;
	public final static int TAB 	= 9;
	public final static int SHIFT 	= 16;
	public final static int CTRL 	= 17;
	public final static int ALT 	= 18;
	public final static int ENTER 	= 10;
	public final static int SPACE 	= 32;
	
	// special functions
	public final static int PAUSE 		= 19;
	public final static int BACKSPACE 	= 8;
	
	// function
	public final static int F1 		= 112; 
	public final static int F2 		= 113; 
	public final static int F3 		= 114; 
	public final static int F4 		= 115; 
	public final static int F5 		= 116; 
	public final static int F6 		= 117; 
	public final static int F7 		= 118; 
	public final static int F8 		= 119; 
	public final static int F9 		= 120;
	public final static int F10 	= 121;
	
	// arrow
	public final static int DOWN 	= 40;
	public final static int RIGHT 	= 39;
	public final static int UP 		= 38;
	public final static int LEFT 	= 37;
	
	// numbers
	public final static int num0 = 48;
	public final static int num1 = 49;
	public final static int num2 = 50;
	public final static int num3 = 51;
	public final static int num4 = 52;
	public final static int num5 = 53;
	public final static int num6 = 54;
	public final static int num7 = 55;
	public final static int num8 = 56;
	public final static int num9 = 57;
	
	// alphabet
	public final static int A = 65;
	public final static int B = 66;
	public final static int C = 67;
	public final static int D = 68;
	public final static int E = 69;
	public final static int F = 70;
	public final static int G = 71;
	public final static int H = 72;
	public final static int I = 73;
	public final static int J = 74;
	public final static int K = 75;
	public final static int L = 76;
	public final static int M = 77;
	public final static int N = 78;
	public final static int O = 79;
	public final static int P = 80;
	public final static int Q = 81;
	public final static int R = 82;
	public final static int S = 83;
	public final static int T = 84;
	public final static int U = 85;
	public final static int V = 86;
	public final static int W = 87;
	public final static int X = 88;
	public final static int Y = 89;
	public final static int Z = 90;
}
