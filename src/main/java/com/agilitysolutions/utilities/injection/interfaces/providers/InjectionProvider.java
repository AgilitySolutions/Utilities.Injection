package com.agilitysolutions.utilities.injection.interfaces.providers;

import com.agilitysolutions.utilities.injection.InjectionBinding;

import java.util.List;

public interface InjectionProvider {
    void setBindings(List<InjectionBinding> bindings);

    <T> T getInstance(Class<T> type);
}
