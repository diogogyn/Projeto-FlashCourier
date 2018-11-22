package org.dos.common.utils;

import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by stephan on 04.07.17.
 */
public class TimeProviderTest {
    @Test
    public void now() throws Exception {
        assertThat(new TimeProvider().now()).isCloseTo(new Date(), 1000);
    }

}