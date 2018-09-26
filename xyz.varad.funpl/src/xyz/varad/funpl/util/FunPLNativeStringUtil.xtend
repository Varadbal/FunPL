package xyz.varad.funpl.util

import xyz.varad.funpl.funPL.*

class FunPLNativeStringUtil {
	
	def static dispatch String stringRepr(FunProgram e){
		'''
		«FOR s : e.elements»
		«s.stringRepr»«IF s instanceof ValueDeclaration»;«ENDIF»
		«ENDFOR»
		'''
	}
	
	def static dispatch String stringRepr(ValueDeclaration e){
		'''«IF e.isConstant»const «ELSE»var «ENDIF»«IF e.isSetDeclaredType»«e.declaredType.stringRepr» «ENDIF»«e.name»«IF e.isSetExpression» = «e.expression.stringRepr»«ENDIF»'''
	}
	
	def static dispatch String stringRepr(FunctionDeclaration e){
		'''function «IF e.isSetDeclaredType»«e.declaredType.stringRepr» «ENDIF»«e.name»«e.parameterList.stringRepr»«e.body.stringRepr»'''
	}
	
	def static dispatch String stringRepr(ParameterList e){
		'''(«FOR p : e.parameters»«p.declaredType.stringRepr» «p.name»«IF p !== e.parameters.last», «ENDIF»«ENDFOR»)'''
	}
	
	def static dispatch String stringRepr(Block e){
		'''{
	«FOR s : e.statements»
	«s.stringRepr»«IF ((s instanceof ValueDeclaration) || (s instanceof Expression) || (s instanceof ReturnStatement))»;«ENDIF»
	«ENDFOR»
}
'''
	}
	
	def static dispatch String stringRepr(ReturnStatement e){
		'''return«IF e.isSetExpression» «e.expression.stringRepr»«ENDIF»'''
	}
	
	def static dispatch String stringRepr(ArgumentList e){
		'''(«FOR a : e.arguments»«a.stringRepr»«IF a !== e.arguments.last», «ENDIF»«ENDFOR»)'''
	}
	
////////////////////////////////////////////////////////Expression language////////////////////////////////////////////////////////////////////////////////////////
	
	def static dispatch String stringRepr(Expression e){
		switch(e){
			BinaryExpression: {
				switch(e){
					AssignmentExpression: {
						'''(«e.left.stringRepr» = «e.right.stringRepr»)'''
					}
					PlusExpression: {
						'''(«e.left.stringRepr» + «e.right.stringRepr»)'''
					}
				}
			}
			LiteralExpression: {
				switch(e){
					IntegerLiteralExpression: {
						'''«e.value.toString»'''
					}
					BooleanLiteralExpression: {
						'''«e.value.toString»'''
					}
				}
			}
			ReferenceExpression: {
				var String ret = '''«e.declaration.name»'''
				if(e.isSetArgumentList){
					ret += e.argumentList.stringRepr
				}
				return ret
			}
			
		}
	}
////////////////////////////////////////////////////Type Language/////////////////////////////////////////////////////////////////////////////////////////////////
	def static dispatch String stringRepr(TypeDefinition e){
		switch(e){
			BasicTypeDefinition: {
				switch(e){
					IntegerTypeDefinition:{
						'''int'''
					}
					BooleanTypeDefinition:{
						'''bool'''
					}
					VoidTypeDefinition:{
						'''void'''	
					}
				}
			}
		}
	}
}