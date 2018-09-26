package xyz.varad.funpl.util;

import xyz.varad.funpl.funPL.BooleanTypeDefinition;
import xyz.varad.funpl.funPL.IntegerTypeDefinition;
import xyz.varad.funpl.funPL.Type;
import xyz.varad.funpl.funPL.VoidTypeDefinition;

@SuppressWarnings("all")
public class FunPLTypingUtil {
  public static boolean isInteger(final Type t) {
    return (t instanceof IntegerTypeDefinition);
  }
  
  public static boolean isBoolean(final Type t) {
    return (t instanceof BooleanTypeDefinition);
  }
  
  public static boolean isVoid(final Type t) {
    return (t instanceof VoidTypeDefinition);
  }
}
