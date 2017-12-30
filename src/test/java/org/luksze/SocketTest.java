package org.luksze;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void connectionShouldBeRefusedWhenNothingIsListening() throws IOException {

        expectedException.expect(ConnectException.class);
        expectedException.expectMessage(CoreMatchers.startsWith("Connection refused"));

        new Socket(InetAddress.getLoopbackAddress(), 4723);

    }
}
