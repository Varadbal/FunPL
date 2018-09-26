package xyz.varad.funpl.util;

import java.util.List;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.xtext.EcoreUtil2;
import xyz.varad.funpl.funPL.Block;
import xyz.varad.funpl.funPL.Declaration;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.FunctionDeclaration;
import xyz.varad.funpl.funPL.ReturnStatement;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.funPL.ValueDeclaration;

@SuppressWarnings("all")
public class FunPLModelUtil {
  public static List<Declaration> declarations(final FunProgram f) {
    return EcoreUtil2.<Declaration>typeSelect(f.getElements(), Declaration.class);
  }
  
  public static List<FunctionDeclaration> functionDeclarations(final FunProgram f) {
    return EcoreUtil2.<FunctionDeclaration>typeSelect(f.getElements(), FunctionDeclaration.class);
  }
  
  public static List<ValueDeclaration> valueDeclarations(final FunProgram f) {
    return EcoreUtil2.<ValueDeclaration>typeSelect(f.getElements(), ValueDeclaration.class);
  }
  
  public static List<ValueDeclaration> parameterDeclarations(final FunctionDeclaration f) {
    return f.getParameterList().getParameters();
  }
  
  public static List<ValueDeclaration> bodyValueDeclarations(final FunctionDeclaration f) {
    return FunPLModelUtil.valueDeclarations(f.getBody());
  }
  
  public static List<ValueDeclaration> valueDeclarations(final FunctionDeclaration f) {
    final BasicEList<ValueDeclaration> p = new BasicEList<ValueDeclaration>();
    p.addAll(FunPLModelUtil.parameterDeclarations(f));
    p.addAll(FunPLModelUtil.bodyValueDeclarations(f));
    return ((List<ValueDeclaration>) p);
  }
  
  public static List<Statement> statements(final FunctionDeclaration f) {
    return f.getBody().getStatements();
  }
  
  public static List<ReturnStatement> returnStatements(final FunctionDeclaration _f) {
    Block _body = null;
    if (_f!=null) {
      _body=_f.getBody();
    }
    final List<ReturnStatement> rets = EcoreUtil2.<ReturnStatement>getAllContentsOfType(_body, ReturnStatement.class);
    return rets;
  }
  
  public static List<ValueDeclaration> valueDeclarations(final Block b) {
    return EcoreUtil2.<ValueDeclaration>typeSelect(b.getStatements(), ValueDeclaration.class);
  }
}
