package xyz.varad.funpl.tests.ParsingTests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.util.ParseHelper
import xyz.varad.funpl.funPL.*
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import xyz.varad.funpl.tests.FunPLInjectorProvider

import static extension org.junit.Assert.*
import static extension xyz.varad.funpl.util.FunPLModelUtil.*
import org.eclipse.xtext.testing.XtextRunner
import org.junit.runner.RunWith
import org.junit.Test

@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLParsingTestModel {
	
	@Inject	extension ParseHelper<FunProgram>
	@Inject extension ValidationTestHelper
	
	
	@Test
	def void funProgramElements(){
		'''
		var int i;
		function foo(){
			
		}
		'''.parse => [
			(elements.size > 0).assertTrue
		]
	}
	
	/////////////////////////////////////////////////Declarations/////////////////////////////
	
	@Test
	def void valueDeclarationIsConstant() {
		'''
		var i;
		const j;
		'''.parse.valueDeclarations => [
			get(0).isConstant.assertFalse
			get(1).isConstant.assertTrue
		]
	}
	
	@Test
	def void parameterIsConstant(){
		'''
		function foo(int p){
			
		}
		'''.parse.functionDeclarations.head.parameterDeclarations => [
			head.constant.assertTrue
		]
	}
	
	@Test
	def void DeclarationHasDeclaredType(){
		'''
		var int i;
		function bool foo(){
			
		}
		const j = 1;
		function void bar(){
			
		}
		function baz(){
			
		}
		'''.parse.declarations => [
			get(0).isSetDeclaredType.assertTrue
			get(1).isSetDeclaredType.assertTrue
			get(2).isSetDeclaredType.assertFalse
			get(3).isSetDeclaredType.assertTrue
			get(4).isSetDeclaredType.assertFalse
		]
	}
	
	@Test
	def void valueDeclarationHasExpression(){
		'''
		var i = 5;
		var bool j;
		'''.parse.declarations =>[
			(get(0) as ValueDeclaration).isSetExpression.assertTrue;
			(get(1) as ValueDeclaration).isSetExpression.assertFalse;
		]
	}
	
	//////////////////////////////////////////////////Expressions////////////////////////////////////////////////////
	
	@Test
	def void referenceExpressionHasArgumentList(){
		'''
		function baz(int i){
			
		}
		function bar(){
			
		}
		function foo(int p1){
			
			baz(1);
			p1;
		}
		'''.parse.functionDeclarations.get(2).statements =>[
			(get(0) as ReferenceExpression).isSetArgumentList.assertTrue;
			(get(1) as ReferenceExpression).isSetArgumentList.assertFalse;
		]
	}
	
	@Test
	def void LiteralExpressions(){
		'''
		var int i = 5;
		var bool j = true;
		'''.parse.valueDeclarations => [
			5.assertEquals((get(0).expression as IntegerLiteralExpression).value)
			true.assertEquals((get(1).expression as BooleanLiteralExpression).value)
		]
	}
	
	@Test
	def void PlusExpression(){
		'''
		function foo(){
			5 + -1;
		}
		'''.parse.functionDeclarations.head.statements.head as PlusExpression => [
			5.assertEquals((left as IntegerLiteralExpression).value);
			(-1).assertEquals((right as IntegerLiteralExpression).value);
		]
	}
	
	@Test
	def void AssignmentExpression(){
		'''
		function foo(){
			var int i;
			i = 3;
		}
		'''.parse.functionDeclarations.head.statements.get(1) as AssignmentExpression => [
			"i".assertEquals((left as ReferenceExpression).declaration.name);
			3.assertEquals((right as IntegerLiteralExpression).value);
		]
	}
	
	///////////////////////////////////////////////Statements////////////////////////////////////////////////////////
	
	@Test
	def void returnStatementHasExpression(){
		'''
		function foo(){
			return;
		}
		
		function bar(){
			return 5;
		}
		'''.parse.functionDeclarations => [
			get(0).returnStatements.head.isSetExpression.assertFalse;
			get(1).returnStatements.head.isSetExpression.assertTrue;
		]
	}
	
}