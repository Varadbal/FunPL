/*
 * generated by Xtext 2.15.0
 */
package xyz.varad.funpl.tests.ParsingTests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.util.ParseHelper
import xyz.varad.funpl.funPL.*
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import xyz.varad.funpl.tests.FunPLInjectorProvider
import org.junit.runner.RunWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Test


@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLParsingTestError {
	
	@Inject	extension ParseHelper<FunProgram>
	@Inject extension ValidationTestHelper
	
	
	@Test
	def void testCommentsAndWhiteSpaces(){
		'''
		//Comment 1
			
		/* Comment 2*/
		     
		/*
		  Comment 3
		*/
		function foo(){
			
		}
		'''.parse.assertNoErrors
	}
	
	@Test
	def void expressionedValueDeclarations(){
		'''
		var i = 5;
		var j = -5;
		const k = 3;
		var l = true;
		const m = false;
		'''.parse.assertNoErrors
	}
	
	@Test
	def void typedValueDeclarations(){
		'''
		var int i;
		const int j;
		var bool k;
		const bool l;
		'''.parse.assertNoErrors
	}
	
	@Test
	def void basicFunctionDeclarations(){
		'''
		function foo(){
			
		}
		'''.parse.assertNoErrors
	}
	
	@Test
	def void parameteredFunctionDeclarations(){
		'''
		function foo(int i, bool j){
			
		}
		'''.parse.assertNoErrors
	}
	
	@Test
	def void typedFunctionDeclarations(){
		'''
		function int foo(){
			return 5;
		}
		function bool bar(){
			return true;
		}
		'''.parse.assertNoErrors
	}
	
	@Test
	def void statements(){
		'''
		function foo(){
			var i = 5;
			const j = false;
			
			5;
			true;
			5 + 5;
			i;
			
			return -1;
		}
		'''.parse.assertNoErrors
	}
	
	@Test
	def void expressions(){
		'''
		function void baz(){
			
		}
		
		function void bar(int p1, bool p2){
			
		}
		function void foo(int p8){
			1;
			0;
			-1;
			false;
			true;
			
			var int i;
			i = 1;
			var bool j = 1;
			j = 2;
			i = p8;
			
			5 + 5;
			5 + 5 + 5;
			
			baz();
			bar(1, true);
			i;
			var int k = i;
			
		}'''.parse.assertNoErrors
	}
}
