package xyz.varad.funpl.tests.ParsingTests;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.FunctionDeclaration;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.tests.FunPLInjectorProvider;
import xyz.varad.funpl.util.FunPLModelUtil;
import xyz.varad.funpl.util.FunPLNativeStringUtil;

@RunWith(XtextRunner.class)
@InjectWith(FunPLInjectorProvider.class)
@SuppressWarnings("all")
public class FunPLParsingTestExpressions {
  @Inject
  @Extension
  private ParseHelper<FunProgram> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void plusExpressionAssociativity() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function foo(int p){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5 + 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + (5 + 5);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("1 + 2 + (p + 3);");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      List<Statement> _statements = FunPLModelUtil.statements(IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder))));
      final Procedure1<List<Statement>> _function = (List<Statement> it) -> {
        Assert.assertEquals("(5 + 5)", FunPLNativeStringUtil.stringRepr(it.get(0)));
        Assert.assertEquals("((5 + 5) + 5)", FunPLNativeStringUtil.stringRepr(it.get(1)));
        Assert.assertEquals("(5 + (5 + 5))", FunPLNativeStringUtil.stringRepr(it.get(2)));
        Assert.assertEquals("((1 + 2) + (p + 3))", FunPLNativeStringUtil.stringRepr(it.get(3)));
      };
      ObjectExtensions.<List<Statement>>operator_doubleArrow(_statements, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void assignmentExpressionAssociativity() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function foo(int p1, bool p2){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var int i;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var bool j;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("i = p1; ");
      _builder.append("\t\t\tj = p2; ");
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var i2 = 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var i3 = 6;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("i = i2 = i3;");
      _builder.append("\t\t\t(i = i2) = i3;");
      _builder.append("\t\t\t(i = i2) = i3 = p1;");
      _builder.append("\t\t}");
      _builder.newLine();
      List<Statement> _statements = FunPLModelUtil.statements(IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder))));
      final Procedure1<List<Statement>> _function = (List<Statement> it) -> {
        Assert.assertEquals("(i = p1)", FunPLNativeStringUtil.stringRepr(it.get(2)));
        Assert.assertEquals("(j = p2)", FunPLNativeStringUtil.stringRepr(it.get(3)));
        Assert.assertEquals("(i = (i2 = i3))", FunPLNativeStringUtil.stringRepr(it.get(6)));
        Assert.assertEquals("((i = i2) = i3)", FunPLNativeStringUtil.stringRepr(it.get(7)));
        Assert.assertEquals("((i = i2) = (i3 = p1))", FunPLNativeStringUtil.stringRepr(it.get(8)));
      };
      ObjectExtensions.<List<Statement>>operator_doubleArrow(_statements, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void mixedAssociativity() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function int foo(int p1, bool p2){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bool bar(int p1, int p2, int p3){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function baz(int p1, int p2){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var int i = -3;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("i = 5 + foo(foo(p1 + p2 + (3 + 2), false), bar(i, i = 2 + p1 + p2, foo(i, true)));");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      List<Statement> _statements = FunPLModelUtil.statements(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)).get(2));
      final Procedure1<List<Statement>> _function = (List<Statement> it) -> {
        Assert.assertEquals("(i = (5 + foo(foo(((p1 + p2) + (3 + 2)), false), bar(i, (i = ((2 + p1) + p2)), foo(i, true)))))", FunPLNativeStringUtil.stringRepr(it.get(1)));
      };
      ObjectExtensions.<List<Statement>>operator_doubleArrow(_statements, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
