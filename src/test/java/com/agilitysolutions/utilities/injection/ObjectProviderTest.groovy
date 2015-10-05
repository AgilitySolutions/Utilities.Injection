package com.agilitysolutions.utilities.injection

import com.agilitysolutions.utilities.injection.interfaces.providers.InjectionProvider
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
}
