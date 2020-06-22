package com.mqtt.ssl;

import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.security.KeyStore;

public class HttpsClient {

    private SSLSocket sslSocket;
    private static final int DEFAULT_PORT = 8080;
    private static final String DEFAULT_HOST = "127.0.0.1";
    public static void main(String[] args) throws IOException {
        HttpsClient client = new HttpsClient();
        client.init();
        client.process();
    }

    public void process() {
        if (sslSocket == null) {
            System.out.println("ERROR");
            return;
        }
        try {
            InputStream input = sslSocket.getInputStream();
            OutputStream output = sslSocket.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(input);
            BufferedOutputStream bos = new BufferedOutputStream(output);
            bos.write("Client Message".getBytes());
            bos.flush();
            byte[] buffer = new byte[64];
            bis.read(buffer);
            System.out.println(new String(buffer));
            sslSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public void init(){
        try {

            SSLContext sslContext = SSLContext.getInstance("SSL");
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            KeyStore keyStore = KeyStore.getInstance("jks");
            String serverKeyPassword = "123456";
            KeyStore trustKeyStore = KeyStore.getInstance("jks");
            String trustKeyPassword = "123456";
            keyStore.load(new FileInputStream("D:\\work\\iot\\redis-test\\mqtt-demo\\src\\main\\resources\\keytools\\client2.keystore"),serverKeyPassword.toCharArray());
            //trustKeyStore.load(new FileInputStream("D:\\work\\iot\\redis-test\\mqtt-demo\\src\\main\\resources\\keytools\\client2.p12"),trustKeyPassword.toCharArray());
            keyManagerFactory.init(keyStore, serverKeyPassword.toCharArray());
            //trustManagerFactory.init(trustKeyStore);
            //trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore)null);
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            sslSocket = (SSLSocket) sslContext.getSocketFactory().createSocket(DEFAULT_HOST, DEFAULT_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
