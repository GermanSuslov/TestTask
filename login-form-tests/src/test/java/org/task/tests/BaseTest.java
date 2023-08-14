package org.task.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.task.utils.Browser;
import org.task.utils.Waiter;
import org.testng.annotations.AfterMethod;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @AfterMethod
    public void tearDown() {
        Waiter.quitWaiter();
        Browser.quitDriver();
    }
}
