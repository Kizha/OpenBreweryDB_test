package com.parimatch.tech;

import com.parimatch.tech.tests.BaseTest;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.reflections.Reflections;

import java.util.Set;


public class TestRunner {

    public static void main(String[] args) {
        Reflections reflections = new Reflections("com/parimatch/tech/tests");
        Set<Class<? extends BaseTest>> subTypes = reflections.getSubTypesOf(BaseTest.class);
        JUnitCore jUnitCore = new JUnitCore();
        jUnitCore.run(
            new ParallelComputer(true, false),
            subTypes.toArray(new Class<?>[subTypes.size()])
        );

        System.exit(0);
    }
}
