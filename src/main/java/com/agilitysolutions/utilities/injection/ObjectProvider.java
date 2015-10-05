package com.agilitysolutions.utilities.injection;

import com.agilitysolutions.utilities.injection.interfaces.providers.InjectionProvider;

import java.util.List;

public class ObjectProvider {
    private final InjectionProvider _injectionProvider;

    public ObjectProvider() {
        _injectionProvider = getInjectionProvider();
    }

    public ObjectProvider(List<InjectionBinding> bindings) {
        _injectionProvider = getInjectionProvider();
        _injectionProvider.setBindings(bindings);
    }

    public ObjectProvider(InjectionProvider injectionProvider) {
        _injectionProvider = injectionProvider;
    }

    public <T> T getInstance(Class<T> type) {
        return _injectionProvider.getInstance(type);
    }

    private InjectionProvider getInjectionProvider() {
        String providerClassName = "com.agilitysolutions.utilities.injection.providers.GuiceInjectionProvider";

        try {
            Class providerClass = Class.forName(providerClassName);
            return (InjectionProvider) providerClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }
}
