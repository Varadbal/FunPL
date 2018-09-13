package xyz.varad.funpl.util

import java.util.List
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.ecore.EObject
import xyz.varad.funpl.funPL.Block
import xyz.varad.funpl.funPL.Expression
import xyz.varad.funpl.funPL.FunProgram
import xyz.varad.funpl.funPL.Function
import xyz.varad.funpl.funPL.Statement
import xyz.varad.funpl.funPL.Symbol
import xyz.varad.funpl.funPL.SymbolRef
import xyz.varad.funpl.funPL.Value

import static extension org.eclipse.xtext.EcoreUtil2.*
import xyz.varad.funpl.funPL.ReturnStatement

class FunPLModelUtil{

	 def boolean isDefinedBefore(SymbolRef _sym){
	 	(_sym as Expression).symbolsDefinedBefore.contains(_sym.symbol)
	 }
	 
	 def boolean isDefinedBefore(Symbol _sym){
	 	_sym.symbolsDefinedBefore.containsSimilar(_sym)
	 }
	 
	 def List<Symbol> symbolsDefinedBefore(Expression _exp){
	 	val before = new BasicEList<Symbol>
	 	
	 	val outerElement = _exp.walkUpContainmentUntilContainerIs(FunProgram)
	 	val allGlobalSyms = _exp.getContainerOfType(FunProgram).symbols
	 	before.addAll(allGlobalSyms.subList(0, allGlobalSyms.indexOf(outerElement)))
	 	if(outerElement !== _exp)
	 		before.add(outerElement as Symbol)
	 	
	 	val contFunc = _exp.getContainerOfType(Function)
	 	if(contFunc !== null){
	 		before.addAll(contFunc.params)		//_exp cannot be param, so all params are added
	 		
	 		val topLevel = _exp.walkUpContainmentUntilContainerIs(Block)
	 		val fullList = contFunc.statements	//must be statement as topLevel can be Expression too (not Symbol)
	 		before.addAll(fullList.subList(0, fullList.indexOf(topLevel)).typeSelect(Symbol))
	 	}
	 	
	 	return before
	 }
	 
	 def List<Symbol> symbolsDefinedBefore(Symbol _sym){
	 	val before = new BasicEList<Symbol>
	 	
	 	val outerElement = _sym.walkUpContainmentUntilContainerIs(FunProgram)
	 	val allGlobalSyms = _sym.getContainerOfType(FunProgram).symbols
	 	before.addAll(allGlobalSyms.subList(0, allGlobalSyms.indexOf(outerElement)))
	 	if(outerElement !== _sym)
	 		before.add(outerElement as Symbol)
	 	
	 	val contFunc = _sym.getContainerOfType(Function)
	 	if(contFunc !== null && contFunc != _sym){
	 		val fullList = contFunc.symbols
	 		before.addAll(fullList.subList(0, fullList.indexOf(_sym)))
	 	}
	 	return before
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
	
	def static List<ReturnStatement> returnStatements(Function _f){
		//TODO rewrite when recursion makes sense (IF/FOR/etc. statements added)
	 	val rets = _f?.body.getAllContentsOfType(ReturnStatement)
	 	return rets
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
	
	
	def dispatch private boolean containsSimilar(List<Symbol> symsSet, Symbol _sym){
		for(elem : symsSet){
			if(elem.name == _sym.name){
				return true
			}
		}
		return false
	}
	
	def dispatch private boolean containsSimilar(List<Symbol> symsSet, Function _func){
		for(elem : symsSet){
			if((elem.name == _func.name) &&
				(elem instanceof Function) &&
				((elem as Function).params.size == _func.params.size)
			){
				return true
			}
		}
		return false
	}
	
}