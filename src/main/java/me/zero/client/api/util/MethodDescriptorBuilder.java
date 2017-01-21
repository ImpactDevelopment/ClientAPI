package me.zero.client.api.util;

import me.zero.client.api.transformer.reference.ClassReference;

import java.util.Arrays;

/**
 * Used to contstruct bytecode method descriptors.
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
public class MethodDescriptorBuilder {

    private final ClassReference returnValue;
    private final ClassReference[] parameters;

    public MethodDescriptorBuilder(ClassReference returnValue, ClassReference... parameters) {
        this.returnValue = returnValue;
        this.parameters = parameters;
    }

    public String build() {
        StringBuilder paramDesc = new StringBuilder();
        Arrays.asList(this.parameters).forEach(param -> paramDesc.append(param.getDescriptor()));
        return "(" + paramDesc + ")" + returnValue.getDescriptor();
    }
}
