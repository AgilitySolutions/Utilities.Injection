package com.agilitysolutions.utilities.injection.interfaces.providers;

public interface InjectionProvider {
    <T> T getInstance(Class<T> type);

    void bind(Class from);
    void bind(Class from, Class to);
}
