package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A simple parser for {@link Sexp}s.  The details of how this class
 * works are not critical.
 */
public class Parser {
  /**
   * Parses the given string as an s-expression.  The expected syntax is
   *
   * <ul>
   * <li><code>true</code> or <code>false</code> for Boolean values</li>
   * <li><code>double</code> Numeric literals</li>
   * <li><code>"Quoted strings"</code></li>
   * <li>Identifiers</li>
   * <li><code>(<i>sexp</i> ...)</code> for nested {@link Sexp}s</li>
   * </ul>
   *
   * @param in The string to be parsed
   * @return The resulting s-expression
   * @throws IllegalArgumentException if the argument cannot be parsed properly and completely
   */
  public static Sexp parse(String in) throws IllegalArgumentException {
    try {
      Scanner s = new Scanner(in);
      // This pattern instructs the scanner to produce new next() results by
      // splitting the input after each space, before each left or right parenthesis,
      // and before or after each double-quote.
      s.useDelimiter(Pattern.compile("\\s+|(?=[()])|(?<=[()])|(?=[\"])|(?<=[\"])"));
      Sexp parsed = parse(s);
      if (s.hasNext()) {
        throw new IllegalArgumentException(
                "The string contains leftover input after the first s-expression");
      } else {
        return parsed;
      }
    }
    catch (IllegalArgumentException a) {
      throw new IllegalArgumentException("Badly formatted sexp: " + in + "\n" + a);
    }
  }

  private static Sexp parse(Scanner scan) {
    if (scan.hasNextDouble()) {
      return new SNumber(scan.nextDouble());
    } else if (scan.hasNextInt()) {
      return new SNumber(scan.nextInt());
    } else if (scan.hasNext()) {
      String s = scan.next();
      switch (s) {
        case "true":
          return new SBoolean(true);
        case "false":
          return new SBoolean(false);
        case "(":
          List<Sexp> parts = parseAll(scan);
          if (scan.hasNext("\\)")) {
            scan.next();
            return new SList(parts);
          } else {
            throw new IllegalArgumentException("Unclosed open paren");
          }
        case ")":
          throw new IllegalArgumentException("Unmatched close paren");
        case "\"":
          Pattern delim = scan.delimiter();
          // When we're trying to read in a Java string, which can have escape sequences,
          // we need to change the delimiter pattern to split the string differently:
          // just before or just after a backslash, or just before or just after a quote
          scan.useDelimiter(Pattern.compile("(?=\\\\)|(?<=\\\\)|(?=\")|(?<=\")"));
          StringBuilder sb = new StringBuilder();
          boolean escapeActive = false;
          while (scan.hasNext()) {
            String piece = scan.next();
            switch (piece) {
              case "\\":
                if (escapeActive) {
                  sb.append(piece);
                }
                escapeActive = !escapeActive;
                break;
              case "\"":
                if (escapeActive) {
                  sb.append(piece);
                  escapeActive = false;
                } else {
                  // restore the original delimiter pattern
                  scan.useDelimiter(delim);
                  return new SString(sb.toString());
                }
                break;
              default:
                sb.append(piece);
                escapeActive = false;
                break;
            }
          }
          throw new IllegalArgumentException("Unclosed string");
        default:
          return new SSymbol(s);
      }
    }
    throw new IllegalArgumentException("No input found to parse");
  }

  private static List<Sexp> parseAll(Scanner scan) {
    java.util.List<Sexp> parts = new ArrayList<>();
    while (scan.hasNext()) {
      if (scan.hasNext("\\)")) {
        return parts;
      }
      parts.add(parse(scan));
    }
    return parts;
  }
}
