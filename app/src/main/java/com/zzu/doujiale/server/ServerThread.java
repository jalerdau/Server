package com.zzu.doujiale.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket s;

    public ServerThread(Socket s){
        this.s = s;
    }

    @Override
    public void run() {
        String port = s.getRemoteSocketAddress().toString();
        System.out.println("连接成功！端口号为："+port);

        try {
            InputStream inputStream = s.getInputStream();
            byte[] msg = new byte[1024];
            int len;
            while((len=inputStream.read(msg))!=-1){
                String text = new String(msg,0,len);
                System.out.println("收到的消息为："+text);
                sendAll(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendAll(String msg) throws IOException {
        for(Socket s : ServerManager.socketList){
            OutputStream outputStream = s.getOutputStream();
            outputStream.write(msg.getBytes("utf-8"));
        }
        return false;
    }
}