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
import java.util.Set
import xyz.varad.funpl.funPL.Value
import xyz.varad.funpl.funPL.Symbol
import xyz.varad.funpl.funPL.SymbolRef
import java.util.List

class FunPLModelUtil{
	/* TODO restructure
	 * def isDefinedBefore(DefinitionRef defRef)
	 * def definedBefore(Statement s)
	 * ^Should be enough, as defRefs and Expressions are Statements too^
	 */
	 
	 def boolean isDefinedBefore(SymbolRef _sym){
	 	(_sym as Expression).symbolsDefinedBefore.contains(_sym.symbol)
	 }
	 
	 def Set<Symbol> symbolsDefinedBefore(Expression _exp){
	 	//println(_exp)
	 	//println(_exp.getContainerOfType(FunProgram))
	 	val before = _exp.getContainerOfType(FunProgram).symbolsDefinedGlobally

	 	val contFunc = _exp.getContainerOfType(Function)
	 	if(contFunc !== null){
	 		before.addAll(contFunc.params)		//_exp cannot be param, so all params are added
	 		
	 		val topLevel = _exp.walkUpContainmentUntilContainerIs(Block)
	 		val fullList = contFunc.statements	//must be statement as topLevel can be Expression too (not Symbol)
	 		before.addAll(fullList.subList(0, fullList.indexOf(topLevel)).typeSelect(Symbol))
	 	}
	 	
	 	return before
	 }
	 
	 def Set<Symbol> symbolsDefinedBefore(Symbol _sym){
	 	val before = _sym.getContainerOfType(FunProgram).symbolsDefinedGlobally
	 	val contFunc = _sym.getContainerOfType(Function)
	 	if(contFunc !== null){
	 		
	 		val fullList = contFunc.symbols
	 		before.addAll(fullList.subList(0, fullList.indexOf(_sym)))
	 	}
	 	return before
	 }
	 
	 def Set<Symbol> symbolsDefinedGlobally(FunProgram _fp){
	 	_fp.symbols().toSet
	 }
	
	//FunProgram
	def static functions(FunProgram f){
		f.elements.typeSelect(typeof(Function))
	}
	
	def static values(FunProgram f){
		f.elements.typeSelect(typeof(Value))
	}
	
	def static symbols(FunProgram f){
		f.elements.typeSelect(typeof(Symbol))
	}
	
	//Function
	def static values(Function f){
		f.body.values()
	}
	
	def static symbols(Function f){
		val p = new BasicEList<Symbol>
		p.addAll(f.params)
		p.addAll(f.values)
		return p as List<Symbol>
	}
	
	def static statements(Function f){
		f.body.statements.toList
	}
	
	
	//Block
	def static values(Block b){
		b.statements.typeSelect(Value)
	}
	
	def static statements(Block b){
		b.statements as List<Statement>
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