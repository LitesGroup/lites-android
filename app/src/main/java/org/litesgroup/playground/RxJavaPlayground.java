package org.litesgroup.playground;

import org.litesgroup.playground.rxutils.Logger;

public class RxJavaPlayground {
    private final Logger logger;

    public RxJavaPlayground(Logger logger) {
        this.logger = logger;
    }

    public void run() {
        logger.log("Hello world");
    }
}
