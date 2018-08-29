package xyz.varad.funpl.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import static extension org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import xyz.varad.funpl.funPL.FunProgram
import xyz.varad.funpl.funPL.Statement
import xyz.varad.funpl.funPL.Assignment
import xyz.varad.funpl.funPL.Plus
import xyz.varad.funpl.funPL.FunctionCall
import xyz.varad.funpl.funPL.DefinitionRef
import xyz.varad.funpl.funPL.Definition
import xyz.varad.funpl.funPL.Function
import xyz.varad.funpl.funPL.Expression
import xyz.varad.funpl.funPL.IntConstant
import xyz.varad.funpl.funPL.StringConstant
import xyz.varad.funpl.funPL.BoolConstant
import xyz.varad.funpl.funPL.Value
import xyz.varad.funpl.FunPLModelUtil

import static extension org.eclipse.xtext.EcoreUtil2.*

@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLModelUtilTest {
	
	@Inject extension ParseHelper<FunProgram> 
	@Inject extension FunPLModelUtil
	
	@Test
	def void testDefinedBefore(){
		'''
		var i = 5;		//(0)
		var j = false;	//(1)
		var k = i + 5;	//(2)
		var l = m + 1;	//(3)
		var o = 3;		//(4)
		
		function myFunc(){	//(5)
			var p = 1;		//(0)
			5 + 5;			//(1)
			var q = 8;		//(2)
		}
		'''.parse => [
			assertDefinedBeforeOutsideValue(0, "")
			assertDefinedBeforeOutsideValue(1, "i")
			assertDefinedBeforeOutsideValue(2, "i,j")
			assertDefinedBeforeOutsideValue(3, "i,j,k")
			assertDefinedBeforeOutsideValue(4, "i,j,k,l")
			
			assertDefinedBeforeFunctionLocalStatement(5, 0, "i,j,k,l,o")
			assertDefinedBeforeFunctionLocalStatement(5, 1, "i,j,k,l,o,p")
			assertDefinedBeforeFunctionLocalStatement(5, 2, "i,j,k,l,o,p")
			
		]
	}
	
	@Test
	def void testIsDefinedBefore(){
		'''
		var i = 5;		//(0)
		var j = i;		//(1)
		var k = m;		//(2)
		var m = m;		//(3)
		
		function myFunc(){	//(4)
			i;				//(0)
			var o = p;		//(1)
		}
		
		function myFunc2(){	//(5)
			o;				//(0)
			var q = i;		//(1)
			var z = myFunc;	//(2)
		}
		'''.parse => [
			((it.elements.get(1) as Value).expression as DefinitionRef).isDefinedBefore.assertTrue;
			((it.elements.get(2) as Value).expression as DefinitionRef).isDefinedBefore.assertFalse;
			((it.elements.get(3) as Value).expression as DefinitionRef).isDefinedBefore.assertFalse;
			((it.elements.get(4) as Function).body.statements.get(0) as DefinitionRef).isDefinedBefore.assertTrue;
			(((it.elements.get(4) as Function).body.statements.get(1) as Value).expression as DefinitionRef).isDefinedBefore.assertFalse;
			((it.elements.get(5) as Function).body.statements.get(0) as DefinitionRef).isDefinedBefore.assertFalse;
			(((it.elements.get(5) as Function).body.statements.get(1) as Value).expression as DefinitionRef).isDefinedBefore.assertTrue;
			(((it.elements.get(5) as Function).body.statements.get(2) as Value).expression as DefinitionRef).isDefinedBefore.assertTrue;
		]
	}
	
	
	//Values must have expressions!!!!
	def private assertDefinedBeforeOutsideValue(FunProgram prog, int elemIndex, CharSequence expected){
		expected.assertEquals((prog.elements.get(elemIndex) as Value).expression.definedBefore.map[name].join(","))
	}
	
	//Values must have expressions!!!
	def private assertDefinedBeforeFunctionLocalStatement
		(FunProgram prog, int elemIndex, int statementIndex, CharSequence expected){
		val st = (prog.elements.get(elemIndex) as Function).body.statements.get(statementIndex)
		switch(st){
			Value:{
				expected.assertEquals(st.expression.definedBefore.map[name].join(","))
			}
			Expression:{
				expected.assertEquals(st.definedBefore.map[name].join(","))
			}
		}
	}
	
	
	
}