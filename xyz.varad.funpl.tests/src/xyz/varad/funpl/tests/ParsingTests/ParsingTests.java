package xyz.varad.funpl.tests.ParsingTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	FunPLParsingTestError.class,
	FunPLParsingTestModel.class,
	FunPLParsingTestExpressions.class
})
public class ParsingTests {

}
