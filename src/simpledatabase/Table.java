package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	private String col, col1;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		if(this.getAttribute){
			try{
				String col2 = br.readLine();
				tuple = new Tuple(this.col,this.col1,col2);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				return tuple;
			}catch(Exception e){
				return null;
			}
		}else{
			try{
				this.col = br.readLine();
				this.col1 = br.readLine();
				String col2 = br.readLine();
				tuple = new Tuple(this.col,this.col1,col2);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				this.getAttribute = true;
				return tuple;
			}catch(Exception e){
				return null;
			}
		}
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}