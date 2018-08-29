package xyz.varad.funpl

import org.eclipse.emf.common.util.BasicEList
import xyz.varad.funpl.funPL.Block
import xyz.varad.funpl.funPL.Definition
import xyz.varad.funpl.funPL.Expression
import xyz.varad.funpl.funPL.FunProgram
import xyz.varad.funpl.funPL.Function
import xyz.varad.funpl.funPL.Statement
import static extension java.lang.Class.*

import static extension org.eclipse.xtext.EcoreUtil2.*
import org.eclipse.emf.ecore.EObject

class FunPLModelUtil {
	
	/*def isDefinedBefore(DefinitionRef defRef){
		defRef.definedBefore.contains(defRef.definition)
	}*/
	
	def definedBefore(Expression e){
		if(e.getContainerOfType(Function) === null){					//is outside of any functions
			return e.getContainerOfType(Definition/*Value*/).definedBefore		//Container cannot possibly be Function-Definition
		}else{															//must be inside
			return e.getContainerOfType(Statement).definedBefore				//Statement = the containing line of the function
		}
		
	}
	
	def private definedBefore(Statement s){						//Precondition: IN a function
		
		val functionLine = s.walkUpContainmentUntilContainerIs(Block)
		
		val allStatementsInsideBefore = (functionLine.eContainer as Block).statements
		val allDefsInsideBefore = allStatementsInsideBefore.
			subList(0, allStatementsInsideBefore.indexOf(functionLine)).
			typeSelect(Definition)							
		
		val containingFunction = ((functionLine.eContainer as Block).eContainer as Function)
		val allDefsOutsideBefore = containingFunction.definedBefore			//Should work for function-def too
		
		val allValidDefsBefore = new BasicEList<Definition>
		allValidDefsBefore.addAll(allDefsOutsideBefore)
		allValidDefsBefore.addAll(allDefsInsideBefore)
		return allValidDefsBefore.toSet
	}
	
	def private definedBefore(Definition d){					//Precondition: OUT of any functions
		val allElements = (d.eContainer as FunProgram).elements
		/*val allDefs = new BasicEList<Definition>
		for(elem : allElements){
			if(elem instanceof Definition)
				allDefs.add(elem)
		}
		allDefs.subList(0, allDefs.indexOf(d))*/
		allElements.subList(0, allElements.indexOf(d)).typeSelect(Definition).toSet
	}
	
	/*Walk up until the "ECONTAINER is of type" (vs. ECoreUtil2.getContainerOfType gets "itself is of type")*/
	def private <T extends EObject> walkUpContainmentUntilContainerIs(EObject start, Class<T> goalContainerType){
		var e = start as EObject
		for(; e !== null; e = e.eContainer){
			if(goalContainerType.isInstance(e.eContainer))
				return e
		}
	}
	
}