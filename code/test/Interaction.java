/**
 * An interaction with the user consists of some input to send the program and some output to
 * expect.  We represent it as an object that takes in two StringBuilders and produces the intended
 * effects on them
 */
interface Interaction {
  /**
   * Applies two StringBuilders and produces the intended effects on them.
   */
  void apply(StringBuilder in, StringBuilder out);
}