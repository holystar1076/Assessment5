package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tempStorage;
	ArrayList<Tuple> tuplesResult;
	private boolean sorted = false;
	
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		if(!sorted){
			tempStorage = new ArrayList<Tuple>();
			for(Tuple tuple = this.child.next();tuple!=null;tuple = this.child.next()){
				tempStorage.add(tuple);
			}
			int i=0;
			for(; i<tempStorage.get(0).getAttributeList().size(); i++){
				if(tempStorage.get(0).getAttributeName(i).equals(this.orderPredicate)){
					break;
				}
			}
			while(tempStorage.size()>0){
				int min=0;
				for(int k=1; k<tempStorage.size(); k++){
					if(tempStorage.get(k).getAttributeValue(i).toString().compareTo(tempStorage.get(min).getAttributeValue(i).toString())<0){
						min=k;
					}
				}
				tuplesResult.add(tempStorage.get(min));
				tempStorage.remove(min);
			}
			sorted=true;
		}
		if(tuplesResult.isEmpty()){
			return null;
		}else{
			Tuple temp = tuplesResult.get(0);
			tuplesResult.remove(0);
			return temp;
		}
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}