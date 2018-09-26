package xyz.varad.funpl.scoping

import com.google.inject.Inject
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.emf.ecore.EObject
import xyz.varad.funpl.funPL.FunPLPackage

class FunPLIndex {
	@Inject ResourceDescriptionsProvider rdp
	
	def getResourceDescription(EObject o){
		val index = rdp.getResourceDescriptions(o.eResource)	//The index itself (implemented through an IResourceDescriptions interface)
		index.getResourceDescription(o.eResource.URI)			//Gets the ResourceDescription of a Resource (given with its URI)
	}
	
	def getExportedEObjectDescriptions(EObject o){
		o.getResourceDescription.getExportedObjects				//Get all the externally visible (globally exported) IEObjectDescription elements (of the resource of the object)
	}
	
	def getExportedFunctionsEObjectDescriptions(EObject o){		//Same as getExportedEObjectDescriptions just filtered
		o.getResourceDescription.getExportedObjectsByType(FunPLPackage.eINSTANCE.functionDeclaration)
	}
	//getExportedClassesEObjectDescriptions(EObject o) and the similar ones when added
	
}