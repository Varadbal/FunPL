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


import static extension xyz.varad.funpl.util.FunPLModelUtil.*
import static extension xyz.varad.funpl.util.FunPLNativeStringUtil.*
import static extension org.junit.Assert.*


@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLParsingTestExpressions {
	
	@Inject	extension ParseHelper<FunProgram>
	@Inject extension ValidationTestHelper
	
	@Test
	def void plusExpressionAssociativity() {
		'''
		function foo(int p){
			5 + 5;
			5 + 5 + 5;
			5 + (5 + 5);
			1 + 2 + (p + 3);
		}
		'''.parse.functionDeclarations.head.statements => [
			"(5 + 5)".assertEquals(get(0).stringRepr)
			"((5 + 5) + 5)".assertEquals(get(1).stringRepr)
			"(5 + (5 + 5))".assertEquals(get(2).stringRepr)
			"((1 + 2) + (p + 3))".assertEquals(get(3).stringRepr)
		]
	}
	
	@Test
	def void assignmentExpressionAssociativity(){
		'''
		function foo(int p1, bool p2){
			var int i;
			var bool j;
			
			i = p1; «««2
			j = p2; «««3
			
			var i2 = 5;
			var i3 = 6;
			
			i = i2 = i3;«««6
			(i = i2) = i3;«««7
			(i = i2) = i3 = p1;«««8
		}
		'''.parse.functionDeclarations.head.statements => [
			"(i = p1)".assertEquals(get(2).stringRepr)
			"(j = p2)".assertEquals(get(3).stringRepr)
			"(i = (i2 = i3))".assertEquals(get(6).stringRepr)
			"((i = i2) = i3)".assertEquals(get(7).stringRepr)
			"((i = i2) = (i3 = p1))".assertEquals(get(8).stringRepr)
		]
	}
	
	@Test
	def void mixedAssociativity(){
		'''
		function int foo(int p1, bool p2){
			
		}
		function bool bar(int p1, int p2, int p3){
			
		}
		function baz(int p1, int p2){
			var int i = -3;
			i = 5 + foo(foo(p1 + p2 + (3 + 2), false), bar(i, i = 2 + p1 + p2, foo(i, true)));
		}
		'''.parse.functionDeclarations.get(2).statements => [
			"(i = (5 + foo(foo(((p1 + p2) + (3 + 2)), false), bar(i, (i = ((2 + p1) + p2)), foo(i, true)))))".assertEquals(get(1).stringRepr)
		]
	}
	
}