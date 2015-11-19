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
		//Delete the lines below and add your code here
		Tuple tuple = child.next();
		if (child.from.equals(whereTablePredicate)==false) {
			return tuple;
		}
		else {
			while (tuple!=null){
				int i=0;
				while(i<tuple.getAttributeList().size()) {
					if (tuple.getAttributeList().get(i).getAttributeName().equals(whereAttributePredicate) && tuple.getAttributeList().get(i).getAttributeValue().toString().equals(whereValuePredicate)) {
						return tuple;
					}
					i++;
				}
				tuple = child.next();
		}
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