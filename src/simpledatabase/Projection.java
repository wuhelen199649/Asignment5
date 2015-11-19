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
	public Tuple next() {
		// Delete the lines below and add your code here
		Tuple tuple = child.next();
		if (tuple==null) {
			return null;
		}
		else{
			int i=0;
			ArrayList<Attribute> list = new ArrayList<Attribute>();
			while(i<tuple.getAttributeList().size()) {
				if(tuple.getAttributeList().get(i).getAttributeName().equals(attributePredicate)) {
					list.add(tuple.getAttributeList().get(i));
					}
				i++;
				}
			Tuple result = new Tuple(list);
			
			return result;
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