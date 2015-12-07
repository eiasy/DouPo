package mmo.tools.filter;

/**
 * 数据包过滤器
 * 
 * @author 李天喜
 * 
 */
public interface IFilter {
	public byte[] onReceiveData(byte[] data);

	public byte[] onSendData(byte[] data);

	public byte[] onReceiveData(byte[] data, byte[] key, int count);

	public byte[] onSendData(byte[] data, byte[] key, int count);

	public int filter(int value, byte[] key, int count);
	
	public boolean isFilter();

	public void setFilter(boolean isFilter);
}
