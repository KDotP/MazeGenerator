public class Node {
    // 'e' means edge, this should never be changed or travelled across
    // 'c' means closed, can be opened, but isn't yep
    // 'o' means open, can be travelled across
    private String up = "e";
    private String right = "e";
    private String down = "e";
    private String left = "e";
    
    // If nothing is passed, create a node that is all edges
    public Node() { }

    public Node(String up, String right, String down, String left)
    {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
    }

    // Unused, but useful while debugging
    public void PrintNode() {
        System.out.print(up + right + down + left);
    }

    // Unused, we only use Right and Down for... reasons
    public boolean GetUp() {
        if (up == "o") return true;
        else return false;
    }

    public boolean GetRight() {
        if (right == "o") return true;
        else return false;
    }

    public boolean GetDown() {
        if (down == "o") return true;
        else return false;
    }

    // Unused
    public boolean GetLeft() {
        if (left == "o") return true;
        else return false;
    }

    /*
     * While it wasn't used in the final product, returning a boolean when there are set conditions can be useful
     * The Maze object doesn't use or store the result, so it can be safely ignored
     * But, if we wanted to, we could attempt to set the down position and act on whether or not we were successful
     */
    public boolean SetUp(String newDir) {
        if (up == "e") return false;
        up = newDir;
        return true;
    }

    public boolean SetRight(String newDir) {
        if (right == "e") return false;
        right = newDir;
        return true;
    }

    public boolean SetDown(String newDir) {
        if (down == "e") return false;
        down = newDir;
        return true;
    }

    public boolean SetLeft(String newDir) {
        if (left == "e") return false;
        left = newDir;
        return true;
    }
}
