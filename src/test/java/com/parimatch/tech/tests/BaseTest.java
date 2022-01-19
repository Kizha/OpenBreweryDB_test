package com.parimatch.tech.tests;

import com.parimatch.tech.config.SpringConfig;
import junitparams.JUnitParamsRunner;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public abstract class BaseTest {

    final JUnitSoftAssertions softAssertions = new JUnitSoftAssertions();

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @After
    public void assertions(){
        softAssertions.assertAll();
    }
}