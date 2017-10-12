package com.ccfsoft.flume.source;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.Charset;

public class UdpSource extends AbstractSource implements Configurable,
		PollableSource
{
	// private String myProp;

	// 声明UDP相关的变量
	private DatagramSocket ds;
	private DatagramPacket dp;

	@Override
	public void configure(Context context)
	{
		// String myProp = context.getString("myProp", "defaultValue");
		// 定义接收空间大小
		byte data[] = new byte[1024];
		// 实例化套接字,绑定8000端口
		try
		{
			this.ds = new DatagramSocket(8000);
		}
		catch (SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 实例化套接字数据存放空间
		this.dp = new DatagramPacket(data, data.length);

		// Process the myProp value (e.g. validation, convert to another type,
		// ...)

		// Store myProp for later retrieval by process() method
		// this.myProp = myProp;
	}

	@Override
	public void start()
	{
		// Initialize the connection to the external client
	}

	@Override
	public void stop()
	{
		// Disconnect from external client and do any additional cleanup
		// (e.g. releasing resources or nulling-out field values) ..
		ds.close();
	}

	@Override
	public Status process() throws EventDeliveryException
	{
		Status status = null;
		try
		{
			// This try clause includes whatever Channel/Event operations you
			// want to do
			ds.receive(dp);

			// Receive new data
			Event event = EventBuilder.withBody(
					new String(dp.getData()).trim(), Charset.forName("UTF-8"));

			// Store the Event into this Source's associated Channel(s)
			getChannelProcessor().processEvent(event);

			status = Status.READY;
		}
		catch (Throwable t)
		{
			// Log exception, handle individual exceptions as needed

			status = Status.BACKOFF;

			// re-throw all Errors
			if (t instanceof Error)
			{
				throw (Error) t;
			}
		}
		finally
		{

		}
		return status;
	}
}
