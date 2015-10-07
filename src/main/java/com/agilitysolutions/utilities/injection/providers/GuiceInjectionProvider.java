package com.agilitysolutions.utilities.injection.providers;

import com.agilitysolutions.utilities.injection.interfaces.providers.InjectionProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceInjectionProvider implements InjectionProvider {
    private GuiceBindingModule _bindingModule;

    public GuiceInjectionProvider() {
        _bindingModule = new GuiceBindingModule();
    }

    public <T> T getInstance(Class<T> type) {
        T instance;

        try {
            instance = createInjector().getInstance(type);
        } catch (Exception e) {
            bind(type);
            instance = createInjector().getInstance(type);
        }

        return instance;
    }

    public void bind(Class from) {
        _bindingModule.addBinding(from);
    }

    public void bind(Class from, Class to) {
        _bindingModule.addBinding(from, to);
    }

    private Injector createInjector() {
         return Guice.createInjector(_bindingModule);
    }
}
