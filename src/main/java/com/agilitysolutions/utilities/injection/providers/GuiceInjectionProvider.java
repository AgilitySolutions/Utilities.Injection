package com.agilitysolutions.utilities.injection.providers;

import com.agilitysolutions.utilities.injection.InjectionBinding;
import com.agilitysolutions.utilities.injection.interfaces.providers.InjectionProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.List;

public class GuiceInjectionProvider implements InjectionProvider {
    private List<InjectionBinding> _bindings;

    public GuiceInjectionProvider() {
        _bindings = new ArrayList<InjectionBinding>();
    }

    public void setBindings(List<InjectionBinding> bindings) {
        _bindings = bindings;
    }

    public <T> T getInstance(Class<T> type) {
        T instance;

        try {
            instance = createInjector().getInstance(type);
        } catch (Exception e) {
            _bindings.add(new InjectionBinding(type));
            instance = createInjector().getInstance(type);
        }

        return instance;
    }

    private Injector createInjector() {
        GuiceBindingModule bindingModule = _bindings.size() == 0 ? new GuiceBindingModule() : new GuiceBindingModule(_bindings);

        return Guice.createInjector(bindingModule);
    }
}
