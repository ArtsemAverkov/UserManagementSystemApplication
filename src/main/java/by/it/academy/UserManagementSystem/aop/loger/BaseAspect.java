package by.it.academy.UserManagementSystem.aop.loger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * BaseAspect is a component class responsible for logging aspects in the User Management System application.
 * It contains common functionality to log method arguments, results, and other important information for
 * different components and methods in the application.
 *
 * <p>The class uses SLF4J (Simple Logging Facade for Java) for logging purposes.
 * It provides the following logging patterns:
 * - BEFORE_SERVICE_PATTERN: The pattern for logging service method arguments before their execution.
 * - AFTER_SERVICE_PATTERN: The pattern for logging service method results and arguments after their execution.
 * - BEFORE_PATTERN_CONTROLLER: The pattern for logging controller method arguments before their execution.
 * - AFTER_PATTERN_CONTROLLER: The pattern for logging controller method results and arguments after their execution.
 *
 * <p>The class also provides utility methods for formatting arguments and results in a readable manner.
 *
 * <p>NOTE: This class is designed to be extended by specific aspect classes for different components of the
 * User Management System application, such as controllers and services.
 */

@Slf4j
@Component
public class BaseAspect {
    public static final String ARGS = "ARGUMENT is";
    public static final String RESULT = "RESULT is";
    public static final String PREFIX = "#";
    public static final String BEFORE_SERVICE_PATTERN =PREFIX + "{}:" + ARGS + "{}";
    public static final String AFTER_SERVICE_PATTERN =PREFIX + "{}:" + RESULT + "{{}}" + ARGS +"{}";

    /**
     * Formats method arguments with parameter names into a readable string representation.
     *
     * @param point The JoinPoint containing method and argument information.
     * @return A formatted string representing method arguments with their parameter names.
     */

    protected String getArgsWhitName(JoinPoint point){
        String[] parameterName = ((CodeSignature)point.getSignature()).getParameterNames();
        Object[] args = point.getArgs();
        StringBuilder stringBuilder = new StringBuilder("(");
        for (int i = 0; i < args.length; i++) {
            stringBuilder
                    .append(parameterName[i])
                    .append("=")
                    .append(getStringInstanceOf(Optional.ofNullable(args[i]).orElse("not defined")));
            if (i !=args.length -1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    /**
     * Formats the result object or other objects into a readable string representation.
     * If the object is an array or a HashMap, it will be formatted accordingly.
     *
     * @param result The object to be formatted.
     * @return A formatted string representing the object.
     */

    protected String getStringInstanceOf(Object result){
        if (result instanceof Object[]){
            return Arrays.toString((Object[]) result);
        }
        if (result instanceof HashMap){
            Map<Object, Object> resultMap = (Map) result;
            return resultMap.entrySet()
                    .stream()
                    .map(entry -> String.join("#", entry.getKey().toString(), entry.getValue().toString()))
                    .collect(Collectors.joining(",","{", "}"));
        }
        return Optional.ofNullable(result).orElse("not definet").toString();
    }
}
