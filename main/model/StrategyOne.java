package model;

import java.util.ArrayList;
import java.util.HashSet;

public class StrategyOne implements IStrategy {

	public ArrayList<Meld> play(ArrayList<Tile> hand, RummikubModel table, boolean playWithBoard) {
		ArrayList<Meld> plays = new ArrayList<Meld>();
		
		//checks all possible plays and returns all possible plays
		plays = checkHandPlays(hand);
		return plays;
		
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
					hold.add(new Tile(i,e.getValue(),false)); // THIS IS NOT RIGHT - BECAUSE JOKERS
					while(allValues.contains(next)) {
						hold.add(new Tile(i, next,false)); //THIS IS NOT RIGHT - IM BATMAN
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
}