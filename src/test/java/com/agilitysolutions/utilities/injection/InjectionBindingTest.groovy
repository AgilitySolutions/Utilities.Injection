package com.agilitysolutions.utilities.injection

class InjectionBindingTest extends GroovyTestCase {
    protected final Class fromClass = String.class;
    protected final Class toClass = Integer.class;

    void setUp() {
        super.setUp()
    }

    public void testGetFromWithFromOnlyInConstructor() throws Exception {
        def injectionBinding = new InjectionBinding(fromClass);

        assertEquals(fromClass, injectionBinding.getFrom());
    }

    public void testGetFromWithFromAndToInConstructor() throws Exception {
        def injectionBinding = new InjectionBinding(fromClass, toClass);

        assertEquals(fromClass, injectionBinding.getFrom());
    }

    public void testGetToWithFromOnlyInConstructor() throws Exception {
        def injectionBinding = new InjectionBinding(fromClass);

        assertEquals(null, injectionBinding.getTo());
    }

    public void testGetToWithFromAndToInConstructor() throws Exception {
        def injectionBinding = new InjectionBinding(fromClass, toClass);

        assertEquals(toClass, injectionBinding.getTo());
    }
}
