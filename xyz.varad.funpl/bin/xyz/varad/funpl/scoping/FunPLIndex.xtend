package xyz.varad.funpl.scoping

import com.google.inject.Inject
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.emf.ecore.EObject
import xyz.varad.funpl.funPL.FunPLPackage

class FunPLIndex {
	@Inject ResourceDescriptionsProvider rdp
	
	def getResourceDescription(EObject o){
		val index = rdp.getResourceDescriptions(o.eResource)
		index.getResourceDescription(o.eResource.URI)
	}
	
	def getExportedEObjectDescriptions(EObject o){
		o.getResourceDescription.getExportedObjects
	}
	
	//getExportedClassesEObjectDescriptions(EObject o) and the similar ones
	
}