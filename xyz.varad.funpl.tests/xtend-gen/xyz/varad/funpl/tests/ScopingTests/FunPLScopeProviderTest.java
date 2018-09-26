package xyz.varad.funpl.tests.ScopingTests;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.varad.funpl.funPL.Declaration;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunPLPackage;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.FunctionDeclaration;
import xyz.varad.funpl.funPL.IntegerLiteralExpression;
import xyz.varad.funpl.funPL.ReferenceExpression;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.funPL.ValueDeclaration;
import xyz.varad.funpl.tests.FunPLInjectorProvider;
import xyz.varad.funpl.util.FunPLModelUtil;

@RunWith(XtextRunner.class)
@InjectWith(FunPLInjectorProvider.class)
@SuppressWarnings("all")
public class FunPLScopeProviderTest {
  @Inject
  @Extension
  private ParseHelper<FunProgram> _parseHelper;
  
  @Inject
  @Extension
  private IScopeProvider _iScopeProvider;
  
  @Test
  public void scopeForReferenceExpression() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;");
      _builder.newLine();
      _builder.append("var j = i;");
      _builder.newLine();
      _builder.append("function A(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function myFunc(int p){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var f = true;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = 1;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("k = 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("myFunc(3);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var q = false;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function myFunc2() { }");
      _builder.newLine();
      Statement _get = FunPLModelUtil.statements(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)).get(1)).get(3);
      final Procedure1<Statement> _function = (Statement it) -> {
        final Function1<IEObjectDescription, QualifiedName> _function_1 = (IEObjectDescription it_1) -> {
          return it_1.getName();
        };
        final String scope = IterableExtensions.join(IterableExtensions.<IEObjectDescription, QualifiedName>map(this._iScopeProvider.getScope(((Expression) it), FunPLPackage.eINSTANCE.getReferenceExpression_Declaration()).getAllElements(), _function_1), ", ");
        Assert.assertEquals("f, k, p, i, j, A", scope);
      };
      ObjectExtensions.<Statement>operator_doubleArrow(_get, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void declarationMasking() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var int i = -1;");
      _builder.newLine();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var int i = 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("i;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      Statement _get = FunPLModelUtil.statements(IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)))).get(1);
      final Procedure1<ReferenceExpression> _function = (ReferenceExpression it) -> {
        Declaration _declaration = it.getDeclaration();
        Expression _expression = ((ValueDeclaration) _declaration).getExpression();
        Assert.assertEquals(5, ((IntegerLiteralExpression) _expression).getValue());
      };
      ObjectExtensions.<ReferenceExpression>operator_doubleArrow(
        ((ReferenceExpression) _get), _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
