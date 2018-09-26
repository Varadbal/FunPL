package xyz.varad.funpl.tests.ScopingTests;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.scoping.FunPLIndex;
import xyz.varad.funpl.tests.FunPLInjectorProvider;

@RunWith(XtextRunner.class)
@InjectWith(FunPLInjectorProvider.class)
@SuppressWarnings("all")
public class FunPLIndexTest {
  @Inject
  @Extension
  private ParseHelper<FunProgram> _parseHelper;
  
  @Inject
  private Provider<ResourceSet> resourceSetProvider;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private FunPLIndex _funPLIndex;
  
  @Test
  public void multipleFileResolution() {
    try {
      final ResourceSet resourceSet = this.resourceSetProvider.get();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package first;");
      _builder.newLine();
      _builder.append("var i = 1;");
      _builder.newLine();
      final FunProgram first = this._parseHelper.parse(_builder, resourceSet);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package second;");
      _builder_1.newLine();
      _builder_1.append("var j = first.i;");
      _builder_1.newLine();
      final FunProgram second = this._parseHelper.parse(_builder_1, resourceSet);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void multipleFileResolution2() {
    try {
      final ResourceSet resourceSet = this.resourceSetProvider.get();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package my.first.pack;");
      _builder.newLine();
      _builder.append("function foo(){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var int i;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final FunProgram first = this._parseHelper.parse(_builder, resourceSet);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package my.second.pack;");
      _builder_1.newLine();
      _builder_1.append("function bar(){");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("my.first.pack.foo();");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      final FunProgram second = this._parseHelper.parse(_builder_1, resourceSet);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testIndex() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var int i = 1;");
      _builder.newLine();
      FunProgram _parse = this._parseHelper.parse(_builder);
      final Procedure1<FunProgram> _function = (FunProgram it) -> {
        InputOutput.<IResourceDescription>println(this._funPLIndex.getResourceDescription(it));
      };
      ObjectExtensions.<FunProgram>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
