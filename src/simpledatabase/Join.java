package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;
	private boolean getLeft = false;
	private int leftIndex = -1;
	private int rightIndex = -1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}
	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		newAttributeList = new ArrayList<Attribute>();
		Tuple temp = this.rightChild.next();;
		if (temp!=null){
			if(!getLeft){
				Tuple leftTemp = this.leftChild.next();
				while (leftTemp!=null){
					tuples1.add(leftTemp);
					leftTemp = this.leftChild.next();
				}
				getLeft = true;
				for(int i=0; i<temp.getAttributeList().size(); i++){
					for(int j=0; j<tuples1.get(0).getAttributeList().size(); j++){
						if(temp.getAttributeName(i).equals(tuples1.get(0).getAttributeName(j))){
							rightIndex = i;
							leftIndex = j;
						}
					}
				}
			}
			if(leftIndex!=-1){
				for(int i=0; i<tuples1.size(); i++){
					if(temp.getAttributeValue(rightIndex).equals(tuples1.get(i).getAttributeValue(leftIndex))){
						for(int j=0; j<tuples1.get(i).getAttributeList().size(); j++){
							this.newAttributeList.add(tuples1.get(i).getAttributeList().get(j));
						}
						for(int j=0; j<temp.getAttributeList().size(); j++){
							this.newAttributeList.add(temp.getAttributeList().get(j));
						}
						return new Tuple(this.newAttributeList);
					}
				}
			}
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}