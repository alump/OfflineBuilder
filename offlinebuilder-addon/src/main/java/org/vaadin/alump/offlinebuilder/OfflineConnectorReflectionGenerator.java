/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder;

import com.google.gwt.core.ext.*;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import org.vaadin.alump.offlinebuilder.client.InstanceFromClassName;
import org.vaadin.alump.offlinebuilder.client.conn.OfflineConnector;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Reflection generator
 * TODO: Is this still required?
 */
public class OfflineConnectorReflectionGenerator extends Generator {

    public static final Class<?> REQUIRED_INTERFACE = OfflineConnector.class;

    @Override
    public String generate(TreeLogger treeLogger, GeneratorContext context, String typeName) throws UnableToCompleteException {
        // Filter all classes visible to GWT client side and return only supported ones
        List<JClassType> allInstantiableClasses = getAllInstantiableClassesByThisfactory(context);

        // find package name for the generated implementing class
        // use same package as has the interface
        // example: my.code.client.reflection
        final String genPackageName = InstanceFromClassName.class.getPackage().getName();
        treeLogger.log(TreeLogger.Type.INFO, "genPackageName: " + genPackageName);

        // find class name for the generated implementing class
        // base the name on the interface class name, suffix it
        // example: my.code.client.reflection.ClassFromStringFactoryImpl
        final String genClassName = InstanceFromClassName.class.getSimpleName() + "Impl";
        treeLogger.log(TreeLogger.Type.INFO, "genClassName: " + genClassName);

        // prepare Composer. Composer prepares shell of the Java code, so we need to set package and class name
        ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(genPackageName, genClassName);

        // register (add) required imports to Composer
        // but the generated class uses fully qualified names everywhere, no imports required

        PrintWriter printWriter = context.tryCreate(treeLogger, genPackageName, genClassName);
        if (printWriter != null) {
            // printWriter == null perhaps signalises that class was already generated.
            // It is not an error! and it regularly happens.

            // Add interface to factory class signature
            // Example: ClassFromStringFactoryImpl implements ClassFromStringFactory
            // composer#addImplementedInterface() must be called before composer#createSourceWriter()
            composer.addImplementedInterface(InstanceFromClassName.class.getSimpleName());

            // Get source stream from Composer. It already contains shell of the new class
            // like package, imports and full class signature
            SourceWriter sourceWriter = composer.createSourceWriter(context, printWriter);

            // generate class content, content of the Java file, contructor and factory method
            generateFactoryClass(genClassName, composer, sourceWriter, treeLogger, allInstantiableClasses);

            treeLogger.log(TreeLogger.Type.INFO, "Deferred binding finished generating class: " + composer.getCreatedClassName());
        }
        return composer.getCreatedClassName();
    }

    /**
     * Filters out all classes not implementing the required class
     * @param context
     * @return
     */
    private List<JClassType> getAllInstantiableClassesByThisfactory(GeneratorContext context) {
        TypeOracle oracle = context.getTypeOracle();
        JClassType markerInterface = oracle.findType(REQUIRED_INTERFACE.getName());
        List<JClassType> allInstantiableClasses = new LinkedList<JClassType>();

        for (JClassType classType : oracle.getTypes()) {
            if (!classType.equals(markerInterface) && classType.isAssignableTo(markerInterface)) {
                allInstantiableClasses.add(classType);
            }
        }

        return allInstantiableClasses;
    }

    /**
     * Generates generator class with instantiate method
     * @param genClassName
     * @param composer
     * @param sourceWriter
     * @param logger
     * @param allInstantiableClasses
     */
    private void generateFactoryClass(String genClassName, ClassSourceFileComposerFactory composer, SourceWriter sourceWriter,
            TreeLogger logger, List<JClassType> allInstantiableClasses) {
        // generate factory constructor; simple non-parametric constructor
        sourceWriter.println("public " + genClassName + "( ) {}");
        sourceWriter.println();

        // generate factory method
        generateFactoryMethod(allInstantiableClasses, sourceWriter);

        // flush all writes to stream; mark generating process as finished and ready for compiler
        sourceWriter.commit(logger);
    }

    /**
     * Generated method that returns new instances matching with given class names
     * @param allInstantiableClasses
     * @param sourceWriter
     */
    private void generateFactoryMethod(List<JClassType> allInstantiableClasses, SourceWriter sourceWriter) {
        sourceWriter.println("public Object instantiate(String className) {");
        sourceWriter.indent();
        sourceWriter.println("if (className == null) { return null; }");
        for (JClassType classType : allInstantiableClasses) {
            if (classType.isAbstract()) {
                continue;
            }
            sourceWriter.println("else if (className.equals(\"" + classType.getQualifiedSourceName() + "\")) {");
            sourceWriter.indent();
            sourceWriter.println("return new " + classType.getQualifiedSourceName() + "( );");
            sourceWriter.outdent();
            sourceWriter.println("}");
        }
        sourceWriter.println("return null;");
        sourceWriter.outdent();
        sourceWriter.println("}");
    }


}
