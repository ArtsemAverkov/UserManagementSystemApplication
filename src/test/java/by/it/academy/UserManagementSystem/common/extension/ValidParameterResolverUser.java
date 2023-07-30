package by.it.academy.UserManagementSystem.common.extension;

import by.it.academy.UserManagementSystem.dto.UserDto;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ValidParameterResolverUser implements ParameterResolver {
    private final List<UserDto> userDtoList = Arrays.asList(
         new UserDto(
                 "admin",
                 "AdminSurname",
                 "admin@mail.com",
                 "ADMINISTRATOR"
         )
            );

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == UserDto.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return userDtoList.get(new Random().nextInt(userDtoList.size()));
    }
}
