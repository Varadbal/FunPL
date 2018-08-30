package xyz.varad.funpl.tests

import org.junit.runner.RunWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.InjectWith
import com.google.inject.Inject
import org.eclipse.xtext.testing.util.ParseHelper
import xyz.varad.funpl.funPL.FunProgram
import xyz.varad.funpl.FunPLModelUtil
import static extension xyz.varad.funpl.FunPLModelUtil.*
import static extension org.junit.Assert.*
import org.eclipse.xtext.scoping.IScopeProvider
import org.junit.Test
import xyz.varad.funpl.funPL.Expression
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import xyz.varad.funpl.funPL.FunPLPackage

@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLScopeProviderTest {
	@Inject extension ParseHelper<FunProgram>
	@Inject extension FunPLModelUtil
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
			assertScope(FunPLPackage.eINSTANCE.definitionRef_Definition,
				"k, i, j, myFunc, myFunc.k"
			)
			
		]
	}
	
	def private assertScope(EObject context, EReference reference, CharSequence expected){
		expected.toString.assertEquals(
			context.getScope(reference).allElements.map[name].join(", ")
		)
	}
	
}