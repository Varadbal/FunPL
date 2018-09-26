package xyz.varad.funpl.tests.UtilTests;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
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
import xyz.varad.funpl.funPL.Declaration;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.FunctionDeclaration;
import xyz.varad.funpl.funPL.ReturnStatement;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.funPL.ValueDeclaration;
import xyz.varad.funpl.tests.FunPLInjectorProvider;
import xyz.varad.funpl.util.FunPLModelUtil;
import xyz.varad.funpl.util.FunPLNativeStringUtil;

@RunWith(XtextRunner.class)
@InjectWith(FunPLInjectorProvider.class)
@SuppressWarnings("all")
public class FunPLModelUtilTest {
  @Inject
  @Extension
  private ParseHelper<FunProgram> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void declarations_FunProgram() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;");
      _builder.newLine();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = 1;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(int p1){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("const bool j = false;\t");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        final Function1<Declaration, String> _function_1 = (Declaration it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("i, foo, bar, j", IterableExtensions.join(ListExtensions.<Declaration, String>map(FunPLModelUtil.declarations(it), _function_1), ", "));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void functionDeclarations_FunProgram() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;");
      _builder.newLine();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = 1;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(int p1){");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("const bool j = false;\t");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        final Function1<FunctionDeclaration, String> _function_1 = (FunctionDeclaration it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("foo, bar", IterableExtensions.join(ListExtensions.<FunctionDeclaration, String>map(FunPLModelUtil.functionDeclarations(it), _function_1), ", "));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void valueDeclarations_FunProgram() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;");
      _builder.newLine();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = 1;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(int p1){");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("const bool j = false;\t");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        final Function1<ValueDeclaration, String> _function_1 = (ValueDeclaration it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("i, j", IterableExtensions.join(ListExtensions.<ValueDeclaration, String>map(FunPLModelUtil.valueDeclarations(it), _function_1), ", "));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void parameterDeclarations_Function() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;");
      _builder.newLine();
      _builder.append("function foo(int p1, int p2, bool p3){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(int p4){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      FunctionDeclaration _head = IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)));
      final Procedure1<FunctionDeclaration> _function = (FunctionDeclaration it) -> {
        final Function1<ValueDeclaration, String> _function_1 = (ValueDeclaration it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("p1, p2, p3", IterableExtensions.join(ListExtensions.<ValueDeclaration, String>map(FunPLModelUtil.parameterDeclarations(it), _function_1), ", "));
      };
      ObjectExtensions.<FunctionDeclaration>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void bodyValueDeclarations_Function() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;");
      _builder.newLine();
      _builder.append("function foo(int p1, int p2, bool p3){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = false;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var int l = i;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(int p4){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var m = 1;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      FunctionDeclaration _head = IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)));
      final Procedure1<FunctionDeclaration> _function = (FunctionDeclaration it) -> {
        final Function1<ValueDeclaration, String> _function_1 = (ValueDeclaration it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("j, k, l", IterableExtensions.join(ListExtensions.<ValueDeclaration, String>map(FunPLModelUtil.bodyValueDeclarations(it), _function_1), ", "));
      };
      ObjectExtensions.<FunctionDeclaration>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void valueDeclarations_Function() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;");
      _builder.newLine();
      _builder.append("function foo(int p1, int p2, bool p3){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = false;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var int l = i;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(int p4){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var m = 1;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      FunctionDeclaration _head = IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)));
      final Procedure1<FunctionDeclaration> _function = (FunctionDeclaration it) -> {
        final Function1<ValueDeclaration, String> _function_1 = (ValueDeclaration it_1) -> {
          return it_1.getName();
        };
        Assert.assertEquals("p1, p2, p3, j, k, l", IterableExtensions.join(ListExtensions.<ValueDeclaration, String>map(FunPLModelUtil.valueDeclarations(it), _function_1), ", "));
      };
      ObjectExtensions.<FunctionDeclaration>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void statements_Function() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;");
      _builder.newLine();
      _builder.append("function foo(int p1, int p2, bool p3){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = false;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var int l = i;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("j;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("true;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("j = 3;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return 2;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(int p4){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var m = 1;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      FunctionDeclaration _head = IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)));
      final Procedure1<FunctionDeclaration> _function = (FunctionDeclaration it) -> {
        final Function1<Statement, String> _function_1 = (Statement it_1) -> {
          return FunPLNativeStringUtil.stringRepr(it_1);
        };
        Assert.assertEquals("var j = 2, var k = false, var int l = i, j, (5 + 5), true, (j = 3), return 2", IterableExtensions.join(ListExtensions.<Statement, String>map(FunPLModelUtil.statements(it), _function_1), ", "));
      };
      ObjectExtensions.<FunctionDeclaration>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void returnStatements_Function() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var int i = 5;");
      _builder.newLine();
      _builder.append("function int foo(int p1){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return 1;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return p1;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return i;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return 3 + 2;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return -5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      FunctionDeclaration _head = IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)));
      final Procedure1<FunctionDeclaration> _function = (FunctionDeclaration it) -> {
        final Function1<ReturnStatement, String> _function_1 = (ReturnStatement it_1) -> {
          return FunPLNativeStringUtil.stringRepr(it_1);
        };
        Assert.assertEquals("return 1, return p1, return i, return (3 + 2)", IterableExtensions.join(ListExtensions.<ReturnStatement, String>map(FunPLModelUtil.returnStatements(it), _function_1), ", "));
      };
      ObjectExtensions.<FunctionDeclaration>operator_doubleArrow(_head, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void values_Block() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var int i = 5;");
      _builder.newLine();
      _builder.append("function int foo(int p1){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var j = 2;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k = false;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var int l = i;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("j;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return -1;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("true;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("j = 3;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return 2;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return -5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      Block _body = IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder))).getBody();
      final Procedure1<Block> _function = (Block it) -> {
        final Function1<ValueDeclaration, String> _function_1 = (ValueDeclaration it_1) -> {
          return FunPLNativeStringUtil.stringRepr(it_1);
        };
        Assert.assertEquals("var j = 2, var k = false, var int l = i", IterableExtensions.join(ListExtensions.<ValueDeclaration, String>map(FunPLModelUtil.valueDeclarations(it), _function_1), ", "));
      };
      ObjectExtensions.<Block>operator_doubleArrow(_body, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
