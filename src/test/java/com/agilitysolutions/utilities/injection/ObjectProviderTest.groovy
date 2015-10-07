package com.agilitysolutions.utilities.injection

import com.agilitysolutions.utilities.injection.interfaces.providers.InjectionProvider
import com.agilitysolutions.utilities.injection.providers.helpers.ClassWithMultipleDependencies
import com.agilitysolutions.utilities.injection.providers.helpers.InterfaceWithMultipleDependencies
import org.mockito.Mockito

class ObjectProviderTest extends GroovyTestCase {
    private InjectionProvider _injectionProvider;
    private ObjectProvider _objectProvider;

    void setUp() {
        super.setUp()

        _injectionProvider =  Mockito.mock(InjectionProvider.class)
        _objectProvider =  new ObjectProvider(_injectionProvider);
    }

    void testGetInstance() {
        _objectProvider.getInstance(String.class);
        Mockito.verify(_injectionProvider, Mockito.times(1)).getInstance(String.class);
    }

    void testGetInstanceEmptyConstructor() {
        _objectProvider =  new ObjectProvider();

        Object instance = _objectProvider.getInstance(String.class);

        assertEquals(String.class, instance.class);
    }

    void testGetInstanceEmptyConstructorWithBinding() {
        _objectProvider =  new ObjectProvider();
        _objectProvider.addBinding(InterfaceWithMultipleDependencies.class, ClassWithMultipleDependencies.class);

        Object instance = _objectProvider.getInstance(InterfaceWithMultipleDependencies.class);

        assertEquals(ClassWithMultipleDependencies.class, instance.class);
    }
}
