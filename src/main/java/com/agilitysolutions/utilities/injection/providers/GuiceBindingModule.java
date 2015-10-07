package com.agilitysolutions.utilities.injection.providers;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import javafx.util.Pair;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GuiceBindingModule extends AbstractModule {
    private final List<Pair<Class, Class>> _bindings;

    public GuiceBindingModule() {
        _bindings = new ArrayList<Pair<Class, Class>>();
    }

    public void addBinding(Class from) {
        Pair<Class, Class> pair = new Pair<Class, Class>(from, null);
        addBinding(pair);
    }

    public void addBinding(Class from, Class to) {
        Pair<Class, Class> pair = new Pair<Class, Class>(from, to);
        addBinding(pair);
    }

    private void addBinding(Pair<Class, Class> binding) {
        if (!isIncludedInBindings(binding.getKey())) {
            _bindings.add(binding);
        }
    }

    protected void configure() {
        for (Pair<Class, Class> binding : _bindings) {
            doBinding(binding);
        }
    }

    @SuppressWarnings("unchecked")
    private void doBinding(Pair<Class, Class> binding) {
        Constructor constructor = getConstructorToBindTo(getClassToUse(binding));

        if (constructor != null) {
            bind(binding.getKey()).toConstructor(constructor);
            bindConstructorParameterTypes(constructor);
        } else if (binding.getValue() == null) {
            bind(binding.getKey());
        } else {
            bind(binding.getKey()).to(binding.getValue());
        }
    }

    private Class getClassToUse(Pair<Class, Class> binding) {
        Class classToUse = binding.getValue() == null ? binding.getKey() : binding.getValue();

        if (classToUse.isInterface()) {
            classToUse = getImplementationOfInterface(classToUse);
        }

        return classToUse;
    }

    private Constructor getConstructorToBindTo(Class type) {
        List<Constructor> constructors = Arrays.asList(type.getConstructors());

        if (constructors.size() > 0) {
            Constructor injectAnnotatedConstructor = getInjectAnnotatedConstructor(constructors);
            if (injectAnnotatedConstructor != null) {
                return injectAnnotatedConstructor;
            }

            return constructors.get(0);
        }

        return null;
    }

    private Constructor getInjectAnnotatedConstructor(List<Constructor> constructors) {
        for (Constructor constructor : constructors) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                return constructor;
            }
        }

        return null;
    }

    private Class getImplementationOfInterface(Class interfaceType) {
        Class implementationType;

        implementationType = getClassForName(interfaceType.getName().concat("Impl"));
        if (implementationType != null) {
            return implementationType;
        }

        implementationType = getClassForName(interfaceType.getName().concat("Implementation"));
        if (implementationType != null) {
            return implementationType;
        }

        implementationType = getClassForName(interfaceType.getName().concat("Class"));
        if (implementationType != null) {
            return implementationType;
        }

        implementationType = getClassForName(interfaceType.getName().replace("Interface", "Class"));
        if (implementationType != null) {
            return implementationType;
        }

        return interfaceType;
    }

    private Class getClassForName(String name) {
        Class foundClass;

        try {
            foundClass = Class.forName(name);
        } catch (ClassNotFoundException e) {
            foundClass = null;
        }

        return foundClass;
    }

    private void bindConstructorParameterTypes(Constructor constructor) {
        for (Class type : constructor.getParameterTypes()) {
            if (!isIncludedInBindings(type)) {
                Pair<Class, Class> pair = new Pair<Class, Class>(type, null);
                doBinding(pair);
            }
        }
    }

    private boolean isIncludedInBindings(Class type) {
        for (Pair<Class, Class> binding : _bindings) {
            if (binding.getKey() == type || binding.getValue() == type) {
                return true;
            }
        }

        return false;
    }
}
