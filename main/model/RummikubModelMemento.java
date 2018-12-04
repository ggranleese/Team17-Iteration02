package model;

public class RummikubModelMemento {
	 private RummikubModel state;

	   public RummikubModelMemento(RummikubModel model){
	      this.state = new RummikubModel(model);
	   }
 
	   public RummikubModel getState(){
	      return state;
	   }	
	}
