package xyz.varad.funpl.validation;

import org.eclipse.xtext.validation.ComposedChecks;

@ComposedChecks(validators= {
		FunPLScopingValidator.class,
		FunPLExpressionsValidator.class,
		FunPLDeclarationsValidator.class
})
public class FunPLValidator extends AbstractFunPLValidator {

}
