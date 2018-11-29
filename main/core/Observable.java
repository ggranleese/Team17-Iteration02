package core;

import java.util.ArrayList;

public interface Observable {
	public void registerObserver(Observer observer);
	public void notifyObservers();
	public void removeObserver(Observer observer);
	public void updateTable(ArrayList<Meld> updatedMelds, boolean gameOver, boolean status, Pile updatedPile);
}
