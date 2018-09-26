package xyz.varad.funpl.tests.UtilTests;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.tests.FunPLInjectorProvider;
import xyz.varad.funpl.util.FunPLNativeStringUtil;

@RunWith(XtextRunner.class)
@InjectWith(FunPLInjectorProvider.class)
@SuppressWarnings("all")
public class FunPLNativeStringUtilTest {
  @Inject
  @Extension
  private ParseHelper<FunProgram> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void valueDeclarations() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("const j;");
      _builder.newLine();
      _builder.append("var int i;");
      _builder.newLine();
      _builder.append("const bool j;");
      _builder.newLine();
      final String progCode = _builder.toString();
      Assert.assertEquals(progCode, FunPLNativeStringUtil.stringRepr(this._parseHelper.parse(progCode)));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void functionDeclarationsAndStatements() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function int foo2(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return -5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bool bar(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function void baz(){");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function par(int p1, bool p2){");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function block(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var i = 2;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String progCode = _builder.toString();
      Assert.assertEquals(progCode, FunPLNativeStringUtil.stringRepr(this._parseHelper.parse(progCode)));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void expressions() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(int p1, bool p2){");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function baz(int p){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var i = 1;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("false;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("(i = 1);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("(2 + 2);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("p;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("i;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("foo();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("bar(1, true);");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String progCode = _builder.toString();
      Assert.assertEquals(progCode, FunPLNativeStringUtil.stringRepr(this._parseHelper.parse(progCode)));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
