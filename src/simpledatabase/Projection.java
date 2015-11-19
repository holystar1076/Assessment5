package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple tuple = this.child.next();
		if (tuple!=null){
			int i=0;
			while(i<tuple.getAttributeList().size()){
				if(tuple.getAttributeList().get(i).getAttributeName().equals(attributePredicate)){
					i++;
				}else{
					tuple.getAttributeList().remove(i);
				}
			}
		}
		return tuple;
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}