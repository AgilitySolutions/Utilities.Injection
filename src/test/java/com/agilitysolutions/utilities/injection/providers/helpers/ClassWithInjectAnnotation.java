package com.agilitysolutions.utilities.injection.providers.helpers;

import com.google.inject.Inject;

public class ClassWithInjectAnnotation implements InterfaceWithInjectAnnotation {
    @Inject
    public ClassWithInjectAnnotation() {

    }
}
