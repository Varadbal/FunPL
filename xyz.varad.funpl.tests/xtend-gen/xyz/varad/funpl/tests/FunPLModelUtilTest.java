package xyz.varad.funpl.tests;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
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
import xyz.varad.funpl.funPL.AbstractElement;
import xyz.varad.funpl.funPL.Definition;
import xyz.varad.funpl.funPL.DefinitionRef;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.Function;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.funPL.Value;
import xyz.varad.funpl.tests.FunPLInjectorProvider;

@RunWith(XtextRunner.class)
@InjectWith(FunPLInjectorProvider.class)
@SuppressWarnings("all")
public class FunPLModelUtilTest {
  @Inject
  @Extension
  private ParseHelper<FunProgram> _parseHelper;
  
  @Inject
  @Extension
  private FunPLModelUtil _funPLModelUtil;
  
  @Test
  public void testDefinedBefore() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;\t\t//(0)");
      _builder.newLine();
      _builder.append("var j = false;\t//(1)");
      _builder.newLine();
      _builder.append("var k = i + 5;\t//(2)");
      _builder.newLine();
      _builder.append("var l = m + 1;\t//(3)");
      _builder.newLine();
      _builder.append("var o = 3;\t\t//(4)");
      _builder.newLine();
      _builder.newLine();
      _builder.append("function myFunc(){\t//(5)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var p = 1;\t\t//(0)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;\t\t\t//(1)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var q = 8;\t\t//(2)");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        this.assertDefinedBeforeOutsideValue(it, 0, "");
        this.assertDefinedBeforeOutsideValue(it, 1, "i");
        this.assertDefinedBeforeOutsideValue(it, 2, "i,j");
        this.assertDefinedBeforeOutsideValue(it, 3, "i,j,k");
        this.assertDefinedBeforeOutsideValue(it, 4, "i,j,k,l");
        this.assertDefinedBeforeFunctionLocalStatement(it, 5, 0, "i,j,k,l,o");
        this.assertDefinedBeforeFunctionLocalStatement(it, 5, 1, "i,j,k,l,o,p");
        this.assertDefinedBeforeFunctionLocalStatement(it, 5, 2, "i,j,k,l,o,p");
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testIsDefinedBefore() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;\t\t//(0)");
      _builder.newLine();
      _builder.append("var j = i;\t\t//(1)");
      _builder.newLine();
      _builder.append("var k = m;\t\t//(2)");
      _builder.newLine();
      _builder.append("var m = m;\t\t//(3)");
      _builder.newLine();
      _builder.newLine();
      _builder.append("function myFunc(){\t//(4)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("i;\t\t\t\t//(0)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var o = p;\t\t//(1)");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("function myFunc2(){\t//(5)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("o;\t\t\t\t//(0)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var q = i;\t\t//(1)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var z = myFunc;\t//(2)");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        AbstractElement _get = it.getElements().get(1);
        Expression _expression = ((Value) _get).getExpression();
        Assert.assertTrue(this._funPLModelUtil.isDefinedBefore(((DefinitionRef) _expression)));
        AbstractElement _get_1 = it.getElements().get(2);
        Expression _expression_1 = ((Value) _get_1).getExpression();
        Assert.assertFalse(this._funPLModelUtil.isDefinedBefore(((DefinitionRef) _expression_1)));
        AbstractElement _get_2 = it.getElements().get(3);
        Expression _expression_2 = ((Value) _get_2).getExpression();
        Assert.assertFalse(this._funPLModelUtil.isDefinedBefore(((DefinitionRef) _expression_2)));
        AbstractElement _get_3 = it.getElements().get(4);
        Statement _get_4 = ((Function) _get_3).getBody().getStatements().get(0);
        Assert.assertTrue(this._funPLModelUtil.isDefinedBefore(((DefinitionRef) _get_4)));
        AbstractElement _get_5 = it.getElements().get(4);
        Statement _get_6 = ((Function) _get_5).getBody().getStatements().get(1);
        Expression _expression_3 = ((Value) _get_6).getExpression();
        Assert.assertFalse(this._funPLModelUtil.isDefinedBefore(((DefinitionRef) _expression_3)));
        AbstractElement _get_7 = it.getElements().get(5);
        Statement _get_8 = ((Function) _get_7).getBody().getStatements().get(0);
        Assert.assertFalse(this._funPLModelUtil.isDefinedBefore(((DefinitionRef) _get_8)));
        AbstractElement _get_9 = it.getElements().get(5);
        Statement _get_10 = ((Function) _get_9).getBody().getStatements().get(1);
        Expression _expression_4 = ((Value) _get_10).getExpression();
        Assert.assertTrue(this._funPLModelUtil.isDefinedBefore(((DefinitionRef) _expression_4)));
        AbstractElement _get_11 = it.getElements().get(5);
        Statement _get_12 = ((Function) _get_11).getBody().getStatements().get(2);
        Expression _expression_5 = ((Value) _get_12).getExpression();
        Assert.assertTrue(this._funPLModelUtil.isDefinedBefore(((DefinitionRef) _expression_5)));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertDefinedBeforeOutsideValue(final FunProgram prog, final int elemIndex, final CharSequence expected) {
    AbstractElement _get = prog.getElements().get(elemIndex);
    final Function1<Definition, String> _function = (Definition it) -> {
      return it.getName();
    };
    Assert.assertEquals(expected, IterableExtensions.join(IterableExtensions.<Definition, String>map(this._funPLModelUtil.definedBefore(((Value) _get).getExpression()), _function), ","));
  }
  
  private void assertDefinedBeforeFunctionLocalStatement(final FunProgram prog, final int elemIndex, final int statementIndex, final CharSequence expected) {
    AbstractElement _get = prog.getElements().get(elemIndex);
    final Statement st = ((Function) _get).getBody().getStatements().get(statementIndex);
    boolean _matched = false;
    if (st instanceof Value) {
      _matched=true;
      final Function1<Definition, String> _function = (Definition it) -> {
        return it.getName();
      };
      Assert.assertEquals(expected, IterableExtensions.join(IterableExtensions.<Definition, String>map(this._funPLModelUtil.definedBefore(((Value)st).getExpression()), _function), ","));
    }
    if (!_matched) {
      if (st instanceof Expression) {
        _matched=true;
        final Function1<Definition, String> _function = (Definition it) -> {
          return it.getName();
        };
        Assert.assertEquals(expected, IterableExtensions.join(IterableExtensions.<Definition, String>map(this._funPLModelUtil.definedBefore(((Expression)st)), _function), ","));
      }
    }
  }
}
