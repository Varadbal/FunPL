package xyz.varad.funpl.tests;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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
import xyz.varad.funpl.FunPLModelUtil;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunPLPackage;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.Function;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.tests.FunPLInjectorProvider;

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
  public void testScopeProvider() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("var j = i;");
      _builder.newLine();
      _builder.append("function myFunc(p){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = 1;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("k = 5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      Statement _last = IterableExtensions.<Statement>last(FunPLModelUtil.statements(IterableExtensions.<Function>head(FunPLModelUtil.functions(this._parseHelper.parse(_builder)))));
      final Procedure1<Expression> _function = (Expression it) -> {
        this.assertScope(it, FunPLPackage.eINSTANCE.getSymbolRef_Symbol(), 
          "p, k, i, j, myFunc, myFunc.p, myFunc.k");
      };
      ObjectExtensions.<Expression>operator_doubleArrow(
        ((Expression) _last), _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertScope(final EObject context, final EReference reference, final CharSequence expected) {
    final Function1<IEObjectDescription, QualifiedName> _function = (IEObjectDescription it) -> {
      return it.getName();
    };
    Assert.assertEquals(expected.toString(), 
      IterableExtensions.join(IterableExtensions.<IEObjectDescription, QualifiedName>map(this._iScopeProvider.getScope(context, reference).getAllElements(), _function), ", "));
  }
}
