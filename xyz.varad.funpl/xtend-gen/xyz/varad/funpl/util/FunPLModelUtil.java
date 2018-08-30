package xyz.varad.funpl.util;

import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import xyz.varad.funpl.funPL.Block;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.Function;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.funPL.Symbol;
import xyz.varad.funpl.funPL.SymbolRef;
import xyz.varad.funpl.funPL.Value;

@SuppressWarnings("all")
public class FunPLModelUtil {
  /**
   * TODO restructure
   * def isDefinedBefore(DefinitionRef defRef)
   * def definedBefore(Statement s)
   * ^Should be enough, as defRefs and Expressions are Statements too^
   */
  public boolean isDefinedBefore(final SymbolRef _sym) {
    return this.symbolsDefinedBefore(((Expression) _sym)).contains(_sym.getSymbol());
  }
  
  public Set<Symbol> symbolsDefinedBefore(final Expression _exp) {
    final Set<Symbol> before = this.symbolsDefinedGlobally(EcoreUtil2.<FunProgram>getContainerOfType(_exp, FunProgram.class));
    final Function contFunc = EcoreUtil2.<Function>getContainerOfType(_exp, Function.class);
    if ((contFunc != null)) {
      before.addAll(contFunc.getParams());
      final EObject topLevel = this.<Block>walkUpContainmentUntilContainerIs(_exp, Block.class);
      final List<Statement> fullList = FunPLModelUtil.statements(contFunc);
      before.addAll(EcoreUtil2.<Symbol>typeSelect(fullList.subList(0, fullList.indexOf(topLevel)), Symbol.class));
    }
    return before;
  }
  
  public Set<Symbol> symbolsDefinedBefore(final Symbol _sym) {
    final Set<Symbol> before = this.symbolsDefinedGlobally(EcoreUtil2.<FunProgram>getContainerOfType(_sym, FunProgram.class));
    final Function contFunc = EcoreUtil2.<Function>getContainerOfType(_sym, Function.class);
    if ((contFunc != null)) {
      final List<Symbol> fullList = FunPLModelUtil.symbols(contFunc);
      before.addAll(fullList.subList(0, fullList.indexOf(_sym)));
    }
    return before;
  }
  
  public Set<Symbol> symbolsDefinedGlobally(final FunProgram _fp) {
    return IterableExtensions.<Symbol>toSet(FunPLModelUtil.symbols(_fp));
  }
  
  public static List<Function> functions(final FunProgram f) {
    return EcoreUtil2.<Function>typeSelect(f.getElements(), Function.class);
  }
  
  public static List<Value> values(final FunProgram f) {
    return EcoreUtil2.<Value>typeSelect(f.getElements(), Value.class);
  }
  
  public static List<Symbol> symbols(final FunProgram f) {
    return EcoreUtil2.<Symbol>typeSelect(f.getElements(), Symbol.class);
  }
  
  public static List<Value> values(final Function f) {
    return FunPLModelUtil.values(f.getBody());
  }
  
  public static List<Symbol> symbols(final Function f) {
    final BasicEList<Symbol> p = new BasicEList<Symbol>();
    p.addAll(f.getParams());
    p.addAll(FunPLModelUtil.values(f));
    return ((List<Symbol>) p);
  }
  
  public static List<Statement> statements(final Function f) {
    return IterableExtensions.<Statement>toList(f.getBody().getStatements());
  }
  
  public static List<Value> values(final Block b) {
    return EcoreUtil2.<Value>typeSelect(b.getStatements(), Value.class);
  }
  
  public static List<Statement> statements(final Block b) {
    EList<Statement> _statements = b.getStatements();
    return ((List<Statement>) _statements);
  }
  
  /**
   * Walk up until the "ECONTAINER is of type" (vs. ECoreUtil2.getContainerOfType gets "itself is of type")
   */
  private <T extends EObject> EObject walkUpContainmentUntilContainerIs(final EObject start, final Class<T> goalContainerType) {
    EObject e = ((EObject) start);
    for (; (e != null); e = e.eContainer()) {
      boolean _isInstance = goalContainerType.isInstance(e.eContainer());
      if (_isInstance) {
        return e;
      }
    }
    return null;
  }
}
