package xtc.oop.helper;
import java.util.ArrayList;

public class Bubble{
    String name;
    String[] methods;
    String[] dataFields;
    Bubble parent;
    String[] children;
    Bubble[] bChildren;
    ArrayList<String> vtable;

    public Bubble(String name, String[] methods,
		  String[] dataFields, Bubble parent, String[] children){

        this.name = name;
        this.methods = methods;
        this.dataFields = dataFields;
        this.parent = parent;
        this.children = children;
        this.vtable = new ArrayList<String>();
    }

    public Bubble(String name, String child) {
        this.name = name;
        if (child != null) {
            String temp[] = { child };
            this.children = temp;
        }
        this.vtable = new ArrayList<String>();
        this.methods = null;
        this.parent = null;
    }

    public void setMethods(String[] methods) {
	if (methods == null) {
	    return;
	}
	this.methods = methods;
    }
    
    public String[] getMethods(){
        return this.methods;
    }

    //changed to make it arraylist
    public void setVtable(ArrayList<String> vtable) {
	if (vtable == null) {
	    return;
	}
	this.vtable = vtable;
    }
    
    public void add2Vtable(String add){
        this.vtable.add(add);
    }
    
    public ArrayList<String> getVtable(){
        return this.vtable;
    }
    
    public void printVtable(){
        System.out.println("================================");
        System.out.println(this.name + "'s vtable:"); 
        for(String s : this.vtable)
            System.out.println(s);
            
        System.out.println("================================");
    }
    
    //sets the vtable at index i to string s
    public void setVtableIndex(int i, String s)
    {
        this.vtable.set(i, s);
    }

    public void setDataFields(String[] dataFields) {
	if (dataFields == null) {
	    return;
	}
	this.dataFields = dataFields;
    }

    public String getName() {
	if (name == null) {
	    return "No Name";
	}
	return name;
    }

    public Bubble getParent() {
	return parent;
    }
    
    public void setParent(Bubble parent) {
	if (parent == null) {
	    return;
	}
	this.parent = parent;
    }

    public void setChildren(String[] children) {
	if (children == null) {
	    return;
	}
	this.children = children;
    }
    
    public String[] getChildren()
    {
        return this.children;
    }

    public void addChild(String child) {
	if (child == null) {
	    return;
	}
        int len = children == null ? 1 : children.length + 1;
        String[] temp = new String[len];
        if (children == null) {
            temp[0] = child;
            children = temp;
        }
        else {
            for (int i = 0; i < children.length; i++) {
                temp[i] = children[i];
            }

            temp[len - 1] = child;
            children = temp;
        }
    }

    public String childrenToString() {
	if (children == null) {
	    return "No Children";
	}
	else { 
	    StringBuilder s = new StringBuilder("[");
	    for (int i = 0; i < children.length; i++) {
		s.append(children[i]);
		if (i != children.length - 1)
		    s.append(", ");
	    }
	    return s.append("]").toString();
	}	
    }

    public String parentToString(){
        if(this.parent != null) {
            return this.parent.getName();
	}
        else {
            return "No Parent";
	}
    }

    public String toString() {
	StringBuilder s = new StringBuilder("Name: " + getName() + "\n");
	s.append("Children: " + childrenToString() + "\n");
	s.append("Parent: " + parentToString());
	return s.toString();
    }
}
