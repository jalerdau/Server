package com.zzu.doujiale.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerManager {
	public static ArrayList<Socket> socketList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(30000);
		System.out.println("服务器已启动...");

		while (true){
			Socket s = ss.accept();
			socketList.add(s);
			new Thread(new ServerThread(s)).start();
			System.out.println("一个客户端已连接...");
		}
	}
	}