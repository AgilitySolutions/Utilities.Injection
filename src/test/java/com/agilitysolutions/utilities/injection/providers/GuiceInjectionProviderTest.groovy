package com.agilitysolutions.utilities.injection.providers

import com.agilitysolutions.utilities.injection.InjectionBinding
import com.agilitysolutions.utilities.injection.providers.helpers.*

class GuiceInjectionProviderTest extends GroovyTestCase {
    private GuiceInjectionProvider _providerWithNoBindings;
    private GuiceInjectionProvider _providerWithFromOnlyBindings;
    private GuiceInjectionProvider _providerWithFromAndToBindings;

    void setUp() {
        super.setUp()

        _providerWithNoBindings = new GuiceInjectionProvider();

        _providerWithFromOnlyBindings = new GuiceInjectionProvider();
        _providerWithFromOnlyBindings.setBindings(getFromOnlyBindings());

        _providerWithFromAndToBindings = new GuiceInjectionProvider();
        _providerWithFromAndToBindings.setBindings(getFromAndToBindings());
    }

    private static List<InjectionBinding> getFromOnlyBindings() {
        ArrayList<InjectionBinding> bindings = new ArrayList<InjectionBinding>();

        bindings.add(new InjectionBinding(ClassWithInjectAnnotation.class));
        bindings.add(new InjectionBinding(ClassWithNoConstructor.class));
        bindings.add(new InjectionBinding(ClassWithDefaultConstructor.class));
        bindings.add(new InjectionBinding(ClassWithAlternateConstructorWithDifficultlyBoundDependencies.class));
        bindings.add(new InjectionBinding(ClassWithAlternateConstructorWithEasilyBoundDependencies.class));
        bindings.add(new InjectionBinding(InterfaceWithImplementedByAnnotation.class));
        bindings.add(new InjectionBinding(InterfaceWithoutImplementedByAnnotation.class));
        bindings.add(new InjectionBinding(ClassWithMultipleDependencies.class));

        return bindings;
    }

    private static List<InjectionBinding> getFromAndToBindings() {
        ArrayList<InjectionBinding> bindings = new ArrayList<InjectionBinding>();

        bindings.add(new InjectionBinding(InterfaceWithInjectAnnotation.class, ClassWithInjectAnnotation.class));
        bindings.add(new InjectionBinding(InterfaceWithNoConstructor.class, ClassWithNoConstructor.class));
        bindings.add(new InjectionBinding(InterfaceWithDefaultConstructor.class, ClassWithDefaultConstructor.class));
        bindings.add(new InjectionBinding(InterfaceWithAlternateConstructorWithDifficultlyBoundDependencies.class, ClassWithAlternateConstructorWithDifficultlyBoundDependencies.class));
        bindings.add(new InjectionBinding(InterfaceWithAlternateConstructorWithEasilyBoundDependencies.class, ClassWithAlternateConstructorWithEasilyBoundDependencies.class));
        bindings.add(new InjectionBinding(InterfaceWithImplementedByAnnotation.class, ClassWithImplementedByAnnotation.class));
        bindings.add(new InjectionBinding(InterfaceWithoutImplementedByAnnotation.class, ClassWithoutImplementedByAnnotation.class));
        bindings.add(new InjectionBinding(InterfaceWithMultipleDependencies.class, ClassWithMultipleDependencies.class));

        return bindings;
    }

    // With bindings - from only
    // binding to self with @Inject annotation
    public void testGetInstanceWithFromOnlyBindingsWithInjectAnnotation() throws Exception {
        ClassWithInjectAnnotation instance = _providerWithFromOnlyBindings.getInstance(ClassWithInjectAnnotation.class);
        assertEquals(ClassWithInjectAnnotation.class, instance.getClass());
    }

    // binding to self with no constructor
    public void testGetInstanceWithFromOnlyBindingsWithNoConstructor() throws Exception {
        ClassWithNoConstructor instance = _providerWithFromOnlyBindings.getInstance(ClassWithNoConstructor.class);
        assertEquals(ClassWithNoConstructor.class, instance.getClass());
    }

    // binding to self with default constructor
    public void testGetInstanceWithFromOnlyBindingsWithDefaultConstructor() throws Exception {
        ClassWithDefaultConstructor instance = _providerWithFromOnlyBindings.getInstance(ClassWithDefaultConstructor.class);
        assertEquals(ClassWithDefaultConstructor.class, instance.getClass());
    }

    // binding to self with alternate constructor with easily bound dependencies
    public void testGetInstanceWithFromOnlyBindingsWithAlternateConstructorWithEasilyBoundDependencies() throws Exception {
        ClassWithAlternateConstructorWithEasilyBoundDependencies instance = _providerWithFromOnlyBindings.getInstance(ClassWithAlternateConstructorWithEasilyBoundDependencies.class);
        assertEquals(ClassWithAlternateConstructorWithEasilyBoundDependencies.class, instance.getClass());
    }

    // binding to self with alternate constructor with difficultly bound dependencies
    public void testGetInstanceWithFromOnlyBindingsWithAlternateConstructorWithDifficultlyBoundDependencies() throws Exception {
        ClassWithAlternateConstructorWithDifficultlyBoundDependencies instance = _providerWithFromOnlyBindings.getInstance(ClassWithAlternateConstructorWithDifficultlyBoundDependencies.class);
        assertEquals(ClassWithAlternateConstructorWithDifficultlyBoundDependencies.class, instance.getClass());
    }

    // automatically bind interface to implementation with @ImplementedBy annotation
    public void testGetInstanceWithFromOnlyBindingsWithImplementedByAnnotation() throws Exception {
        InterfaceWithImplementedByAnnotation instance = _providerWithFromOnlyBindings.getInstance(InterfaceWithImplementedByAnnotation.class);
        assertEquals(ClassWithImplementedByAnnotation.class, instance.getClass());
    }

    // automatically bind interface to implementation without @ImplementedBy annotation
    public void testGetInstanceWithFromOnlyBindingsWithoutImplementedByAnnotation() throws Exception {
        InterfaceWithoutImplementedByAnnotation instance = _providerWithFromOnlyBindings.getInstance(InterfaceWithoutImplementedByAnnotation.class);
        assertEquals(ClassWithoutImplementedByAnnotation.class, instance.getClass());
    }

    // binding to self with multiple dependencies
    public void testGetInstanceWithFromOnlyBindingsWithMultipleDependencies() throws Exception {
        ClassWithMultipleDependencies instance = _providerWithFromOnlyBindings.getInstance(ClassWithMultipleDependencies.class);
        assertEquals(ClassWithMultipleDependencies.class, instance.getClass());
    }

    // With bindings - from and to
    // binding to object with @Inject annotation
    public void testGetInstanceWithFromAndToBindingsWithInjectAnnotation() throws Exception {
        InterfaceWithInjectAnnotation instance = _providerWithFromAndToBindings.getInstance(InterfaceWithInjectAnnotation.class);
        assertEquals(ClassWithInjectAnnotation.class, instance.getClass());
    }

    // binding to object with no constructor
    public void testGetInstanceWithFromAndToBindingsWithNoConstructor() throws Exception {
        InterfaceWithNoConstructor instance = _providerWithFromAndToBindings.getInstance(InterfaceWithNoConstructor.class);
        assertEquals(ClassWithNoConstructor.class, instance.getClass());
    }

    // binding to object with default constructor
    public void testGetInstanceWithFromAndToBindingsWithDefaultConstructor() throws Exception {
        InterfaceWithDefaultConstructor instance = _providerWithFromAndToBindings.getInstance(InterfaceWithDefaultConstructor.class);
        assertEquals(ClassWithDefaultConstructor.class, instance.getClass());
    }

    // binding to object with alternate constructor with easily bound dependencies
    public void testGetInstanceWithFromAndToBindingsWithAlternateConstructorWithEasilyBoundDependencies() throws Exception {
        InterfaceWithAlternateConstructorWithEasilyBoundDependencies instance = _providerWithFromAndToBindings.getInstance(InterfaceWithAlternateConstructorWithEasilyBoundDependencies.class);
        assertEquals(ClassWithAlternateConstructorWithEasilyBoundDependencies.class, instance.getClass());
    }

    // binding to object with alternate constructor with difficultly bound dependencies
    public void testGetInstanceWithFromAndToBindingsWithAlternateConstructorDifficultlyBoundDependencies() throws Exception {
        InterfaceWithAlternateConstructorWithDifficultlyBoundDependencies instance = _providerWithFromAndToBindings.getInstance(InterfaceWithAlternateConstructorWithDifficultlyBoundDependencies.class);
        assertEquals(ClassWithAlternateConstructorWithDifficultlyBoundDependencies.class, instance.getClass());
    }

    // automatically bind interface to implementation with @ImplementedBy annotation
    public void testGetInstanceWithFromAndToBindingsWithImplementedByAnnotation() throws Exception {
        InterfaceWithImplementedByAnnotation instance = _providerWithFromAndToBindings.getInstance(InterfaceWithImplementedByAnnotation.class);
        assertEquals(ClassWithImplementedByAnnotation.class, instance.getClass());
    }

    // automatically bind interface to implementation without @ImplementedBy annotation
    public void testGetInstanceWithFromAndToBindingsWithoutImplementedByAnnotation() throws Exception {
        InterfaceWithoutImplementedByAnnotation instance = _providerWithFromAndToBindings.getInstance(InterfaceWithoutImplementedByAnnotation.class);
        assertEquals(ClassWithoutImplementedByAnnotation.class, instance.getClass());
    }

    // binding to object with multiple dependencies
    public void testGetInstanceWithFromAndToBindingsWithMultipleDependencies() throws Exception {
        InterfaceWithMultipleDependencies instance = _providerWithFromAndToBindings.getInstance(InterfaceWithMultipleDependencies.class);
        assertEquals(ClassWithMultipleDependencies.class, instance.getClass());
    }

    // Without bindings
    // binding to self with @Inject annotation
    public void testGetInstanceWithoutBindingsWithInjectAnnotation() throws Exception {
        ClassWithInjectAnnotation instance = _providerWithNoBindings.getInstance(ClassWithInjectAnnotation.class);
        assertEquals(ClassWithInjectAnnotation.class, instance.getClass());
    }

    // binding to self with no constructor
    public void testGetInstanceWithoutBindingsWithNoConstructor() throws Exception {
        ClassWithNoConstructor instance = _providerWithNoBindings.getInstance(ClassWithNoConstructor.class);
        assertEquals(ClassWithNoConstructor.class, instance.getClass());
    }

    // binding to self with default constructor
    public void testGetInstanceWithoutBindingsWithDefaultConstructor() throws Exception {
        ClassWithDefaultConstructor instance = _providerWithNoBindings.getInstance(ClassWithDefaultConstructor.class);
        assertEquals(ClassWithDefaultConstructor.class, instance.getClass());
    }

    // binding to self with alternate constructor with easily bound dependencies
    public void testGetInstanceWithoutBindingsWithAlternateConstructorWithEasilyBoundDependencies() throws Exception {
        ClassWithAlternateConstructorWithEasilyBoundDependencies instance = _providerWithNoBindings.getInstance(ClassWithAlternateConstructorWithEasilyBoundDependencies.class);
        assertEquals(ClassWithAlternateConstructorWithEasilyBoundDependencies.class, instance.getClass());
    }

    // binding to self with alternate constructor with difficultly bound dependencies
    public void testGetInstanceWithoutBindingsWithAlternateConstructorWithDifficultlyBoundDependencies() throws Exception {
        ClassWithAlternateConstructorWithDifficultlyBoundDependencies instance = _providerWithNoBindings.getInstance(ClassWithAlternateConstructorWithDifficultlyBoundDependencies.class);
        assertEquals(ClassWithAlternateConstructorWithDifficultlyBoundDependencies.class, instance.getClass());
    }

    // automatically bind interface to implementation with @ImplementedBy annotation
    public void testGetInstanceWithoutBindingsWithImplementedByAnnotation() throws Exception {
        InterfaceWithImplementedByAnnotation instance = _providerWithNoBindings.getInstance(InterfaceWithImplementedByAnnotation.class);
        assertEquals(ClassWithImplementedByAnnotation.class, instance.getClass());
    }

    // automatically bind interface to implementation without @ImplementedBy annotation
    public void testGetInstanceWithoutBindingsWithoutImplementedByAnnotation() throws Exception {
        InterfaceWithoutImplementedByAnnotation instance = _providerWithNoBindings.getInstance(InterfaceWithoutImplementedByAnnotation.class);
        assertEquals(ClassWithoutImplementedByAnnotation.class, instance.getClass());
    }

    // binding to object with multiple dependencies
    public void testGetInstanceWithoutBindingsWithMultipleDependencies() throws Exception {
        InterfaceWithMultipleDependencies instance = _providerWithNoBindings.getInstance(InterfaceWithMultipleDependencies.class);
        assertEquals(ClassWithMultipleDependencies.class, instance.getClass());
    }

    // binding to object with short suffix
    public void testGetInstanceWithShortSuffix() throws Exception {
        WithShortSuffix instance = _providerWithNoBindings.getInstance(WithShortSuffix.class);
        assertEquals(WithShortSuffixImpl.class, instance.getClass());
    }

    // binding to object with long suffix
    public void testGetInstanceWithLongSuffix() throws Exception {
        WithLongSuffix instance = _providerWithNoBindings.getInstance(WithLongSuffix.class);
        assertEquals(WithLongSuffixImplementation.class, instance.getClass());
    }

    // binding to object with class suffix
    public void testGetInstanceWithClassSuffix() throws Exception {
        WithClassSuffix instance = _providerWithNoBindings.getInstance(WithClassSuffix.class);
        assertEquals(WithClassSuffixClass.class, instance.getClass());
    }
}
