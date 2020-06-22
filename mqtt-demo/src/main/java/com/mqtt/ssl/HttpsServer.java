package com.mqtt.ssl;


import javax.net.ssl.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class HttpsServer {

    private SSLServerSocket serverSocket;
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws IOException {
        HttpsServer server = new HttpsServer();
        server.init();
        server.start();
    }

    public void start(){
        //创建一个serverSocket
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            while (true) {
                System.out.println("server listener");
                Socket socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                bufferedInputStream = new BufferedInputStream(inputStream);
                bufferedOutputStream = new BufferedOutputStream(outputStream);
                byte[] buffer = new byte[64];
                bufferedInputStream.read(buffer);
                System.out.println(new String(buffer));
                bufferedOutputStream.write("server echo:".getBytes());
                bufferedOutputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(bufferedInputStream != null){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedOutputStream != null){
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
            keyStore.load(new FileInputStream("D:\\work\\iot\\redis-test\\mqtt-demo\\src\\main\\resources\\keytools\\server.p12"),serverKeyPassword.toCharArray());
            trustKeyStore.load(new FileInputStream("D:\\work\\iot\\redis-test\\mqtt-demo\\src\\main\\resources\\keytools\\server.p12"),trustKeyPassword.toCharArray());
            keyManagerFactory.init(keyStore, serverKeyPassword.toCharArray());
            trustManagerFactory.init(trustKeyStore);
            sslContext.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(), null);
            serverSocket = (SSLServerSocket) sslContext.getServerSocketFactory().createServerSocket(DEFAULT_PORT);
            serverSocket.setNeedClientAuth(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
