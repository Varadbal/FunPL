package xyz.varad.funpl.scoping;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import xyz.varad.funpl.funPL.FunPLPackage;

@SuppressWarnings("all")
public class FunPLIndex {
  @Inject
  private ResourceDescriptionsProvider rdp;
  
  public IResourceDescription getResourceDescription(final EObject o) {
    IResourceDescription _xblockexpression = null;
    {
      final IResourceDescriptions index = this.rdp.getResourceDescriptions(o.eResource());
      _xblockexpression = index.getResourceDescription(o.eResource().getURI());
    }
    return _xblockexpression;
  }
  
  public Iterable<IEObjectDescription> getExportedEObjectDescriptions(final EObject o) {
    return this.getResourceDescription(o).getExportedObjects();
  }
  
  public Iterable<IEObjectDescription> getExportedFunctionsEObjectDescriptions(final EObject o) {
    return this.getResourceDescription(o).getExportedObjectsByType(FunPLPackage.eINSTANCE.getFunctionDeclaration());
  }
}
