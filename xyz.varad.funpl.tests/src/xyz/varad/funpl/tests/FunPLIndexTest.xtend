package xyz.varad.funpl.tests

import org.junit.runner.RunWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.InjectWith
import com.google.inject.Inject
import org.eclipse.xtext.testing.util.ParseHelper
import xyz.varad.funpl.funPL.FunProgram
import xyz.varad.funpl.scoping.FunPLIndex
import org.junit.Test
import org.eclipse.emf.ecore.EObject
import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLIndexTest {
	@Inject extension ParseHelper<FunProgram>
	@Inject extension FunPLIndex
	
	@Test
	def void testExportedEObjectDescriptions(){
		'''
		var i;
		function A(p1){
			var k;
		}
		function B() { }
		'''.parse.assertExportedEObjectDescriptions("i, A, A.p1, A.k, B")
	}
	
	def private assertExportedEObjectDescriptions(EObject o, CharSequence expected){
		expected.toString.assertEquals(o.getExportedEObjectDescriptions.map[qualifiedName].join(", "))
	}
}