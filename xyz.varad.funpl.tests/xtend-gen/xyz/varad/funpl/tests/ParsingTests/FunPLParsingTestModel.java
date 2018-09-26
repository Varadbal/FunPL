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
import xyz.varad.funpl.funPL.AssignmentExpression;
import xyz.varad.funpl.funPL.BooleanLiteralExpression;
import xyz.varad.funpl.funPL.Declaration;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.FunctionDeclaration;
import xyz.varad.funpl.funPL.IntegerLiteralExpression;
import xyz.varad.funpl.funPL.PlusExpression;
import xyz.varad.funpl.funPL.ReferenceExpression;
import xyz.varad.funpl.funPL.ReturnStatement;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.funPL.ValueDeclaration;
import xyz.varad.funpl.tests.FunPLInjectorProvider;
import xyz.varad.funpl.util.FunPLModelUtil;

@RunWith(XtextRunner.class)
@InjectWith(FunPLInjectorProvider.class)
@SuppressWarnings("all")
public class FunPLParsingTestModel {
  @Inject
  @Extension
  private ParseHelper<FunProgram> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void funProgramElements() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var int i;");
      _builder.newLine();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        int _size = it.getElements().size();
        Assert.assertTrue((_size > 0));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void valueDeclarationIsConstant() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("const j;");
      _builder.newLine();
      List<ValueDeclaration> _valueDeclarations = FunPLModelUtil.valueDeclarations(this._parseHelper.parse(_builder));
      final Procedure1<List<ValueDeclaration>> _function = (List<ValueDeclaration> it) -> {
        Assert.assertFalse(it.get(0).isConstant());
        Assert.assertTrue(it.get(1).isConstant());
      };
      ObjectExtensions.<List<ValueDeclaration>>operator_doubleArrow(_valueDeclarations, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void parameterIsConstant() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function foo(int p){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      List<ValueDeclaration> _parameterDeclarations = FunPLModelUtil.parameterDeclarations(IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder))));
      final Procedure1<List<ValueDeclaration>> _function = (List<ValueDeclaration> it) -> {
        Assert.assertTrue(IterableExtensions.<ValueDeclaration>head(it).isConstant());
      };
      ObjectExtensions.<List<ValueDeclaration>>operator_doubleArrow(_parameterDeclarations, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void DeclarationHasDeclaredType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var int i;");
      _builder.newLine();
      _builder.append("function bool foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("const j = 1;");
      _builder.newLine();
      _builder.append("function void bar(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function baz(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      List<Declaration> _declarations = FunPLModelUtil.declarations(this._parseHelper.parse(_builder));
      final Procedure1<List<Declaration>> _function = (List<Declaration> it) -> {
        Assert.assertTrue(it.get(0).isSetDeclaredType());
        Assert.assertTrue(it.get(1).isSetDeclaredType());
        Assert.assertFalse(it.get(2).isSetDeclaredType());
        Assert.assertTrue(it.get(3).isSetDeclaredType());
        Assert.assertFalse(it.get(4).isSetDeclaredType());
      };
      ObjectExtensions.<List<Declaration>>operator_doubleArrow(_declarations, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void valueDeclarationHasExpression() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 5;");
      _builder.newLine();
      _builder.append("var bool j;");
      _builder.newLine();
      List<Declaration> _declarations = FunPLModelUtil.declarations(this._parseHelper.parse(_builder));
      final Procedure1<List<Declaration>> _function = (List<Declaration> it) -> {
        Declaration _get = it.get(0);
        Assert.assertTrue(((ValueDeclaration) _get).isSetExpression());
        Declaration _get_1 = it.get(1);
        Assert.assertFalse(((ValueDeclaration) _get_1).isSetExpression());
      };
      ObjectExtensions.<List<Declaration>>operator_doubleArrow(_declarations, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void referenceExpressionHasArgumentList() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function baz(int i){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function bar(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function foo(int p1){");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("baz(1);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("p1;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      List<Statement> _statements = FunPLModelUtil.statements(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)).get(2));
      final Procedure1<List<Statement>> _function = (List<Statement> it) -> {
        Statement _get = it.get(0);
        Assert.assertTrue(((ReferenceExpression) _get).isSetArgumentList());
        Statement _get_1 = it.get(1);
        Assert.assertFalse(((ReferenceExpression) _get_1).isSetArgumentList());
      };
      ObjectExtensions.<List<Statement>>operator_doubleArrow(_statements, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void LiteralExpressions() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var int i = 5;");
      _builder.newLine();
      _builder.append("var bool j = true;");
      _builder.newLine();
      List<ValueDeclaration> _valueDeclarations = FunPLModelUtil.valueDeclarations(this._parseHelper.parse(_builder));
      final Procedure1<List<ValueDeclaration>> _function = (List<ValueDeclaration> it) -> {
        Expression _expression = it.get(0).getExpression();
        Assert.assertEquals(5, ((IntegerLiteralExpression) _expression).getValue());
        Expression _expression_1 = it.get(1).getExpression();
        Assert.assertEquals(Boolean.valueOf(true), Boolean.valueOf(((BooleanLiteralExpression) _expression_1).isValue()));
      };
      ObjectExtensions.<List<ValueDeclaration>>operator_doubleArrow(_valueDeclarations, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void PlusExpression() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5 + -1;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      Statement _head = IterableExtensions.<Statement>head(FunPLModelUtil.statements(IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)))));
      final Procedure1<PlusExpression> _function = (PlusExpression it) -> {
        Expression _left = it.getLeft();
        Assert.assertEquals(5, ((IntegerLiteralExpression) _left).getValue());
        Expression _right = it.getRight();
        Assert.assertEquals((-1), ((IntegerLiteralExpression) _right).getValue());
      };
      ObjectExtensions.<PlusExpression>operator_doubleArrow(
        ((PlusExpression) _head), _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void AssignmentExpression() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var int i;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("i = 3;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      Statement _get = FunPLModelUtil.statements(IterableExtensions.<FunctionDeclaration>head(FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder)))).get(1);
      final Procedure1<AssignmentExpression> _function = (AssignmentExpression it) -> {
        Expression _left = it.getLeft();
        Assert.assertEquals("i", ((ReferenceExpression) _left).getDeclaration().getName());
        Expression _right = it.getRight();
        Assert.assertEquals(3, ((IntegerLiteralExpression) _right).getValue());
      };
      ObjectExtensions.<AssignmentExpression>operator_doubleArrow(
        ((AssignmentExpression) _get), _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void returnStatementHasExpression() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("function bar(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return 5;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      List<FunctionDeclaration> _functionDeclarations = FunPLModelUtil.functionDeclarations(this._parseHelper.parse(_builder));
      final Procedure1<List<FunctionDeclaration>> _function = (List<FunctionDeclaration> it) -> {
        Assert.assertFalse(IterableExtensions.<ReturnStatement>head(FunPLModelUtil.returnStatements(it.get(0))).isSetExpression());
        Assert.assertTrue(IterableExtensions.<ReturnStatement>head(FunPLModelUtil.returnStatements(it.get(1))).isSetExpression());
      };
      ObjectExtensions.<List<FunctionDeclaration>>operator_doubleArrow(_functionDeclarations, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
