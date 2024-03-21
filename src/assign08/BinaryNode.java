package assign08;

public class BinaryNode<Type extends Comparable<? super Type>> {
    // Instance variables
    private Type data;
    private BinaryNode<Type> left;
    private BinaryNode<Type> right;

    // Constructor
    public BinaryNode(Type data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Getters and setters
    public Type getData() {
        return data;
    }
    public void setData(Type data){
        this.data = data;
    }
    public BinaryNode<Type> getLeft(){
        return left;
    }

    public BinaryNode<Type> getRight(){
        return right;
    }

    public void setLeft(BinaryNode<Type> left){
        this.left = left;
    }

    public void setRight(BinaryNode<Type> right){
        this.right = right;
    }

    //DOT generate
    //http://www.webgraphviz.com
    public String generateDot() {
        StringBuilder dot = new StringBuilder();
        generateDotRecursive(dot);
        return "digraph BST {\n" + dot.toString() + "}";
    }

    private void generateDotRecursive(StringBuilder dot) {
        dot.append("    \"" + data + "\";\n");
        if (left != null) {
            dot.append("    \"" + data + "\" -> \"" + left.data + "\";\n");
            left.generateDotRecursive(dot);
        }
        if (right != null) {
            dot.append("    \"" + data + "\" -> \"" + right.data + "\";\n");
            right.generateDotRecursive(dot);
        }
    }
}
