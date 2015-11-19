package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		if(this.whereTablePredicate.equals(this.child.from)){//asking this table, process
			Tuple tuple = this.child.next();
			while(tuple!=null){
				for(int i=0;i<tuple.getAttributeList().size();i++){
					if(tuple.getAttributeName(i).equals(this.whereAttributePredicate)){
						if(tuple.getAttributeValue(i).equals(this.whereValuePredicate)){
							return tuple;
						}
					}
				}
				tuple = this.child.next();
			}
		}else{//not asking this table, just return
			Tuple tuple = this.child.next();
			return tuple;
		}
		return null;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}