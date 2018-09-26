package xyz.varad.funpl.tests.TypingTests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.util.ParseHelper
import xyz.varad.funpl.funPL.*
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import xyz.varad.funpl.tests.FunPLInjectorProvider
import org.junit.runner.RunWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Test

import static extension xyz.varad.funpl.typing.FunPLTypeComputer.*
import static extension org.junit.Assert.*
import static extension xyz.varad.funpl.util.FunPLTypingUtil.*
import static extension xyz.varad.funpl.util.FunPLModelUtil.*

@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class TypeInferenceTest {
	
	@Inject	extension ParseHelper<FunProgram>
	@Inject extension ValidationTestHelper
	
	@Test
	def void valueDeclarationType(){
		'''
		var int i = 5;
		var bool j;
		var k = -1;
		'''.parse.elements => [
			(get(0) as TypeInferredElement).returnType.isInteger.assertTrue;
			(get(1) as TypeInferredElement).returnType.isBoolean.assertTrue;
			(get(2) as TypeInferredElement).returnType.isInteger.assertTrue;
		]
	}
	
	@Test
	def void functionDeclarationType(){
		'''
		function foo(){
			
		}
		function int bar(){
			return 5;
		}
		function baz(){
			return false;
		}
		'''.parse.elements => [
			(get(0) as TypeInferredElement).returnType.isVoid.assertTrue;
			(get(1) as TypeInferredElement).returnType.isInteger.assertTrue;
			(get(2) as TypeInferredElement).returnType.isBoolean.assertTrue;
		]
	}
	
	@Test
	def void expressionType(){
		'''
		var int i;
		function foo(int p1){
			p1;
			5 + 5;
			i = 8;
			-1;
			false;
			return;
		}
		'''.parse.functionDeclarations.head.statements => [
			(get(0) as TypeInferredElement).returnType.isInteger.assertTrue;
			(get(1) as TypeInferredElement).returnType.isInteger.assertTrue;
			(get(2) as TypeInferredElement).returnType.isInteger.assertTrue;
			(get(3) as TypeInferredElement).returnType.isInteger.assertTrue;
			(get(4) as TypeInferredElement).returnType.isBoolean.assertTrue;
			(get(5) as TypeInferredElement).returnType.isVoid.assertTrue;
		]
	}
	
}