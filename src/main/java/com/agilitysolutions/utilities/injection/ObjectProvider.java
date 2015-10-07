package com.agilitysolutions.utilities.injection;

import com.agilitysolutions.utilities.injection.interfaces.providers.InjectionProvider;
import com.agilitysolutions.utilities.injection.providers.GuiceInjectionProvider;

public class ObjectProvider {
    private final InjectionProvider _injectionProvider;

    public ObjectProvider() {
        _injectionProvider = getInjectionProvider();
    }

    public ObjectProvider(InjectionProvider injectionProvider) {
        _injectionProvider = injectionProvider;
    }

    public <T> T getInstance(Class<T> type) {
        return _injectionProvider.getInstance(type);
    }

    public void addBinding(Class from, Class to) {
        _injectionProvider.bind(from, to);
    }

    private InjectionProvider getInjectionProvider() {
        return new GuiceInjectionProvider();
    }
}
