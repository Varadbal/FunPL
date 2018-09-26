package xyz.varad.funpl.tests.ScopingTests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.testing.util.ParseHelper
import xyz.varad.funpl.funPL.*
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import xyz.varad.funpl.tests.FunPLInjectorProvider
import org.junit.runner.RunWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Test
import org.eclipse.xtext.scoping.IScopeProvider

import static extension xyz.varad.funpl.util.FunPLModelUtil.*
import static extension org.junit.Assert.*


@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLScopeProviderTest {
	
	@Inject	extension ParseHelper<FunProgram>
	@Inject extension IScopeProvider
	
	@Test
	def void scopeForReferenceExpression(){
		'''
		var i = 5;
		var j = i;
		function A(){
			
		}
		function myFunc(int p){
			var f = true;
			var k = 1;
			k = 5;
			myFunc(3);
			var q = false;
		}
		function myFunc2() { }
		'''.parse.functionDeclarations.get(1).statements.get(3) => [
			val scope = (it as Expression).getScope(FunPLPackage.eINSTANCE.referenceExpression_Declaration).allElements.map[name].join(", ")
			"f, k, p, i, j, A".assertEquals(scope)
		]
	}
	
	
	//Other tests
	
	@Test
	def void declarationMasking(){
		'''
		var int i = -1;
		function foo(){
			var int i = 5;
			i;
		}
		'''.parse.functionDeclarations.head.statements.get(1) as ReferenceExpression => [
			5.assertEquals(((it.declaration as ValueDeclaration).expression as IntegerLiteralExpression).value)
		]
	} 
	
	
	
}