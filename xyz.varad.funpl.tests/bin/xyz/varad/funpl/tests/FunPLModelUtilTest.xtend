package xyz.varad.funpl.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith
import xyz.varad.funpl.util.FunPLModelUtil
import xyz.varad.funpl.funPL.FunProgram
import xyz.varad.funpl.funPL.IntConstant
import xyz.varad.funpl.funPL.Plus
import xyz.varad.funpl.funPL.Symbol
import xyz.varad.funpl.funPL.SymbolRef
import xyz.varad.funpl.funPL.Value

import static extension org.junit.Assert.*
import static extension xyz.varad.funpl.util.FunPLModelUtil.*
import static extension xyz.varad.funpl.tests.FunPLParsingTest.*

@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLModelUtilTest {
	
	@Inject extension ParseHelper<FunProgram> 
	@Inject extension FunPLModelUtil
	
	@Test
	def void testIsDefinedBefore_SymbolRef(){
		'''
		var i;
		var j = 2;
		function myFunc(p){
			var k = j;		//(0) - global visibility
			var q = k;		//(1) - local visibility
			l;				//(2) - undefined
			5 + p;			//(3) - parameter visibility
			var m = myFunc; //(4) - function as symbol
		}
		'''.parse.functions.head.statements => [
			((get(0) as Value).expression as SymbolRef).isDefinedBefore.assertTrue;
			((get(1) as Value).expression as SymbolRef).isDefinedBefore.assertTrue;
			(get(2) as SymbolRef).isDefinedBefore.assertFalse;
			((get(3) as Plus).right as SymbolRef).isDefinedBefore.assertTrue;		
			((get(4) as Value).expression as SymbolRef).isDefinedBefore
		]
	}
	
	@Test
	def void symbolsDefinedBefore_Expression(){
		'''
		var i;
		var j = 2;
		function myFunc(){
			var k = 1;
		}
		function myFunc2(p1, p2){
			var l;
			5 + 5;
		}
		'''.parse.functions.last.statements.last as Plus => [
			"i,j,myFunc,myFunc2,p1,p2,l".assertEquals((left as IntConstant).symbolsDefinedBefore.map[name].join(","))
		]
	}
	
	@Test
	def void symbolsDefinedBefore_Symbol(){
		'''
		var i;
		var j = 2;
		function myFunc(){
			var k = 1;
		}
		var m;
		function myFunc2(p1, p2, p3){
			var l;
			var f = 2;
			5 + 5;
		}

		'''.parse => [
			"i,j,myFunc,m,myFunc2".assertEquals((it.values.get(0) as Symbol).symbolsDefinedBefore.map[name].join(","))
			"i,j,myFunc,m,myFunc2,p1,p2,p3".assertEquals(it.functions.get(1).values.head.symbolsDefinedBefore.map[name].join(","))
			"i,j,myFunc,m,myFunc2,p1,p2".assertEquals(it.functions.get(1).params.get(2).symbolsDefinedBefore.map[name].join(","))
		]
	}
	
	@Test
	def void symbolsDefinedGlobally(){
		'''
		var i;
		function myFunc() { }
		var j = 2;
		function myFunc2() { var l = 5; }
		var k;
		'''.parse => [
			"i,myFunc,j,myFunc2,k".assertEquals(it.symbols.map[name].join(","))
		]
	}
	
	@Test
	def void testFunctions_FunProgram(){
		'''
		var i;
		function myFunc() { }
		var j = 2;
		function myFunc2() { 5; }
		var k;
		'''.parse => [
			"myFunc,myFunc2".assertEquals(it.functions.map[name].join(","))
		]
	}
	
	@Test
	def void testValues_FunProgram(){
		'''
		var i;
		function myFunc() { }
		var j = 2;
		function myFunc2() { var l = 5; }
		var k;
		'''.parse => [
			"i,j,k".assertEquals(it.values.map[name].join(","))
		]
	}
	
	@Test
	def void testSymbols_FunProgram(){
		'''
		var i;
		function myFunc() { }
		var j = 2;
		function myFunc2() { var l = 5; }
		var k;
		'''.parse => [
			"i,myFunc,j,myFunc2,k".assertEquals(it.symbols.map[name].join(","))
		]
	}
	
	@Test
	def void testValues_Function(){
		'''
		var i;
		function myFunc() {
			
		}
		var j = 2;
		function myFunc2(p1, p2) { 
			var l = 5;
			var m;
			5 + 5;
		}
		var k;
		'''.parse.functions.last => [
			"l,m".assertEquals(it.values.map[name].join(","))
		]
	}
	
	@Test
	def void testSymbols_Function(){
		'''
		var i;
		function myFunc() {
			
		}
		var j = 2;
		function myFunc2(p1, p2) { 
			var l = 5;
			var m;
			5 + 5;
		}
		var k;
		'''.parse.functions.last => [
			"p1,p2,l,m".assertEquals(it.symbols.map[name].join(","))
		]
	}
	
	@Test
	def void testStatements_Function(){
		'''
		var i;
		function myFunc() {
			
		}
		var j = 2;
		function myFunc2(p1, p2) { 
			var l = 5;
			var m;
			5 + 5;
			var k = 5 + 5;
		}
		var k;
		'''.parse.functions.last => [
			4.assertEquals(it.statements.size)
			"var l = 5".assertEquals(it.statements.get(0).stringRepr)
			"var m".assertEquals(it.statements.get(1).stringRepr)
			"(5 + 5)".assertEquals(it.statements.get(2).stringRepr)
			"var k = (5 + 5)".assertEquals(it.statements.get(3).stringRepr)
		]
	}
	
	@Test
	def void testValues_Block(){
		'''
		var i;
		function myFunc() {
			
		}
		var j = 2;
		function myFunc2(p1, p2) { 
			var l = 5;
			var m;
			5 + 5;
		}
		var k;
		'''.parse.functions.last.body => [
			"l,m".assertEquals(it.values.map[name].join(","))
		]
	}
	
	@Test
	def void testStatements_Block(){
		'''
		var i;
		function myFunc() {
			
		}
		var j = 2;
		function myFunc2(p1, p2) { 
			var l = 5;
			var m;
			5 + 5;
			var k = 5 + 5;
		}
		var k;
		'''.parse.functions.last.body => [
			4.assertEquals(it.statements.size)
			"var l = 5".assertEquals(it.statements.get(0).stringRepr)
			"var m".assertEquals(it.statements.get(1).stringRepr)
			"(5 + 5)".assertEquals(it.statements.get(2).stringRepr)
			"var k = (5 + 5)".assertEquals(it.statements.get(3).stringRepr)
		]
	}
	
	
}