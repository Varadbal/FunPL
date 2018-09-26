package xyz.varad.funpl.tests.TypingTests;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.common.util.EList;
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
import xyz.varad.funpl.funPL.AbstractElement;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.FunctionDeclaration;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.funPL.TypeInferredElement;
import xyz.varad.funpl.tests.FunPLInjectorProvider;
import xyz.varad.funpl.util.FunPLModelUtil;
import xyz.varad.funpl.util.FunPLTypingUtil;

@RunWith(XtextRunner.class)
@InjectWith(FunPLInjectorProvider.class)
@SuppressWarnings("all")
public class TypeInferenceTest {
  @Inject
  @Extension
  private ParseHelper<FunProgram> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void valueDeclarationType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var int i = 5;");
      _builder.newLine();
      _builder.append("var bool j;");
      _builder.newLine();
      _builder.append("var k = -1;");
      _builder.newLine();
      EList<AbstractElement> _elements = this._parseHelper.parse(_builder).getElements();
      final Procedure1<EList<AbstractElement>> _function = (EList<AbstractElement> it) -> {
        AbstractElement _get = it.get(0);
        Assert.assertTrue(FunPLTypingUtil.isInteger(((TypeInferredElement) _get).getReturnType()));
        AbstractElement _get_1 = it.get(1);
        Assert.assertTrue(FunPLTypingUtil.isBoolean(((TypeInferredElement) _get_1).getReturnType()));
        AbstractElement _get_2 = it.get(2);
        Assert.assertTrue(FunPLTypingUtil.isInteger(((TypeInferredElement) _get_2).getReturnType()));
      };
      ObjectExtensions.<EList<AbstractElement>>operator_doubleArrow(_elements, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void functionDeclarationType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function int bar(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return 5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function baz(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return false;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      EList<AbstractElement> _elements = this._parseHelper.parse(_builder).getElements();
      final Procedure1<EList<AbstractElement>> _function = (EList<AbstractElement> it) -> {
        AbstractElement _get = it.get(0);
        Assert.assertTrue(FunPLTypingUtil.isVoid(((TypeInferredElement) _get).getReturnType()));
        AbstractElement _get_1 = it.get(1);
        Assert.assertTrue(FunPLTypingUtil.isInteger(((TypeInferredElement) _get_1).getReturnType()));
        AbstractElement _get_2 = it.get(2);
        Assert.assertTrue(FunPLTypingUtil.isBoolean(((TypeInferredElement) _get_2).getReturnType()));
      };
      ObjectExtensions.<EList<AbstractElement>>operator_doubleArrow(_elements, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void expressionType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var int i;");
      _builder.newLine();
      _builder.append("function foo(int p1){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("p1;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + 5;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("i = 8;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("-1;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("false;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      List<Statement> _statements = FunPLModelUtil.statements(IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder))));
      final Procedure1<List<Statement>> _function = (List<Statement> it) -> {
        Statement _get = it.get(0);
        Assert.assertTrue(FunPLTypingUtil.isInteger(((TypeInferredElement) _get).getReturnType()));
        Statement _get_1 = it.get(1);
        Assert.assertTrue(FunPLTypingUtil.isInteger(((TypeInferredElement) _get_1).getReturnType()));
        Statement _get_2 = it.get(2);
        Assert.assertTrue(FunPLTypingUtil.isInteger(((TypeInferredElement) _get_2).getReturnType()));
        Statement _get_3 = it.get(3);
        Assert.assertTrue(FunPLTypingUtil.isInteger(((TypeInferredElement) _get_3).getReturnType()));
        Statement _get_4 = it.get(4);
        Assert.assertTrue(FunPLTypingUtil.isBoolean(((TypeInferredElement) _get_4).getReturnType()));
        Statement _get_5 = it.get(5);
        Assert.assertTrue(FunPLTypingUtil.isVoid(((TypeInferredElement) _get_5).getReturnType()));
      };
      ObjectExtensions.<List<Statement>>operator_doubleArrow(_statements, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
