package com.debug.core;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Lyb
 * @since 2019-08-21
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
