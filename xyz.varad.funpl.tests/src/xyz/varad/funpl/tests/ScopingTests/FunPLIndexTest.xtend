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
import xyz.varad.funpl.scoping.FunPLIndex
import com.google.inject.Provider
import org.eclipse.emf.ecore.resource.ResourceSet

import static extension xyz.varad.funpl.util.FunPLModelUtil.*
import static extension org.junit.Assert.*


@RunWith(XtextRunner)
@InjectWith(FunPLInjectorProvider)
class FunPLIndexTest {
	
	@Inject	extension ParseHelper<FunProgram>
	@Inject Provider<ResourceSet> resourceSetProvider
	@Inject extension ValidationTestHelper
	
	@Test
	def void multipleFileResolution(){
		val resourceSet = resourceSetProvider.get
		val first = 
		'''
		package first;
		var i = 1;
		'''.parse(resourceSet)
		val second = 
		'''
		package second;
		var j = first.i;
		'''.parse(resourceSet)
		//first.assertNoErrors
		//second.assertNoErrors
		
	}
	
	@Test
	def void multipleFileResolution2(){
		val resourceSet = resourceSetProvider.get
		val first = 
		'''
		package my.first.pack;
		function foo(){
			var int i;
		}
		'''.parse(resourceSet)
		val second = 
		'''
		package my.second.pack;
		function bar(){
			my.first.pack.foo;
		}
		'''.parse(resourceSet)
		//first.assertNoErrors
		second.assertNoErrors
		
		val rs = resourceSet.getResources
		for(i : rs){
			val cnt = i.allContents
			while(cnt.hasNext){
				var m = cnt.next
				if(m instanceof NamedElement)
					println((m as NamedElement). name)
			}
		}
		
	}

	
}