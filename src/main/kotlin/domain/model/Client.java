package domain.model;

import java.io.IOException;
import java.io.DataOutputStream;
import java.net.Socket;
 
public class Client {
 
	public Client() {
		try{
			//送信先のIPアドレス(ドメインなどの名前)とポートを指定
			Socket sock = new Socket("localhost",10000);
			
			//送信ストリームの取得(DataOutputStreamでラップ)
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			
			//送信データ
			int intData = 100;
			String strData = "消炭にしてやるんだから！";
			double dblData = 1.983;
			
			//int型送信
			out.writeInt(intData);
			
			//String型送信
			out.writeUTF(strData);
			
			//double型送信
			out.writeDouble(dblData);
			out.writeInt(1111101000);
			
			System.out.println("基本型を送信しました。");
			
			//送信ストリームを表示
			out.close();
			
			//終了
			sock.close();
		
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}