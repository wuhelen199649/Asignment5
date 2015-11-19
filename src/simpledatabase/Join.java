package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
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
		//Delete the lines below and add your code here
		Tuple left = leftChild.next();
		Tuple right = rightChild.next();
		Tuple joined;
		
			while(left!=null) {
			this.tuples1.add(left);
			left = leftChild.next();
			}
		
			while(right!=null) {
			for(int z = 0; z<this.tuples1.size(); z++){
				for(int x=0; x<this.tuples1.get(z).getAttributeList().size();x++){
					for(int y=0; y<right.getAttributeList().size(); y++) { 
						if(right.getAttributeList().get(y).getAttributeName().equals(this.tuples1.get(z).getAttributeList().get(x).getAttributeName()) && right.getAttributeList().get(y).getAttributeValue().equals(this.tuples1.get(z).getAttributeList().get(x).getAttributeValue())) {
								ArrayList<Attribute> list = new ArrayList<Attribute>();
								
								for(int a=0; a< right.getAttributeList().size();a++){
									
									list.add(right.getAttributeList().get(a));
									if(this.tuples1.get(z).getAttributeList().get(a).equals(this.tuples1.get(z).getAttributeList().get(x))==false){
										list.add(this.tuples1.get(z).getAttributeList().get(a));
										}
									}
								joined = new Tuple(list);
								return joined;
						}
						
					}
				}
			}
			right = rightChild.next();
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