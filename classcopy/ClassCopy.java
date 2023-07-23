package classcopy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ClassCopy {
    public static <T> T copyInstance(Object obj, Class<T> copyClass) throws Exception {
        // Create copy instance
        Constructor<T> c = copyClass.getDeclaredConstructor();
        T copyObj = c.newInstance();   

        // Get the source class, and map all the fields.
        Class sourceClass = obj.getClass();
        Map<String, Field> sourceFieldMap = new HashMap<>();
        Field[] sourceFields = sourceClass.getDeclaredFields();
        Stream.of(sourceFields).forEach((field) -> {
            field.setAccessible(true);
            if (field.isAnnotationPresent(FieldCopyName.class)) {
                FieldCopyName annotation = field.getAnnotation(FieldCopyName.class);
                sourceFieldMap.put(annotation.name(), field);
            }
            else {
                sourceFieldMap.put(field.getName(), field);
            }
        });

        // Iterate through all copy fields, and if there is a match then set the field.
        Field[] copyFields = copyClass.getDeclaredFields();
        Stream.of(copyFields).forEach(copyField -> {
            copyField.setAccessible(true);
            Field sourceField = findMatch(sourceFieldMap, copyField);
            if (sourceField != null && copyField.getType().equals(sourceField.getType())) {
                try {
                    copyField.set(copyObj, sourceField.get(obj));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        return copyObj;
    }

    private static Field findMatch(Map<String, Field> sourceFieldMap, Field copyField) {
        if (copyField.isAnnotationPresent(FieldCopyName.class)) {
            FieldCopyName annotation = copyField.getAnnotation(FieldCopyName.class);
            return sourceFieldMap.get(annotation.name());
        }
        return sourceFieldMap.get(copyField.getName());
    }

    public static void main(String[] args) throws Exception {
        Cat cat = new Cat();
        cat.setName("Tontik");
        Class classCat = cat.getClass();

        Dog d = copyInstance(cat, Dog.class);
        System.out.println(d.getDogName()); // Tontik
    }
}
