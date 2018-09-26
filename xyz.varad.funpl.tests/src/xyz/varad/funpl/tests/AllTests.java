package xyz.varad.funpl.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import xyz.varad.funpl.tests.ParsingTests.*;
import xyz.varad.funpl.tests.ScopingTests.*;
import xyz.varad.funpl.tests.UtilTests.*;

@RunWith(Suite.class)
@SuiteClasses({
	ParsingTests.class,	
	
	UtilTests.class,
	
	ScopingTests.class
	
})
public class AllTests {

}
