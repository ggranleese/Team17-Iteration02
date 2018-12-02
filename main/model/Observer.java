package model;

public interface Observer {
	public void update(Table table);
	public void pushToTable(Table table);
}
