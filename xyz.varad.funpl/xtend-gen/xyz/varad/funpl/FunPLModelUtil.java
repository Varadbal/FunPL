package xyz.varad.funpl;

import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import xyz.varad.funpl.funPL.AbstractElement;
import xyz.varad.funpl.funPL.Block;
import xyz.varad.funpl.funPL.Definition;
import xyz.varad.funpl.funPL.Expression;
import xyz.varad.funpl.funPL.FunProgram;
import xyz.varad.funpl.funPL.Function;
import xyz.varad.funpl.funPL.Statement;

@SuppressWarnings("all")
public class FunPLModelUtil {
  /**
   * def isDefinedBefore(DefinitionRef defRef){
   * defRef.definedBefore.contains(defRef.definition)
   * }
   */
  public Set<Definition> definedBefore(final Expression e) {
    Function _containerOfType = EcoreUtil2.<Function>getContainerOfType(e, Function.class);
    boolean _tripleEquals = (_containerOfType == null);
    if (_tripleEquals) {
      return this.definedBefore(EcoreUtil2.<Definition>getContainerOfType(e, Definition.class));
    } else {
      return this.definedBefore(EcoreUtil2.<Statement>getContainerOfType(e, Statement.class));
    }
  }
  
  private Set<Definition> definedBefore(final Statement s) {
    final EObject functionLine = this.<Block>walkUpContainmentUntilContainerIs(s, Block.class);
    EObject _eContainer = functionLine.eContainer();
    final EList<Statement> allStatementsInsideBefore = ((Block) _eContainer).getStatements();
    final List<Definition> allDefsInsideBefore = EcoreUtil2.<Definition>typeSelect(allStatementsInsideBefore.subList(0, allStatementsInsideBefore.indexOf(functionLine)), Definition.class);
    EObject _eContainer_1 = functionLine.eContainer();
    EObject _eContainer_2 = ((Block) _eContainer_1).eContainer();
    final Function containingFunction = ((Function) _eContainer_2);
    final Set<Definition> allDefsOutsideBefore = this.definedBefore(containingFunction);
    final BasicEList<Definition> allValidDefsBefore = new BasicEList<Definition>();
    allValidDefsBefore.addAll(allDefsOutsideBefore);
    allValidDefsBefore.addAll(allDefsInsideBefore);
    return IterableExtensions.<Definition>toSet(allValidDefsBefore);
  }
  
  private Set<Definition> definedBefore(final Definition d) {
    Set<Definition> _xblockexpression = null;
    {
      EObject _eContainer = d.eContainer();
      final EList<AbstractElement> allElements = ((FunProgram) _eContainer).getElements();
      _xblockexpression = IterableExtensions.<Definition>toSet(EcoreUtil2.<Definition>typeSelect(allElements.subList(0, allElements.indexOf(d)), Definition.class));
    }
    return _xblockexpression;
  }
  
  /**
   * Walk up until the "ECONTAINER is of type" (vs. ECoreUtil2.getContainerOfType gets "itself is of type")
   */
  private <T extends EObject> EObject walkUpContainmentUntilContainerIs(final EObject start, final Class<T> goalContainerType) {
    EObject e = ((EObject) start);
    for (; (e != null); e = e.eContainer()) {
      boolean _isInstance = goalContainerType.isInstance(e.eContainer());
      if (_isInstance) {
        return e;
      }
    }
    return null;
  }
}
