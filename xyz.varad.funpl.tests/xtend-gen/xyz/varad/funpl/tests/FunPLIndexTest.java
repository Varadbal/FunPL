package xyz.varad.funpl.tests;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
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
  @Extension
  private FunPLIndex _funPLIndex;
  
  @Test
  public void testExportedEObjectDescriptions() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i;");
      _builder.newLine();
      _builder.append("function A(p1){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var k;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("function B() { }");
      _builder.newLine();
      this.assertExportedEObjectDescriptions(this._parseHelper.parse(_builder), "i, A, A.p1, A.k, B");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void assertExportedEObjectDescriptions(final EObject o, final CharSequence expected) {
    final Function1<IEObjectDescription, QualifiedName> _function = (IEObjectDescription it) -> {
      return it.getQualifiedName();
    };
    Assert.assertEquals(expected.toString(), IterableExtensions.join(IterableExtensions.<IEObjectDescription, QualifiedName>map(this._funPLIndex.getExportedEObjectDescriptions(o), _function), ", "));
  }
}
