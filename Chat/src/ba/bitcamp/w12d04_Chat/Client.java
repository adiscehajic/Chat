package ba.bitcamp.w12d04_Chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {

	private Socket client;
	private String id;
	private BufferedReader reader;
	private BufferedWriter writer;
	private LinkedBlockingQueue<Message> messages;

	public Client(Socket client) {
		this.client = client;
		id = client.getInetAddress().getHostAddress();
		try {
			reader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
			messages = new LinkedBlockingQueue<Message>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

	public void sendMessages() {
		Iterator<Message> it = messages.iterator();
		try {
			while (it.hasNext()) {
				Message m = it.next();
				messages.remove(m);
				writer.write(m.getMessage());
			}
			writer.flush();
		} catch (IOException e) {
			messages.clear();
			close();
			Server.clients.remove(this);
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

}
