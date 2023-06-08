package domain.model;

import java.io.IOException;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Server {
 
	public Server() {
		try{
			//サーバーのポート番号を指定
			ServerSocket svSock = new ServerSocket(10000);
			
			//アクセスを待ち受け
			Socket sock = svSock.accept();
			
			//受信ストリームの取得(DataInputStreamでラップ)
			DataInputStream in = new DataInputStream(sock.getInputStream());
			
			//int型データを受信
			int intData = in.readInt();
			
			//String型データを受信
			String strData = in.readUTF();
			
			//double型データを受信
			double dblData = in.readDouble();
			
			//受信データの表示
			System.out.println("「"+intData+"」を受信しました。");
			System.out.println("「"+strData+"」を受信しました。");
			System.out.println("「"+dblData+"」を受信しました。");
			
			//受信ストリームの終了
			in.close();
			
			//サーバー終了
			svSock.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}