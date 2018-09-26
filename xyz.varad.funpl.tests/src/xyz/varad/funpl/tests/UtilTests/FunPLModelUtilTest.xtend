package xyz.varad.funpl.tests.UtilTests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.util.ParseHelper
import xyz.varad.funpl.funPL.*
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import xyz.varad.funpl.tests.FunPLInjectorProvider
import org.eclipse.xtext.testing.XtextRunner
import org.junit.runner.RunWith
import org.junit.Test

import static extension xyz.varad.funpl.util.FunPLModelUtil.*
import static extension xyz.varad.funpl.util.FunPLNativeStringUtil.*
import static extension org.junit.Assert.*


@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLModelUtilTest {
	
	@Inject	extension ParseHelper<FunProgram>
	@Inject extension ValidationTestHelper
	
	//FunProgram
	@Test
	def void declarations_FunProgram(){
		'''
		var i = 5;
		function foo(){
			var k = 1;
		}
		function bar(int p1){
			
		}
		const bool j = false;	
		'''.parse => [
			"i, foo, bar, j".assertEquals(declarations.map[name].join(", "))
		]
	}
	
	@Test
	def void functionDeclarations_FunProgram() {
		'''
		var i = 5;
		function foo(){
			var k = 1;
		}
		function bar(int p1){
					
		}
		const bool j = false;	
		'''.parse => [
			"foo, bar".assertEquals(functionDeclarations.map[name].join(", "))
		]
	}
	
	@Test
	def void valueDeclarations_FunProgram(){
		'''
		var i = 5;
		function foo(){
			var k = 1;
		}
		function bar(int p1){
					
		}
		const bool j = false;	
		'''.parse => [
			"i, j".assertEquals(valueDeclarations.map[name].join(", "))
		]
	}
	
	//Function
	@Test
	def void parameterDeclarations_Function(){
		'''
		var i = 5;
		function foo(int p1, int p2, bool p3){
			
		}
		function bar(int p4){
			
		}
		'''.parse.functionDeclarations.head => [
			"p1, p2, p3".assertEquals(parameterDeclarations.map[name].join(", "))
		]
	}
	
	@Test
	def void bodyValueDeclarations_Function(){
		'''
		var i = 5;
		function foo(int p1, int p2, bool p3){
			var j = 2;
			var k = false;
			var int l = i;
		}
		function bar(int p4){
			var m = 1;
		}
		'''.parse.functionDeclarations.head => [
			"j, k, l".assertEquals(bodyValueDeclarations.map[name].join(", "))
		]
	}
	
	@Test
	def void valueDeclarations_Function(){
		'''
		var i = 5;
		function foo(int p1, int p2, bool p3){
			var j = 2;
			var k = false;
			var int l = i;
		}
		function bar(int p4){
			var m = 1;
		}
		'''.parse.functionDeclarations.head => [
			"p1, p2, p3, j, k, l".assertEquals(valueDeclarations.map[name].join(", "))
		]
	}
	
	@Test
	def void statements_Function(){
		'''
		var i = 5;
		function foo(int p1, int p2, bool p3){
			var j = 2;
			var k = false;
			var int l = i;
			j;
			5 + 5;
			true;
			j = 3;
			return 2;
		}
		function bar(int p4){
			var m = 1;
		}
		'''.parse.functionDeclarations.head => [
			"var j = 2, var k = false, var int l = i, j, (5 + 5), true, (j = 3), return 2".assertEquals(statements.map[stringRepr].join(", "))
		]
	}
	
	@Test
	def void returnStatements_Function(){
		'''
		var int i = 5;
		function int foo(int p1){
			return 1;
			return p1;
			return i;
			return 3 + 2;
		}
		function bar(){
			return -5;
		}
		'''.parse.functionDeclarations.head => [
			"return 1, return p1, return i, return (3 + 2)".assertEquals(returnStatements.map[stringRepr].join(", "))
		]
	}
	
	//Block
	@Test
	def void values_Block(){
		'''
		var int i = 5;
		function int foo(int p1){
			var j = 2;
			var k = false;
			var int l = i;
			j;
			return -1;
			5 + 5;
			true;
			j = 3;
			return 2;
		}
		function bar(){
			return -5;
		}
		'''.parse.functionDeclarations.head.body => [
			"var j = 2, var k = false, var int l = i".assertEquals(valueDeclarations.map[stringRepr].join(", "))
		]
	}
	
}