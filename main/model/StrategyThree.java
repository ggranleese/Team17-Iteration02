package model;

import java.util.ArrayList;
import java.util.HashSet;

public class StrategyThree implements IStrategy {
	
	public ArrayList<Meld> play(ArrayList<Tile> hand, Table table){
		ArrayList<Meld> plays = new ArrayList<Meld>();
		plays = checkHandPlays(hand);
		
		int counter = 0;
		for(Meld m : plays) {
			counter+= m.getTiles().size();
		}
		
		if(counter - hand.size() == 0) {
			return plays;	
		}
		
		if(threeFewerExists(hand, table) && plays.size() != 0) {
			return plays;
		} 
		
		if(threeFewerExists(hand, table) && plays.size() == 0){
			System.out.println("p3 could play but has not tile to play");
			return plays;
		}
		
		if(!threeFewerExists(hand,table)) {
			System.out.println("p3 would reuse table");
			
		}
		
		if(checkTotalPoints(plays) > 30) {
			return plays;
		}else {
			plays.clear();
			return plays;
		}
		
	}
	
	public ArrayList<Meld> checkHandPlays(ArrayList<Tile> hand){
		ArrayList<Meld> possiblePlays = new ArrayList<Meld>();
		ArrayList<Tile> handHold = new ArrayList<Tile>();
		handHold.addAll(hand);
		
		ArrayList<Run> possibleRuns = checkRun(hand);
		for(Run r : possibleRuns) {
			possiblePlays.add(r);
		}
		for (Run r:possibleRuns ) {
			for(Tile t:r.getTiles()) {
				handHold.remove(t);
			}
		}

		ArrayList<Set> possibleSets = checkSet(handHold);
	
		for(Set r : possibleSets) {
			possiblePlays.add(r);
		}

		return possiblePlays;         
		
	}
	

	
	public int checkTotalPoints(ArrayList<Meld> melds) {
		int counter = 0;
		for(Meld m : melds) {
			for (Tile t : m.getTiles()) {
				counter += t.getValue();
			}
		}
		
		return counter;
	}
	
	public ArrayList<Run> checkRun(ArrayList<Tile> hand){
		
		

		ArrayList<Run> runs = new ArrayList<Run>();
		
		//work on one colour at a time
		for(int i=1; i<=4; i++) {
			//work on colour i
			ArrayList<Tile> colour = new ArrayList<Tile>();
			colour.addAll(colourSplitter(hand, i));
			// if array is empty, return empty
		 
			ArrayList<Integer> allValues = new ArrayList<Integer>();
	
			for (Tile e : colour)
				allValues.add(e.getValue());
	
			for (Tile e : colour) {
				int next = e.getValue() +1;
				int twoNext = e.getValue() +2;
				
				//if its possible for this value to be part of a run (+1 and +2 exist)
				if (allValues.contains(next) && allValues.contains(twoNext)) {
					ArrayList<Tile> hold = new ArrayList<Tile>();
					hold.add(new Tile(i,e.getValue(),false));
					while(allValues.contains(next)) {
						hold.add(new Tile(i, next,false));
						allValues.remove(Integer.valueOf(next));
						next++;
					}
			
					runs.add(new Run(hold));
					
				}
		 
			}
		}
			return runs;
		
		
	}
	
	public ArrayList<Set> checkSet(ArrayList<Tile> hand){
		ArrayList<Set> sets = new ArrayList<Set>();
		HashSet<Integer> values = new HashSet<Integer>();
		ArrayList<ArrayList<Tile>> hold2 = new ArrayList<ArrayList<Tile>>();
		for(Tile t : hand){
			values.add(t.getValue());
		}
		for(Integer i : values) {
			ArrayList<Tile> hold = new ArrayList<Tile>();
			for(Tile t : hand) {
				if(t.getValue() == i) {
					hold.add(t);
				}
			}
			hold2.add(hold);
		}
		for(ArrayList<Tile> o : hold2) {
			ArrayList<Tile> setHold = new ArrayList<Tile>();
			ArrayList<Integer> colours = new ArrayList<Integer>();
			if(o.size() >= 3) {
				for(Tile t: o) {
					if(!colours.contains(t.getColour())){
						colours.add(t.getColour());
						setHold.add(t);
					}
				}
				if(setHold.size() >= 3) {
					sets.add(new Set(setHold));
				}

			}
			
		}

		return sets;
	}
	
	public ArrayList<Tile> colourSplitter(ArrayList<Tile> hand, int i) {
		
		ArrayList<Tile> colour = new ArrayList<Tile>();
		
		for(int t = 0; t<hand.size(); t++) {
			if(hand.get(t).getColour() == i) {
				colour.add(hand.get(t));
			}
		}
		
		return colour;
	}

	public boolean threeFewerExists(ArrayList<Tile> hand,Table table) {
		int size = hand.size();
		for(Observer o : table.getObservers()) {
			if(((Player)o).getHand().size() - size == 3) {
				return true;
			}
		}
		return false;
	}
}
