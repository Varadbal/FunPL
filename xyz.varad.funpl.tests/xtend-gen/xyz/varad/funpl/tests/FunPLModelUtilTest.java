package xyz.varad.funpl.tests;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.varad.funpl.funPL.Block;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.Function;
import xyz.varad.funpl.funPL.IntConstant;
import xyz.varad.funpl.funPL.Plus;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.funPL.Symbol;
import xyz.varad.funpl.funPL.SymbolRef;
import xyz.varad.funpl.funPL.Value;
import xyz.varad.funpl.tests.FunPLInjectorProvider;
import xyz.varad.funpl.tests.FunPLParsingTest;
import xyz.varad.funpl.util.FunPLModelUtil;

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
  public void testIsDefinedBefore_SymbolRef() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc(p){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = j;\t\t//(0) - global visibility");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var q = k;\t\t//(1) - local visibility");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("l;\t\t\t\t//(2) - undefined");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + p;\t\t\t//(3) - parameter visibility");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var m = myFunc; //(4) - function as symbol");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      List<Statement> _statements = FunPLModelUtil.statements(IterableExtensions.<Function>head(FunPLModelUtil.functions(this._parseHelper.parse(_builder))));
      final Procedure1<List<Statement>> _function = (List<Statement> it) -> {
        Statement _get = it.get(0);
        Expression _expression = ((Value) _get).getExpression();
        Assert.assertTrue(this._funPLModelUtil.isDefinedBefore(((SymbolRef) _expression)));
        Statement _get_1 = it.get(1);
        Expression _expression_1 = ((Value) _get_1).getExpression();
        Assert.assertTrue(this._funPLModelUtil.isDefinedBefore(((SymbolRef) _expression_1)));
        Statement _get_2 = it.get(2);
        Assert.assertFalse(this._funPLModelUtil.isDefinedBefore(((SymbolRef) _get_2)));
        Statement _get_3 = it.get(3);
        Expression _right = ((Plus) _get_3).getRight();
        Assert.assertTrue(this._funPLModelUtil.isDefinedBefore(((SymbolRef) _right)));
        Statement _get_4 = it.get(4);
        Expression _expression_2 = ((Value) _get_4).getExpression();
        this._funPLModelUtil.isDefinedBefore(((SymbolRef) _expression_2));
      };
      ObjectExtensions.<List<Statement>>operator_doubleArrow(_statements, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void symbolsDefinedBefore_Expression() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = 1;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function myFunc2(p1, p2){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var l;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      Statement _last = IterableExtensions.<Statement>last(FunPLModelUtil.statements(IterableExtensions.<Function>last(FunPLModelUtil.functions(this._parseHelper.parse(_builder)))));
      final Procedure1<Plus> _function = (Plus it) -> {
        Expression _left = it.getLeft();
        final Function1<Symbol, String> _function_1 = (Symbol it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("i,j,myFunc,myFunc2,p1,p2,l", IterableExtensions.join(IterableExtensions.<Symbol, String>map(this._funPLModelUtil.symbolsDefinedBefore(((IntConstant) _left)), _function_1), ","));
      };
      ObjectExtensions.<Plus>operator_doubleArrow(
        ((Plus) _last), _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void symbolsDefinedBefore_Symbol() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = 1;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var m;");
      _builder.newLine();
      _builder.append("function myFunc2(p1, p2, p3){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var l;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var f = 2;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        Value _get = FunPLModelUtil.values(it).get(0);
        final Function1<Symbol, String> _function_1 = (Symbol it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("i,j,myFunc,m,myFunc2", IterableExtensions.join(IterableExtensions.<Symbol, String>map(this._funPLModelUtil.symbolsDefinedBefore(((Symbol) _get)), _function_1), ","));
        final Function1<Symbol, String> _function_2 = (Symbol it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("i,j,myFunc,m,myFunc2,p1,p2,p3", IterableExtensions.join(IterableExtensions.<Symbol, String>map(this._funPLModelUtil.symbolsDefinedBefore(IterableExtensions.<Value>head(FunPLModelUtil.values(FunPLModelUtil.functions(it).get(1)))), _function_2), ","));
        final Function1<Symbol, String> _function_3 = (Symbol it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("i,j,myFunc,m,myFunc2,p1,p2", IterableExtensions.join(IterableExtensions.<Symbol, String>map(this._funPLModelUtil.symbolsDefinedBefore(FunPLModelUtil.functions(it).get(1).getParams().get(2)), _function_3), ","));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void symbolsDefinedGlobally() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("function myFunc() { }");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc2() { var l = 5; }");
      _builder.newLine();
      _builder.append("var k;");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        final Function1<Symbol, String> _function_1 = (Symbol it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("i,myFunc,j,myFunc2,k", IterableExtensions.join(ListExtensions.<Symbol, String>map(FunPLModelUtil.symbols(it), _function_1), ","));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testFunctions_FunProgram() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("function myFunc() { }");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc2() { 5; }");
      _builder.newLine();
      _builder.append("var k;");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        final Function1<Function, String> _function_1 = (Function it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("myFunc,myFunc2", IterableExtensions.join(ListExtensions.<Function, String>map(FunPLModelUtil.functions(it), _function_1), ","));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testValues_FunProgram() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("function myFunc() { }");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc2() { var l = 5; }");
      _builder.newLine();
      _builder.append("var k;");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        final Function1<Value, String> _function_1 = (Value it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("i,j,k", IterableExtensions.join(ListExtensions.<Value, String>map(FunPLModelUtil.values(it), _function_1), ","));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSymbols_FunProgram() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("function myFunc() { }");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc2() { var l = 5; }");
      _builder.newLine();
      _builder.append("var k;");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        final Function1<Symbol, String> _function_1 = (Symbol it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("i,myFunc,j,myFunc2,k", IterableExtensions.join(ListExtensions.<Symbol, String>map(FunPLModelUtil.symbols(it), _function_1), ","));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testValues_Function() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("function myFunc() {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc2(p1, p2) { ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var l = 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var m;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var k;");
      _builder.newLine();
      Function _last = IterableExtensions.<Function>last(FunPLModelUtil.functions(this._parseHelper.parse(_builder)));
      final Procedure1<Function> _function = (Function it) -> {
        final Function1<Value, String> _function_1 = (Value it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("l,m", IterableExtensions.join(ListExtensions.<Value, String>map(FunPLModelUtil.values(it), _function_1), ","));
      };
      ObjectExtensions.<Function>operator_doubleArrow(_last, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSymbols_Function() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("function myFunc() {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc2(p1, p2) { ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var l = 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var m;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var k;");
      _builder.newLine();
      Function _last = IterableExtensions.<Function>last(FunPLModelUtil.functions(this._parseHelper.parse(_builder)));
      final Procedure1<Function> _function = (Function it) -> {
        final Function1<Symbol, String> _function_1 = (Symbol it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("p1,p2,l,m", IterableExtensions.join(ListExtensions.<Symbol, String>map(FunPLModelUtil.symbols(it), _function_1), ","));
      };
      ObjectExtensions.<Function>operator_doubleArrow(_last, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testStatements_Function() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("function myFunc() {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc2(p1, p2) { ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var l = 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var m;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = 5 + 5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var k;");
      _builder.newLine();
      Function _last = IterableExtensions.<Function>last(FunPLModelUtil.functions(this._parseHelper.parse(_builder)));
      final Procedure1<Function> _function = (Function it) -> {
        Assert.assertEquals(4, FunPLModelUtil.statements(it).size());
        Assert.assertEquals("var l = 5", FunPLParsingTest.stringRepr(FunPLModelUtil.statements(it).get(0)));
        Assert.assertEquals("var m", FunPLParsingTest.stringRepr(FunPLModelUtil.statements(it).get(1)));
        Assert.assertEquals("(5 + 5)", FunPLParsingTest.stringRepr(FunPLModelUtil.statements(it).get(2)));
        Assert.assertEquals("var k = (5 + 5)", FunPLParsingTest.stringRepr(FunPLModelUtil.statements(it).get(3)));
      };
      ObjectExtensions.<Function>operator_doubleArrow(_last, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testValues_Block() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("function myFunc() {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc2(p1, p2) { ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var l = 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var m;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var k;");
      _builder.newLine();
      Block _body = IterableExtensions.<Function>last(FunPLModelUtil.functions(this._parseHelper.parse(_builder))).getBody();
      final Procedure1<Block> _function = (Block it) -> {
        final Function1<Value, String> _function_1 = (Value it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("l,m", IterableExtensions.join(ListExtensions.<Value, String>map(FunPLModelUtil.values(it), _function_1), ","));
      };
      ObjectExtensions.<Block>operator_doubleArrow(_body, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testStatements_Block() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("function myFunc() {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("function myFunc2(p1, p2) { ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var l = 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var m;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = 5 + 5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("var k;");
      _builder.newLine();
      Block _body = IterableExtensions.<Function>last(FunPLModelUtil.functions(this._parseHelper.parse(_builder))).getBody();
      final Procedure1<Block> _function = (Block it) -> {
        Assert.assertEquals(4, it.getStatements().size());
        Assert.assertEquals("var l = 5", FunPLParsingTest.stringRepr(it.getStatements().get(0)));
        Assert.assertEquals("var m", FunPLParsingTest.stringRepr(it.getStatements().get(1)));
        Assert.assertEquals("(5 + 5)", FunPLParsingTest.stringRepr(it.getStatements().get(2)));
        Assert.assertEquals("var k = (5 + 5)", FunPLParsingTest.stringRepr(it.getStatements().get(3)));
      };
      ObjectExtensions.<Block>operator_doubleArrow(_body, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
