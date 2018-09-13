package xyz.varad.funpl.util;

import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import xyz.varad.funpl.funPL.Block;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.Function;
import xyz.varad.funpl.funPL.ReturnStatement;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.funPL.Symbol;
import xyz.varad.funpl.funPL.SymbolRef;
import xyz.varad.funpl.funPL.Value;

@SuppressWarnings("all")
public class FunPLModelUtil {
  public boolean isDefinedBefore(final SymbolRef _sym) {
    return this.symbolsDefinedBefore(((Expression) _sym)).contains(_sym.getSymbol());
  }
  
  public boolean isDefinedBefore(final Symbol _sym) {
    return this.containsSimilar(this.symbolsDefinedBefore(_sym), _sym);
  }
  
  public List<Symbol> symbolsDefinedBefore(final Expression _exp) {
    final BasicEList<Symbol> before = new BasicEList<Symbol>();
    final EObject outerElement = this.<FunProgram>walkUpContainmentUntilContainerIs(_exp, FunProgram.class);
    final List<Symbol> allGlobalSyms = FunPLModelUtil.symbols(EcoreUtil2.<FunProgram>getContainerOfType(_exp, FunProgram.class));
    before.addAll(allGlobalSyms.subList(0, allGlobalSyms.indexOf(outerElement)));
    if ((outerElement != _exp)) {
      before.add(((Symbol) outerElement));
    }
    final Function contFunc = EcoreUtil2.<Function>getContainerOfType(_exp, Function.class);
    if ((contFunc != null)) {
      before.addAll(contFunc.getParams());
      final EObject topLevel = this.<Block>walkUpContainmentUntilContainerIs(_exp, Block.class);
      final List<Statement> fullList = FunPLModelUtil.statements(contFunc);
      before.addAll(EcoreUtil2.<Symbol>typeSelect(fullList.subList(0, fullList.indexOf(topLevel)), Symbol.class));
    }
    return before;
  }
  
  public List<Symbol> symbolsDefinedBefore(final Symbol _sym) {
    final BasicEList<Symbol> before = new BasicEList<Symbol>();
    final EObject outerElement = this.<FunProgram>walkUpContainmentUntilContainerIs(_sym, FunProgram.class);
    final List<Symbol> allGlobalSyms = FunPLModelUtil.symbols(EcoreUtil2.<FunProgram>getContainerOfType(_sym, FunProgram.class));
    before.addAll(allGlobalSyms.subList(0, allGlobalSyms.indexOf(outerElement)));
    if ((outerElement != _sym)) {
      before.add(((Symbol) outerElement));
    }
    final Function contFunc = EcoreUtil2.<Function>getContainerOfType(_sym, Function.class);
    if (((contFunc != null) && (!Objects.equal(contFunc, _sym)))) {
      final List<Symbol> fullList = FunPLModelUtil.symbols(contFunc);
      before.addAll(fullList.subList(0, fullList.indexOf(_sym)));
    }
    return before;
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
  
  public static List<ReturnStatement> returnStatements(final Function _f) {
    Block _body = null;
    if (_f!=null) {
      _body=_f.getBody();
    }
    final List<ReturnStatement> rets = EcoreUtil2.<ReturnStatement>getAllContentsOfType(_body, ReturnStatement.class);
    return rets;
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
  
  private boolean _containsSimilar(final List<Symbol> symsSet, final Symbol _sym) {
    for (final Symbol elem : symsSet) {
      String _name = elem.getName();
      String _name_1 = _sym.getName();
      boolean _equals = Objects.equal(_name, _name_1);
      if (_equals) {
        return true;
      }
    }
    return false;
  }
  
  private boolean _containsSimilar(final List<Symbol> symsSet, final Function _func) {
    for (final Symbol elem : symsSet) {
      if (((Objects.equal(elem.getName(), _func.getName()) && (elem instanceof Function)) && (((Function) elem).getParams().size() == _func.getParams().size()))) {
        return true;
      }
    }
    return false;
  }
  
  private boolean containsSimilar(final List<Symbol> symsSet, final Symbol _func) {
    if (_func instanceof Function) {
      return _containsSimilar(symsSet, (Function)_func);
    } else if (_func != null) {
      return _containsSimilar(symsSet, _func);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(symsSet, _func).toString());
    }
  }
}
