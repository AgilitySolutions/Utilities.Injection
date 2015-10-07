package com.agilitysolutions.utilities.injection.providers

import com.agilitysolutions.utilities.injection.providers.helpers.*
import javafx.util.Pair

class GuiceInjectionProviderTest extends GroovyTestCase {
    private GuiceInjectionProvider _providerWithNoBindings;
    private GuiceInjectionProvider _providerWithFromOnlyBindings;
    private GuiceInjectionProvider _providerWithFromAndToBindings;

    void setUp() {
        super.setUp()

        _providerWithNoBindings = new GuiceInjectionProvider();

        _providerWithFromOnlyBindings = new GuiceInjectionProvider();

        for (Pair<Class, Class> binding : getFromOnlyBindings()) {
            _providerWithFromOnlyBindings.bind(binding.getKey());
        }

        _providerWithFromAndToBindings = new GuiceInjectionProvider();

        for (Pair<Class, Class> binding : getFromAndToBindings()) {
            _providerWithFromAndToBindings.bind(binding.getKey(), binding.getValue());
        }
    }

    private static List<Pair<Class, Class>> getFromOnlyBindings() {
        ArrayList<Pair<Class, Class>> bindings = new ArrayList<Pair<Class, Class>>();

        bindings.add(new Pair<Class, Class>(ClassWithInjectAnnotation.class, null));
        bindings.add(new Pair<Class, Class>(ClassWithNoConstructor.class, null));
        bindings.add(new Pair<Class, Class>(ClassWithDefaultConstructor.class, null));
        bindings.add(new Pair<Class, Class>(ClassWithAlternateConstructorWithDifficultlyBoundDependencies.class, null));
        bindings.add(new Pair<Class, Class>(ClassWithAlternateConstructorWithEasilyBoundDependencies.class, null));
        bindings.add(new Pair<Class, Class>(InterfaceWithImplementedByAnnotation.class, null));
        bindings.add(new Pair<Class, Class>(InterfaceWithoutImplementedByAnnotation.class, null));
        bindings.add(new Pair<Class, Class>(ClassWithMultipleDependencies.class, null));

        return bindings;
    }

    private static List<Pair<Class, Class>> getFromAndToBindings() {
        ArrayList<Pair<Class, Class>> bindings = new ArrayList<Pair<Class, Class>>();

        bindings.add(new Pair<Class, Class>(InterfaceWithInjectAnnotation.class, ClassWithInjectAnnotation.class));
        bindings.add(new Pair<Class, Class>(InterfaceWithNoConstructor.class, ClassWithNoConstructor.class));
        bindings.add(new Pair<Class, Class>(InterfaceWithDefaultConstructor.class, ClassWithDefaultConstructor.class));
        bindings.add(new Pair<Class, Class>(InterfaceWithAlternateConstructorWithDifficultlyBoundDependencies.class, ClassWithAlternateConstructorWithDifficultlyBoundDependencies.class));
        bindings.add(new Pair<Class, Class>(InterfaceWithAlternateConstructorWithEasilyBoundDependencies.class, ClassWithAlternateConstructorWithEasilyBoundDependencies.class));
        bindings.add(new Pair<Class, Class>(InterfaceWithImplementedByAnnotation.class, ClassWithImplementedByAnnotation.class));
        bindings.add(new Pair<Class, Class>(InterfaceWithoutImplementedByAnnotation.class, ClassWithoutImplementedByAnnotation.class));
        bindings.add(new Pair<Class, Class>(InterfaceWithMultipleDependencies.class, ClassWithMultipleDependencies.class));

        return bindings;
    }

    private static List<Pair<Class, Class>> getFromAndToBindingsWithNoPublicConstructor() {
        ArrayList<Pair<Class, Class>> bindings = new ArrayList<Pair<Class, Class>>();

        bindings.add(new Pair<Class, Class>(InterfaceWithNoPublicConstructor.class, ClassWithNoPublicConstructor.class));

        return bindings;
    }

    private static List<Pair<Class, Class>> getFromBindingsWithNoImplementation() {
        ArrayList<Pair<Class, Class>> bindings = new ArrayList<Pair<Class, Class>>();

        bindings.add(new Pair<Class, Class>(InterfaceWithNoImplementation.class, null));

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

    // binding to object with no public constructor
    public void testGetInstanceWithNoPublicConstructor() throws Exception {
        Exception exception = null;

        try {
            InterfaceWithNoPublicConstructor instance = _providerWithNoBindings.getInstance(InterfaceWithNoPublicConstructor.class);
        }
        catch (Exception ex) {
            exception = ex;
        }

        assertNotNull(exception);
        assertEquals("com.google.inject.CreationException",  exception.class.typeName);
    }

    // binding to object with no public constructor with bindings
    public void testGetInstanceWithNoPublicConstructorWithBinding() throws Exception {
        Exception exception = null;

        for (Pair<Class, Class> binding : getFromAndToBindingsWithNoPublicConstructor()) {
            _providerWithNoBindings.bind(binding.getKey(), binding.getValue());
        }

        try {
            InterfaceWithNoPublicConstructor instance = _providerWithNoBindings.getInstance(InterfaceWithNoPublicConstructor.class);
        }
        catch (Exception ex) {
            exception = ex;
        }

        assertNotNull(exception);
        assertEquals("com.google.inject.CreationException",  exception.class.typeName);
    }

    // binding to with no implementation
    public void testGetInstanceWithNoImplementation() throws Exception {
        Exception exception = null;

        for (Pair<Class, Class> binding : getFromBindingsWithNoImplementation()) {
            _providerWithNoBindings.bind(binding.getKey(), binding.getValue());
        }

        try {
            InterfaceWithNoImplementation instance = _providerWithNoBindings.getInstance(InterfaceWithNoImplementation.class);
        }
        catch (Exception ex) {
            exception = ex;
        }

        assertNotNull(exception);
        assertEquals("com.google.inject.CreationException",  exception.class.typeName);
    }
}
