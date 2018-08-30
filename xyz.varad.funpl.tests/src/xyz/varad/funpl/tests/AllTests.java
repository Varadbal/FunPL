package xyz.varad.funpl.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{FunPLParsingTest.class, 
	FunPLModelUtilTest.class,
	FunPLValidatorTest.class,
	FunPLScopeProviderTest.class}
)
public class AllTests {

}
