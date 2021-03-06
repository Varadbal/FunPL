/*
 * generated by Xtext 2.15.0
 */
package xyz.varad.funpl.scoping

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import xyz.varad.funpl.funPL.FunPLPackage
import org.eclipse.xtext.scoping.IScope
import xyz.varad.funpl.funPL.Block
import xyz.varad.funpl.funPL.FunctionDeclaration
import xyz.varad.funpl.funPL.FunProgram
import org.eclipse.xtext.scoping.Scopes
import xyz.varad.funpl.funPL.Declaration

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
class FunPLScopeProvider extends AbstractFunPLScopeProvider {
	
	override getScope(EObject context, EReference reference){
		if(reference == FunPLPackage.eINSTANCE.referenceExpression_Declaration){
			return scopeForReferenceExpression(context)
		}
		return super.getScope(context, reference)
	}
	
	//FIXME sophisticate
	def protected IScope scopeForReferenceExpression(EObject context){
		val container = context.eContainer
		return switch(container) {
			Block: Scopes.scopeFor(container.statements.takeWhile[it != context].filter(Declaration), scopeForReferenceExpression(container))
			FunctionDeclaration: Scopes.scopeFor(container.parameterList.parameters, scopeForReferenceExpression(container))
			FunProgram: Scopes.scopeFor(container.elements.takeWhile[it != context].filter(Declaration))
			default: scopeForReferenceExpression(container)	
		}
	}


}
