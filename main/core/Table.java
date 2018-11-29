package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table implements Observable{
	//MEMBERS
private	ArrayList<Meld> Melds;
private List<Observer> observers;
private Pile pile;
private boolean gameOver = false;
public boolean status;

	public Table(){
		this.setObservers(new ArrayList<Observer>());
		this.Melds = new ArrayList<Meld>();
		this.pile = new Pile();
		this.status = false;
	}
	//METHODS
	//--don't really need this, I think it's just for testing.
	public void add(Meld meld) {
		this.Melds.add(meld);
	}
	//OBSERVER
	public void registerObserver(Observer observer) {
		if(observer!=null) {
			this.getObservers().add(observer);
		}
	}
	public void notifyObservers() {
		Iterator<Observer> i = getObservers().iterator();
		while(i.hasNext()) {
			Observer observer = i.next();
			observer.update(this);
		}
		
	}
	public void removeObserver(Observer observer) {
		if(observer!=null) {
			this.getObservers().remove(observer);
		}
		
	}
	public void updateTable(ArrayList<Meld> updatedMelds, boolean gameOver, boolean status, Pile updatedPile) {
		this.gameOver = gameOver;
		this.Melds = updatedMelds;
		this.pile = updatedPile;

		if(status == true) {
			this.status = true;
		}
		notifyObservers();
	}
	//GETTERS
	public ArrayList<Meld> getMelds() {
		return this.Melds;
	}
	public Pile getPile() {
		return this.pile;
	}
	public boolean getGameOver() {
		return this.gameOver;
	}
	//SETTERS
	public void setMelds(ArrayList<Meld> melds) {
		this.Melds = melds;
	}
	public void setPile(Pile pile) {
		this.pile = pile;
	}
	public List<Observer> getObservers() {
		return observers;
	}
	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
}
