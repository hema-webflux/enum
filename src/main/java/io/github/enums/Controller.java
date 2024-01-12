package io.github.enums;

import io.github.enums.exception.EnumerationException;
import io.github.enums.exception.ImplementationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
public class Controller {

    @GetMapping("/")
    public void index() {

    }

    public static void main(String[] args) throws NoSuchFieldException, EnumerationException, ImplementationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        System.out.println(Color.Red.toMap());

    }
}
