// In most programming languages, unchanging values are CAPITALIZED
/*
 * An enum is a data type that holds values
 * Think of a enum as you would with letters and numbers
 * You can't compare letters to numbers because they're entirely different
 * Similarly, SideType.EDGE is not equal to anything but SideType.EDGE
 * The name of state doesn't mean anything, it's just like how the alphabet starts with a b c.
 * Using an enum is efficent and readable, in part because our compiler will automatically pick up if something is misspelled
 * Enums can be initialized above classes to make them available to all classes
 */
enum SideType {
    EDGE,
    CLOSED,
    OPEN
}

public class Node {
    private SideType up = SideType.EDGE;
    private SideType right = SideType.EDGE;
    private SideType down = SideType.EDGE;
    private SideType left = SideType.EDGE;
    
    // If nothing is passed, create a node that is all edges
    public Node() { }

    public Node(SideType up, SideType right, SideType down, SideType left)
    {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
    }

    // Unused, but useful while debugging
    public void PrintNode() {
        System.out.print("" + up + right + down + left);
    }

    // Unused, we only use Right and Down for... reasons
    public boolean GetUp() {
        if (up == SideType.OPEN) return true;
        else return false;
    }

    public boolean GetRight() {
        if (right == SideType.OPEN) return true;
        else return false;
    }

    public boolean GetDown() {
        if (down == SideType.OPEN) return true;
        else return false;
    }

    // Unused
    public boolean GetLeft() {
        if (left == SideType.OPEN) return true;
        else return false;
    }

    /*
     * While it wasn't used in the final product, returning a boolean when there are set conditions can be useful
     * The Maze object doesn't use or store the result, so it can be safely ignored
     * But, if we wanted to, we could attempt to set the down position and act on whether or not we were successful
     */
    public boolean SetUp(SideType newDir) {
        if (up == SideType.EDGE) return false;
        up = newDir;
        return true;
    }

    public boolean SetRight(SideType newDir) {
        if (right == SideType.EDGE) return false;
        right = newDir;
        return true;
    }

    public boolean SetDown(SideType newDir) {
        if (down == SideType.EDGE) return false;
        down = newDir;
        return true;
    }

    public boolean SetLeft(SideType newDir) {
        if (left == SideType.EDGE) return false;
        left = newDir;
        return true;
    }
}
