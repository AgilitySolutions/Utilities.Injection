package com.agilitysolutions.utilities.injection.providers.helpers;

public class ClassWithAlternateConstructorWithDifficultlyBoundDependencies implements InterfaceWithAlternateConstructorWithDifficultlyBoundDependencies {
    private InterfaceUnbound interfaceUnbound;

    public ClassWithAlternateConstructorWithDifficultlyBoundDependencies(InterfaceUnbound interfaceUnbound) {
        this.interfaceUnbound = interfaceUnbound;
    }
}
