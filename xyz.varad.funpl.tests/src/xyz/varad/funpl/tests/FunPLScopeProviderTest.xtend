package xyz.varad.funpl.tests

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.scoping.IScopeProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith
import xyz.varad.funpl.funPL.Expression
import xyz.varad.funpl.funPL.FunPLPackage
import xyz.varad.funpl.funPL.FunProgram

import static extension org.junit.Assert.*
import static extension xyz.varad.funpl.FunPLModelUtil.*

@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLScopeProviderTest {
	@Inject extension ParseHelper<FunProgram>
	@Inject extension IScopeProvider
	
	
	@Test
	def void testScopeProvider(){
		'''
		var i;
		var j = i;
		function myFunc(p){
			var k = 1;
			k = 5;
		}
		'''.parse.functions.head.statements.last as Expression => [
			assertScope(FunPLPackage.eINSTANCE.symbolRef_Symbol,
				"p, k, i, j, myFunc, myFunc.p, myFunc.k"
			)
		]
	}
	
	def private assertScope(EObject context, EReference reference, CharSequence expected){
		expected.toString.assertEquals(
			context.getScope(reference).allElements.map[name].join(", ")
		)
	}
	
}