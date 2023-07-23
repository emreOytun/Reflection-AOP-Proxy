package spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// RetentionPolicy.SOURCE : The annotation is only in the source code. Once you compile the code, there is no such annotation and so it is not seen using reflections. Compiler uses those annotations to make some checks.
// Exp: @Override, @SuppressWarnings -> Compiler uses these annotations
// Exp: Lombok annotations -> They are used to create getters/setters etc. in the compile-time. After compilation, these annotations are gone but instead getters/setters exist.

// RetentionPolicy.RUNTIME : It is in the bytecode after the source code is compiled. Reflections use those annotations. We can except this, where we think reflections are used.
// Exp: @Component, @Service, @Bean, @Transactional 

// ElementType.TYPE : Only at the top of classes
// ElementType.FIELD : ElementType.TYPE : Only at the top of fields
// ElementType.CONSTRUCTOR : Only at the top of constructorss
// ElementType.METHOD : Only at the top of methods
// ElementType.PARAMETER : Only at the top of parameters

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
    
}
