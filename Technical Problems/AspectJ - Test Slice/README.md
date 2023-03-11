<h1>How to test AOP with minimum cost</h1>

<p>Problem: add an integration (slice test) for an aspect to an existing project (of any size and degree of dependencies at application startup).</p>

<h3>FactoryAspectWithAspectJProxyFactoryTest</h3>

<p>
WORKS: YES<br>
TESTS AOP CALL: YES<br>
COST: MOCK TEST (partially)<br>
CONCLUSION: This testing method uses AspectJProxyFactory, which requires the presence of an interface for the class
under test, and there must also be a method in the interface (that is, the presenceof a marker interface does not 
solve this problem)<br>
MEDIUM SPEED (on my machine): 832 ms<br></p>

<h3>FactoryAspectWithContextConfigurationTest</h3>

<p>
WORKS: YES<br>
TESTS AOP CALL: YES<br>
COST: RAISE ALL CONTEXT<br>
CONCLUSION: The option is working, but raising the entire context is not the best option in a large application.<br>
MEDIUM SPEED (on my machine): 534 ms<br></p>

<h3>FactoryAspectWithIntegrationMockTest</h3>

<p>
WORKS: YES<br>
TESTS AOP CALL: NO<br>
COST: MOCK TEST<br>
CONCLUSION: Makes a direct call to the factory, does not call Aspect.<br>
MEDIUM SPEED (on my machine): 97 ms<br></p>

<h3>FactoryAspectWithSpringBootAnnotationTest</h3>

<p>
WORKS: YES<br>
TESTS AOP CALL: YES<br>
COST: RAISE ALL CONTEXT<br>
CONCLUSION: The option is working, but raising the entire context is not the best option in a large application.<br>
MEDIUM SPEED (on my machine): 483 ms<br></p>

<h3>FactoryAspectWithUnitMockTest</h3>

<p>
WORKS: YES<br>
TESTS AOP CALL: NO<br>
COST: MOCK TEST<br>
CONCLUSION: The variant is working, but it tests the direct call of the aspect, but not the full work of AOP.<br>
MEDIUM SPEED (on my machine): 46 ms<br></p>

<h1>Conclusion</h1>

<p>The best option is FactoryAspectWithAspectJProxyFactoryTest. There are some disadvantages: you need an interface 
and the presence of all methods (the marker interface is not suitable) - IT REQUIRED, rather high execution speed on a small
application (compared to @SpringBootTest, which will only grow further and the loading 
speed will fall - this is a plus for AspectJProxyFactory, since the speed will remain the same).

FactoryAspectWithSpringBootAnnotationTest is only a good option if your application is small and doesn't require a lot 
of configuration when the application starts up. Otherwise, for each change in your program, you will have to make 
additional changes in the test - this is the biggest minus.</p>