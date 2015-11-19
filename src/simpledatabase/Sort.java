package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
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
		//Delete the lines below and add your code here
		if(this.tuplesResult.isEmpty() == true)	{
		ArrayList<Tuple> list = new ArrayList<Tuple>();
		Tuple tuple = child.next();
		int index = 0;
		if(tuple ==  null) {
			return null;
		}
		else{
			while (tuple!=null) {
				list.add(tuple);
				tuple = child.next();
			}
			for(int i=0; i<list.size(); i++){
				tuple = list.get(0);
				for (int j=0; j<tuple.getAttributeList().size(); j++){
					if (tuple.getAttributeList().get(j).getAttributeName().equals(orderPredicate)) {
						index = j;

					}
				}
			}
		
			while(list.isEmpty()==false){
				int minIndex = 0;
				Tuple min = list.get(0);
				for(int i=0; i<list.size(); i++) {
					if(min.getAttributeValue(index).toString().compareTo(list.get(i).getAttributeValue(index).toString())>0){
						minIndex = i;
						min = list.get(i);
					}
				}
				list.remove(minIndex);
				this.tuplesResult.add(min);
				}
			}
		}
		
		Tuple result = this.tuplesResult.get(0);
		this.tuplesResult.remove(0);
		return result;

	}
		
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}
	


}