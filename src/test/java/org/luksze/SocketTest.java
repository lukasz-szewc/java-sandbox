package org.luksze;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SocketTest {

    public static final String PING = "Ping";

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void connectionShouldBeRefusedWhenNothingIsListening() throws IOException {

        expectedException.expect(ConnectException.class);
        expectedException.expectMessage(CoreMatchers.startsWith("Connection refused"));

        new Socket(InetAddress.getLoopbackAddress(), 4729);

    }

    @Test
    public void clientServerConversation() throws IOException, InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ServerSocket serverSocket = new ServerSocket(4723, 1, InetAddress.getLoopbackAddress());
        serverSocket.setSoTimeout(5000);

        Callable<String> serverComputable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                PrintWriter printWriter = new PrintWriter(serverSocket.accept().getOutputStream());
                printWriter.write(PING);
                printWriter.flush();
                printWriter.close();
                return PING;
            }
        };

        Callable<String> clientComputation = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Socket socket = new Socket(InetAddress.getLoopbackAddress(), 4723);
                InputStream inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                return bufferedReader.readLine();
            }
        };

        Future<String> server = executorService.submit(serverComputable);
        Future<String> client = executorService.submit(clientComputation);


        Assert.assertEquals(PING, server.get());
        Assert.assertEquals(PING, client.get());

        executorService.shutdown();
    }
}
