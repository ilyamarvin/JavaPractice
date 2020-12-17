package ru.mirea.task27_28;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        Worker worker = new Worker();
        Class<? extends Worker> aClass = worker.getClass();

        List<Method> methodList = Arrays.stream(aClass.getDeclaredMethods())
                .filter(a -> Arrays.stream(a.getAnnotations())
                        .anyMatch(b -> b instanceof OperationType)).collect(Collectors.toList());

        List<ReflectionTask> tasks = worker.getTasks();
        OperationType operationType;

        for (ReflectionTask task : tasks) {
            for (Method method : methodList) {

                operationType = method.getAnnotation(OperationType.class);
                if (task.getType().equals(operationType.name())) {
                    try {
                        method.invoke(worker, task.getData());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
