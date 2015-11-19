package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	String attributeType;
	String attributeName;
	String attributeValue;

	
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
		//Delete the lines below and add your code here
		
		if (getAttribute == false) {
			try{
				attributeName = br.readLine();
				attributeType = br.readLine();
				attributeValue = br.readLine();
				tuple = new Tuple(attributeName, attributeType, attributeValue);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				getAttribute = true;
				return tuple;
			}
			catch (IOException e) {
				e.printStackTrace();
			} 
		}
		else if (getAttribute == true) {
			try{
				attributeValue = br.readLine();
				if (attributeValue != null) {
					Tuple tuple2 = new Tuple(attributeName, attributeType, attributeValue);
					tuple2.setAttributeName();
					tuple2.setAttributeType();
					tuple2.setAttributeValue();
					return tuple2;
				}
				else {
					return null;
				}
				
			}
			catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return null;
		
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
	
}