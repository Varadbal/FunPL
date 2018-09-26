/**
 * generated by Xtext 2.15.0
 */
package xyz.varad.funpl.scoping;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import xyz.varad.funpl.funPL.AbstractElement;
import xyz.varad.funpl.funPL.Block;
import xyz.varad.funpl.funPL.Declaration;
import xyz.varad.funpl.funPL.FunPLPackage;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.FunctionDeclaration;
import xyz.varad.funpl.funPL.Statement;
import xyz.varad.funpl.scoping.AbstractFunPLScopeProvider;

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
@SuppressWarnings("all")
public class FunPLScopeProvider extends AbstractFunPLScopeProvider {
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    EReference _referenceExpression_Declaration = FunPLPackage.eINSTANCE.getReferenceExpression_Declaration();
    boolean _equals = Objects.equal(reference, _referenceExpression_Declaration);
    if (_equals) {
      return this.scopeForReferenceExpression(context);
    }
    return super.getScope(context, reference);
  }
  
  protected IScope scopeForReferenceExpression(final EObject context) {
    final EObject container = context.eContainer();
    IScope _switchResult = null;
    boolean _matched = false;
    if (container instanceof Block) {
      _matched=true;
      final Function1<Statement, Boolean> _function = (Statement it) -> {
        return Boolean.valueOf((!Objects.equal(it, context)));
      };
      _switchResult = Scopes.scopeFor(Iterables.<Declaration>filter(IterableExtensions.<Statement>takeWhile(((Block)container).getStatements(), _function), Declaration.class), this.scopeForReferenceExpression(container));
    }
    if (!_matched) {
      if (container instanceof FunctionDeclaration) {
        _matched=true;
        _switchResult = Scopes.scopeFor(((FunctionDeclaration)container).getParameterList().getParameters(), this.scopeForReferenceExpression(container));
      }
    }
    if (!_matched) {
      if (container instanceof FunProgram) {
        _matched=true;
        final Function1<AbstractElement, Boolean> _function = (AbstractElement it) -> {
          return Boolean.valueOf((!Objects.equal(it, context)));
        };
        _switchResult = Scopes.scopeFor(Iterables.<Declaration>filter(IterableExtensions.<AbstractElement>takeWhile(((FunProgram)container).getElements(), _function), Declaration.class));
      }
    }
    if (!_matched) {
      _switchResult = this.scopeForReferenceExpression(container);
    }
    return _switchResult;
  }
}
