package mmo.tools.script.xasm;
/**
 * Author: cuirongzhou
 * Date: 2007-10-29
 * Time: 16:16:49
 */
public class Lib
{

    /******************************************************************************************
    *
    *	IsCharWhitespace ()
    *
    *	Returns a nonzero if the given character is whitespace, or zero otherwise.
    */

    public static final boolean IsCharWhitespace ( char cChar )
    {
        // Return true if the character is a space or tab.

        return( cChar == ' ' || cChar == '\t' );        
    }

    /******************************************************************************************
    *
    *	IsCharNumeric ()
    *
    *	Returns a nonzero if the given character is numeric, or zero otherwise.
    */

    public static final boolean IsCharNumeric ( char cChar )
    {
        // Return true if the character is between 0 and 9 inclusive.

        return ( cChar >= '0' && cChar <= '9' );
    }

    /******************************************************************************************
    *
    *	IsCharIdentifier ()
    *
    *	Returns a nonzero if the given character is part of a valid identifier, meaning it's an
    *	alphanumeric or underscore. Zero is returned otherwise.
    */

    public static final boolean  IsCharIdent ( char cChar )
    {
        // Return true if the character is between 0 or 9 inclusive or is an uppercase or
        // lowercase letter or underscore

        return ( ( cChar >= '0' && cChar <= '9' ) ||
             ( cChar >= 'A' && cChar <= 'Z' ) ||
             ( cChar >= 'a' && cChar <= 'z' ) ||
             cChar == '_' );
    }

    /******************************************************************************************
    *
    *	IsCharDelimiter ()
    *
    *	Return a nonzero if the given character is a token delimeter, and return zero otherwise
    */

    public static final boolean  IsCharDelimiter ( char cChar )
    {
        // Return true if the character is a delimiter

        return ( cChar == ':' || cChar == ',' || cChar == '"' ||
             cChar == '[' || cChar == ']' ||
             cChar == '{' || cChar == '}' ||
             IsCharWhitespace ( cChar ) || cChar == '\n' );
    }

    /******************************************************************************************
    *
    *	TrimWhitespace ()
    *
    *	Trims whitespace off both sides of a string.
    */

    public final static void TrimWhitespace ( char[] pstrString )
    {
        int iStringLength = pstrString.length;
        int iPadLength;
        int iCurrCharIndex;

        if ( iStringLength > 1 )
        {
            // First determine whitespace quantity on the left

            for ( iCurrCharIndex = 0; iCurrCharIndex < iStringLength; ++ iCurrCharIndex )
                if ( ! IsCharWhitespace ( pstrString [ iCurrCharIndex ] ) )
                    break;

            // Slide string to the left to overwrite whitespace

            iPadLength = iCurrCharIndex;
            if ( iPadLength != 0)
            {
                for ( iCurrCharIndex = iPadLength; iCurrCharIndex < iStringLength; ++ iCurrCharIndex )
                    pstrString [ iCurrCharIndex - iPadLength ] = pstrString [ iCurrCharIndex ];

                for ( iCurrCharIndex = iStringLength - iPadLength; iCurrCharIndex < iStringLength; ++ iCurrCharIndex )
                    pstrString [ iCurrCharIndex ] = ' ';
            }

            // Terminate string at the start of right hand whitespace

            for ( iCurrCharIndex = iStringLength - 1; iCurrCharIndex > 0; -- iCurrCharIndex )
            {
                if ( ! IsCharWhitespace ( pstrString [ iCurrCharIndex ] ) )
                {
                    pstrString [ iCurrCharIndex + 1 ] = '\0';
                    break;
                }
            }
        }
    }

    /******************************************************************************************
    *
    *	StripComments ()
    *
    *	Strips the comments from a given line of source code, ignoring comment symbols found
    *	inside strings. The new string is shortended to the index of the comment symbol
    *	character.
    */

    public final static String StripComments ( char[] pstrSourceLine )
    {
        int iCurrCharIndex;
        boolean iInString;

        // Scan through the source line and terminate the string at the first semicolon

        iInString = false;
        for ( iCurrCharIndex = 0; iCurrCharIndex < pstrSourceLine.length - 1; ++ iCurrCharIndex )
        {
            // Look out for strings; they can contain semicolons

            if ( pstrSourceLine [ iCurrCharIndex ] == '"' )
                if ( iInString )
                    iInString = false;
                else
                    iInString = true;

            // If a non-string semicolon is found, terminate the string at it's position

            if ( pstrSourceLine [ iCurrCharIndex ] == ';' )
            {
                if ( ! iInString )
                {
                    pstrSourceLine [ iCurrCharIndex ] = '\n';
//                    pstrSourceLine [ iCurrCharIndex + 1 ] = '\0';
                    return new String(pstrSourceLine,0,iCurrCharIndex+1);
                }
            }
        }
        return new String(pstrSourceLine);
    }

    /******************************************************************************************
    *
    *	IsStringWhitespace ()
    *
    *	Returns a nonzero if the given string is whitespace, or zero otherwise.
    */

    public static final boolean IsStringWhitespace ( char [] pstrString )
    {
        // If the string is NULL, return false

        if ( pstrString == null)
            return false;

        // If the length is zero, it's technically whitespace

        if ( pstrString.length == 0 )
            return true;

        // Loop through each character and return false if a non-whitespace is found

        for ( int iCurrCharIndex = 0; iCurrCharIndex < ( pstrString.length ); ++ iCurrCharIndex )
            if ( ! IsCharWhitespace ( pstrString [ iCurrCharIndex ] ) && pstrString [ iCurrCharIndex ] != '\n' )
                return false;

        // Otherwise return true

        return true;
    }

    /******************************************************************************************
    *
    *	IsStringIdentifier ()
    *
    *	Returns a nonzero if the given string is composed entirely of valid identifier
    *	characters and begins with a letter or underscore. Zero is returned otherwise.
    */

    public static final boolean IsStringIdent ( char[] pstrString )
    {
        // If the string is NULL return false

        if ( pstrString == null)
            return false;

        // If the length of the string is zero, it's not a valid identifier

        if ( ( pstrString ).length == 0 )
            return false;

        // If the first character is a number, it's not a valid identifier

        if ( pstrString [ 0 ] >= '0' && pstrString [ 0 ] <= '9' )
            return false;

        // Loop through each character and return zero upon encountering the first invalid identifier
        // character

        for ( int iCurrCharIndex = 0; iCurrCharIndex < ( pstrString ).length; ++ iCurrCharIndex )
            if ( ! IsCharIdent ( pstrString [ iCurrCharIndex ] ) )
                return false;

        // Otherwise return true

        return true;
    }

    /******************************************************************************************
    *
    *	IsStringInteger ()
    *
    *	Returns a nonzero if the given string is composed entire of integer characters, or zero
    *	otherwise.
    */

    public static final boolean IsStringInteger ( char[] pstrString )
    {
        // If the string is NULL, it's not an integer

        if ( pstrString == null)
            return false;

        // If the string's length is zero, it's not an integer

        if ( ( pstrString ).length == 0 )
            return false;

        int iCurrCharIndex;

        // Loop through the string and make sure each character is a valid number or minus sign

        for ( iCurrCharIndex = 0; iCurrCharIndex < ( pstrString ).length; ++ iCurrCharIndex )
            if ( ! IsCharNumeric ( pstrString [ iCurrCharIndex ] ) && ! ( pstrString [ iCurrCharIndex ] == '-' ) )
                return false;

        // Make sure the minus sign only occured at the first character

        for ( iCurrCharIndex = 1; iCurrCharIndex < ( pstrString ).length; ++ iCurrCharIndex )
            if ( pstrString [ iCurrCharIndex ] == '-' )
                return false;

        return true;
    }

    /******************************************************************************************
    *
    *	IsStringFloat ()
    *
    *	Returns a nonzero if the given string is composed entire of float characters, or zero
    *	otherwise.
    */

    public static final boolean IsStringFloat( char[] pstrString )
    {
        if ( pstrString == null)
            return false;

        if ( ( pstrString ).length == 0 )
            return false;

        // First make sure we've got only numbers and radix points

        int iCurrCharIndex;

        for ( iCurrCharIndex = 0; iCurrCharIndex < ( pstrString ).length; ++ iCurrCharIndex )
            if ( ! IsCharNumeric ( pstrString [ iCurrCharIndex ] ) && ! ( pstrString [ iCurrCharIndex ] == '.' ) && ! ( pstrString [ iCurrCharIndex ] == '-' ) )
                return false;

        // Make sure only one radix point is present

        boolean iRadixPointFound = false;

        for ( iCurrCharIndex = 0; iCurrCharIndex < ( pstrString ).length; ++ iCurrCharIndex )
            if ( pstrString [ iCurrCharIndex ] == '.' )
                if ( iRadixPointFound )
                    return false;
                else
                    iRadixPointFound = true;

        // Make sure the minus sign only appears in the first character

        for ( iCurrCharIndex = 1; iCurrCharIndex < ( pstrString ).length; ++ iCurrCharIndex )
            if ( pstrString [ iCurrCharIndex ] == '-' )
                return false;

        // If a radix point was found, return true; otherwise, it must be an integer so return false

        if ( iRadixPointFound )
            return true;
        else
            return false;
    }


}
