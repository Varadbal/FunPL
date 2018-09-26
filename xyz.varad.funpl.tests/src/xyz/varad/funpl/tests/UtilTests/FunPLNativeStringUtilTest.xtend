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

import static extension xyz.varad.funpl.util.FunPLNativeStringUtil.*
import static extension org.junit.Assert.*


@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLNativeStringUtilTest {
	
	@Inject	extension ParseHelper<FunProgram>
	@Inject extension ValidationTestHelper
	
	@Test
	def void valueDeclarations() {
		val progCode = 
'''
var i;
const j;
var int i;
const bool j;
'''
		progCode.assertEquals(progCode.parse.stringRepr)
	}
	
	@Test 
	def void functionDeclarationsAndStatements(){
		val progCode = 
'''
function foo(){
}
function int foo2(){
	return -5;
}
function bool bar(){
	return false;
}
function void baz(){
}
function par(int p1, bool p2){
}
function block(){
	5;
	var i = 2;
	return;
}
'''
		progCode.assertEquals(progCode.parse.stringRepr)
	}
	
	@Test def void expressions(){
		val progCode = 
'''
function foo(){
}
function bar(int p1, bool p2){
}
function baz(int p){
	var i = 1;
««« 
	5;
	false;
	(i = 1);
	(2 + 2);
	p;
	i;
	foo();
	bar(1, true);
}
'''
		progCode.assertEquals(progCode.parse.stringRepr)
	}
}