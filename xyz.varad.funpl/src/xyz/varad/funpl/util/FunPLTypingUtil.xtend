package xyz.varad.funpl.util

import xyz.varad.funpl.funPL.*


class FunPLTypingUtil {
	
	def static boolean isInteger(Type t){
		return (t instanceof IntegerTypeDefinition)
	}
	
	def static boolean isBoolean(Type t){
		return (t instanceof BooleanTypeDefinition)
	}
	
	def static boolean isVoid(Type t){
		return(t instanceof VoidTypeDefinition)
	}
	
	
	
}