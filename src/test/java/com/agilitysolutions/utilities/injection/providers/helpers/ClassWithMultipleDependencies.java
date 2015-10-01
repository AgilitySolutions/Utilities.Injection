package com.agilitysolutions.utilities.injection.providers.helpers;

public class ClassWithMultipleDependencies implements InterfaceWithMultipleDependencies {

    private InterfaceUnbound interfaceUnbound;
    private InterfaceWithAlternateConstructorWithDifficultlyBoundDependencies interfaceWithAlternateConstructorWithDifficultlyBoundDependencies;
    private InterfaceWithAlternateConstructorWithEasilyBoundDependencies interfaceWithAlternateConstructorWithEasilyBoundDependencies;
    private InterfaceWithDefaultConstructor interfaceWithDefaultConstructor;
    private InterfaceWithImplementedByAnnotation interfaceWithImplementedByAnnotation;
    private InterfaceWithInjectAnnotation interfaceWithInjectAnnotation;
    private InterfaceWithNoConstructor interfaceWithNoConstructor;
    private InterfaceWithoutImplementedByAnnotation interfaceWithoutImplementedByAnnotation;

    public ClassWithMultipleDependencies(InterfaceUnbound interfaceUnbound, InterfaceWithAlternateConstructorWithDifficultlyBoundDependencies interfaceWithAlternateConstructorWithDifficultlyBoundDependencies, InterfaceWithAlternateConstructorWithEasilyBoundDependencies interfaceWithAlternateConstructorWithEasilyBoundDependencies, InterfaceWithDefaultConstructor interfaceWithDefaultConstructor, InterfaceWithImplementedByAnnotation interfaceWithImplementedByAnnotation, InterfaceWithInjectAnnotation interfaceWithInjectAnnotation, InterfaceWithNoConstructor interfaceWithNoConstructor, InterfaceWithoutImplementedByAnnotation interfaceWithoutImplementedByAnnotation) {
        this.interfaceUnbound = interfaceUnbound;
        this.interfaceWithAlternateConstructorWithDifficultlyBoundDependencies = interfaceWithAlternateConstructorWithDifficultlyBoundDependencies;
        this.interfaceWithAlternateConstructorWithEasilyBoundDependencies = interfaceWithAlternateConstructorWithEasilyBoundDependencies;
        this.interfaceWithDefaultConstructor = interfaceWithDefaultConstructor;
        this.interfaceWithImplementedByAnnotation = interfaceWithImplementedByAnnotation;
        this.interfaceWithInjectAnnotation = interfaceWithInjectAnnotation;
        this.interfaceWithNoConstructor = interfaceWithNoConstructor;
        this.interfaceWithoutImplementedByAnnotation = interfaceWithoutImplementedByAnnotation;
    }
}
