package xyz.varad.funpl.util

import xyz.varad.funpl.funPL.*
import static extension org.eclipse.xtext.EcoreUtil2.*
import java.util.List
import org.eclipse.emf.common.util.BasicEList

class FunPLModelUtil {
	
	
	//FunProgram
	def static List<Declaration> declarations(FunProgram f){
		f.elements.typeSelect(typeof(Declaration))
	}
	
	def static List<FunctionDeclaration> functionDeclarations(FunProgram f){
		f.elements.typeSelect(typeof(FunctionDeclaration))
	}
	
	def static List<ValueDeclaration> valueDeclarations(FunProgram f){
		f.elements.typeSelect(typeof(ValueDeclaration))
	}
	
	//Function
	def static List<ValueDeclaration> parameterDeclarations(FunctionDeclaration f){
		f.parameterList.parameters
	}
	
	def static List<ValueDeclaration> bodyValueDeclarations(FunctionDeclaration f){
		f.body.valueDeclarations
	}
	
	def static List<ValueDeclaration> valueDeclarations(FunctionDeclaration f){
		val p = new BasicEList<ValueDeclaration>
		p.addAll(f.parameterDeclarations)
		p.addAll(f.bodyValueDeclarations)
		return p as List<ValueDeclaration>
	}
	
	def static List<Statement> statements(FunctionDeclaration f){
		f.body.statements
	}
	
	def static List<ReturnStatement> returnStatements(FunctionDeclaration _f){
		//FIXME rewrite when recursion makes sense (IF/FOR/etc. statements added) - allContents(=recursive).filter?
	 	val rets = _f?.body.getAllContentsOfType(ReturnStatement)
	 	return rets
	 }
	
	
	//Block
	def static valueDeclarations(Block b){
		b.statements.typeSelect(ValueDeclaration)
	}
}