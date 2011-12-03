package xtc.oop.helper;
import java.util.ArrayList;

//import helper.Pubble;
//import helper.Bubble;
//import helper.Field;

public class Mubble {
    //possible values for flag
    private final char INHERITED = 'i';
    private final char NEW = 'n';
    private final char OVERLOADED = 'l';
    private final char OVERWRITTEN = 'w';

    boolean constructor;
    boolean main;
    boolean staticMethod;
    char flag;

    Bubble className;
    Pubble packageName;

    String methodName;
    String group;
    String returnType; //if none and not a constructor -->> void
    String visibility;
    String originallyFrom;
    ArrayList<String> paraName;
    ArrayList<String> paraType;
    ArrayList<String> paraMod;

    ArrayList<Field> parameters;

    String code;

    public Mubble(String methodName) { // constructor with a method name
        parameters = new ArrayList<Field>();
        paraType = new ArrayList<String>();
        paraName = new ArrayList<String>();
        paraMod = new ArrayList<String>();
        constructor = false;
        main = false;
        staticMethod = false;
        group = this.methodName = methodName;
        if(methodName.equals("main")){
            main = true;
        }
        code = "";
        flag = '@';
    }

    public void addCode(String code){
        this.code += code;
    }

    public void addParameter(Field parameter){
        parameters.add(parameter);
    }

    public void mangleName(String pre) {
        methodName = pre + methodName;
    }

    public boolean belongToGroup(String g) {
        return group.equals(g);
    }

    public int rank(ArrayList<String> type) {
        if (paraType.size() != type.size()) {
            return -1; // should it return a big number?
        }
        int sum = 0;
        /*
           for (int i = 0; i < paraType.size(); i++) {
           how do i access bubbles???
           }
           */
        return 0;
    }

    public String getCode() {
        return this.code;
    }

    public String findBirthPlace() {
	return originallyFrom;
    }

    public String getCC(){
        if(main)
            return "";
        String ret = ccHeader() + "{\n";
        ret += getCode() + "\n}";
        return ret;
    }

    /* generates header for .cc files */
    public String ccHeader() {
        StringBuilder s = new StringBuilder("");
        if (staticMethod) {
            // working?
            s.append(returnType).append(" _").append(className).
                append("::").append(methodName).append("(");
            for (int i = 0; i < paraType.size(); i++) {
                if (i != 0) {
                    s.append(", ").append(paraType.get(i));
                }
                else {
                    s.append(paraType.get(i));
                }
            }
            s.append(")");
        }
        else {
            if(!this.isConstructor())
                s.append(returnType);
            s.append(" _").append(getClassName()).
            append("::").append(methodName).append("(").
            append(getClassName()).append(" __this");
            for (String para : paraType) {
                s.append(", ").append(para);
            }
            s.append(")");
        }
        return s.toString();
    }

    public String forward() {
        if(main){
            return "";
        }
        if (staticMethod) {
            System.out.println(methodName + " is a static method.");
            return "FIX THIS SHIT. forward() in Mubble";
        }
        StringBuilder s = new StringBuilder();
        s.append(returnType).append(" ").append(methodName).append("(").
            append(getClassName());
        for (String para : paraType) {
            s.append(", ").append(para);
        }
        s.append(");");

        return s.toString();
    }

    public char getFlag() {
        return flag;
    }

    public String getClassName(){
        return className.getName();
    }
    public Bubble getBubble() {
        return className;
    }

    public Pubble getPackage() {
        return packageName;
    }

    public String getPackageName() {
        return packageName.getName();
    }

    public String getName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getVisibility() {
        return visibility;
    }

    public ArrayList<String> getParameterNames() {
        return paraName;
    }

    public ArrayList<String> getParameterModifier() {
        return paraMod;
    }

    public ArrayList<String> getParameterTypes() {
        return paraType;
    }

    public ArrayList<Field> getParameters() {
        return parameters;
    }

    public boolean isConstructor() { // returns true if this is constructor
        return constructor;
    }

    public boolean isMain() { // returns ture if this is main method
        return main;
    }

    public boolean isStatic() { // returns true if this is static method
        return staticMethod;
    }

    public Mubble setBubble(Bubble className) {
        this.className = className;
        return this;
    }

    public Mubble setConstructor(boolean constructor) {
        this.constructor = constructor;
        return this;
    }

    public Mubble setFlag(char flag) {
        this.flag = flag;
        if (flag == NEW) {
            originallyFrom = className.getName();
        }
        return this;
    }

    public Mubble setMain(boolean main) {
        this.main = main;
        return this;
    }

    public Mubble setPackage(Pubble packageName) {
        this.packageName = packageName;
        return this;
    }

    public Mubble setReturnType(String returnType) {
        this.returnType = returnType;
        return this;
    }

    public Mubble setStatic(boolean staticMethod) {
        this.staticMethod = staticMethod;
        return this;
    }

    public Mubble setVisibility(String visibility) {
        this.visibility = visibility;
        return this;
    }

    public Mubble setParameters() {
        for (Field f : parameters) {
            paraName.add(f.name);
            paraType.add(f.type);
            if (f.modifiers.size() == 1) {
                paraMod.add(f.modifiers.get(0));
            }
            else if (f.modifiers.size() == 0) {
                paraMod.add("");
            }
            else {
                System.out.println("Error size cannot be bigger than 2");
            }
        }
        return this;
    }

    public Mubble setParameterNames(ArrayList<String> paraName) {
        this.paraName = paraName;
        return this;
    }

    public Mubble setParameterTypes(ArrayList<String> paraType) {
        this.paraType = paraType;
        return this;
    }

    /* generates entry for vtable1 */
    public String vTable1() {
        if(main){
            return "";
        }
        StringBuilder s = new StringBuilder();
        s.append(returnType).append(" (*");
        s.append(methodName).append(")(");
        if (!isStatic()) {
            s.append(getClassName());
            for (String para : paraType) {
                s.append(", ").append(para);
            }
        }
        else {
            // not sure what to do with static methods
            if (paraType.size() > 0) {
                s.append(paraType.get(0));
            }

            for (int i = 1; i < paraType.size(); i++) {
                s.append(", ").append(paraType.get(i));
            }
        }

        s.append(");");

        return s.toString();
    }

    /* generates entry for vtable.
     */
    public String vTable2() {
        if(main){
            return "";
        }
        StringBuilder type = new StringBuilder();
        /*
           if (from == INHERITED) {
           type.append("(").append(returnType).append("(*)");
           type.append(getClassName());
           for (String para : paraType) {
           type.append(",").append(para);
           }
           type.append(")");
           }
           */

        StringBuilder s = new StringBuilder();
        s.append(methodName).append("(");
        //if (type != null) {
        //    s.append(type.toString()).append(")");
        //}

        if (flag == INHERITED) { // this line is not quite right
            s.append("(").append(returnType).append("(*)(");
            s.append(getClassName());
            for (String para : paraType) {
                s.append(",").append(para);
            }
            /*
            if(className.getName().equals("String") || className.getName().equals("Object") )
                s.append(")").append("&__");
            else
            */

            s.append("))").append("&");
            Bubble ancestor = className.getParentBubble();
	    Bubble anc = null;
            String inheritedfrom = "Object";
            boolean found = true;
            while (ancestor != null && found) {
                ArrayList<Mubble> mubbles = ancestor.getMubbles();
                for (Mubble mub : mubbles) {
                    if (mub.getName().equals(methodName) && mub.getFlag() != INHERITED) {
                        inheritedfrom = ancestor.getName();
			anc = ancestor;
                        found = false;
                    }
                }
                ancestor = ancestor.getParentBubble();
            }
            if (inheritedfrom.equals("Object") || inheritedfrom.equals("String") ||
                    inheritedfrom.equals("Class")) {
                s.append("java::lang::__").append(inheritedfrom);
                    }
            else {
		String pack = anc.getPackageName().trim().replace(" ", "::");
		
                s.append("_").append(inheritedfrom);
            }
        }
        else {
            s.append("&_").append(getClassName());
        }
        s.append("::").append(methodName);
        s.append(")");

        return s.toString();
    }
}

