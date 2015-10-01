package com.agilitysolutions.utilities.injection.providers.helpers;

public class ClassWithAlternateConstructorWithEasilyBoundDependencies implements InterfaceWithAlternateConstructorWithEasilyBoundDependencies {
    private ClassWithNoConstructor classWithNoConstructor;

    public ClassWithAlternateConstructorWithEasilyBoundDependencies(ClassWithNoConstructor classWithNoConstructor) {
        this.classWithNoConstructor = classWithNoConstructor;
    }
}
