/*
 * generated by Xtext 2.14.0
 */
package xyz.varad.funpl


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class FunPLStandaloneSetup extends FunPLStandaloneSetupGenerated {

	def static void doSetup() {
		new FunPLStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}