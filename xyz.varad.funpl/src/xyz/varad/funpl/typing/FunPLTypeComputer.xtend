package xyz.varad.funpl.typing

import xyz.varad.funpl.funPL.*

import static extension xyz.varad.funpl.util.FunPLModelUtil.*
import static extension xyz.varad.funpl.util.FunPLTypingUtil.*

class FunPLTypeComputer {
	private static val factory = FunPLFactory::eINSTANCE
	
	def static Type computeDeepType(TypeInferredElement e){
		switch(e){
			Declaration:{
				switch(e){
					ValueDeclaration:{			//TODO validator: else must never happen
						if(e.isSetExpression){	
							e.expression.returnType
						}else if (e.isSetDeclaredType){//TODO validator: declared&inferred match
							e.declaredType
						}else{
							getVoid
							//TODO throw exception?
						}
					}
					FunctionDeclaration:{
						val rs = e.returnStatements
						if(!rs.empty){
							rs.head.returnType	//TODO validator: returns match
						}else if(e.isSetDeclaredType){//TODO validator: declared&inferred match
							e.declaredType
						}else{
							getVoid
						}
					}
				}
			}
			Expression:{
				switch(e){
					ReferenceExpression:{
						e.declaration.returnType
					}	
					PlusExpression:{
						getInteger	//TODO other types?
					}
					AssignmentExpression:{
						e.left.returnType
					}
					IntegerLiteralExpression:{
						getInteger
					}
					BooleanLiteralExpression:{
						getBoolean
					}
				}
			}
			ReturnStatement:{
				if(e.isSetExpression){
					return e.expression.returnType
				}else{
					return getVoid
				}
			}
			
			default: getVoid //TODO throw exception (should not happen)
		}
	}
	
	
	def private static VoidTypeDefinition getVoid(){
		return factory.createVoidTypeDefinition
	}
	
	def private static IntegerTypeDefinition getInteger(){
		return factory.createIntegerTypeDefinition
	}
	
	def private static BooleanTypeDefinition getBoolean(){
		return factory.createBooleanTypeDefinition
	}
}